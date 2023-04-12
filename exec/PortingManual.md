## Port Number

| Port | 이름 |
| --- | --- |
| 80 | HTTP |
| 443 | HTTPS |
| 3000 | React |
| 3306 | MySQL |
| 8443 | OpenVidu |
| 9090 | Spring Boot |
| 9999 | Jenkins |

---
<br>

## How To Run in Local

- Frontend

```docker
# Node Package Manager 설치
npm install
# 실행
npm start
```

- Backend : src/main/java/com/ssafy/farmcu/FarmcuApplication.java 실행

---
<br>

## How To Run in EC2

#### 1. Nginx 설치

```docker
# Nginx 설치
sudo apt-get install nginx
# 설치 확인
sudo nginx -v
# Nginx 중지
sudo systemctl stop nginx
```

#### 2. SSL 인증서 발급

```docker
# Let's Encrypt 설치
sudo apt-get install letsencrypt
# 인증서 적용 및 .pem 키 발급
sudo letsencrypt certonly --standalone -d [도메인]
# 발급 경로 확인
cd /etc/letsencrypt/live/[도메인]
```

#### 3. Docker 설치

- 공식 문서 참고 : [https://docs.docker.com/engine/install/ubuntu/](https://docs.docker.com/engine/install/ubuntu/)

```docker
sudo apt-get remove docker docker-engine docker.io containerd runc

sudo apt-get update

sudo apt-get install \
    ca-certificates \
    curl \
    gnupg \
    lsb-release

sudo mkdir -m 0755 -p /etc/apt/keyrings

curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /etc/apt/keyrings/docker.gpg

echo \
  "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.gpg] https://download.docker.com/linux/ubuntu \
  $(lsb_release -cs) stable" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null

sudo apt-get update

sudo apt-get install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin
```

#### 4. MySQL 설치

```docker
# EC2 서버 업데이트 진행
sudo apt update
# MySQL 설치
sudo apt install mysql-server
# MySQL 상태 확인
sudo systemctl status mysql
# root 계정 접속
sudo mysql -u root -p
```

```docker
# 외부 접속 허용
# bind-address = 0.0.0.0
sudo vi /etc/mysql/mysql.conf.d/mysqld.cnf
# MySQL 재실행
sudo service mysql restart
```

#### 5. 방화벽 설정

```docker
# ufw 활성화
sudo ufw enabled
# ufw 상태 확인
sudo ufw status
# ufw 허용
sudo ufw allow ssh
sudo ufw allow [포트번호]
```

#### 6. OpenVidu 배포

- 공식 문서 참고 : [https://docs.openvidu.io/en/2.25.0/deployment/ce/on-premises/](https://docs.openvidu.io/en/2.25.0/deployment/ce/on-premises/)

#### 7. Nginx 설정

```docker
# 설정 파일 위치로 이동
cd /etc/nginx/sites-available
# conf 파일 생성
sudo vim [파일명].conf
# 파일 연동 및 테스트
sudo ln -s /etc/nginx/sites-available/[파일명].conf /etc/nginx/sites-enabled/[파일명].conf
sudo nginx -t
# Nginx 재시작
sudo systemctl restart nginx
# Nginx 상태 확인
sudo systemctl status nginx
```

```docker
server {
        # 프론트 연결(포트 번호는 본인의 프론트 포트번호를 입력)
        location / {
                proxy_pass http://localhost:3000;
        }

        # 백엔드 연결(포트 번호는 본인의 백엔드 포트번호를 입력)
        location /api {
                proxy_pass http://localhost:9090/api;
        }

        listen 443 ssl; # managed by Certbot
        # 도메인 이름을 써줘야함
        ssl_certificate /etc/letsencrypt/live/[도메인]/fullchain.pem; # managed by Certbot
        # 도메인 이름을 써줘야함
        ssl_certificate_key /etc/letsencrypt/live/[도메인]/privkey.pem; # managed by Certbot
        # include /etc/letsencrypt/options-ssl-nginx.conf; # managed by Certbot
        # ssl_dhparam /etc/letsencrypt/ssl-dhparams.pem; # managed by Certbot
}

server {
        # 도메인 이름을 입력
        if ($host = [도메인]) {
                return 301 https://$host$request_uri;
        } # managed by Certbot

        listen 80;
        server_name [도메인];
        return 404; # managed by Certbot
}
```

#### 8. Dockerfile 및 nginx.conf 작성

- Frontend
    - Dockerfile
    
    ```docker
    FROM nginx:stable-alpine
    WORKDIR /app
    RUN mkdir ./build
    ADD ./build ./build
    RUN rm /etc/nginx/conf.d/default.conf
    COPY ./nginx.conf /etc/nginx/conf.d
    EXPOSE 3000
    CMD ["nginx", "-g", "daemon off;"]
    ```
    
    - nginx.conf
    
    ```docker
    server {
        listen 443;
        location / {
            root /app/build;
            index index.html;
            try_files $uri $uri/ /index.html;
        }
    }
    ```
    
- Backend
    - Dockerfile
    
    ```docker
    FROM openjdk:11-jdk-slim
    ARG JAR_FILE=build/libs/*.jar
    COPY ${JAR_FILE} app.jar
    EXPOSE 9090
    ENTRYPOINT ["java", "-jar", "/app.jar"]
    ```
    

#### 9. Jenkins 설치

```docker
# jenkins 설치
sudo docker pull jenkins/jenkins:lts
# jenkins 컨테이너 실행
sudo docker run -d -p 9999:8080 -v /home/ubuntu/jenkins:/var/jenkins_home --name jenkins -u root jenkins/jenkins:lts
# jenkins 컨테이너 접속
sudo docker exec -it jenkins bash
# docker 설치 (3. Docker 설치 참고)
# npm 설치
apt install npm
```

#### 10. Pipeline 작성

- Frontend

```docker
pipeline {
    agent any
 
 environment {
     GIT_URL = "https://lab.ssafy.com/s08-webmobile1-sub2/S08P12B103.git"
 }
 
 stages {
     stage('Pull') {
         steps {
             script {
                 git url: "${GIT_URL}", branch: "main", credentialsId: 'ssafy', poll: true, changelog: true
             }
         }
     }
     
     stage('React Build') {
         steps {
             sh 'npm install -g yarn'
             sh 'yarn --cwd ./front/farm-come-us install --network-timeout 100000'
             sh 'yarn --cwd ./front/farm-come-us build'
         }
     }
     
     stage('Build') {
         steps {
             script {
                 sh 'docker build -t react-deploy ./front/farm-come-us'
             }
         }
     }
   
   stage('Deploy') {
       steps {
           script {
               try {
                   sh 'docker stop react-deploy && docker rm react-deploy'
               } catch(Exception e) {
                   echo e.getMessage()
                   echo "Error detected, but we will continue."
               }
               
               sh 'docker run -d --name react-deploy -p 3000:443 -v /etc/letsencrypt/archive:/etc/letsencrypt/archive -u root react-deploy'
           }
       }
   }
   
   stage('Finish') {
       steps {
           script {
               sh 'docker images -qf dangling=true | xargs -I{} docker rmi {}'
           }
       }
   }
 }
}
```

- Backend

```docker
pipeline {
    agent any
 
 environment {
     GIT_URL = "https://lab.ssafy.com/s08-webmobile1-sub2/S08P12B103.git"
 }
 
 stages {
     stage('Pull') {
         steps {
             script {
                 git url: "${GIT_URL}", branch: "main", credentialsId: 'ssafy', poll: true, changelog: true
             }
         }
     }
   
   stage('SpringBoot Build') {
       steps {
           script {
               dir('back/farmcu') {
                   sh 'chmod +x ./gradlew'
                   sh './gradlew clean build -x test'
                   sh 'docker build -t spring-deploy .'
               }
           }
       }
   }
   
   stage('Deploy') {
       steps {
           script {
               try {
                   sh 'docker stop spring-deploy && docker rm spring-deploy'
               } catch(Exception e) {
                   echo e.getMessage()
                   echo "Error detected, but we will continue."
               }
               
               sh 'docker run --name spring-deploy -d -p 9090:9090 -u root spring-deploy'
           }
       }
   }
   
   stage('Finish') {
       steps {
           script {
               sh 'docker images -qf dangling=true | xargs -I{} docker rmi {}'
           }
       }
   }  
 }
}
```

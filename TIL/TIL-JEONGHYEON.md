## **TIL (Today's I Learned)**

**2023. 01. 16 (월) : WebRTC**
<br>

<img src="./resources/img/WebRTC_Logo.svg" style="width: 60%" alt="WebRTC_Logo" />

### **WebRTC 초기 설정**

---

1. [Docker Desktop 최신 버전 설치](https://www.docker.com/products/docker-desktop/)

2. [WSL2 (Windows Subsystem for Linux) 설치](https://learn.microsoft.com/ko-kr/windows/wsl/install-manual#step-4---download-the-linux-kernel-update-package)

3. 리눅스 가상머신 설치

   윈도우 > Microsoft Store에서 Ubuntu 22.04.1(LTS) 설치

4. Docker에 kurento media server 설치 (만약 openVidu API를 사용한다면 설치할 필요가 없음)

   설치된 Ubuntu CLI를 실행하여 아래 코드를 입력

```powershell
# docker에 kurento media server를 최신버전으로 이미지 생성
docker pull kurento/kurento-media-server:latest

# 생성된 kurento 이미지에서 컨테이너를 생성하여 실행
docker run -d --name kurento --network host \
kurento/kurento-media-server:latest
```

5. 여기서부터는 <a href="kurento-media-server">kurento-media-server만을 사용한 샘플코드</a>와 openVidu API를 사용한 샘플코드 실행법으로 나뉩니다.

<br><br>

## [**WebRTC kurento-media-server 샘플코드**](#kurento-media-server)

---

[참고 블로그](https://gh402.tistory.com/44)

<br><br>

## [**WebRTC openVidu API 샘플코드**](#openVidu)

---

[OpenVidu 공식 가이드 문서](https://docs.openvidu.io/en/stable/tutorials/openvidu-library-react/)

<br><br>

**2023. 01. 18 (수) : WebRTC - 커스텀 관련 자료 조사**
<br>

- 튜토리얼 코드 실행 성공, 코드 내에서 다대다 화상 채팅 및 실시간 채팅기능 확인.

- openVidu 커스텀 페이지 작성 시도..를 했으나 공식문서의 일부에서 openVidu Component는 Angular.js에서만 지원한다는 내용 발견...

- 공식 문서에서 react로 openVidu 컴포넌트를 호출해 RTC를 하는 코드 발견!

- [공식문서](https://docs.openvidu.io/en/stable/tutorials/openvidu-react/)

<br><br>

**2023. 01. 19 (목) : WebRTC 커스텀 테스트**
<br>

- 커스텀 작업 중 세션 리스트(라이브 방 목록)을 불러오는 호출(GET)에서 애플리케이션 서버와 클라이언트의 주소가 모두 localhost인 상황에서 테스트를 진행해 CORS에러 발생.

- 이후에 애플리케이션 서버, 미디어 서버 등을 EC2에 올린 뒤 다시 테스트 필요.

- 생성된 세션 방(라이브 스토어)의 커스텀 작업 중.

<br><br>

**2023. 01. 20 (금) : Front-end 디렉토리 구조 및 컴포넌트 명세**
<br>

- 디렉토리 구조 및 컴포넌트 명세서 작성

- 각 파트별 기능 사전 테스트 과제 부여

  <br><br>

**2023. 01. 25 (수) : \[React\] Router 구조 디자인 & 코드 작성**
<br>

- 프로젝트 경로에 Router 디자인 및 컴포넌트 파일 추가
- icon은 react-icons 라이브러리 사용
- [react-icons 사용 가이드](https://react-icons.github.io/react-icons/)

**2023. 01. 27 (금) : \[OpenVidu\] 오픈비두 방 생성 삽질**
<br>

- 현재 생성된 세션에 다른 사용자가 참여했을 때 publisher의 입장에서 subscriber들의 영상을 확인하지 못하는 에러 발생

- 오픈비두에서 제공해주는 튜토리얼에서도 동일한 문제 발생... 로컬에서 세팅한 서버 오류인가..?

```
StreamManager of Stream str_CAM_G0gV_con_WvivPOXdur (Subscriber) did not trigger "streamPlaying" event in 4000 ms
```

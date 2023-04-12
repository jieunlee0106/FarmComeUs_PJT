# <span style="color:orange">팜 컴어스 👩‍🌾🥕 </span>

#### 온라인 농산물 직거래 플랫폼

![로고](./img/로고.png)

<!-- 필수 항목 -->

#### 카테고리

| Application                       | Domain                                | Language                         | Framework                            |
| --------------------------------- | ------------------------------------- | -------------------------------- | ------------------------------------ |
| :white_check_mark: Desktop Web    | :black_square_button: AI              | :white_check_mark: JavaScript    | :black_square_button: Vue.js         |
| :white_check_mark: Mobile Web     | :black_square_button: Big Data        | :black_square_button: TypeScript | :white_check_mark: React             |
| :white_check_mark: Responsive Web | :black_square_button: Blockchain      | :black_square_button: C/C++      | :black_square_button: Angular        |
| :black_square_button: Android App | :black_square_button: IoT             | :black_square_button: C#         | :black_square_button: Node.js        |
| :black_square_button: iOS App     | :black_square_button: AR/VR/Metaverse | :black_square_button: Python     | :black_square_button: Flask/Django   |
| :black_square_button: Desktop App | :black_square_button: Game            | :white_check_mark: Java          | :white_check_mark: Spring/Springboot |
|                                   |                                       | :black_square_button: Kotlin     |                                      |

<br>
<!-- 필수 항목 -->

# 프로젝트 소개

## ✏ <b>프로젝트명</b>: 팜 컴어스 (Farm Come Us) 👩‍🌾🥕

### ✨ <b>서비스 특징</b><br>

- 웹/모바일(웹 기술)을 활용한 온라인 농산물 직거래 라이브 커머스 서비스

### 👤 <b>서비스 대상</b><br>

- 농산물 생산자, 소비자

### ☝🏻 <b>주요 기능</b>

- 회원 관리
- 스마트 스토어
- 농산물 라이브 직거래
- 라이브 채팅
- 농산물 상품 결제

### 👑 <b>주요 기술</b>

- WebRTC
- WebSocket
- JWT Authentication
- OAuth2.0 기반 회원 인증
- REST API

### 🧵 <b>기술 스택</b><br>

- 프론트엔드 : React.js 18.2.0 / Redux 8.0.5 / SASS 1.57.1 / JavaScript <br>
- 백엔드 : Java 11 / JPA 2.7.6 / Spring Boot 2.7.7 <br>
- DB : MySQL 8.0.31 / S3 <br>
- Infra : EC2 Ubuntu 20.04 / Docker 23.0.0 / Nginx 1.18.0 / Jenkins

### 🎠 <b>배포 환경</b>

- URL : https://i8b103.p.ssafy.io/
- 테스트 계정 : ID - myfarm, PW - asd12345!
- [포팅 메뉴얼](./exec/PortingManual.md)

### 🗓 <b>진행 일정</b><br>

- 23.01.02 ~ 23.02.17 (총 7주)

<br>
<!-- 자유 양식 -->

### 팀 소개 - <span style="color:crimson">일</span><span style="color:darkblue">이</span><span style="color:darkgreen">삼</span><span style="color:orange">사</span><span style="color:indigo">오</span>🎉

| 김지희                                            | 차현경                                                | 이지은                                             | 이정현                                          | 김성중                      | 김태영                  |
| ------------------------------------------------- | ----------------------------------------------------- | -------------------------------------------------- | ----------------------------------------------- | --------------------------- | ----------------------- |
| ![지희](./img/지희.png)                           | ![현경](./img/현경.png)                               | ![지은](./img/지은.png)                            | ![정현](./img/정현.png)                         | ![성중](./img/성중.png)     | ![태영](./img/태영.png) |
| 팀장, BE, 회원 인증 개발, 회원 및 스토어 API 담당 | BE 리더, 상품 및 라이브 API, CI/CD, 인프라, 배포 담당 | 팀원, BE, 주문 및 결제 서버 및 클라이언트 개발 담당 | FE 리더, 디자인, 화면 설계 및 구현, WebRTC 개발 | FE, 기획 및 화면 설계 | FE, 화면 설계, UCC 담당 |

<br>
<!-- 자유 양식 -->

# 프로젝트 상세 설명<br>

🥕 팜컴어스는 농가와 소비자를 바로 이어 유통 과정에서 발생하는 마진과 상품 손상을 방지하여<br>
🥕 소비자에게 합리적인 가격에 신선한 작물을 제공해주는 라이브 플랫폼입니다.

## <b>서비스 설명/주요 기능</b>

1. 라이브 커머스 중 목표 판매 재고를 실시간으로 확인 (생동감 있는 라이브 판매 시각화)
2. 가입부터 결제까지 매끄럽게 이어지는 UX 흐름, 판매자 입장을 고려한 쉬운 라이브 등록, 소비자 입장을 고려한 간편한 상품 구매
3. 카카오 로그인 API, 카카오 결제 API 사용으로 손쉬운 로그인 및 결제 가능

<!-- // 개발 환경, 기술 스택, 시스템 구성도, ERD, 기능 상세 설명 등 -->

## 기획/설계

### 🛠 <b>아키텍처</b>

![아키텍처](./img/아키텍처.png)

### 💾 <b>[ERD](https://www.erdcloud.com/d/JmuHQc8YEA35RinPv)</b>

![ERD](./img/테이블.png)

### 🎨 <b>[와이어 프레임](https://www.figma.com/file/7hqnEIqEnz7Ie2u7b3MlFz/Untitled?t=wZac7adufZbT6cHS-0)</b>

![화면 흐름도](./img/flow.png)

### ⛓ <b>파일 구조</b>

- Frontend

```
├── App.css
├── App.js
├── components
│   ├── broadcast
│   │   ├── ChatItem.js
│   │   ├── ChatList.js
│   │   ├── LiveChat.js
│   │   ├── LiveHeader.js
│   │   └── LiveInfo.js
│   ├── common
│   │   ├── Badge.js
│   │   ├── Button.js
│   │   ├── Card.js
│   │   ├── CardSlider.js
│   │   ├── Header.js
│   │   ├── ImageButton.js
│   │   ├── ImageButtonList.js
│   │   ├── ImageSlider.js
│   │   ├── Input.js
│   │   ├── MiniCard.js
│   │   ├── Navigation.js
│   │   ├── SideMenu.js
│   │   └── SubTab.js
│   ├── create
│   │   ├── MyStoreImageForm.js
│   │   └── MyStoreInfoForm.js
│   ├── live
│   │   ├── LiveInfo.js
│   │   ├── LiveItem.js
│   │   └── LiveList.js
│   ├── mypage
│   │   ├── MyPageHeader.js
│   │   ├── MyReceiptItem.js
│   │   ├── MyReceiptList.js
│   │   ├── StoreLikeItem.js
│   │   └── StoreLikeList.js
│   ├── mystore
│   │   ├── MyStoreHeader.js
│   │   ├── MyStoreLiveItem.js
│   │   ├── MyStoreLiveList.js
│   │   ├── MyStoreNoData.js
│   │   ├── MyStoreProductItem.js
│   │   ├── MyStoreProductList.js
│   │   ├── MyStoreReceiptItem.js
│   │   └── MyStoreReceiptList.js
│   ├── product
│   │   ├── Category.js
│   │   ├── ProductItem.js
│   │   └── ProductList.js
│   └── store
│       ├── StoreHeader.js
│       ├── StoreLiveItem.js
│       ├── StoreLiveList.js
│       ├── StoreNoData.js
│       ├── StoreProductItem.js
│       └── StoreProductList.js
├── index.css
├── index.js
├── pages
│   ├── BroadCast.js
│   ├── Cart.js
│   ├── Home.js
│   ├── Login.js
│   ├── Signup.js
│   ├── create
│   │   ├── CreateLive.js
│   │   └── CreateProduct.js
│   ├── live
│   │   ├── Live.js
│   │   ├── RunningLive.js
│   │   └── SheduledLive.js
│   ├── mypage
│   │   ├── MyPageInfo.js
│   │   ├── MyReceipt.js
│   │   ├── MyReceiptDetail.js
│   │   ├── Mypage.js
│   │   └── StoreLike.js
│   ├── mystore
│   │   ├── MyStore.js
│   │   ├── MyStoreAddLive.js
│   │   ├── MyStoreAddProduct.js
│   │   ├── MyStoreInfo.js
│   │   ├── MyStoreLive.js
│   │   ├── MyStoreProducts.js
│   │   ├── MyStoreReceipt.js
│   │   └── MyStoreReceiptDetail.js
│   ├── product
│   │   ├── Payment.js
│   │   ├── ProductDetail.js
│   │   └── Products.js
│   └── store
│       ├── Store.js
│       ├── StoreLive.js
│       └── StoreProducts.js

```

- Backend

```
C:.
├─java
│  └─com
│      └─ssafy
│          └─farmcu
│              ├─api
│              │  ├─controller
│              │  │  ├─live
│              │  │  ├─member
│              │  │  ├─order
│              │  │  └─store
│              │  ├─dto
│              │  │  ├─live
│              │  │  ├─member
│              │  │  ├─order
│              │  │  └─store
│              │  ├─entity
│              │  │  ├─live
│              │  │  ├─member
│              │  │  ├─order
│              │  │  │  └─pay
│              │  │  └─store
│              │  ├─repository
│              │  └─service
│              │      ├─image
│              │      ├─kakao
│              │      ├─live
│              │      ├─member
│              │      ├─order
│              │      └─store
│              ├─config
│              │  └─properties
│              ├─exception
│              ├─oauth
│              │  ├─dto
│              │  ├─filter
│              │  ├─handler
│              │  ├─Info
│              │  ├─repository
│              │  ├─service
│              │  └─token
│              └─utils
└─resources
```

<br>
<!-- 서비스 시연 -->

## 서비스 구현 내용

### 🎁 메인 페이지

![메인페이지](./img/메인페이지.png)

### 👤 로그인<br>

<img src="./img/Login.gif" width="380">

### 📱 마이 페이지

![마이페이지](./img/마이페이지.png)

### 🥗 마이 스토어

![마이스토어](./img/마이스토어.png)<br>
<img src="./img/MyStore.gif" width="380">

### 📺 라이브

- 라이브 생성<br>
  <img src="./img/LiveCreate.gif" width="380">
- 라이브 시작<br>
  <img src="./img/Live.gif" width="380">

- 라이브 참여<br>
  <img src="./img/DoLive.gif" width="380">

### 🥕 상품

- 상품 등록<br>
  <img src="./img/Item.gif" width="380">

- 상품 구매<br>
  <img src="./img/BuyItem.gif" width="380">

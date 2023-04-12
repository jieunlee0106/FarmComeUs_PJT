import classes from "./KakaoLogin.module.scss";
import axios from "axios";

const KakaoLogin = (props) => {
  // const KAKAO_AUTH_URL = `https://kauth.kakao.com/oauth/authorize?client_id=${process.env.REACT_APP_REST_API_KEY}&redirect_uri=${process.env.REACT_APP_REDIRECT_URI}&response_type=code`;
  // 버튼 누르면 OAuth2RedirectHandler를 보여주는걸로 라우팅 추가
  // const KAKAO_AUTH_URL = `https://kauth.kakao.com/oauth/authorize?client_id=${process.env.REACT_APP_REST_API_KEY}&redirect_uri=${process.env.REACT_APP_REDIRECT_URI}&response_type=code`;

  const KAKAO_AUTH_URL =
    process.env.REACT_APP_API_SERVER_URL +
    `/oauth2/authorization/kakao?redirect_uri=https://localhost:3000/kakao`;

  // const KAKAO_AUTH_URL =
  //   process.env.REACT_APP_API_SERVER_URL + "/api/v1/login/oauth";

  // console.log(KAKAO_AUTH_URL);
  const handleKakaoLogin = (e) => {
    e.preventDefault();
    alert("준비 중인 기능입니다.");
    return;
    console.log(KAKAO_AUTH_URL);
    axios.get(`${KAKAO_AUTH_URL}}`);
  };

  return (
    <a href={""} onClick={handleKakaoLogin}>
      <img
        src="img/kakao_login_button.png"
        alt=""
        className={classes.kakaobutton}
      />
    </a>
  );
};

export default KakaoLogin;

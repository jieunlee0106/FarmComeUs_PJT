import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import userSlice from "../../reduxStore/userSlice";

import classes from "./OAuth2RedirectHandler.module.scss";

// import Spinner from "../spinner";

const getStateURL =
  process.env.REACT_APP_API_SERVER_URL + "/api/v1/login/oauth";
const getCallbackURL =
  process.env.REACT_APP_API_SERVER_URL + "/api/v1/login/callback";

const getUserInfoURL = process.env.REACT_APP_API_SERVER_URL + "/api/v1/member";

function OAuth2RedirectHandler(props) {
  const dispatch = useDispatch();
  const navigate = useNavigate();

  // 인가코드
  let code = new URL(window.location.href).searchParams.get("code");
  // const config = {
  //   headers: {
  //     "Content-Type": "application/json",
  //     "Access-Control-Allow-Origin": "*",
  //   },
  //   withCredentials: false,
  // };
  // 원래 있던거.

  const data = {
    code: code,
  };

  const KakaoLoginMatch = async (value) => {
    if (value?.status === 200) {
      const accessToken = sessionStorage.getItem("accessToken");
      const userDataRes = await axios.get("/api/api/v1/member/", {
        headers: {
          "Content-Type": "application/json",
          "Access-Control-Allow-Origin": "*",
          token: accessToken,
        },
      });

      // const userInfoRes = await axios.get(getUserInfoURL);
      dispatch(userSlice.actions.login(value?.data));
      // 백엔드에서 넘겨주는 데이터를 dispatch로 내 리덕스에 넘김.

      navigate("/");
      // 😀 헤더에 있는 nickname이 null이면, additional Info로 넘긴다.
      // if (value?.header.nickname === null) {navigate(/additional-info)}
    } else {
      alert("로그인이 실패하였습니다. 다시 시도해주세요.");
      navigate("/login");
      //예외처리 추가
    }
  };

  const getToken = async (code, state) => {
    const params = { state: state, code: code };
    await axios
      .get(getCallbackURL, params)
      .then((res) => console.log(`res:${res}`))
      .catch((err) => console.log(err));
  };

  // 😀 여기서 시작
  const getState = async () => {
    await axios
      .get(getStateURL, { params: { code: code } })
      .then((res) => {
        let state = new URL(res.data).searchParams.get("state");

        getToken(code, state);
      })

      .catch((err) => {
        console.log(err);
      });
  };

  const getUserData = async () => {
    const accessToken = sessionStorage.getItem("accessToken");

    const userDataRes = await axios.get(
      process.env.REACT_APP_API_SERVER_URL + "/api/v1/member/",
      {
        headers: {
          "Content-Type": "application/json",
          "Access-Control-Allow-Origin": "*",
          token: accessToken,
        },
      }
    );
    dispatch(userSlice.actions.login(userDataRes.data.userInfo));
    navigate("/");
  };

  useEffect(() => {
    getUserData();
  }, []);

  const config = {
    headers: {
      "Content-Type": "application/json",
      "Access-Control-Allow-Origin": "*",
    },
    withCredentials: false,
  };

  return (
    <div className={classes.container}>
      <div className={classes.loadingTxt}>Kakao Loading...</div>
    </div>
  );
}

export default OAuth2RedirectHandler;

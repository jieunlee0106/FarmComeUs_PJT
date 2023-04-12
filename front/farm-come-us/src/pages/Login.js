import React, { useState } from "react";
import { useDispatch } from "react-redux";
import { useNavigate, useLocation } from "react-router-dom";
import { MdPermIdentity, MdLockOutline } from "react-icons/md";
import { AiFillEye, AiFillEyeInvisible } from "react-icons/ai";
import axios from "axios";
import userSlice from "../reduxStore/userSlice";

// 이 함수도 수정필요 😀 기본형으로 해둠.
import Button from "../components/common/Button";
import KakaoLogin from "../components/user/KakaoLogin";
import classes from "./style/Login.module.scss";

function Login() {
  // const dispatch = useDispatch();
  const dispatch = useDispatch();
  const location = useLocation();

  let signUpId = "";
  let signUpPassword = "";

  const [userId, setUserId] = useState("");
  const [password, setPassword] = useState("");

  const [showPassword, setShowPassword] = useState(false);
  const [isError, setIsError] = useState(false);
  const [errMessage, setErrMessage] = useState("");
  const navigate = useNavigate();

  if (location.state !== null) {
    signUpId = location.state.id;
    signUpPassword = location.state.password;
    if (!userId && !password) {
      setUserId(signUpId);
      setPassword(signUpPassword);
    }
  } else {
  }

  const loginHandler = async () => {
    const data = {
      id: userId,
      password: password,
    };
    const config = {
      headers: {
        "Content-Type": "application/json",
        "Access-Control-Allow-Origin": "*",
      },
      withCredentials: false,
    };

    try {
      const response = await axios.post(
        process.env.REACT_APP_API_SERVER_URL + "/api/v1/member/login",
        data,
        config
      );

      const accessToken = response.data["token"];
      sessionStorage.setItem("accessToken", accessToken);

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
    } catch (err) {
      setIsError(true);
      setErrMessage("입력 정보를 확인해주세요.");
      setTimeout(() => {
        setIsError(false);
        setErrMessage("");
      }, 500);
      console.log(err);
    }
  };

  const LoginSubmit = (e) => {
    e.preventDefault();
    loginHandler();
  };

  return (
    <div className={classes.screen}>
      <h1 className={classes.headertxt}>로그인</h1>
      <form
        className={`${classes.centeralign} ${classes.marginSpacing20px}`}
        onSubmit={LoginSubmit}
      >
        <div className={isError ? classes.vibration : ""}>
          <MdPermIdentity className={classes.idicon} />
          <input
            className={`${classes.inputbar}`}
            placeholder="아이디"
            value={userId ? userId : ""}
            onChange={(e) => {
              setUserId(e.target.value);
            }}
            id="userId"
          />
        </div>
        <br />

        <div>
          <div
            className={`${classes.password} ${
              isError ? classes.vibration : ""
            }`}
          >
            <MdLockOutline className={classes.pwicon} />
            {showPassword ? (
              <AiFillEyeInvisible
                className={classes.smallicon}
                onClick={() => {
                  setShowPassword(!showPassword);
                }}
              />
            ) : (
              <AiFillEye
                className={classes.smallicon}
                onClick={() => {
                  setShowPassword(!showPassword);
                }}
              />
            )}
            <input
              className={classes.inputbar}
              placeholder="비밀번호"
              value={password ? password : ""}
              onChange={(e) => {
                setPassword(e.target.value);
              }}
              id="password"
              type={showPassword ? "text" : "password"}
            />
          </div>
          {isError && (
            <span className={`${errMessage ? classes.errMessage : ""}`}>
              {errMessage}
            </span>
          )}
        </div>
        <br />

        <div className={classes.marginSpacing_ratio}>
          <Button
            type="submit"
            className={`${classes.loginButton} ${classes.marginSpacing16px}`}
          >
            사용자 로그인
          </Button>
        </div>
      </form>
      <div className={`${classes.marginSpacing16px}`}>
        <KakaoLogin />
      </div>
      <Button
        className={`${classes.signUpButton} ${classes.marginSpacing16px}`}
        onClick={() => {
          navigate("/sign-up");
        }}
      >
        회원가입
      </Button>
    </div>
  );
}

export default Login;

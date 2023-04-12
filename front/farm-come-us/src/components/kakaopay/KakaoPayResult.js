import React, { useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import classes from "./KakaoPayResult.module.scss";

const KakaoPayResult = (props) => {
  const navigate = useNavigate();

  useEffect(() => {
    alert("결제가 완료되었습니다.");
    setTimeout(navigate("/"), 3000);
  }, []);

  return (
    <div className={classes.screen}>
      결제가 완료되었습니다. 잠시 후 메인 페이지로 이동합니다.{" "}
    </div>
  );
};

export default KakaoPayResult;

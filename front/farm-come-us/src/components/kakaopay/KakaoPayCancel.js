import { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import classes from "./KakaoPayCancel.module.scss";

const KakaoPayCancel = (props) => {
  const navigate = useNavigate();
  useEffect(() => {
    setTimeout(() => {
      navigate("/");
    }, 3000);
  }, []);
  return (
    <div className={classes.screen}>결제 취소. 메인페이지로 이동합니다.</div>
  );
};

export default KakaoPayCancel;

import { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import classes from "./KakaoPayFail.module.scss";

const KakaoPayFail = (props) => {
  const navigate = useNavigate();
  useEffect(() => {
    // setTimeout(navigate("/"), 2000);
    setTimeout(() => {
      navigate(-1);
    }, 3000);
  }, []);
  return (
    <div className={classes.screen}>
      결제 실패. 관리자에게 문의 주세요. (1599-3211) 잠시 후 직전페이지로
      이동합니다.
    </div>
  );
};

export default KakaoPayFail;

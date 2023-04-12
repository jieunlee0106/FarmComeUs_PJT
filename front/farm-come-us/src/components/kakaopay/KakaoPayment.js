import axios from "axios";
import { useSelector } from "react-redux";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import Button from "../common/Button";
import classes from "./KakaoPayment.module.scss";

const KakaoPayment = (props) => {
  const navigate = useNavigate();
  const urlParams = new URLSearchParams(window.location.search);
  const pgToken = urlParams.get("pg_token");
  console.log(pgToken);

  useEffect(() => {
    axios
      .get(process.env.REACT_APP_API_SERVER_URL + "/api/v1/payment/success", {
        params: { pg_token: pgToken },
      })
      .then((res) => {
        if (res.status === 100 || res.status === 200) {
          navigate("/payment-result");
        }
        console.log(res.data);
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);

  return (
    <div className={classes.screen}>
      <div>결제 상세</div>
      <div>카카오페이 결제 중입니다.</div>{" "}
    </div>
  );
};

export default KakaoPayment;

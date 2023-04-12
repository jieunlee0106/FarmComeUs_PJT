import React, { useState, useEffect } from "react";
import { useNavigate, useLocation } from "react-router-dom";
import axios from "axios";
import classes from "./style/KakaopayEvent.module.scss";

const KakaopayEvent = (props) => {
  const navigate = useNavigate();
  const kakaoClick = async () => {
    try {
      axios
        .get(process.env.REACT_APP_API_SERVER_URL + "/api/v1/pay/kakaoreq", {
          params: {
            itemCount: props.itemCount,
            memberId: props.memberId,
            orderId: props.orderId,
          },
        })
        .then((response) => {
          console.log(
            "여기 아래에 redirect랑 tid정보 들어옵니다. 결제가 다 완료되고 오는지?"
          );
          console.log(response);
          const setUrl = response.data.next_redirect_pc_url;
          const tid = response.data.tid;

          console.log(setUrl);

          console.log(tid);

          try {
            console.log("tid 주기");
            axios.put(
              process.env.REACT_APP_API_SERVER_URL +
                `/api/v1/pay/tid?tid=${tid}&orderId=${props.orderId}`
            );
            navigate("/");
          } catch (err) {
            console.log(err);
          }
          window.open(setUrl);

        })
        .catch((error) => {
          console.log("여기서에러");
          console.log(error);
        });
    } catch (err) {
      console.err(err);
    }
    return;
  };
  return (
    <div className={classes.buttonspace} onClick={kakaoClick}>
            <div className={classes.button}>카카오페이로 결제하기</div>   {" "}
    </div>
  );
};

export default KakaopayEvent;

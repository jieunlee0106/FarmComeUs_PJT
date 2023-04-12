import React from "react";
import classes from "./style/Payment.module.scss";
import Card from "../../components/common/Card";
import { useLocation, useNavigate } from "react-router-dom";
import { MdOutlineArrowBackIos } from "react-icons/md";
import { orderProduct } from "../../utils/api/order-http";

const CartPayment = () => {
  const location = useLocation();

  const navigate = useNavigate();

  const res = orderProduct()
  console.log(res)

  const convertedPrice = (price) =>
    price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");

  return (
    <div className={classes.container}>
      <div className={classes.title}>
        <MdOutlineArrowBackIos
          onClick={() => navigate(-1)}
        ></MdOutlineArrowBackIos>
        <div>주문 / 결제</div>
      </div>
      <Card className={classes.paymentcard}>
        <div className={classes.cardheader}>배송지</div>
        <div className={classes.cardscript}>
          <div className={classes.username}>김덕배</div>
          <div className={classes.userphonenumber}>010-5251-1234</div>
          <div className={classes.useraddress}>
            대전광역시 유성구 온천북로7 레자미멀티홈 102-892
          </div>
        </div>
      </Card>

      <div className={classes.foot}>
        <div className={classes.text}>
          주문 내용을 확인하였으며, 정보 제공 등에 동의합니다.
        </div>
        <div className={classes.buttonspace}>
          <div className={classes.button}>카카오페이로 결제하기</div>
        </div>
      </div>
    </div>
  );
};

export default CartPayment;

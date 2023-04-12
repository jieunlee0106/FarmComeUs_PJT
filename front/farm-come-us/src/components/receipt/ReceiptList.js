import { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import ReceiptCard from "./ReceiptCard";
import ReciptCardSole from "./ReceiptCardSole";

import { MdOutlineArrowBackIos } from "react-icons/md";

import classes from "./style/ReceiptList.module.scss";

const ReceiptList = (props) => {
  const navigate = useNavigate();
  useEffect(() => {
    "axios 주문번호, 유저아이디";
  }, []);

  let reciptCards = (
    <div className={classes.flexbox}>
      <div className={classes.noItem}>주문 내역이 없습니다.</div>
    </div>
  );
  let [orderData, setOrderData] = useState([]);
  const data = [
    {
      img: "첫번째이미지",
      title: "명이네 과수원",
      unit: "30",
      storename: "명이네 과수원",
      cost: "1597750",
    },
    {
      img: "두번째이미지",
      title: "싱싱청과마켓",
      unit: "25",
      storename: "싱싱청과마켓",
      cost: "123456",
    },
    {
      img: "첫번째이미지",
      title: "청정 고랭지 엔비사과",
      unit: "12",
      storename: "이것도 가게라고",
      cost: "429000",
    },
  ];
  const convertedPrice = (price) =>
    price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");

  const costConvertedData = data.map(
    (item) => ((item.cost = convertedPrice(item.cost)), item)
  );
  // console.log(costConvertedData);

  reciptCards = costConvertedData.map((itemcard, idx) => (
    <ReceiptCard
      key={idx}
      img={itemcard.img}
      title={itemcard.title}
      unit={itemcard.unit}
      storename={itemcard.storename}
      cost={itemcard.cost}
    />
  ));

  // <ReciptCardSole />;

  return (
    <div className={classes.screen}>
      <div className={classes.upperCard}>
        <div className={classes.flexrow}>
          <div
            onClick={() => {
              navigate(-1);
            }}
          >
            <MdOutlineArrowBackIos className={classes.backButton} />
          </div>
          <div className={classes.backButton}>주문상세</div>
        </div>
        <div className={classes.orderInfoMt}>
          <div
            className={classes.orderinfo}
          >{`주문번호 : ${props.orderid}`}</div>
          <div
            className={classes.orderinfo}
          >{`구매일자 : ${props.orderDate}`}</div>
        </div>
        <div
          className={`${classes.orderinfo} ${classes.orderLength} ${classes.orderlength}`}
        >
          상품 {orderData.length} 개
        </div>
      </div>

      {data.length === 1 ? ReciptCardSole : reciptCards}
    </div>
  );
};

export default ReceiptList;

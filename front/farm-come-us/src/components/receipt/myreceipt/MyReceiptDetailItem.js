import axios from "axios";
import React, { Fragment, useEffect, useState } from "react";
import classes from "./style/MyReceiptDetailItem.module.scss";

const MyReceiptDetailItem = (props) => {
  console.log("prop을 보자");
  console.log(props);
  const [imgSrc, setImgSrc] = useState("");
  const [storeName, setStoreName] = useState("");

  //   const totalPrice =
  //     props.receipt.length > 1
  //       ? props.receipt.reduce((acc, curr, idx) => {
  //           if (idx === 1) return acc.price + curr.price;
  //           else return acc + curr.price;
  //         })
  //       : props.receipt[0].price;

  const convertedPrice = props.itemPrice
    .toString()
    .replace(/\B(?=(\d{3})+(?!\d))/g, ",");

  const getImageWithItemID = (itemId) => {
    axios
      .get(process.env.REACT_APP_API_SERVER_URL + "/api/v1/item", {
        params: { itemId: itemId },
      })
      .then((res) => {
        console.log(`이미지경로: ${res.data.item.savedPath[0]}`);
        setImgSrc(res.data.item.savedPath[0]);
        setStoreName(res.data.storeName);
      });
  };

  useEffect(() => {
    getImageWithItemID(props.itemId);
  }, [props.itemId]);

  //   const dateFormatted = dateFormatter();

  return (
    <div
      className={classes.receiptItem}
      onClick={() => props.onClick(props.receipt)}
    >
      <div className={classes.receiptImg}>
        <img src={imgSrc} />
      </div>
      <div className={classes.receiptInfo}>
        <p className={classes.receiptTitle}>{props.receipt[0].productName}</p>
        <p className={classes.receiptOrderId}>{storeName}</p>
        <p className={classes.totalPrice}>{`${convertedPrice}원`}</p>
      </div>
    </div>
  );
};

export default MyReceiptDetailItem;

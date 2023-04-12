import React, { Fragment } from "react";

import classes from "./style/MyStoreReceiptItem.module.scss";

const MyStoreReceiptItem = (props) => {
  const totalPrice =
    props.receipt.length > 1
      ? props.receipt.reduce((acc, curr, idx) => {
          if (idx === 1) return acc.price + curr.price;
          else return acc + curr.price;
        })
      : props.receipt[0].price;

  const convertedPrice = totalPrice
    .toString()
    .replace(/\B(?=(\d{3})+(?!\d))/g, ",");

  const dateFormatter = () => {
    const regDt = props.receipt[0].date;
    const year = regDt.getFullYear();
    const month = regDt.getMonth() + 1;
    const date = regDt.getDate();
    let hour = regDt.getHours();
    hour = hour < 12 ? "0" + hour : hour;
    let minute = regDt.getMinutes();
    minute = minute < 10 ? "0" + minute : minute;

    return `${year}.${month}.${date} ${hour}:${minute}`;
  };

  const dateFormatted = dateFormatter();

  return (
    <div
      className={classes.receiptItem}
      onClick={() => props.onClick(props.receipt)}
    >
      {props.receipt.length === 1 ? (
        <Fragment>
          <div className={classes.receiptImg}>
            <img src={props.receipt[0].imgSrc} />
          </div>
          <div className={classes.receiptInfo}>
            <p className={classes.receiptTitle}>
              {props.receipt[0].productName}
            </p>
            <p
              className={classes.receiptOrderId}
            >{`주문번호 ${props.receipt[0].orderId}`}</p>
            <p className={classes.totalPrice}>{`${convertedPrice}원`}</p>
            <p className={classes.receiptDate}>{`${dateFormatted}`}</p>
          </div>
        </Fragment>
      ) : (
        <Fragment>
          <div className={classes.receiptImg}>
            <img src={props.receipt[0].imgSrc} />
          </div>
          <div className={classes.receiptInfo}>
            <p className={classes.receiptTitle}>
              {props.receipt[0].productName}
              <span className={classes.otherItemCnt}>{`외 ${
                props.receipt.length - 1
              }개`}</span>
            </p>
            <p
              className={classes.receiptOrderId}
            >{`주문번호 ${props.receipt[0].orderId}`}</p>
            <p className={classes.totalPrice}>{`${convertedPrice}원`}</p>
            <p className={classes.receiptDate}>{`${dateFormatted}`}</p>
          </div>
        </Fragment>
      )}
    </div>
  );
};

export default MyStoreReceiptItem;

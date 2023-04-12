import React from "react";

import classes from "./style/LiveProductInfo.module.scss";

import CardCaption from "../common/CardCaption";

const LiveProductInfo = (props) => {
  const convertedPrice = props.liveInfo.liveItemPrice
    .toString()
    .replace(/\B(?=(\d{3})+(?!\d))/g, ",");

  return (
    <div className={classes.infoContainer}>
      <CardCaption className={classes.productCaption}>
        <div className={classes.storeInfo}>
          <p>
            {props.liveInfo.storeName}
            <span className={classes.subfix}> 스토어</span>
          </p>
        </div>
        <div className={classes.productTitle}>
          <span className={classes.text}>{props.liveInfo.productName}</span>
        </div>
        <div className={classes.liveInfo}>
          <span className={classes.discount}>{props.liveInfo.discount}%</span>
          <span>
            {convertedPrice} / {props.liveInfo.count}
            {props.liveInfo.unit}
          </span>
        </div>
      </CardCaption>
    </div>
  );
};

export default LiveProductInfo;

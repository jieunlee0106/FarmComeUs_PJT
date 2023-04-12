import React from "react";

import classes from "./style/StoreLiveItem.module.scss";

const StoreLiveItem = (props) => {
  const today = new Date();
  const onedayAfter =
    new Date(props.item.liveStart).getTime() + 1 * 60 * 60 * 1000;
  const endDate = new Date(onedayAfter);
  const isLiveEnded = today.getTime() >= endDate.getTime() ? true : false;

  const convertedPrice = props.item.liveItemPrice
    .toString()
    .replace(/\B(?=(\d{3})+(?!\d))/g, ",");

  const reservedDate = () => {
    const date = new Date(props.item.liveStart);
    const startTime = date.getTime();
    const endDate = new Date(startTime + 1 * 60 * 60 * 1000);

    const year = date.getFullYear();
    const month = date.getMonth() + 1;
    const dt = date.getDate();
    let hour = date.getHours();
    hour = hour < 12 ? "0" + hour : hour;

    let minute = date.getMinutes();
    minute = minute < 10 ? "0" + minute : minute;

    const endYear = endDate.getFullYear();
    const endMonth = endDate.getMonth() + 1;
    const endDt = endDate.getDate();
    let endHour = endDate.getHours();
    endHour = endHour < 12 ? "0" + endHour : endHour;

    let endMinute = endDate.getMinutes();
    endMinute = endMinute < 10 ? "0" + endMinute : endMinute;

    return `${year}.${month}.${dt} ${hour}:${minute} ~ ${endYear}.${endMonth}.${endDt} ${endHour}:${endMinute}`;
  };

  return (
    <div className={classes.liveItem}>
      <div className={classes.liveThumbnail}>
        {isLiveEnded ? (
          <div className={classes.backdrop}>
            <span>종료된 라이브</span>
          </div>
        ) : null}
        <img
          className={classes.thumnail}
          src={props.item.savedPath}
          alt="live_img"
        />
      </div>
      <div className={classes.liveInfo}>
        <div className={classes.productInfo}>
          <span className={classes.title}>{props.item.liveTitle}</span>
          {/* <span
            className={classes.stock}
          >{`남은 수량: ${props.item.stock}${props.item.unit}`}</span> */}
        </div>
        <div className={classes.unitPrice}>
          <span>{`${convertedPrice} 원`}</span>
          <span
            className={classes.discount}
          >{`${props.item.liveDiscount}%`}</span>
        </div>
        <div className={classes.date}>{`${reservedDate()}`}</div>
      </div>
    </div>
  );
};

export default StoreLiveItem;

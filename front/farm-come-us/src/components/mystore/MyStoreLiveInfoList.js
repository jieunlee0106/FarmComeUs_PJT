import React from "react";

import classes from "./style/MyStoreLiveInfoList.module.scss";

import MyStoreInput from "./MyStoreInput";

const MyStoreLiveInfoList = (props) => {
  const discount = !props.newLiveInfo.liveDiscount
    ? 0
    : props.newLiveInfo.liveDiscount;

  const discountedPrice =
    Math.floor((props.newLiveInfo.itemPrice * (100 - discount)) / 100 / 10) *
    10;

  const getToday = () => {
    const date = new Date(
      new Date().getTime() - new Date().getTimezoneOffset() * 60000
    )
      .toISOString()
      .slice(0, -5);
    return date;
  };

  const getNextWeek = () => {
    const date = new Date(
      new Date().getTime() - new Date().getTimezoneOffset() * 60000 + 604800000 // 7일(1주일)
    )
      .toISOString()
      .slice(0, -5);
    return date;
  };

  const onLiveTimeChangeHandler = (name, value) => {
    if (name === "liveStartDate") {
      const [year, month, date] = value.split("-");

      props.onChange(
        "liveStart",
        value + " " + props.newLiveInfo.liveStartTime
      );
    } else if (name === "liveStartTime") {
      value += ":00";
      const [hour, minute] = value.split(":");

      props.onChange(
        "liveStart",
        props.newLiveInfo.liveStartDate + "T" + value
      );
    }
    props.onChange(name, value);
  };

  return (
    <ul className={`${classes.infoList} ${props.className}`}>
      <li className={classes.infoItem}>
        <MyStoreInput
          label="라이브 제목"
          type="text"
          onChange={props.onChange}
          name="liveTitle"
          value={props.newLiveInfo.liveTitle}
          required={true}
        />
      </li>
      <li className={classes.infoItem}>
        <MyStoreInput
          label="상품명"
          type="text"
          onChange={props.onChange}
          readOnly={true}
          value={props.newLiveInfo.itemName}
        />
      </li>
      <li className={classes.infoItem}>
        <MyStoreInput
          label="재고"
          name="itemStock"
          type="number"
          onChange={props.onChange}
          readOnly={true}
          value={props.newLiveInfo.itemStock}
        />
      </li>
      <li className={classes.infoItem}>
        <MyStoreInput
          label="할인율"
          type="number"
          min="0"
          max="100"
          onChange={props.onChange}
          name="liveDiscount"
          value={props.newLiveInfo.liveDiscount}
        />
      </li>
      <li className={classes.infoItem}>
        <MyStoreInput
          label="가격"
          type="number"
          name="livePrice"
          readOnly={true}
          value={discountedPrice}
        />
      </li>
      <li className={classes.infoItem}>
        <MyStoreInput
          label="상품 상세정보"
          type="text"
          name="itemDescription"
          onChange={props.onChange}
          value={props.newLiveInfo.itemDescription}
        />
      </li>
      <li className={classes.infoItem}>
        <MyStoreInput
          label="Live 날짜"
          type="date"
          onChange={onLiveTimeChangeHandler}
          name="liveStartDate"
          value={props.newLiveInfo.liveStartDate}
          required={true}
          min={getToday()}
          max={getNextWeek()}
        />
      </li>
      <li className={classes.infoItem}>
        <MyStoreInput
          label="Live 시작시간"
          type="time"
          onChange={onLiveTimeChangeHandler}
          name="liveStartTime"
          value={props.newLiveInfo.liveStartTime}
          required={true}
        />
      </li>
    </ul>
  );
};

export default MyStoreLiveInfoList;

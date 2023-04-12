import React, { useState, useEffect } from "react";
import classes from "./style/MyStoreInfoList.module.scss";

import MyStoreInput from "./MyStoreInput";
import DaumPostcodeEmbed from "react-daum-postcode";

const MyStoreCreateInfoList = (props) => {
  const [openModal, setOpenModal] = useState(false);

  const selectAddress = (data) => {
    props.onStoreInfoChange("storeStreetAddr", data.roadAddress);
    props.onStoreInfoChange("storeZipcode", data.zonecode);
    setOpenModal(!openModal);
  };

  const validityHandler = () => {};

  const onEditAddr = () => {
    setOpenModal(!openModal);
  };

  const storeValidation = () => {
    // props.onValidationCheck();
  };

  return (
    <ul className={classes.infoList}>
      <li className={classes.infoItem}>
        <MyStoreInput
          label="스토어 이름"
          value={props.info.storeName}
          readOnly={false}
          name="storeName"
          onChange={props.onStoreInfoChange}
          onBlur={storeValidation}
        />
        {props.storeNameIsValid !== undefined ? (
          <div className={classes.validationBox}>
            <span className={props.storeNameIsValid ? classes.valid : null}>
              {props.storeNameIsValid
                ? "사용 가능한 스토어명 입니다."
                : "이미 존재하는 스토어명 입니다."}
            </span>
          </div>
        ) : null}
      </li>
      <li className={classes.infoItem}>
        <MyStoreInput
          label="스토어 설명"
          value={props.info.storeDescription}
          readOnly={false}
          name="storeDescription"
          onChange={props.onStoreInfoChange}
        />
      </li>
      <li className={classes.infoItem}>
        <MyStoreInput
          label="대표 번호"
          value={props.info.storePhoneNumber}
          readOnly={false}
          name="storePhoneNumber"
          onChange={props.onStoreInfoChange}
        />
      </li>
      <li className={classes.infoItem}>
        <MyStoreInput
          label="기본 배송비"
          value={props.info.storeDeliveryCost}
          readOnly={false}
          name="storeDeliveryCost"
          onChange={props.onStoreInfoChange}
        />
      </li>
      <li className={classes.infoItem}>
        <MyStoreInput
          label="무료배송 기준 금액"
          value={props.info.storeDeliveryFree}
          readOnly={false}
          name="storeDeliveryFree"
          onChange={props.onStoreInfoChange}
        />
      </li>
      <li className={classes.infoItem}>
        <MyStoreInput
          label="농장 주소"
          value={props.info.storeStreetAddr}
          readOnly={true}
          onChange={props.onStoreInfoChange}
          name="storeStreetAddr"
          onFocus={onEditAddr}
        />
      </li>
      <li className={classes.infoItem}>
        <MyStoreInput
          label="우편번호"
          value={props.info.storeZipcode}
          readOnly={true}
          onChange={props.onStoreInfoChange}
          name="storeZipcode"
        />
      </li>
      <li className={classes.infoItem}>
        <MyStoreInput
          label="상세주소"
          value={props.info.storeDetailAddr}
          readOnly={false}
          onChange={props.onStoreInfoChange}
          name="storeDetailAddr"
        />
      </li>
      {openModal && (
        <div className={`${classes.modal} ${classes.openModal}`}>
          <DaumPostcodeEmbed
            onComplete={selectAddress} // 값을 선택할 경우 실행되는 이벤트
            autoClose={false} // 값을 선택할 경우 사용되는 DOM을 제거하여 자동 닫힘 설정
            defaultQuery="동서대로 98-39" // 팝업을 열때 기본적으로 입력되는 검색어. 대전캠주소 해놨음.
          />
        </div>
      )}
      {openModal && (
        <div
          className={classes.backdrop}
          onClick={() => {
            setOpenModal(false);
          }}
        />
      )}
    </ul>
  );
};

export default MyStoreCreateInfoList;

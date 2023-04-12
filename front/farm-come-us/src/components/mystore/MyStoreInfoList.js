import React, { useState, useEffect } from "react";

import classes from "./style/MyStoreInfoList.module.scss";

import MyStoreInput from "./MyStoreInput";
import DaumPostcodeEmbed from "react-daum-postcode";

const MyStoreInfoList = (props) => {
  const [openModal, setOpenModal] = useState(false);

  useEffect(() => {
    if (props.isEditting) {
      const myInput = document.querySelector("#myInput");
      myInput.focus();
    }
  }, [props.isEditting]);

  const selectAddress = (data) => {
    props.onChange("storeStreetAddr", data.roadAddress);
    props.onChange("storeZipcode", data.zonecode);
    setOpenModal((prev) => !prev);
  };

  const onEditAddr = () => {
    if (props.isEditting) {
      setOpenModal((prev) => !prev);
    }
  };

  return (
    <ul className={classes.infoList}>
      <li className={classes.infoItem}>
        <MyStoreInput
          label="스토어 이름"
          value={props.info.storeName}
          readOnly={!props.isEditting}
          onChange={props.onChange}
          name="storeName"
          id="myInput"
        />
      </li>
      <li className={classes.infoItem}>
        <MyStoreInput
          label="스토어 설명"
          value={props.info.storeDescription}
          readOnly={!props.isEditting}
          onChange={props.onChange}
          name="storeDescription"
        />
      </li>
      <li className={classes.infoItem}>
        <MyStoreInput
          label="대표 번호"
          value={props.info.storePhoneNumber}
          readOnly={!props.isEditting}
          onChange={props.onChange}
          name="storePhoneNumber"
        />
      </li>
      <li className={classes.infoItem}>
        <MyStoreInput
          label="농장 주소"
          value={props.info.storeStreetAddr}
          readOnly={!props.isEditting}
          onChange={props.onChange}
          name="storeStreetAddr"
          onFocus={onEditAddr}
        />
      </li>
      <li className={classes.infoItem}>
        <MyStoreInput
          label="우편번호"
          value={props.info.storeZipcode}
          readOnly={true}
          onChange={props.onChange}
          name="storeZipcode"
        />
      </li>
      <li className={classes.infoItem}>
        <MyStoreInput
          label="상세주소"
          value={props.info.storeDetailAddr}
          readOnly={!props.isEditting}
          onChange={props.onChange}
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

export default MyStoreInfoList;

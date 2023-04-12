import React, { useEffect } from "react";
import { useOutletContext } from "react-router-dom";

import classes from "./style/MyStoreInfo.module.scss";

import MyStoreContentTitle from "../../components/mystore/MyStoreContentTItle";
import MyStoreInfoList from "../../components/mystore/MyStoreInfoList";
import Button from "../../components/common/Button";

const MyStoreInfo = (props) => {
  const {
    storeInfo,
    isEditting,
    onChangeInfoHandler,
    editInfoHandler,
    cancelInfoEditHandler,
    toggleIsEditting,
  } = useOutletContext();

  return (
    <div className={classes.storeInfo}>
      <MyStoreContentTitle text="스토어 정보" />
      <form encType="multipart/form-data" onSubmit={editInfoHandler}>
        <MyStoreInfoList
          className={classes.infoList}
          info={storeInfo}
          isEditting={isEditting}
          onChange={onChangeInfoHandler}
        />
        {isEditting ? (
          <div className={classes.btnBox}>
            <Button className={classes.btnSubmit}>수정</Button>
            <Button
              className={classes.btnCancel}
              onClick={cancelInfoEditHandler}
            >
              취소
            </Button>
          </div>
        ) : (
          <Button className={classes.btnEditInfo} onClick={toggleIsEditting}>
            스토어 정보 수정
          </Button>
        )}
      </form>
    </div>
  );
};

export default MyStoreInfo;

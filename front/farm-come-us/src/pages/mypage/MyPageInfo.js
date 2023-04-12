import React, { useState } from "react";
import { useOutletContext } from "react-router-dom";

import classes from "./style/MyPageInfo.module.scss";

import MyPageContentTitle from "../../components/mypage/MyPageContentTItle";
import MyPageInfoList from "../../components/mypage/MyPageInfoList";
import Button from "../../components/common/Button";

const MyUserInfo = () => {
  const {
    userInfo,
    isEditting,
    toggleIsEditting,
    editInfoHandler,
    userInfoChangeHandler,
    cancelInfoEditHandler,
  } = useOutletContext();

  return (
    <div className={classes.userInfo}>
      <MyPageContentTitle text="가입 정보" />
      <form encType="multipart/form-data">
        <MyPageInfoList
          className={classes.infoList}
          info={userInfo}
          isEditting={isEditting}
          onChange={userInfoChangeHandler}
        />
        {isEditting ? (
          <div className={classes.btnBox}>
            <Button className={classes.btnSubmit} onClick={editInfoHandler}>
              수정
            </Button>
            <Button
              className={classes.btnCancel}
              onClick={cancelInfoEditHandler}
            >
              취소
            </Button>
          </div>
        ) : (
          <Button className={classes.btnEditInfo} onClick={toggleIsEditting}>
            사용자 정보 수정
          </Button>
        )}
      </form>
    </div>
  );
};

export default MyUserInfo;

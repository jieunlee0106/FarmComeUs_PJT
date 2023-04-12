import React, { useState, useEffect } from "react";
import { useNavigate, useLocation } from "react-router-dom";
import { fetchMyStoreDetail } from "../../utils/api/store-http";
import classes from "./style/MyStoreCreate.module.scss";

import { useDispatch } from "react-redux";
import MyStoreHeader from "../../components/mystore/MyStoreHeader";
import MyStoreContentTitle from "../../components/mystore/MyStoreContentTItle";
import MyStoreCreateInfoList from "../../components/mystore/MyStoreCreateInfoList";
import Button from "../../components/common/Button";
import { useSelector } from "react-redux";
import userSlice from "../../reduxStore/userSlice";
import axios from "axios";

const MyStoreCreate = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const dispatch = useDispatch();

  let userInfo = null;
  if (location.state) {
    userInfo = location.state;
  }

  const [storeInfo, setStoreInfo] = useState({
    memberId: "",
    storeDeliveryCost: "",
    storeDeliveryFree: "",
    storeDescription: "",
    storeDetailAddr: "",
    storeImg: "",
    storeName: "",
    storePhoneNumber: "",
    storeStreetAddr: "",
    storeZipcode: "",
  });
  const [storeNameIsValid, setStoreNameIsValid] = useState();

  useEffect(() => {
    if (!userInfo) {
      alert("잘못된 접근입니다.");
      navigate(-1);
      return;
    }

    fetchMyStoreDetail(userInfo.memberId)
      .then((res) => {
        navigate("/mystore");
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);

  const user = useSelector((state) => state.userSlice.value);

  const createStoreHandler = (e) => {
    async function fetchCreateStore(storeInfo, userInfo) {
      const formData = new FormData();
      formData.append("uploadFile", storeInfo.uploadFile);

      const data = {
        memberId: userInfo.memberId,
        storeDeliveryCost: storeInfo.storeDeliveryCost,
        storeDeliveryFree: storeInfo.storeDeliveryFree,
        storeDescription: storeInfo.storeDescription,
        storeDetailAddr: storeInfo.storeDetailAddr,
        storeImg: storeInfo.filename,
        storeName: storeInfo.storeName,
        storePhoneNumber: storeInfo.storePhoneNumber,
        storeStreetAddr: storeInfo.storeStreetAddr,
        storeZipcode: storeInfo.storeZipcode,
      };

      formData.append(
        "store",
        new Blob([JSON.stringify(data)], {
          type: "application/json",
        })
      );

      const config = {
        headers: {
          "Content-Type": "multipart/form-data",
          "Access-Control-Allow-Origin": "*",
          Authorization: { token: sessionStorage.getItem("accessToken") },
          token: sessionStorage.getItem("accessToken"),
        },
        withCredentials: false,
      };

      dispatch(userSlice.actions.saveStoreInfo());
      axios
        .post(
          process.env.REACT_APP_API_SERVER_URL + "/api/v1/store",
          formData,
          config
        )
        .then((res) => {
          console.log(res);
        })
        .catch((err) => console.log(err));
    }

    // 😀 실행부
    e.preventDefault();
    try {
      const res = fetchCreateStore(storeInfo, user);
      alert("스토어가 생성되었습니다.");
      // 스토어 생성하고, 내 스토어로 넘김.
    } catch (err) {
      console.log(err);
    }
    navigate("/mystore", { replace: true });
  };

  const storeInfoChangeHandler = (name, value) => {
    setStoreInfo((prev) => {
      return {
        ...prev,
        [name]: value,
      };
    });
  };

  const validationHandler = () => {
    if (storeInfo.storeName.length > 0) {
      alert("스토어 DB에서 조회해서 스토어 명 중복 체크");
      setStoreNameIsValid((prev) => !prev);
    } else {
      setStoreNameIsValid(undefined);
    }
  };

  return (
    <div className={classes.storeInfo}>
      <MyStoreHeader
        info={storeInfo}
        onStoreInfoChange={storeInfoChangeHandler}
        isEditting={true}
      />
      <MyStoreContentTitle text="스토어 정보 입력" />
      <form>
        <MyStoreCreateInfoList
          className={classes.infoList}
          info={storeInfo}
          onStoreInfoChange={storeInfoChangeHandler}
          onValidationCheck={validationHandler}
          storeNameIsValid={storeNameIsValid}
        />
        <Button className={classes.btnEditInfo} onClick={createStoreHandler}>
          마이 스토어 생성
        </Button>
      </form>
    </div>
  );
};

export default MyStoreCreate;

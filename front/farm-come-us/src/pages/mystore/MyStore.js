import React, { useEffect, useState } from "react";
import { useNavigate, Outlet } from "react-router-dom";
import { useSelector, useDispatch } from "react-redux";
import { fetchUserInfoWithAccessToken } from "../../utils/api/user-http";
import classes from "./style/MyStore.module.scss";
import {
  fetchUpdateStore,
  fetchStoreDetail,
  fetchMyStoreDetail,
} from "../../utils/api/store-http";
import axios from "axios";
import userSlice from "../../reduxStore/userSlice";
import MyStoreHeader from "../../components/mystore/MyStoreHeader";

const MyStore = () => {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const user = useSelector((state) => state.userSlice.value);
  let storeId = useSelector((state) => state.userSlice.store);

  const [isEditting, setIsEditting] = useState(false);
  const [userInfo, setUserInfo] = useState();
  const [storeInfo, setStoreInfo] = useState({
    storeId: "",
    storeName: "",
    storeDescription: "",
    storeStreetAddr: "",
    storeZipcode: "",
    storeDetailAddr: "",
    storePhoneNumber: "",
    storeImg: "",
    savePath: "",
    uploadFile: "",
  });

  const [initStoreInfo, setInitStoreInfo] = useState({
    storeId: "",
    storeName: "",
    storeDescription: "",
    storeStreetAddr: "",
    storeZipcode: "",
    storeDetailAddr: "",
    storePhoneNumber: "",
    storeImg: "",
    savePath: "",
    uploadFile: "",
  });

  useEffect(() => {
    const accessToken = sessionStorage.getItem("accessToken");
    if (!accessToken) {
      alert("로그인 후 사용가능한 페이지입니다.");
      navigate("/", { replace: true });
    }

    fetchUserInfoWithAccessToken()
      .then((res) => {
        const data = res.data;
        console.log(data);
        if (data.userInfo) {
          setUserInfo((prev) => {
            return {
              ...prev,
              ...data.userInfo,
            };
          });
        }
      })
      .catch((err) => {
        console.error(err);
        // alert("존재하지 않는 사용자입니다.");
        // navigate("/", { replace: true });
      });
  }, []);

  useEffect(() => {
    if (userInfo) {
      fetchMyStoreDetail(userInfo.memberId)
        .then((res) => {
          const data = res.data;

          setStoreInfo(() => {
            return { ...data };
          });
          setInitStoreInfo(() => {
            return { ...data };
          });
        })
        .catch((err) => {
          console.error(err);
        });
    }
  }, [userInfo]);

  const reLoadUserData = async () => {
    const accessToken = sessionStorage.getItem("accessToken");
    const userDataRes = await axios.get("/api/api/v1/member/", {
      headers: {
        "Content-Type": "application/json",
        "Access-Control-Allow-Origin": "*",
        token: accessToken,
      },
    });
    dispatch(userSlice.actions.login(userDataRes.data.userInfo));
    return userDataRes.data.userInfo.memberId;
  };

  const onChangeInfoHandler = (name, value) => {
    setStoreInfo((prev) => {
      return {
        ...prev,
        [name]: value,
      };
    });
  };

  const editInfoHandler = (e) => {
    e.preventDefault();

    const newStoreInfo = {
      memberId: storeInfo.memberId,
      storeDeliveryCost: storeInfo.storeDeliveryCost,
      storeDeliveryFree: storeInfo.storeDeliveryFree,
      storeDescription: storeInfo.storeDescription,
      storeDetailAddr: storeInfo.storeDetailAddr,
      storeId: storeInfo.storeId,
      storeImg: storeInfo.storeImg,
      storeName: storeInfo.storeName,
      storePhoneNumber: storeInfo.storePhoneNumber,
      storeStreetAddr: storeInfo.storeStreetAddr,
      storeZipcode: storeInfo.storeZipcode,
      uploadFile: storeInfo.uploadFile,
    };

    fetchUpdateStore(newStoreInfo)
      .then((res) => {
        console.log(res);
        alert("스토어 정보가 수정되었습니다.");
        fetchStoreDetail(newStoreInfo.storeId);
      })
      .catch((err) => {
        console.error(err);
        alert("스토어 정보 수정 중 오류가 발생했습니다.");
      });
    setIsEditting((prev) => !prev);
  };

  const cancelInfoEditHandler = () => {
    setStoreInfo((prev) => {
      return {
        storeId: initStoreInfo.storeId,
        storeName: initStoreInfo.storeName,
        storeDescription: initStoreInfo.storeDescription,
        storeStreetAddr: initStoreInfo.storeStreetAddr,
        storeZipcode: initStoreInfo.storeZipcode,
        storeDetailAddr: initStoreInfo.storeDetailAddr,
        storePhoneNumber: initStoreInfo.storePhoneNumber,
        storeImg: initStoreInfo.storeImg,
      };
    });

    setIsEditting((prev) => !prev);

    alert("수정이 취소되었습니다.");
  };

  const toggleIsEditting = (e) => {
    e.preventDefault();
    setIsEditting((prev) => !prev);
  };

  return (
    <div className={classes.mystore}>
      <MyStoreHeader
        info={storeInfo}
        isEditting={isEditting}
        onStoreInfoChange={onChangeInfoHandler}
      />
      <div className={classes.container}>
        <Outlet
          context={{
            storeInfo,
            isEditting,
            onChangeInfoHandler,
            editInfoHandler,
            cancelInfoEditHandler,
            toggleIsEditting,
          }}
        />
      </div>
    </div>
  );
};

export default MyStore;

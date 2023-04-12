import React, { useState, useEffect } from "react";
import { Outlet, useNavigate } from "react-router-dom";
import MyPageHeader from "../../components/mypage/MyPageHeader";
import { fetchUpdateUserInfo } from "../../utils/api/user-http";
import { fetchMyStoreDetail } from "../../utils/api/store-http";
import { useSelector } from "react-redux";
import userSlice from "../../reduxStore/userSlice";
import axios from "axios";

const MyPage = () => {
  const navigate = useNavigate();
  const [isEditting, setIsEditting] = useState(false);

  const user = useSelector((state) => {
    return state.userSlice.value;
  });

  const [userInfo, setUserInfo] = useState({
    ...user,
    imgSrc: "",
    uploadFile: "",
  });
  const [initUserInfo, setInitUserInfo] = useState({
    ...user,
    imgSrc: "",
    uploadFile: "",
  });
  const [hasMyStore, setHasMyStore] = useState(false);

  useEffect(() => {
    const accessToken = sessionStorage.getItem("accessToken");
    if (!user.memberId || !accessToken) {
      alert("로그인 후에 이용가능합니다.");
      navigate("/login", { replace: true });
    }

    axios
      .get(process.env.REACT_APP_API_SERVER_URL + "/api/v1/member/", {
        headers: {
          "Content-Type": "application/json",
          "Access-Control-Allow-Origin": "*",
          token: accessToken,
        },
      })
      .then((res) => {
        const { userInfo, userImage } = res.data;
        if (userImage) {
          userInfo["imgSrc"] = userImage.savedPath;
        }
        setUserInfo((prev) => {
          return { ...prev, ...userInfo };
        });
        userSlice.actions.login(userInfo);
        setInitUserInfo((prev) => {
          return { ...prev, ...userInfo };
        });
      })
      .catch((err) => {
        console.error(err);
      });

    fetchMyStoreDetail(userInfo.memberId)
      .then(() => {
        setHasMyStore(true);
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);

  const toggleIsEditting = (e) => {
    e.preventDefault();
    setIsEditting((prev) => !prev);
  };

  const editInfoHandler = (e) => {
    e.preventDefault();
    fetchUpdateUserInfo(userInfo)
      .then(() => {
        alert("사용자 정보가 수정되었습니다.");
      })
      .catch((err) => {
        console.error(err);
      });
    setIsEditting((prev) => !prev);
  };

  const userInfoChangeHandler = (name, value) => {
    setUserInfo((prev) => {
      return {
        ...prev,
        [name]: value,
      };
    });
  };

  const cancelInfoEditHandler = () => {
    setUserInfo((prev) => {
      return {
        ...initUserInfo,
      };
    });

    setIsEditting((prev) => !prev);

    alert("수정이 취소되었습니다.");
  };

  return (
    <div>
      <MyPageHeader
        profileImg={userInfo.imgSrc}
        userInfo={userInfo}
        isEditting={isEditting}
        userInfoChangeHandler={userInfoChangeHandler}
        hasMyStore={hasMyStore}
      />
      <Outlet
        context={{
          userInfo: userInfo,
          isEditting,
          toggleIsEditting,
          editInfoHandler,
          userInfoChangeHandler,
          cancelInfoEditHandler,
        }}
      />
    </div>
  );
};

export default MyPage;

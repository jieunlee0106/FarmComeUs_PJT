import React, { useState, useEffect } from "react";
import { useNavigate, useOutletContext } from "react-router-dom";
import { fetchStoreLive } from "../../utils/api/live-http";

import classes from "./style/MyStoreLive.module.scss";

import MyStoreContentTitle from "../../components/mystore/MyStoreContentTItle";
import MyStoreLiveList from "../../components/mystore/MyStoreLiveList";
import AddButton from "../../components/mystore/AddButton";
import AddLiveModal from "../../components/mystore/AddLiveModal";
import Loading from "../../components/common/Loading";

import useHttp from "../../hooks/use-http";

const MyStoreLive = () => {
  const { storeInfo } = useOutletContext();
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [sessionInfo, setSessionInfo] = useState({
    id: storeInfo.memberId,
    username: storeInfo.storeName,
  });

  const navigate = useNavigate();

  const {
    sendRequest: getStoreLive,
    status: status,
    data: liveList,
    error,
  } = useHttp(fetchStoreLive, true);

  useEffect(() => {
    if (storeInfo.storeId) {
      const params = {
        storeId: storeInfo.storeId,
        page: 0,
        size: 100,
      };
      getStoreLive(params);
    }
  }, [storeInfo.storeId, getStoreLive]);

  /* 기타 메서드 */
  const modalToggleHandler = () => {
    if (!isModalOpen) {
      document.body.style.overflow = "hidden";
    } else {
      document.body.style.overflow = "scroll";
    }

    setIsModalOpen((prev) => !prev);
  };

  const startLiveHandler = (liveInfo) => {
    const endDate = new Date(
      new Date(liveInfo.liveStart).getTime() + 1 * 60 * 60 * 1000
    );
    const today = new Date();

    // const isLiveEnd = today.getTime() >= endDate.getTime() ? true : false;
    const isLiveEnd = false;
    if (true) {
      const flag = window.confirm("라이브를 시작하시겠습니까?");
      if (!flag) return;

      setSessionInfo({
        id: liveInfo.liveId + "" + storeInfo.storeName,
        username: sessionInfo.username,
      });

      navigate("/broadcast", {
        state: {
          id: liveInfo.liveId + "",
          username: sessionInfo.username,
          liveInfo: liveInfo,
          isPublisher: true,
        },
      });
    }
    return;
  };

  return (
    <div className={classes.liveContainer}>
      <MyStoreContentTitle text="Live" />
      {status === "pending" && !liveList ? (
        <Loading className={classes.loading} />
      ) : (
        <MyStoreLiveList
          liveList={liveList}
          startLiveHandler={startLiveHandler}
        />
      )}

      <div className={classes.btnBox}>
        <AddButton className={classes.btnAdd} onClick={modalToggleHandler} />
      </div>
      {isModalOpen ? (
        <AddLiveModal
          title="Live 정보 입력"
          className={isModalOpen ? null : "close"}
          storeInfo={storeInfo}
          onToggleModal={modalToggleHandler}
          onFetchLive={getStoreLive}
        />
      ) : null}
    </div>
  );
};

export default MyStoreLive;

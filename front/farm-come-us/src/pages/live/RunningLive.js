import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { fetchLiveSession, fetchLiveSessions } from "../../utils/api/ov-http";
import { fetchRunningLiveList } from "../../utils/api/live-http";
import { fetchUserInfoWithAccessToken } from "../../utils/api/user-http";

import useHttp from "../../hooks/use-http";

import classes from "./style/RunningLive.module.scss";

import LiveList from "../../components/live/LiveList";
import Loading from "../../components/common/Loading";

const RunningLive = (props) => {
  const navigate = useNavigate();
  const [currPage, setCurrPage] = useState(0);

  const {
    sendRequest: getLiveSessions,
    status: ovStatus,
    data: sessionList,
    errorOv,
  } = useHttp(fetchLiveSessions, true);

  const {
    sendRequest: getRunningLiveInfo,
    status: rllStatus,
    data: runningLiveData,
    errorRll,
  } = useHttp(fetchRunningLiveList, true);

  useEffect(() => {
    getLiveSessions();
  }, [getLiveSessions]);

  useEffect(() => {
    getRunningLiveInfo(currPage);
  }, [getRunningLiveInfo]);

  console.log(sessionList, runningLiveData);
  const liveRoomEnterHandler = async (liveInfo) => {
    fetchLiveSession(liveInfo.liveId)
      .then(() => {
        const accessToken = sessionStorage.getItem("accessToken");
        if (!accessToken) {
          alert("로그인 후에 이용 가능한 서비스 입니다.");
          return;
        }

        fetchUserInfoWithAccessToken()
          .then((res) => {
            const userInfo = res.data.userInfo;

            navigate("/broadcast", {
              state: {
                id: liveInfo.liveId,
                username: userInfo.nickname,
                liveInfo: liveInfo,
                isPublisher: false,
              },
            });
          })
          .catch((err) => {
            console.error(err);
          });
      })
      .catch(() => {
        alert("진행 중인 라이브가 아닙니다.");
        getLiveSessions();
      });
  };

  return (
    <div className={classes.liveContainer}>
      {ovStatus === "pending" || rllStatus === "pending" ? (
        <Loading className={classes.loading} />
      ) : (
        <LiveList
          liveList={runningLiveData.liveOnList}
          hasNextPage={runningLiveData.hasNextPage}
          sessionList={sessionList}
          isLive={true}
          isPreview={props.isPreview}
          onClick={liveRoomEnterHandler}
        />
      )}
    </div>
  );
};

export default RunningLive;

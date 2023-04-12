import React, { useState, useEffect } from "react";
import useHttp from "../../hooks/use-http";
import { fetchScheduledLiveList } from "../../utils/api/live-http";

import classes from "./style/ScheduledLive.module.scss";

import LiveList from "../../components/live/LiveList";
import Loading from "../../components/common/Loading";

const ScheduledLive = (props) => {
  const [currPage, setCurrPage] = useState(0);

  const {
    sendRequest: getScheduledLiveInfo,
    status: sllStatus,
    data: scheduledLiveData,
    errorSll,
  } = useHttp(fetchScheduledLiveList, true);

  useEffect(() => {
    getScheduledLiveInfo(currPage);
  }, [getScheduledLiveInfo]);

  return (
    <div className={classes.liveContainer}>
      {sllStatus === "pending" ? (
        <Loading className={classes.loading} />
      ) : (
        <LiveList
          liveList={scheduledLiveData.liveOffList}
          hasNextPage={scheduledLiveData.hasNextPage}
          isLive={false}
          isPreview={props.isPreview}
        />
      )}
    </div>
  );
};

export default ScheduledLive;

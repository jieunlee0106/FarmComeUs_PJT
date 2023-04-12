import React from "react";

import classes from "./style/LiveList.module.scss";

import LiveItem from "./LiveItem";
import ReservedLiveItem from "./ReservedLiveItem";

const LivePreviewList = (props) => {
  let list = (
    <p className={classes.noData}>
      {props.isLive
        ? "진행 중인 라이브가 없습니다"
        : "등록된 라이브가 없습니다."}
    </p>
  );

  if (props.isLive) {
    if (
      // props.sessionList &&
      // props.sessionList.content.length > 0 &&
      props.liveList.length > 0
    ) {
      list = props.liveList.map((item, idx) => (
        <LiveItem key={idx} live={item} onClick={props.onClick} />
      ));
    }
  } else {
    list = props.liveList.map((item, idx) => (
      <ReservedLiveItem key={idx} live={item} />
    ));
  }

  return (
    <ul
      className={`${classes.liveList} ${
        props.isPreview ? classes.preview : null
      }`}
    >
      {list}
    </ul>
  );
};

export default LivePreviewList;

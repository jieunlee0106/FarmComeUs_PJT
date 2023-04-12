import React, { Fragment } from "react";

import classes from "./style/MyStoreLiveList.module.scss";

import MyStoreLiveItem from "./MyStoreLiveItem";

const MyStoreLiveList = (props) => {
  const content =
    !props.liveList || props.liveList.length === 0 ? (
      <div className={classes.noData}>등록된 Live가 없습니다.</div>
    ) : (
      <ul className={classes.liveList}>
        {props.liveList.map((live, idx) => (
          <li key={idx} onClick={(e) => props.startLiveHandler(live, e)}>
            <MyStoreLiveItem key={idx} item={live} />
          </li>
        ))}
      </ul>
    );
  return <Fragment>{content}</Fragment>;
};

export default MyStoreLiveList;

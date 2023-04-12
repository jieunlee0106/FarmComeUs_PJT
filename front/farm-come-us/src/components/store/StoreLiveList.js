import React, { Fragment } from "react";

import classes from "./style/StoreLiveList.module.scss";

import StoreLiveItem from "./StoreLiveItem";

const StoreLiveList = (props) => {
  const content =
    !props.liveList || props.liveList.length === 0 ? (
      <div className={classes.noData}>등록된 Live가 없습니다.</div>
    ) : (
      <ul className={classes.liveList}>
        {props.liveList.map((live, idx) => (
          <li key={idx}>
            <StoreLiveItem key={idx} item={live} />
          </li>
        ))}
      </ul>
    );
  return <Fragment>{content}</Fragment>;
};

export default StoreLiveList;

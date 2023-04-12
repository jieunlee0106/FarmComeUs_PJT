import React, { Fragment } from "react";

import classes from "./style/MyPageContentTitle.module.scss";

const MyStoreContentTitle = (props) => {
  return (
    <Fragment>
      <div className={`${classes.contentTitle} title`}>{props.text}</div>
      <div className={classes.horzLine}></div>
    </Fragment>
  );
};

export default MyStoreContentTitle;

import React from "react";

import classes from "./style/MyStoreHeaderInfo.module.scss";

const MyStoreHeaderInfo = (props) => {
  return (
    <div className={classes.infoContainer}>
      <div className={`${classes.storeName} title`}>{props.storeName}</div>
      <p className={classes.storeDesc}>{props.storeDescription}</p>
    </div>
  );
};

export default MyStoreHeaderInfo;

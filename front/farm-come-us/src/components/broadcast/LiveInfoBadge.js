import React from "react";

import classes from "./style/LiveInfoBadge.module.scss";

const LiveInfoBadge = (props) => {
  return (
    <div className={classes.badge}>
      <div className={classes.iconBox}>{props.icon}</div>
      <div className={classes.valueBox}>{props.value}</div>
    </div>
  );
};

export default LiveInfoBadge;

import React from "react";

import classes from "./style/Badge.module.scss";

const Badge = (props) => {
  return (
    <div className={`${classes.badge} ${props.className}`}>
      {props.children}
    </div>
  );
};

export default Badge;

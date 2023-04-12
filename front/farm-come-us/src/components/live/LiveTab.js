import React from "react";
import { NavLink } from "react-router-dom";

import classes from "./style/LiveTab.module.scss";

const LiveTab = () => {
  return (
    <div className={classes.LiveTab}>
      <NavLink
        className={({ isActive }) => (isActive ? classes.active : null)}
        to="running"
      >
        <span className={classes.highlight}>진행 중</span>
        <span>인 라이브</span>
      </NavLink>
      <NavLink
        className={({ isActive }) => (isActive ? classes.active : null)}
        to={`scheduled`}
      >
        <span className={classes.highlight}>라이브</span>
        <span>예정</span>
      </NavLink>
    </div>
  );
};

export default LiveTab;

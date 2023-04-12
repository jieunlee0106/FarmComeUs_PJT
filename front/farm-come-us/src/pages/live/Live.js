import React from "react";
import { Outlet } from "react-router-dom";

import classes from "./style/Live.module.scss";

import LiveTab from "../../components/live/LiveTab";

const Live = () => {
  return (
    <div className={classes.live}>
      <LiveTab />
      <div className={classes.container}>
        <Outlet />
      </div>
    </div>
  );
};

export default Live;

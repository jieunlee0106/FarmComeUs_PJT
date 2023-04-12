import React from "react";
import { NavLink } from "react-router-dom";

import classes from "./style/StoreTab.module.scss";

const StoreTab = (props) => {
  return (
    <div className={classes.LiveTab}>
      <NavLink
        className={({ isActive }) => (isActive ? classes.active : null)}
        to="live"
        state={{ storeId: props.storeId }}
      >
        <span>Store</span>
        <span className={classes.highlight}> live</span>
      </NavLink>
      <NavLink
        className={({ isActive }) => (isActive ? classes.active : null)}
        to="products"
        state={{ storeId: props.storeId }}
      >
        <span className={classes.highlight}>판매</span>
        <span> 상품</span>
      </NavLink>
    </div>
  );
};

export default StoreTab;

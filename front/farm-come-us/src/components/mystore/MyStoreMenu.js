import React from "react";
import { NavLink } from "react-router-dom";
import ImageButton from "../common/ImageButton";

import { MdStoreMallDirectory } from "react-icons/md";
import { MdOutlineLiveTv } from "react-icons/md";
import { MdLocalGroceryStore } from "react-icons/md";
import { MdCreditCard } from "react-icons/md";

import classes from "./style/MyStoreMenu.module.scss";

const MyStoreMenu = () => {
  return (
    <ul className={classes.menuList}>
      <li>
        <NavLink
          to="info"
          className={({ isActive }) => (isActive ? classes.active : null)}
        >
          <ImageButton
            className={classes.menuItem}
            icon={<MdStoreMallDirectory />}
            text="스토어 정보"
          />
        </NavLink>
      </li>
      <li>
        <NavLink
          to="live"
          className={({ isActive }) => (isActive ? classes.active : null)}
        >
          <ImageButton
            className={classes.menuItem}
            icon={<MdOutlineLiveTv />}
            text="Live"
          />
        </NavLink>
      </li>
      <li>
        <NavLink
          to="product"
          className={({ isActive }) => (isActive ? classes.active : null)}
        >
          <ImageButton
            className={classes.menuItem}
            icon={<MdLocalGroceryStore />}
            text="판매상품"
          />
        </NavLink>
      </li>
      <li>
        <NavLink
          to="receipt"
          className={({ isActive }) => (isActive ? classes.active : null)}
        >
          <ImageButton
            className={classes.menuItem}
            icon={<MdCreditCard />}
            text="판매내역"
          />
        </NavLink>
      </li>
    </ul>
  );
};

export default MyStoreMenu;

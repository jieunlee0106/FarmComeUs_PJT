import React from "react";
import { NavLink, useNavigate } from "react-router-dom";
import ImageButton from "../../components/common/ImageButton";

import { HiUser } from "react-icons/hi";
import { MdStoreMallDirectory } from "react-icons/md";
import { MdOutlineCreditCard } from "react-icons/md";
import { FaHeart } from "react-icons/fa";

import classes from "./style/MyPageMenu.module.scss";

const MyPageMenu = (props) => {
  const navigate = useNavigate();
  const redirectMyStoreHandler = (e) => {
    e.preventDefault();
    if (!window.confirm("마이스토어로 이동하시겠습니까?")) return;
    if (!props.hasMyStore) {
      alert("조회된 스토어가 없습니다. 생성 페이지로 이동합니다.");
      navigate("/mystorecreate", { state: { userInfo: props.userInfo } });
      return;
    }
    navigate("/mystore");
  };

  return (
    <ul className={classes.menuList}>
      <li>
        <NavLink
          to="info"
          className={({ isActive }) => (isActive ? classes.active : null)}
        >
          <ImageButton
            className={classes.menuItem}
            icon={<HiUser />}
            text="가입정보"
          />
        </NavLink>
      </li>
      <li>
        <NavLink
          to="receipts"
          className={({ isActive }) => (isActive ? classes.active : null)}
        >
          <ImageButton
            className={classes.menuItem}
            icon={<MdOutlineCreditCard />}
            text="주문내역"
          />
        </NavLink>
      </li>
      <li>
        <NavLink to="" onClick={redirectMyStoreHandler}>
          <ImageButton
            className={classes.menuItem}
            icon={<MdStoreMallDirectory />}
            text="마이스토어"
          />
        </NavLink>
      </li>
      <li>
        <NavLink
          to="likestores"
          className={({ isActive }) => (isActive ? classes.active : null)}
        >
          <ImageButton
            className={classes.menuItem}
            icon={<FaHeart />}
            text="관심스토어"
          />
        </NavLink>
      </li>
    </ul>
  );
};

export default MyPageMenu;

import React from "react";
import { useNavigate } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { MdMenu } from "react-icons/md";
import menuSlice from "../../reduxStore/menuSlice";
import classes from "./style/Header.module.scss";

const Header = (props) => {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const menu = useSelector((state) => state.menuSlice.isOpen);
  const logo_name = "farmcomeus_logo";

  const sendHomeHandler = () => {
    navigate("/");
  };

  return (
    <div className={`${classes.header} ${props.className}`}>
      <div className={`${classes.logo}`} onClick={sendHomeHandler}>
        <img
          className={classes.siteLogo}
          src={`${process.env.PUBLIC_URL}/img/${logo_name}.png`}
          alt="logo"
        />
      </div>
      <div className={classes.rightflexbox}>
        <div
          className={classes.menuBtnWrapper}
          onClick={() => {
            dispatch(menuSlice.actions.toggle());
          }}
        >
          <MdMenu className={classes.menuBtn} alt="메뉴버튼" />
        </div>
      </div>
    </div>
  );
};

export default Header;

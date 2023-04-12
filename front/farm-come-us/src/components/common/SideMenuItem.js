import React from "react";
import classes from "./style/SideMenuItem.module.scss";
import { useNavigate } from "react-router-dom";
import { useDispatch } from "react-redux";
import { useSelector } from "react-redux";
import menuSlice from "../../reduxStore/menuSlice";

const SideMenuItem = (props) => {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const user = useSelector((state) => state.userSlice.value.memberId);

  return (
    <div
      className={classes.sideMenuItem}
      onClick={() => {
        dispatch(menuSlice.actions.toggle());

        if ((props.linkTo === "/mypage" || props.linkTo === "/cart") && !user) {
          alert("로그인해주세요.");
          setTimeout(navigate("/login"), 300);
        } else {
          setTimeout(navigate(props.linkTo), 300);
        }
      }}
    >
      {/* 상위 컴포넌트에서 prop으로 주는 주소로 들어가게 했음. */}
      <div className={classes.flexWrapper}>
        <div className={classes.imgWrapper}>
          <img
            src={process.env.PUBLIC_URL + `/img/${props.imageName}.png`}
            alt={props.imageName}
            className={classes.iconImg}
          />
        </div>
        <div className={classes.itemTxt}>{props.itemName}</div>
      </div>
    </div>
  );
};

//
export default SideMenuItem;

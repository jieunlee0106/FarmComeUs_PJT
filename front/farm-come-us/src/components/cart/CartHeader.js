import React from "react";
import classes from "./style/CartHeader.module.scss";
import { MdOutlineArrowBackIos } from "react-icons/md";
import { useNavigate } from "react-router-dom";

const CartHeader = () => {
  const navigate = useNavigate();

  return (
    <div className={classes.container}>
      <MdOutlineArrowBackIos
        onClick={() => navigate(-1)}
      ></MdOutlineArrowBackIos>
      <div className={classes.title}>장바구니</div>
    </div>
  );
};

export default CartHeader;

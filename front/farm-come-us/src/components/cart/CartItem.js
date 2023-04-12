import React, { useState, useEffect } from "react";
import classes from "./style/CartItem.module.scss";
import { MdCheck } from "react-icons/md";

const CartItem = (props) => {
  const [check, onCheck] = useState(false);

  useEffect(() => {
    onCheck(props.allCheck);
  }, [props.allCheck]);

  const dealOnCheck = () => {
    if (check === false) {
      onCheck(!check);
      props.checkItem(props.item.productId, props.item.discountPrice);
    } else {
      onCheck(!check);
      props.uncheckItem(props.item.productId, props.item.discountPrice);
    }
  };

  return (
    <div className={classes.container}>
      <div className={classes.buttonSpace}>
        <div
          onClick={dealOnCheck}
          className={`${classes.button} ${check ? classes.active : null}`}
        >
          <MdCheck className={`${classes.checkIcon}`}></MdCheck>
        </div>
      </div>
      <div className={classes.imgSpace}>
        <img src="https://via.placeholder.com/300" alt="productImg" />
      </div>
      <div className={classes.itemDes}>
        <div className={classes.topSpace}>
          <div>{props.item.productName}</div>
        </div>
        <div className={classes.middleSpace}>
          <div className={classes.option}>
            옵션: {props.item.productOption}개
          </div>
          <div className={classes.secondSpace}>
            <div className={classes.discount}>{props.item.discount}%</div>
            <div className={classes.price}>{props.item.price}</div>
          </div>
        </div>
        <div className={classes.bottomSpace}>
          <div className={classes.discountPrice}>
            {props.item.discountPrice}
          </div>
        </div>
      </div>
    </div>
  );
};

export default CartItem;

import React from "react";
import classes from "./style/CartFooter.module.scss";
import { Link } from "react-router-dom";

const CartFooter = (props) => {
  return (
    <div className={classes.container}>
      <div className={classes.priceSpace}>
        전체 결제 금액: {props.resultPrice}
      </div>
      <Link
        to="/cart-payment"
        state={{
          itemList: props.DUMMY_CART_LIST,
          itemIdList: props.itemIdList,
          resultPrice: props.resultPrice,
        }}
        className={classes.buyButton}
      >
        구매하기
      </Link>
    </div>
  );
};

export default CartFooter;

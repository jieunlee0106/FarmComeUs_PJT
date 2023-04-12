import React, { useState, useEffect } from "react";
import classes from "./style/CartCard.module.scss";
import Card from "../common/Card";
import CartItem from "./CartItem";
import { MdCheck } from "react-icons/md";

const CartCard = (props) => {
  const [check, onCheck] = useState(false);
  const [styleCheck, onStyleCheck] = useState(false);
  const [itemIdList, setList] = useState([]);
  const [storePrice, setPrice] = useState(0);

  useEffect(() => {
    if (itemIdList.length === props.itemList.length) {
      onCheck(true);
      onStyleCheck(true);
    } else if (itemIdList.length !== props.itemList.length) {
      onStyleCheck(false);
    }
  }, [itemIdList.length, props.itemList.length]);

  const dealOnCheck = () => {
    if (check === false) {
      onCheck(!check);
      const arr = [];
      let price = 0;
      for (let i = 0; i < props.itemList.length; i++) {
        arr.push(props.itemList[i].productId);
        price = price + props.itemList[i].discountPrice;
      }
      let uncheckedArr = [];
      const uncheckedPrice = price - storePrice;
      if (itemIdList.length === 0) {
        uncheckedArr = arr;
      } else {
        for (let l = 0; l < itemIdList.length; l++) {
          uncheckedArr = arr.filter((id) => id !== itemIdList[l]);
        }
      }
      setList(arr);
      setPrice(price);
      props.getStoreProducts(uncheckedArr, uncheckedPrice);
    } else {
      onCheck(!check);
      props.popStoreProducts(itemIdList, storePrice);
      setList([]);
      setPrice(0);
    }
  };

  const plusSetList = (Id, price) => {
    setList([...itemIdList, Id]);
    setPrice(storePrice + price);
    props.getProduct(Id, price);
  };

  const minusSetList = (Id, price) => {
    setList(itemIdList.filter((id) => id !== Id));
    setPrice(storePrice - price);
    props.popProduct(Id, price);
  };

  let item = props.itemList.map((item) => (
    <CartItem
      key={item.productId}
      item={item}
      checkItem={plusSetList}
      uncheckItem={minusSetList}
      allCheck={check}
    ></CartItem>
  ));

  const shippingFee = 2500;

  return (
    <div className={classes.container}>
      <Card className={classes.card}>
        <div className={classes.cardHeader}>
          <div className={classes.buttonSpace}>
            <div
              onClick={dealOnCheck}
              className={`${classes.button} ${
                styleCheck ? classes.active : null
              }`}
            >
              <MdCheck className={`${classes.checkIcon}`}></MdCheck>
            </div>
          </div>
          <div className={classes.textArea}>
            <div className={classes.storeName}>
              {props.itemList[0].storeName}
            </div>
            <div className={classes.productAmount}>
              상품 {itemIdList.length}개
            </div>
          </div>
        </div>
        <div className={classes.cardBody}>{item}</div>
        <div className={classes.cardFooter}>
          <div className={classes.shippingFee}>배송비 {shippingFee}원</div>
          <div className={classes.resultPrice}>
            <div className={classes.text}>총 금액</div>
            <div className={classes.price}>{storePrice}원</div>
          </div>
        </div>
      </Card>
    </div>
  );
};

export default CartCard;

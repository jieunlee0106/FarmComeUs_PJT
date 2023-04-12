import React, { useEffect, useState } from "react";
import classes from "./style/CartList.module.scss";
import CartCard from "./CartCard";
import { MdCheck } from "react-icons/md";

const DUMMY_CART_LIST = [
  [
    {
      storeId: 1,
      storeName: "애플 인 더 청송",
      productId: 1,
      productName: "사과 1박스",
      productOption: 3,
      price: 60000,
      discount: 20,
      discountPrice: 48000,
    },
    {
      storeId: 1,
      storeName: "애플 인 더 청송",
      productId: 2,
      productName: "애플망고 1박스",
      productOption: 1,
      price: 30000,
      discount: 20,
      discountPrice: 24000,
    },
  ],
  [
    {
      storeId: 2,
      storeName: "페어 인 더 청송",
      productId: 3,
      productName: "배 1박스",
      productOption: 1,
      price: 20000,
      discount: 20,
      discountPrice: 16000,
    },
  ],
  [
    {
      storeId: 3,
      storeName: "퍼시먼 인 더 청송",
      productId: 4,
      productName: "감 1박스",
      productOption: 1,
      price: 20000,
      discount: 20,
      discountPrice: 16000,
    },
  ],
];

const CartList = (props) => {
  const [styleCheck, onStyleCheck] = useState(false);
  const [productIdList, setProductIdList] = useState([]);
  const [resultPrice, setResultPrice] = useState(0);

  useEffect(() => {}, [productIdList, resultPrice]);

  const dealOnCheck = () => {
    if (styleCheck === false) {
      onStyleCheck(!styleCheck);
    } else {
      onStyleCheck(!styleCheck);
    }
  };

  const getStoreProducts = (idList, price) => {
    setProductIdList(productIdList.concat(idList));
    setResultPrice(resultPrice + price);
  };

  const getProduct = (Id, price) => {
    setProductIdList([...productIdList, Id]);
    setResultPrice(resultPrice + price);
    props.plusSetList(Id, price);
  };

  const popStoreProducts = (idList, price) => {
    let tmpIdList = [];
    for (let i = 0; i < idList.length; i++) {
      tmpIdList = productIdList.filter((id) => id !== idList[i]);
    }
    setProductIdList(tmpIdList);
    setResultPrice(resultPrice - price);
  };

  const popProduct = (Id, price) => {
    setProductIdList(productIdList.filter((id) => id !== Id));
    setResultPrice(resultPrice - price);
    props.minusSetList(Id, price);
  };

  let list = DUMMY_CART_LIST.map((array, index) => (
    <CartCard
      key={index}
      itemList={array}
      getStoreProducts={getStoreProducts}
      getProduct={getProduct}
      popStoreProducts={popStoreProducts}
      popProduct={popProduct}
    ></CartCard>
  ));

  return (
    <div className={classes.container}>
      <div className={classes.subHeader}>
        <div className={classes.select}>
          <div
            onClick={dealOnCheck}
            className={`${classes.button} ${
              styleCheck ? classes.active : null
            }`}
          >
            <MdCheck className={`${classes.checkIcon}`}></MdCheck>
          </div>
        </div>
        <div className={classes.text}>
          <div>전체선택</div>
          <div>선택삭제</div>
        </div>
      </div>
      <div>{list}</div>
    </div>
  );
};

export default CartList;

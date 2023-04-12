import React, { Fragment } from "react";

import classes from "./style/MyStoreProductList.module.scss";

import MyStoreProductItem from "./MyStoreProductItem";

const MyStoreProductList = (props) => {
  const content =
    !props.products || props.products.length === 0 ? (
      <div className={classes.noData}>등록된 상품이 없습니다.</div>
    ) : (
      <ul className={classes.productList}>
        {props.products.map((product, idx) => (
          <li key={idx} onClick={(e) => props.onClick(product, e)}>
            <MyStoreProductItem key={idx} item={product} />
          </li>
        ))}
      </ul>
    );
  return <Fragment>{content}</Fragment>;
};

export default MyStoreProductList;

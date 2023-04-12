import React, { Fragment } from "react";

import classes from "./style/StoreProductList.module.scss";

import StoreProductItem from "./StoreProductItem";

const StoreProductList = (props) => {
  const content =
    !props.products || props.products.length === 0 ? (
      <div className={classes.noData}>등록된 상품이 없습니다.</div>
    ) : (
      <ul className={classes.productList}>
        {props.products.map((product, idx) => (
          <li key={idx} onClick={(e) => props.onClick(product, e)}>
            <StoreProductItem key={idx} item={product} />
          </li>
        ))}
      </ul>
    );
  return <Fragment>{content}</Fragment>;
};

export default StoreProductList;

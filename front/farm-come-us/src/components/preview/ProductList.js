import React from "react";

import classes from "./style/ProductList.module.scss";

import ProductItem from "./ProductItem";

const ProductList = (props) => {
  const content =
    !props.productList || props.productList.length === 0 ? (
      <p className={classes.noData}>등록된 상품이 없습니다.</p>
    ) : (
      props.productList.map((item, idx) => (
        <div className={classes.itemContainer} key={idx}>
          <ProductItem className={classes.itemCard} key={idx} product={item} />
        </div>
      ))
    );

  return (
    <ul
      className={`${classes.productList} ${props.isPreview ? "preview" : null}`}
    >
      {content}
    </ul>
  );
};

export default ProductList;

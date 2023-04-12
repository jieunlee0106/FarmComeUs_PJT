import React from "react";

import classes from "./style/ProductNoData.module.scss";

const ProductNoData = (props) => {
  return <div className={classes.noData}>{props.children}</div>;
};

export default ProductNoData;

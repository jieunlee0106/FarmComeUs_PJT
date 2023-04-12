import React from "react";
import classes from "./style/CategoryButton.module.scss";

const CategoryButton = (props) => {
  return (
    <div className={classes.container}>
      <img src={props.category_img} alt="productImg" />
      <div className={classes.text}>{props.category_name}</div>
    </div>
  );
};

export default CategoryButton;

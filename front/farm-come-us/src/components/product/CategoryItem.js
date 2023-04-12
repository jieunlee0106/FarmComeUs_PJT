import React from "react";
import classes from "./style/CategoryItem.module.scss";
import CategoryButton from "./CategoryButton";

const CategoryItem = (props) => {
  const sendName = () => {
    props.getName(props.category_name);
  };

  return (
    <div className={classes.container} onClick={sendName}>
      <CategoryButton
        className={classes.categoryItem}
        category_name={props.category_name}
        category_img={props.category_img}
      ></CategoryButton>
    </div>
  );
};

export default CategoryItem;

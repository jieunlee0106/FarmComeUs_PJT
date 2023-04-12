import React from "react";
import classes from "./style/SubCategoryItem.module.scss";

const SubCategoryItem = (props) => {
  const sendSubCategoryName = () => {
    props.getSubCategoryName(props.sub_category_name);
  };

  return (
    <div className={classes.container} onClick={sendSubCategoryName}>
      {props.sub_category_name}
    </div>
  );
};

export default SubCategoryItem;

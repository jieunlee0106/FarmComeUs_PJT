import React, { useState, useEffect } from "react";
import classes from "./style/SubCategory.module.scss";
import SubCategoryItem from "./SubCategoryItem";
import { categoryDetail } from "../../utils/api/category-http";

const SubCategory = (props) => {
  const [categoryDetailState, setCategoryDetailState] = useState([]);

  useEffect(() => {
    async function getcategoryDetail(categoryName) {
      try {
        const List = await categoryDetail(categoryName);
        setCategoryDetailState(List);
      } catch (err) {
        console.log(err);
      }
    }

    getcategoryDetail(props.categoryName);
  }, [props.categoryName]);

  const sendSubCategoryName = (name) => {
    props.getSubCategoryName(name);
  };

  if (categoryDetailState.length > 0) {
    let list = [];
    list = categoryDetailState.map((item) => (
      <SubCategoryItem
        sub_category_name={item.categoryName}
        key={item.categoryCode}
        sub_category_id={item.categoryCode}
        getSubCategoryName={sendSubCategoryName}
      ></SubCategoryItem>
    ));

    return (
      <div className="subCategoryNav">
        <div className={classes.container}>{list}</div>
      </div>
    );
  }
};

export default SubCategory;

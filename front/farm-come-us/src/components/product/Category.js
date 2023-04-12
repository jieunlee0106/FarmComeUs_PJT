import React, { useState, useEffect } from "react";
import CategoryItem from "./CategoryItem";
import classes from "./style/Category.module.scss";
import { categoryTitle } from "../../utils/api/category-http";

const Category = (props) => {
  const [categoryTitleState, setCategoryTitleState] = useState([]);

  useEffect(() => {
    async function getCategoryTitle() {
      try {
        const categoryList = await categoryTitle();
        setCategoryTitleState(categoryList);
        return categoryList;
      } catch (err) {
        console.log(err);
      }
    }

    getCategoryTitle();
  }, []);

  const sendName = (category_name) => {
    props.getCategoryName(category_name);
  };

  if (categoryTitleState.length > 0) {
    let list = [];
    list = categoryTitleState.map((item) => (
      <CategoryItem
        category_img={item.imageUrl}
        category_name={item.categoryName}
        category_id={item.categoryCode}
        key={item.categoryCode}
        getName={sendName}
      ></CategoryItem>
    ));

    return (
      <div className={classes.categoryNav}>
        <div className={classes.container}>{list}</div>
      </div>
    );
  }
};

export default Category;

import React, { useState } from "react";
import Category from "../../components/product/Category.js";
import SubCategory from "../../components/product/SubCategory.js";
import ProductList from "../../components/product/ProductList.js";

const Products = () => {
  const [categoryNameState, setCategoryName] = useState("전체");
  const [subCategoryNameState, setSubCategoryName] = useState("전체");

  const getCategoryName = (name) => {
    setCategoryName(name);

    if (name) {
      setSubCategoryName("전체");
    }
  };

  const getSubCategoryName = (name) => {
    setSubCategoryName(name);
  };

  return (
    <div>
      <Category getCategoryName={getCategoryName} />
      <SubCategory
        categoryName={categoryNameState}
        getSubCategoryName={getSubCategoryName}
      ></SubCategory>
      <ProductList
        category_name={categoryNameState}
        sub_category_name={subCategoryNameState}
      />
    </div>
  );
};

export default Products;

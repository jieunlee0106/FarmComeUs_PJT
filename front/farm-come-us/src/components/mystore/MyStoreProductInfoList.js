import React, { useState, useEffect } from "react";
import { categoryTitle, categoryDetail } from "../../utils/api/category-http";

import classes from "./style/MyStoreProductInfoList.module.scss";

import MyStoreInput from "./MyStoreInput";

const DUMMY_CATEGORY = [
  { id: 1, name: "뿌리채소" },
  { id: 2, name: "쌈채소" },
  { id: 3, name: "고구마/감자" },
  { id: 4, name: "줄기채소" },
];

const MyStoreProductInfoList = (props) => {
  const [categoryTitleList, setCategoryTitleList] = useState([]);
  const [categoryDetailList, setCategoryDetailList] = useState([]);

  useEffect(() => {
    categoryTitle().then((res) => {
      setCategoryTitleList((prev) => {
        return [...res];
      });
    });
  }, []);

  const categoryTitleChangeHandler = (e) => {
    const { name, value } = e.target;
    props.onChange(name, value);

    if ((e.target.value = "")) {
      setCategoryDetailList((prev) => {
        return [];
      });
      return;
    }

    categoryDetail(value).then((res) => {
      setCategoryDetailList((prev) => {
        return [...res];
      });
    });
  };

  const categoryDetailCahangeHandler = (e) => {
    const { name, value } = e.target;
    props.onChange(name, value);
  };

  return (
    <ul className={`${classes.productInfoList} ${props.className}`}>
      <li className={classes.infoItem}>
        <select
          name="categoryTitle"
          value={props.productInfo.categoryTitle}
          onChange={categoryTitleChangeHandler}
          required
        >
          <option value="">대분류</option>
          {categoryTitleList.map((item, idx) => (
            <option key={idx} value={item.categoryName}>
              {item.categoryName}
            </option>
          ))}
        </select>
      </li>
      <li className={classes.infoItem}>
        <select
          name="categoryDetail"
          value={props.productInfo.categoryDetail}
          onChange={categoryDetailCahangeHandler}
          required
        >
          <option value="">상세분류</option>
          {categoryDetailList.map((item, idx) => (
            <option key={idx} value={item.categoryName}>
              {item.categoryName}
            </option>
          ))}
        </select>
      </li>
      <li className={classes.infoItem}>
        <MyStoreInput
          label="상품명"
          type="text"
          name="itemName"
          value={props.productInfo.itemName}
          onChange={props.onChange}
          required={true}
        />
      </li>
      <li className={classes.infoItem}>
        <MyStoreInput
          label="가격"
          type="number"
          value={props.productInfo.itemPrice}
          name="itemPrice"
          onChange={props.onChange}
          required={true}
        />
      </li>
      <li className={classes.infoItem}>
        <MyStoreInput
          label="수량"
          type="number"
          value={props.productInfo.itemStock}
          name="itemStock"
          onChange={props.onChange}
          required={true}
        />
      </li>
      <li className={classes.infoItem}>
        <MyStoreInput
          label="상품 상세정보"
          type="text"
          value={props.productInfo.itemDescription}
          name="itemDescription"
          onChange={props.onChange}
          required={true}
        />
      </li>
      <li className={classes.infoItem}>
        <MyStoreInput
          label="상품 이미지"
          type="file"
          value={props.productInfo.imgSrc}
          name="imgSrc"
          onChange={props.onChange}
          required={true}
        />
      </li>
    </ul>
  );
};

export default MyStoreProductInfoList;

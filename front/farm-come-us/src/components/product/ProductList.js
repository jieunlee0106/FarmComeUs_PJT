import React, { useState, useEffect } from "react";
import useHttp from "../../hooks/use-http";

import classes from "./style/ProductList.module.scss";
import { fetchProductList } from "../../utils/api/product-http";

import ProductItem from "./ProductItem";
import ProductNoData from "./ProductNoData";
import Loading from "../../components/common/Loading";

const ProductList = (props) => {
  const categoryTitle = props.category_name;
  const categoryDetail = props.sub_category_name;

  const [productsList, setProductsList] = useState([]);
  const [hasNextPage, setHasNextPage] = useState(true);
  const [currPage, setCurrPage] = useState(0);

  const {
    sendRequest: getItemList,
    status: itemStatus,
    data: productsInfo,
    errorItem,
  } = useHttp(fetchProductList, true);

  useEffect(() => {
    if (productsInfo) {
      setProductsList(() => {
        return [...productsInfo.itemInfoList];
      });
      setHasNextPage(() => productsInfo.hasNextPage);
    }
  }, [productsInfo]);

  useEffect(() => {
    const data = {
      category: categoryTitle,
      itemName: "",
      subCategory: categoryDetail,
      page: currPage,
      size: 8,
    };
    getItemList(data);

    if (productsInfo) {
      setProductsList(() => {
        return [...productsInfo.itemInfoList];
      });
    }
  }, [categoryTitle, categoryDetail, getItemList]);

  return (
    <ul
      className={`${classes.productList} ${
        props.isPreview ? classes.preview : null
      }`}
    >
      {itemStatus === "pending" ? (
        <Loading className={classes.loading} />
      ) : productsList && productsList.length > 0 ? (
        productsList.map((item, idx) => <ProductItem key={idx} item={item} />)
      ) : (
        <ProductNoData>등록된 상품이 없습니다.</ProductNoData>
      )}
    </ul>
  );
};

export default ProductList;

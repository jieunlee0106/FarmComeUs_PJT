import React, { useState } from "react";
import { createProduct } from "../../utils/api/product-http";

import classes from "./style/AddLiveModal.module.scss";

import BottomUpModal from "../common/BottomUpModal";
import MyStoreProductInfoList from "./MyStoreProductInfoList";
import Button from "../common/Button";

const AddProductModal = (props) => {
  const [productInfo, setProductInfo] = useState({
    categoryTitle: "",
    categoryDetail: "",
    itemCreatedAt: "",
    itemDescription: "",
    itemDiscount: 0,
    itemId: 0,
    itemName: "",
    itemPrice: 0,
    itemStock: 0,
    storeId: 0,
    imgSrc: "",
    uploadFile: "",
    storeId: props.storeInfo.storeId,
  });

  const addProductSubmitHandler = (e) => {
    e.preventDefault();
    createProduct(productInfo)
      .then(() => {
        alert("상품이 등록되었습니다.");
        props.onFetchProducts(props.storeInfo.storeId, props.currPage);
      })
      .catch((err) => {
        console.error(err);
        alert("상품 등록 중 오류가 발생했습니다.");
      });
    props.onToggleModal();
  };

  const onInputChangeHandler = (name, value) => {
    setProductInfo((prev) => {
      return {
        ...prev,
        [name]: value,
      };
    });
  };

  return (
    <BottomUpModal
      className={`${props.className} ${classes.modal}`}
      title={props.title}
      onToggleModal={props.onToggleModal}
    >
      <form
        className={classes.form}
        onSubmit={addProductSubmitHandler}
        encType="multipart/form-data"
      >
        <MyStoreProductInfoList
          className={classes.infoList}
          productInfo={productInfo}
          onChange={onInputChangeHandler}
        />
        <Button className={classes.btnRegist}>상품 등록</Button>
      </form>
    </BottomUpModal>
  );
};

export default AddProductModal;

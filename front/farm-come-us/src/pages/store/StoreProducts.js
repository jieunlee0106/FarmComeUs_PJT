import React, { useEffect } from "react";
import { useOutletContext, useNavigate } from "react-router-dom";

import classes from "./style/StoreProducts.module.scss";
import StoreProductList from "../../components/store/StoreProductList";
import { fetchStoreProducts } from "../../utils/api/product-http";
import useHttp from "../../hooks/use-http";
import Loading from "../../components/common/Loading";

const StoreProducts = () => {
  const navigate = useNavigate();
  const [storeId] = useOutletContext();
  const {
    sendRequest: getStoreItems,
    status: status,
    data: storeItems,
    error,
  } = useHttp(fetchStoreProducts, true);

  useEffect(() => {
    getStoreItems(storeId, 0);
  }, [getStoreItems]);

  const showProductDetailHandler = (product) => {
    navigate("/product-detail", { state: { itemId: product.itemId } });
  };

  return (
    <div className={classes.container}>
      {status === "pending" ? (
        <Loading className={classes.loading} />
      ) : (
        <StoreProductList
          products={storeItems.itemInfoList}
          onClick={showProductDetailHandler}
        />
      )}
    </div>
  );
};

export default StoreProducts;

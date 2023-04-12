import React, { useState, useEffect } from "react";
import { Outlet, useLocation } from "react-router-dom";
import StoreHeader from "../../components/store/StoreHeader";
import StoreTab from "../../components/store/StoreTab";
import { fetchStoreDetail } from "../../utils/api/store-http";

const Store = () => {
  const location = useLocation();
  const [storeDetail, setStoreDetail] = useState();

  console.log(location.state.storeId);
  useEffect(() => {
    fetchStoreDetail(location.state.storeId)
      .then((res) => {
        setStoreDetail({ ...res.data });
      })
      .catch((err) => {
        console.error(err);
      });
  }, [location.state.storeId]);

  if (storeDetail) {
    return (
      <div>
        <StoreHeader storeInfo={storeDetail}></StoreHeader>
        <StoreTab storeId={location.state.storeId}></StoreTab>
        <div>
          <Outlet context={[location.state.storeId]} />
        </div>
      </div>
    );
  }
};

export default Store;

import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import useHttp from "../../hooks/use-http";

import classes from "./style/LivePreview.module.scss";

import PreviewHeader from "../../components/preview/PreviewHeader";
import ProductList from "../../components/preview/ProductList";
import Loading from "../../components/common/Loading";

import { MdOutlineLiveTv } from "react-icons/md";
import { MdOutlineCalendarToday } from "react-icons/md";
import { MdStorefront } from "react-icons/md";
import { fetchProductList } from "../../utils/api/product-http";
import ScheduledLive from "./ScheduledLive";
import RunningLive from "./RunningLive";

const LivePreview = () => {
  const navigate = useNavigate();

  const {
    sendRequest: getItemList,
    status: itemStatus,
    data: itemList,
    errorItem,
  } = useHttp(fetchProductList, true);

  useEffect(() => {
    const data = {
      category: "전체",
      itemName: "",
      subCategory: "전체",
      page: 0,
      size: 8,
    };
    getItemList(data);
  }, [getItemList]);

  const moveMorePageHandler = (uri) => {
    navigate(uri);
  };

  return (
    <div className={classes.container}>
      {/* 라이브 목록 */}
      <PreviewHeader
        className={`${classes.header} title`}
        moveMorePage={() => moveMorePageHandler("/livestore/running")}
        text="진행 중인 라이브"
        logo={<MdOutlineLiveTv className={`${classes.logo} ${classes.red}`} />}
      />
      <RunningLive isPreview={true} />
      <div className={classes.horzLine} />
      {/* 예정된 라이브 */}
      <PreviewHeader
        className={`${classes.header} title`}
        moveMorePage={() => moveMorePageHandler("/livestore/scheduled")}
        text="라이브 예정"
        logo={<MdOutlineCalendarToday className={`${classes.logo}`} />}
      />
      <ScheduledLive isPreview={true} />
      <div className={classes.horzLine} />
      {/* 상품 최신순 */}
      <PreviewHeader
        className={`${classes.header} title`}
        moveMorePage={() => moveMorePageHandler("/products")}
        text="상품 최신순"
        logo={<MdStorefront className={`${classes.logo}`} />}
      />
      {itemStatus === "pending" ? (
        <Loading className={classes.loading} />
      ) : (
        <ProductList productList={itemList.itemInfoList} isPreview={true} />
      )}
    </div>
  );
};

export default LivePreview;

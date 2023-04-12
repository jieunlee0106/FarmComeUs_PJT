import React, { useEffect } from "react";
import { useOutletContext } from "react-router-dom";
import classes from "./style/StoreProducts.module.scss";
import { fetchStoreLive } from "../../utils/api/live-http";

import useHttp from "../../hooks/use-http";

import Loading from "../../components/common/Loading";
import StoreLiveList from "../../components/store/StoreLiveList";

const StoreLive = () => {
  const { storeId } = useOutletContext();

  const {
    sendRequest: getStoreLive,
    status: status,
    data: storeLive,
    error,
  } = useHttp(fetchStoreLive, true);

  useEffect(() => {
    const params = {
      storeId,
      page: 0,
      size: 100,
    };

    getStoreLive(params);
  }, [getStoreLive]);

  return (
    <div className={classes.container}>
      {status === "pending" ? (
        <Loading className={classes.loading} />
      ) : (
        <StoreLiveList liveList={storeLive} />
      )}
    </div>
  );
};

export default StoreLive;

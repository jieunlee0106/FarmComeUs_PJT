import { Fragment, React, useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";

import classes from "./style/BroadCast.module.scss";
import OvContainer from "../utils/OV/OvContainer";

import { productDetail } from "../utils/api/product-http";

const BroadCast = () => {
  const width = 1280;
  const height = 720;

  const navigate = useNavigate();
  const { state } = useLocation();
  console.log(state);

  const [itemInfo, setItemInfo] = useState();

  useEffect(() => {
    if (!state) {
      alert("존재하지 않는 방송입니다.");
      navigate(-1);
      return;
    }

    productDetail(state.liveInfo.itemId)
      .then((res) => {
        setItemInfo(res.item);
      })
      .catch((err) => {
        console.error(err);
      });
  }, []);
  console.log(state);

  return (
    <Fragment>
      {state ? (
        <OvContainer
          width={width}
          height={height}
          sessionId={`session${state.id}`}
          username={state.username}
          liveInfo={state.liveInfo}
          itemInfo={itemInfo}
          isPublisher={state.isPublisher}
          className={classes.ovContainer}
        />
      ) : null}
    </Fragment>
  );
};

export default BroadCast;

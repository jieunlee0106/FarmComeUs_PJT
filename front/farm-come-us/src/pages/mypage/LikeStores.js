import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { useSelector } from "react-redux";

import axios from "axios";
import MyReceiptItem from "../../components/mypage/MyReceiptItem";
import StoreLikeItem from "../../components/mypage/StoreLikeItem";
import { fetchFavStores } from "../../utils/api/store-http";
import classes from "./style/MyReceipts.module.scss";

const LikeStores = (props) => {
  const navigate = useNavigate();
  const [likeStoresData, setLikeStoresData] = useState([]);
  const [currPage, setCurrPage] = useState(0);
  const memberId = useSelector((state) => {
    return state.userSlice.value.memberId;
  });

  useEffect(() => {
    alert("준비 중인 기능입니다.");
    navigate(-1);

    const params = {
      page: currPage,
      size: 10,
    };
    axios
      .get(
        `${process.env.REACT_APP_API_SERVER_URL}/api/v1/storelikes/${memberId}`,
        {
          params,
        }
      )
      .then((res) => {
        const data = res.data;
        if (data) {
          if (data.hasNextPage) {
            setCurrPage((prev) => prev + 1);
          }
          if (data.storeLikes) {
            setLikeStoresData((prev) => [...prev, data.storeLikes]);
          }
        }
      })
      .catch((err) => {
        console.error(err);
      });
  }, []);
  // const res = fetchFavStores(memberId);

  let list = <span className={classes.noItem}>찜한 스토어가 없습니다.</span>;

  // list = <MyReceiptItem />;
  if (likeStoresData) {
    list = likeStoresData.map((item, idx) => (
      <StoreLikeItem
        key={idx}
        id={item.id}
        img_address={item.storeImg}
        title={item.storeName}
        address={item.storeStreetAddr}
        representative={item.memberName}
        memberId={memberId}
        storeId={item.storeId}
      />
    ));
  }

  return (
    <div>
      <div className={classes.header}>찜한 스토어</div>
      <hr />
      <div
        className={`${classes.flexbox} ${classes.mt} ${classes.screen} ${
          list.length ? "" : classes.centerAlignWrapper
        }`}
      >
        {list}
      </div>
    </div>
  );
};

export default LikeStores;

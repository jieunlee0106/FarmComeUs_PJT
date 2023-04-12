import { useState, useEffect } from "react";
import classes from "./style/StoreLikeItem.module.scss";
import { AiFillHeart, AiOutlineHeart } from "react-icons/ai";
import { deleteFavStore } from "../../utils/api/store-http";
import axios from "axios";

const StoreLikeItem = (props) => {
  const [isLike, setIsLike] = useState(true);
  // const data = {
  //   img_address: "/img/cabbage.png",
  //   isLike: true,
  //   title: "고랭강원농장",
  //   address: "강원도 평창군 봉평면 무이리 23-12",
  //   representative: "강남자",
  //   created: "2020. 05. 10",
  // };

  useEffect(() => {
    return () => {
      if (isLike !== props.isLike) {
        deleteFavStore(props.id, props.memberId, props.storeId);
      }
    };
  }, []);

  return (
    <div className={`${classes.card} ${classes.mt}`}>
      <div className={`${classes.imgWrapper}`}>
        <img className={classes.img} src={props.storeImg} />
      </div>
      <div className={`${classes.colflexbox} ${classes.mt}`}>
        <div
          className={classes.heartsize}
          onClick={() => {
            console.log(props.title);
            setIsLike(!isLike);
          }}
        >
          {isLike ? (
            <AiFillHeart className={classes.filledheart} />
          ) : (
            <AiOutlineHeart className={classes.emptyheart} />
          )}
        </div>
        <div className={classes.title}>{props.title}</div>

        <div className={classes.rowflexbox}>
          <div
            className={`${classes.description} ${classes.fullwidth} `}
          >{`주소${" : "}`}</div>
          <div className={classes.description}>{props.address}</div>
        </div>
        <div
          className={classes.description}
        >{`대표자 : ${props.representative}`}</div>
        {/* <div className={classes.description}>{`등록일 : ${props.created}`}</div> */}
      </div>
    </div>
  );
};

export default StoreLikeItem;

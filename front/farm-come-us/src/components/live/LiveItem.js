import React from "react";

import classes from "./style/LiveItem.module.scss";

import Card from "../common/Card";
import Badge from "../common/Badge";
import CardCaption from "../common/CardCaption";

const LiveItem = (props) => {
  const str_price = props.live.liveItemPrice;
  const convertedPrice = str_price
    ? str_price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")
    : null;

  return (
    <li className={classes.liveItem}>
      <Card
        className={classes.liveCard}
        onClick={() => props.onClick(props.live)}
      >
        <figure>
          <img src={props.live.savedPath} alt="livePreview" />
          <figcaption className={classes.badge}>
            <Badge className={classes.liveBadge}>Live</Badge>
          </figcaption>
          <CardCaption className={classes.liveCaption}>
            <div className={classes.storeInfo}>
              <p>
                {props.live.storeName}
                <span className={classes.subfix}> 스토어</span>
              </p>
            </div>
            <div className={classes.liveTitle}>
              <span className={classes.text}>{props.live.liveTitle}</span>
            </div>
            <div className={classes.productInfo}>
              <span className={classes.discount}>
                {props.live.liveDiscount}%
              </span>
              <span>{convertedPrice} 원</span>
            </div>
          </CardCaption>
        </figure>
      </Card>
    </li>
  );
};

export default LiveItem;

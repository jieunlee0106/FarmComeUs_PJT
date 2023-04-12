import React from "react";
import classes from "./style/PreviewHeader.module.scss";

const PreviewHeader = (props) => {
  return (
    <div className={`${props.className} ${classes.header}`}>
      <div className={classes.titleBox}>
        {props.logo}
        <span className={`${classes.title}`}>{props.text}</span>
      </div>
      <div className={classes.more} onClick={props.moveMorePage}>
        더보기
      </div>
    </div>
  );
};

export default PreviewHeader;

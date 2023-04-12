import React from "react";

import classes from "./style/LiveHeader.module.scss";

import { MdOutlineClose } from "react-icons/md";
import { MdVideocam } from "react-icons/md";
import { MdFlipCameraIos } from "react-icons/md";
import { BsMicMuteFill } from "react-icons/bs";
import { BsMicFill } from "react-icons/bs";

const LiveHeader = (props) => {
  return (
    <div className={props.className}>
      <div className={classes.titleContainer}>
        <MdVideocam className={`${classes.icon} ${classes.red}`} />
        <p className={classes.title}>
          <span>라이브 </span>
          <span className={classes.red}>송출</span>
          <span> 중</span>
        </p>
      </div>
      {!props.isPublisher ? (
        <MdOutlineClose
          className={classes.closeBtn}
          onClick={props.onLiveLeave}
        />
      ) : props.isMute ? (
        <div className={classes.btnList}>
          <MdFlipCameraIos
            className={classes.btn}
            onClick={props.onCameraSwitch}
          />
          <BsMicMuteFill
            className={`${classes.btn} ${classes.red}`}
            onClick={props.onToggleMute}
          />
        </div>
      ) : (
        <div className={classes.btnList}>
          <MdFlipCameraIos
            className={classes.btn}
            onClick={props.onCameraSwitch}
          />
          <BsMicFill className={classes.btn} onClick={props.onToggleMute} />
        </div>
      )}
    </div>
  );
};

export default LiveHeader;

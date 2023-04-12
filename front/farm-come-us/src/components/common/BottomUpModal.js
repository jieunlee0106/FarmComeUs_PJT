import React from "react";

import classes from "./style/BottomUpModal.module.scss";

const BottomUpModal = (props) => {
  return (
    <div className={`${props.className} ${classes.container}`}>
      <div className={classes.backdrop} onClick={props.onToggleModal}></div>
      <div className={classes.modal}>
        <div className={classes.modalHeader}>
          <div className={classes.btnClose} onClick={props.onToggleModal}></div>
        </div>
        <div className={classes.modalContent}>
          <div className={classes.title}>
            <span>{props.title}</span>
            <div className={classes.horzLine}></div>
          </div>
          <div>{props.children}</div>
        </div>
      </div>
    </div>
  );
};

export default BottomUpModal;

import React from "react";
import classes from "./style/ImageButton.module.scss";

const ImageButton = (props) => {
  return (
    <div className={classes.container}>
      <div className={classes.icon}>{props.icon}</div>
      <div className={classes.text}>{props.text}</div>
    </div>
  );
};

export default ImageButton;

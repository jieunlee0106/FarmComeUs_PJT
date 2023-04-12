import React from "react";

import classes from "./style/CardCaption.module.scss";

const CardCaption = (props) => {
  return (
    <figcaption className={`${classes.caption} ${props.className}`}>
      {props.children}
    </figcaption>
  );
};

export default CardCaption;

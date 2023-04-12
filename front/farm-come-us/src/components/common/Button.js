import React from "react";

import classes from "./style/Button.module.scss";

const Button = (props) => {
  return (
    <button
      className={`${classes.button} ${props.className}`}
      onClick={props.onClick}
      onSubmit={props.onSubmit}
    >
      {props.children}
    </button>
  );
};

export default Button;

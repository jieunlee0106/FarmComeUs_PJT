import React from "react";

import { MdAddCircle } from "react-icons/md";

import classes from "./style/AddButton.module.scss";

const AddButton = (props) => {
  return (
    <MdAddCircle
      className={`${props.className} ${classes.btnAdd}`}
      onClick={props.onClick}
    />
  );
};

export default AddButton;

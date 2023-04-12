import React from "react";

import classes from "./style/LeaveButton.module.scss";

import Button from "../common/Button";

const LeaveButton = (props) => {
  return (
    <Button className={classes.btnLeave} onClick={props.onClick}>
      라이브 종료
    </Button>
  );
};

export default LeaveButton;

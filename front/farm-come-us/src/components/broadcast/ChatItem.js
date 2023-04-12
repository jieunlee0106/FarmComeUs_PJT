import React from "react";

import classes from "./style/ChatItem.module.scss";

const ChatItem = (props) => {
  return (
    <li className={classes.chatItem}>
      <p>
        <span>{props.sender}</span>
        {props.msg}
      </p>
    </li>
  );
};

export default ChatItem;

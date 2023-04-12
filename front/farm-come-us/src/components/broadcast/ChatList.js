import React, { Fragment } from "react";

import classes from "./style/ChatList.module.scss";

import ChatItem from "./ChatItem";

const ChatList = (props) => {
  return (
    <Fragment>
      <ul className={classes.chatList}>
        {props.chatList.map((chatItem, idx) => (
          <ChatItem key={idx} sender={chatItem.sender} msg={chatItem.msg} />
        ))}
      </ul>
    </Fragment>
  );
};

export default ChatList;

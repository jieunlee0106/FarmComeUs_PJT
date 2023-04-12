import React from "react";

import classes from "./style/LiveChat.module.scss";

import ChatList from "./ChatList";
import ChatForm from "./ChatForm";

const LiveChat = (props) => {
  return (
    <div className={classes.chatContainer}>
      <ChatList chatList={props.chatList} />
      {!props.isPublisher ? (
        <ChatForm
          onTextChange={props.onTextMsgChangeHandler}
          onSubmit={props.onSubmit}
          msg={props.msg}
        />
      ) : null}
    </div>
  );
};

export default LiveChat;

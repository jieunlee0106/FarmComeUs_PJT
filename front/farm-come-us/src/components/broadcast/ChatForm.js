import React from "react";

import classes from "./style/ChatForm.module.scss";
import { MdSend } from "react-icons/md";

const ChatForm = (props) => {
  return (
    // <form onSubmit={props.onSubmit}>
    <form onSubmit={props.onSubmit} className={classes.chatForm}>
      <input
        className={classes.chatInput}
        type="text"
        onChange={props.onTextChange}
        value={props.msg}
      />
      <button type="submit">
        <MdSend />
      </button>
    </form>
  );
};

export default ChatForm;

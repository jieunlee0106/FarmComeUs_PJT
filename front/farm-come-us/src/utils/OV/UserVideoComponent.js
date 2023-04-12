import React from "react";
import OpenViduVideoComponent from "./OvVideo";
import "./UserVideo.css";

const UserVideoComponent = (props) => {
  const getNicknameTag = () => {
    // Gets the nickName of the user
    return JSON.parse(props.streamManager.stream.connection.data).clientData;
  };

  return (
    <div className={props.className}>
      {props.streamManager !== undefined ? (
        <OpenViduVideoComponent streamManager={props.streamManager} />
      ) : null}
    </div>
  );
};

export default UserVideoComponent;

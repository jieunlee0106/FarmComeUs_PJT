import axios from "axios";

export function fetchLiveSessions() {
  return axios
    .get(`${process.env.REACT_APP_OPENVIDU_SERVER}/openvidu/api/sessions`, {
      headers: {
        Authorization:
          "Basic " +
          btoa("OPENVIDUAPP:" + process.env.REACT_APP_OPENVIDU_SECRET),
        "Content-Type": "application/json",
        "Access-Control-Allow-Origin": "*",
      },
    })
    .then((res) => {
      return res.data;
    })
    .catch((err) => {
      console.error(err);
    });
}

export async function fetchLiveSession(sessionId) {
  try {
    const { data } = await axios.get(
      `${process.env.REACT_APP_OPENVIDU_SERVER}/openvidu/api/sessions/${sessionId}`,
      {
        headers: {
          Authorization:
            "Basic " +
            btoa("OPENVIDUAPP:" + process.env.REACT_APP_OPENVIDU_SECRET),
          "Content-Type": "application/json",
          "Access-Control-Allow-Origin": "*",
        },
      }
    );
    return data;
  } catch (err) {
    if (err.response.status === 404) {
      return null;
    } else {
      console.error(err);
    }
  }
  return null;
}

export async function fetchCloseSession(sessionId) {
  try {
    const response = await axios.delete(
      `${process.env.REACT_APP_OPENVIDU_SERVER}/openvidu/api/sessions/${sessionId}`,
      {
        headers: {
          Authorization:
            "Basic " +
            btoa("OPENVIDUAPP:" + process.env.REACT_APP_OPENVIDU_SECRET),
          "Content-Type": "application/json",
          "Access-Control-Allow-Origin": "*",
        },
      }
    );
    return response;
  } catch (err) {
    if (err.response.status === 404) {
      console.error(err);
    } else {
      console.error(err);
    }
  }
}

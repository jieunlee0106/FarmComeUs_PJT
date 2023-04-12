import axios from "axios";

const LIVE_API_URL = `${process.env.REACT_APP_API_SERVER_URL}/api/v1/live`;

/* 라이브 등록 */
export async function fetchAddLive(liveInfo) {
  const data = { ...liveInfo };
  return axios.post(`${LIVE_API_URL}`, data);
}

/* 진행 중인 라이브 목록 조회 */
export async function fetchRunningLiveList(page) {
  const params = {
    liveTitle: "",
    page: page,
    size: 8,
  };
  return await axios
    .get(`${LIVE_API_URL}/list/on`, { params })
    .then((res) => {
      return res.data;
    })
    .catch((err) => {
      console.error(err);
    });
}

/* 예약된 라이브 목록 조회 */
export async function fetchScheduledLiveList(page) {
  const params = {
    liveTitle: "",
    page: page,
    size: 8,
  };

  return await axios
    .get(`${process.env.REACT_APP_API_SERVER_URL}/api/v1/live/list/off`, {
      params,
    })
    .then((res) => {
      return res.data;
    })
    .catch((err) => {
      console.error(err);
    });
}

/* 스토어 라이브 목록 조회 */
export async function fetchStoreLive(data) {
  const params = {
    storeId: data.storeId,
    page: data.page,
    size: data.size,
  };

  return axios
    .get(`${process.env.REACT_APP_API_SERVER_URL}/api/v1/live/store`, {
      params,
    })
    .then((res) => {
      return res.data.storeLiveList;
    })
    .catch((err) => {
      console.error(err);
    });
}

/* 라이브 상세 조회 */
export async function getLiveDetail(id) {
  try {
  } catch (err) {
    console.error(err);
  }

  return null;
}

/* 라이브 수정 */
export async function updateLive(liveInfo) {
  try {
  } catch (err) {
    console.error(err);
  }
}

/* 라이브 삭제 */
export async function deleteLive(id) {
  try {
  } catch (err) {
    console.error(err);
  }
}

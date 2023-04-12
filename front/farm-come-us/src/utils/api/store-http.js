import axios from "axios";

const STORE_API_URL = `${process.env.REACT_APP_API_SERVER_URL}/api/v1/store`;
const STORE_LIKE_API_URL = `${process.env.REACT_APP_API_SERVER_URL}/storelikes`;

/* 스토어 생성 */
export async function fetchCreateStore(storeInfo, userInfo) {
  const formData = new FormData();
  formData.append("uploadFile", storeInfo.uploadFile);

  const data = {
    memberId: userInfo.memberId,
    storeDeliveryCost: storeInfo.deliveryCost,
    storeDeliveryFree: storeInfo.deliveryFree,
    storeDescription: storeInfo.desc,
    storeDetailAddr: storeInfo.detailAddr,
    storeImg: storeInfo.imgSrc,
    storeName: storeInfo.storeName,
    storePhoneNumber: storeInfo.phoneNumber,
    storeStreetAddr: storeInfo.streetAddr,
    storeZipcode: storeInfo.zipcode,
  };

  formData.append(
    "store",
    new Blob([JSON.stringify(data)], {
      type: "application/json",
    })
  );

  const config = {
    headers: {
      "Content-Type": "multipart/form-data",
      "Access-Control-Allow-Origin": "*",
      Authorization: { token: sessionStorage.getItem("accessToken") },
      token: sessionStorage.getItem("accessToken"),
    },
    withCredentials: false,
  };
  try {
    return axios.post("/api/api/v1/store", formData, config);
  } catch (err) {
    console.err(err);
  }
}

/* 스토어 상세 조회 */
export async function fetchStoreDetail(storeId) {
  const id = storeId;
  try {
    const response = axios.get(
      `${process.env.REACT_APP_API_SERVER_URL}/api/v1/store/${id}`,
      {
        // params: {
        //   storeId,
        // },
      }
    );

    console.log(response);
    return response;
  } catch (err) {
    console.err(err);
  }
}

/* 사용자 아이디로 조회 */
export async function fetchMyStoreDetail(id) {
  return axios.get(
    `${process.env.REACT_APP_API_SERVER_URL}/api/v1/store/mystore/${id}`
  );
}

/* 스토어 정보 수정 */
export async function fetchUpdateStore(storeInfo) {
  const formData = new FormData();
  formData.append("uploadFile", storeInfo.uploadFile);

  const data = {
    storeId: storeInfo.storeId,
    storeDeliveryCost: storeInfo.deliveryCost,
    storeDeliveryFree: storeInfo.deliveryFree,
    storeDescription: storeInfo.storeDescription,
    storeDetailAddr: storeInfo.storeDetailAddr,
    storeImg: storeInfo.storeImg,
    storeName: storeInfo.storeName,
    storePhoneNumber: storeInfo.storePhoneNumber,
    storeStreetAddr: storeInfo.storeStreetAddr,
    storeZipcode: storeInfo.storeZipcode,
  };

  formData.append(
    "request",
    new Blob([JSON.stringify(data)], {
      type: "application/json",
    })
  );

  const config = {
    headers: {
      "Content-Type": "multipart/form-data",
      "Access-Control-Allow-Origin": "*",
    },
    withCredentials: false,
  };

  return axios.put(`${STORE_API_URL}`, formData, config);
}

/* 스토어 삭제 */
// 해당 스토어의 주인만 삭제할 수 있는 로직 필요 (서버 단에서 처리??)
export async function delteStore(storeId) {
  try {
    const response = axios({
      method: "delete",
      url: STORE_API_URL,
      params: {
        storeId: storeId,
      },
    });
    console.log(response.success);
  } catch (err) {
    console.err(err);
  }
}

/* 유저의 스토어 찜 정보 받아오기 */
export async function fetchFavStores(memberId) {
  try {
    const res = axios.get(
      `${process.env.REACT_APP_API_SERVER_URL}/api/v1/storelikes/${memberId}`
    );

    console.log(res);
  } catch (err) {
    console.log(err);
  }
}

/* 스토어 찜 */
export async function addFavStore(userId, storeId) {
  const config = {
    headers: {
      "Content-Type": "application/json",
      "Access-Control-Allow-Origin": "*",
      Authorization: { token: sessionStorage.getItem("accessToken") },
      token: sessionStorage.getItem("accessToken"),
    },
    withCredentials: false,
  };

  const data = {
    memberId: userId,
    storeId: storeId,
  };

  try {
    const response = axios.post(
      `${process.env.REACT_APP_API_SERVER_URL}/api/v1/storelikes`,
      data,
      config
    );
    console.log(response.success);
  } catch (err) {
    console.err(err);
  }
}

/* 스토어 찜 취소 */
export async function deleteFavStore(id, userId, storeId) {
  try {
    const response = axios({
      method: "delete",
      url: STORE_LIKE_API_URL,
      params: {
        storeId: storeId,
      },
      data: {
        id: id,
        memberId: userId,
        storeId: storeId,
      },
    });
    console.log(response);
  } catch (err) {
    console.err(err);
  }
}

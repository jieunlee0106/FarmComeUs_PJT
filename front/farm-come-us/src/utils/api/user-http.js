import axios from "axios";

export async function userSignUp(userInfo) {
  const data = {
    id: userInfo.id,
    email: userInfo.email,
    nickname: userInfo.nickname,
    name: userInfo.name,
    phoneNumber: userInfo.phoneNumber,
    password: userInfo.password,
    streetAddr: userInfo.streetAddr,
    detailAddr: userInfo.detailAddr,
    zipcode: userInfo.zipcode,
  };

  const config = {
    headers: {
      "Content-Type": "application/json",
      "Access-Control-Allow-Origin": "*",
    },
    withCredentials: false,
  };

  try {
    const response = axios.post(
      process.env.REACT_APP_API_SERVER_URL + "/api/v1/member/join",
      data,
      config
    );
    sessionStorage.setItem("accessToken", response.data);
  } catch (err) {
    console.err(err);
  }
}

export async function login(id, password) {
  const data = { id, password };

  try {
    const response = axios.post(
      process.env.REACT_APP_API_SERVER_URL + "api/v1/member/login",
      data
    );

    const accessToken = response.data["token"];
    sessionStorage.setItem("accessToken", accessToken);
  } catch (err) {
    console.err(err);
  }
}

export async function fetchUserInfoWithAccessToken() {
  const accessToken = sessionStorage.getItem("accessToken");

  return axios.get(`${process.env.REACT_APP_API_SERVER_URL}/api/v1/member/`, {
    headers: {
      "Content-Type": "application/json",
      "Access-Control-Allow-Origin": "*",
      token: accessToken,
    },
  });
}

export async function fetchUserInfo(id) {
  const accessToken = sessionStorage.getItem("accessToken");
  const memberId = id;

  try {
    const response = axios.get(
      `${process.env.REACT_APP_API_SERVER_URL}/api/member`,
      {
        params: { memberId: memberId },
        headers: {
          "Content-Type": "application/json",
          "Access-Control-Allow-Origin": "*",
          token: `${accessToken}`,
        },
      }
    );
  } catch (err) {
    console.err(err);
  }
}

export async function fetchUpdateUserInfo(userInfo) {
  const formData = new FormData();
  formData.append("uploadFile", userInfo.uploadFile);

  const data = {
    nickname: userInfo.nickname,
    name: userInfo.name,
    email: userInfo.email,
    phoneNumber: userInfo.phoneNumber,
    streetAddr: userInfo.streetAddr,
    zipcode: userInfo.zipcode,
    detailAddr: userInfo.detailAddr,
  };
  formData.append(
    "memberUpdateReq",
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

  return axios.put(
    `${process.env.REACT_APP_API_SERVER_URL}/api/v1/member`,
    formData,
    config
  );
}

import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import axios from "axios";
// createAsyncThunk
// import axios from "axios";
const initialStateValue = {
  memberId: "",
  id: "",
  nickname: "",
  name: "",
  email: "",
  streetAddr: "",
  detailAddr: "",
  zipcode: "",
  phoneNumber: "",
  profileImg: "",
  isLogin: false,
  storeId: false,
};

const initialStateOrder = [];

const asynclogin = createAsyncThunk(
  "userSlice/asynclogin",
  async ({ username, password }) => {
    const response = await axios.post("BackendURL/loginURL", {
      username,
      password,
    });
    return response.data;
  }
);

const asyncSomethingFetch = createAsyncThunk(
  "userSlice/something",
  async () => {
    const res = await axios.get("myServerURL");
    const data = await res.json();
    return data.value;
    // value가 주어지는거에 따라서 pending, fulfilled, rejected가 달라짐.
    // 로그인을 처리하는 비동기함수라고 생각하고 있음.
  }
);
// 비동기 처리하는 함수. 이름 안정함.

const userSlice = createSlice({
  name: "userSlice",
  initialState: {
    value: initialStateValue,
    order: initialStateOrder,
  },
  reducers: {
    login: (state, action) => {
      state.value = { isLogin: true, ...action.payload };
    },
    logout: (state, action) => {
      state.value = initialStateValue;
      sessionStorage.clear();
    },
    savetoken: (state, action) => {
      state.accessToken = action.payload.accessToken;
      state.refreshToken = action.payload.refreshToken;
    },
    saveStoreInfo: (state, action) => {
      state.value = { ...state.value, storeId: true };
    },
    // up: (state, action) => {
    //   state.value = state.value + action.payload;
    // }, 참고용 예시 코드
  },

  extraReducers: (builder) => {
    builder.addCase(asyncSomethingFetch.pending, (state, action) => {
      state.status = "loading";
    });
    // 요청 보내서 아직 못받은 상태
    builder.addCase(asyncSomethingFetch.fulfilled, (state, action) => {
      state.value = action.payload;
      state.status = "complete";
    });
    // 요청 보내서 받은 상태. value도 업데이트해줌.
    builder.addCase(asyncSomethingFetch.rejected, (state, action) => {
      state.status = "fail";
    });
    // 요청 보내서 거절 상태. fail상태만 보여줌.
  },
});

export { asyncSomethingFetch };
export default userSlice;

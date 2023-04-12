import {
  configureStore,
  combineReducers,
  getDefaultMiddleware,
} from "@reduxjs/toolkit";

import storageSession from "redux-persist/lib/storage/session";
import storage from "redux-persist/lib/storage";
import { persistReducer } from "redux-persist";
import { createBrowserHistory } from "history";

import userSlice from "./userSlice";
import menuSlice from "./menuSlice";

export const history = createBrowserHistory();

const persistConfig = {
  key: "root",
  storage: storageSession,
  // whitelist: ["userSlice, menuSlice"],
  //User 이거 뭔지 수정필요
};

const rootReducer = combineReducers({
  userSlice: userSlice.reducer,
  menuSlice: menuSlice.reducer,
});

const persistedReducer = persistReducer(persistConfig, rootReducer);

const store = configureStore({
  reducer: persistedReducer,
  // non-serializable value was detected 라고 뜨는 에러 처리를 위해 사용
  middleware: (getDefaultMiddleware) =>
    getDefaultMiddleware({
      // Redux Toolkit에서 사용하기 위해 특별히 생성된 사용자 지정 미들웨어
      // 직렬화할 수 없는 값이 감지되면 직렬화할 수 없는 값이 감지된 키 경로와 함께
      // 콘솔 오류가 인쇄된다.
      serializableCheck: false,
    }),
});

export default store;

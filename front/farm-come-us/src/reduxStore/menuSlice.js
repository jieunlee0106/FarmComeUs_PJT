import { createSlice } from "@reduxjs/toolkit";

const initialStateValue = {
  isOpen: false,
};

const menuSlice = createSlice({
  name: "menuSlice",
  initialState: {
    isOpen: initialStateValue.isOpen,
  },
  reducers: {
    toggle: (state, action) => {
      state.isOpen = !state.isOpen;
    },
    close: (state) => {
      state.isOpen = false;
    },
  },
});

export default menuSlice;

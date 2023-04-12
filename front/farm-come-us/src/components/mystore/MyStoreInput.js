import React, { useState } from "react";

import classes from "./style/MyStoreInput.module.scss";

import Input from "../common/Input";

const MyStoreInput = (props) => {
  const [isFocused, setIsFocused] = useState(false);

  const onFocusHandler = () => {
    if (props.onFocus) {
      props.onFocus();
    }
    setIsFocused((prev) => !prev);
  };

  const onBlurHandler = () => {
    if (props.onBlur) {
      props.onBlur();
    }
    setIsFocused((prev) => !prev);
  };

  const onChangeHandler = (e) => {
    props.onChange(e.target.name, e.target.value);
    if (e.target.name === "imgSrc") {
      props.onChange("uploadFile", e.target.files[0]);
    }
  };

  return (
    <div
      className={`${classes.itemContainer}
      ${!props.readOnly && isFocused ? classes.active : null}`}
    >
      <span className={`${classes.label} title`}>{props.label}</span>
      <Input
        className={`${classes.itemInput} ${
          props.readOnly ? classes.readOnly : null
        }`}
        value={props.value}
        name={props.name}
        readOnly={props.readOnly}
        type={props.type}
        min={props.min}
        max={props.max}
        step={props.step}
        onChange={onChangeHandler}
        id={props.id}
        isActive={!props.readOnly}
        onFocus={onFocusHandler}
        onBlur={onBlurHandler}
        placeholder={props.placeholder}
        accept={props.accept}
        required={props.required}
      />
    </div>
  );
};

export default MyStoreInput;

import { useState } from "react";
import { IconName } from "react-icons/md";
import classes from "./SignUpInput.module.scss";

const SignUpInput = (props) => {
  const [onFocus, setOnFocus] = useState(false);
  // const onFocus = "";
  // const setOnFocus = () => {
  //   console.log("");
  // };

  return (
    <div className={`${classes.outerInput} ${onFocus ? classes.focus : ""}`}>
      {/* {props.render} */}
      <input
        className={classes.innerInput}
        onFocus={() => {
          setOnFocus(true);
        }}
        onBlur={
          (props.onBlur, // 동작확인
          setOnFocus(false))
        }
        placeholder={props.placeholder}
        onChange={props.onChange}
        type={props.type}
        pattern={props.pattern}
        max={props.max}
        // class={props.class}
      ></input>
    </div>
  );
};

export default SignUpInput;

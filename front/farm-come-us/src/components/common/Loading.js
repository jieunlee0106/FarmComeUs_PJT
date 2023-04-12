import classes from "./style/Loading.module.scss";

const Loading = (props) => {
  return (
    <div className={`${classes.loadingBox} ${props.className}`}>
      <div className={classes.one}></div>
      <div className={classes.two}></div>
      <div className={classes.three}></div>
    </div>
  );
};

export default Loading;

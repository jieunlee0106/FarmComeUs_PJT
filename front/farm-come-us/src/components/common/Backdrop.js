import { useDispatch, useSelector } from "react-redux";
import menuSlice from "../../reduxStore/menuSlice";
import classes from "./style/Backdrop.module.scss";

const Backdrop = (props) => {
  const dispatch = useDispatch();
  const menu = useSelector((state) => state.menuSlice.isOpen);
  return (
    <div
      className={`${props.className} ${classes.backdrop}`}
      onClick={() => {
        dispatch(menuSlice.actions.toggle());
      }}
    />
  );
};

export default Backdrop;

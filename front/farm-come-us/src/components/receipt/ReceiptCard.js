import classes from "./style/ReceiptCard.module.scss";

const ReceiptCard = (props) => {
  const data = {
    title: "props.title",
    img: "props.img",
    unit: "props.unit",
    storename: "props.storename",
    cost: "props.cost",
  };
  // api하면서 수정필요
  return (
    <div className={classes.card}>
      <div className={classes.imgWrapper}>
        <img
          className={classes.img}
          src={props.img}
          alt="사진이 들어갑니다"
        ></img>
      </div>
      <div className={classes.letters}>
        <div>
          <div className={classes.titleWrapper}>
            <div className={classes.title}>{props.title}</div>
            <div className={classes.unit}>{props.unit}kg</div>
          </div>
          <div className={classes.store}>{props.storename}</div>
        </div>
        <div className={classes.cost}>{props.cost}원</div>
      </div>
    </div>
  );
};

export default ReceiptCard;

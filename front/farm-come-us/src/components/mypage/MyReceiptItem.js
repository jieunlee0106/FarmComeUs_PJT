import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import ProductDetail from "../../pages/product/ProductDetail";
import classes from "./style/MyReceiptItem.module.scss";

const MyReceiptItem = (props) => {
  console.log(props.info);
  const navigate = useNavigate();
  const [itemName, setItemName] = useState("");

  let item = {
    id: "1084165156",
    items: ["강원도 고랭지 배추", "제주 스윗 당근", "충주 호박고구마"],
    cost: 429000,
    ordertime: "2023.01.10 21:12:58",
  };

  const receiptDetailHandler = () => {
    navigate(`/receipt/${props.info.orderId}`, {
      state: {
        orderId: props.info.orderId,
        orderDate: props.info.orderDate,
        orderLength: props.info.orderItems.length,
      },
    });
  };

  // function formatDate(date) {
  //   const year = date.getFullYear();
  //   const month = String(date.getMonth() + 1).padStart(2, "0");
  //   const day = String(date.getDate()).padStart(2, "0");
  //   const hours = String(date.getHours()).padStart(2, "0");
  //   const minutes = String(date.getMinutes()).padStart(2, "0");
  //   return `${year}.${month}.${day}.${hours}.${minutes}`;
  // }
  // console.log(formatDate(props.info.orderCreateAt));

  //// 😀 백엔드 확인. 왜 오류 나는지.
  useEffect(() => {
    // ProductDetail(props.info.orderItems[0].oitemId);
    axios
      .get(`${process.env.REACT_APP_API_SERVER_URL}/api/v1/item`, {
        params: {
          itemId: props.info.orderItems[0].oitemId,
        },
      })
      .then((res) => {
        console.log("정상응답");
        console.log(res);
      })
      .catch((err) => console.log(err));
    // axios.get();
  }, []);

  // item1 = { id:orderId, items:[강원도배추, 제즈스윗당근],
  //         cost:429,000, orderdate:2023.01.10 21:12:58, }
  //   수정필요. 돈 받아서 , 찍고 출력해야됨. 해당 컴포넌트에서 유효성검사로 바꿔줄 필요 있음.

  return (
    <div
      className={`${classes.card} ${classes.mt}`}
      onClick={receiptDetailHandler}
    >
      <div
        className={`${classes.orderId}`}
      >{`주문번호 : ${props.info.orderId}`}</div>
      <div className={`${classes.rowflexbox} ${classes.mt}`}>
        <div
          className={`${classes.orderDescription} ${classes.widthFull} `}
        >{`구매상품(${props.info.orderItems.length}): `}</div>
        <div className={classes.orderDescription}>{` ${item.items}`}</div>
      </div>
      <div className={`${classes.rowedgeflexbox} ${classes.mt}`}>
        <div
          className={classes.ordertime}
        >{`구매일: ${props.info.orderCreateAt.substr(0, 10)}`}</div>
        <div className={classes.cost}>{`${props.info.totalPrice.toLocaleString(
          "en-US"
        )}원`}</div>
      </div>
    </div>
  );
};

export default MyReceiptItem;

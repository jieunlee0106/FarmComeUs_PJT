import { useEffect, useState } from "react";
import classes from "./style/MyReceiptDetail.module.scss";
import { useLocation, useNavigate, useParams } from "react-router-dom";
import { MdOutlineArrowBackIos } from "react-icons/md";
import MyReceiptDetailItem from "../../components/receipt/myreceipt/MyReceiptDetailItem";
import Button from "../../components/common/Button";
import axios from "axios";

const MyReceiptDetail = () => {
  const param = useParams();
  // console.log("파람");
  const navigate = useNavigate();
  // console.log(param); //param.orderId 를 사용.

  const location = useLocation();
  // console.log("로케이션");
  // console.log(location);

  // 뿌려줄 아이템 데이터. arr형으로 받게됨.
  const [itemsDatas, setItemsDatas] = useState([]);
  const [totalPrice, setTotalPrice] = useState(0);
  const [date, setDate] = useState("");

  // date정보 raw로 받아서 포맷해주는 변경해주는 함수.
  const dateFormatter = (rawDate) => {
    if (rawDate) {
      const regDt = rawDate;
      const year = regDt.getFullYear();
      const month = regDt.getMonth() + 1;
      const date = regDt.getDate();
      let hour = regDt.getHours();
      hour = hour < 12 ? "0" + hour : hour;
      let minute = regDt.getMinutes();
      minute = minute < 10 ? "0" + minute : minute;

      return `${year}.${month}.${date} ${hour}:${minute}`;
    } else {
      return;
    }
  };

  // 가격총합을 구하는 함수 😀
  function getTotalPrice(list) {
    let total = 0;
    for (let i = 0; i < list.length; i++) {
      total += list[i].oitemPrice;
    }
    return total;
  }

  // 가격총합정보 받아서 , 붙혀주는 함수 😀 위의 함수 변환후, 다시 세팅
  const convertedPrice = totalPrice
    .toString()
    .replace(/\B(?=(\d{3})+(?!\d))/g, ",");

  //location.param으로 온 orderId 통해 아래와 같은 형식의 데이터가 온다.
  /* 
  {
  "orderdetailList": [
    {
      "oitemId": 1,
      "oitemCount": 2,
      "oitemCreatedAt": "2023-02-15T10:59:35.678664",
      "oitemPrice": 50000,
      "storeNum": 3,
      "totalPrice": 50000
    }
  ]
  } 형태 데이터 받는 axios함수. res.data
  */
  const getorderData = () => {
    axios
      .get(process.env.REACT_APP_API_SERVER_URL + "/api/v1/order/detail", {
        params: { order: param.orderId },
      })
      .then((res) => {
        // console.log(`itemDatas에 들어감: ${res.data.orderdetailList}`);
        // console.log(res.data.orderdetailList);
        // console.log(typeof eval(res.data.orderdetailList));
        // console.log(res.data.orderdetailList[0]);
        setDate(res.data.orderdetailList[0].oitemCreatedAt);
        setItemsDatas(res.data.orderdetailList);
        setTotalPrice(getTotalPrice(res.data.orderdetailList));
      });
  };

  // itemId를 이용해서 axios 요청을 보내면(아이템에 대한 상세정보) - 거기에 savedPath라는 이름으로 이미지 주소가 주어집니다.(그걸로 이미지 카드를 채움.)
  useEffect(() => {
    getorderData();
  }, []);
  // const dateFormatted = dateFormatter(date);

  let itemsCards = itemsDatas.map((item, idx) => {
    // console.log(item);
    <MyReceiptDetailItem
      key={idx}
      itemId={item.oitemId}
      count={item.oitemCount}
      storeNum={item.storeNum}
      itemPrice={item.oitemPrice}
    />;
    // itemId를 이용해서 axios 요청을 보내면(아이템에 대한 상세정보) - 거기에 savedPath라는 이름으로 이미지 주소가 주어집니다.(그걸로 이미지 카드를 채움.)
  });

  return (
    <div className={classes.screen}>
      <div className={classes.upperCard}>
        <div className={classes.flexrow}>
          <div
            onClick={() => {
              navigate(-1);
            }}
          >
            <MdOutlineArrowBackIos className={classes.backButton} />
          </div>
          <div className={classes.backButton}>주문상세</div>
        </div>
        <div className={classes.orderInfoMt}>
          <div
            className={classes.orderinfo}
          >{`주문번호 : ${param.orderId}`}</div>
          <div className={classes.orderinfo}>{`구매일자 : ${date.substr(
            0,
            10
          )}`}</div>
        </div>
        <div
          className={`${classes.orderinfo} ${classes.orderLength} ${classes.orderlength}`}
        >
          상품 {itemsDatas.length} 개
        </div>
      </div>
      <div className={classes.receiptsBody}>
        {itemsCards}
        여기 아이템 카드들 나와야하는데
        <div className={classes.totalPrice}>
          <div className={classes.totalPrice}>
            전체 결제 금액: {convertedPrice}원
          </div>
        </div>
        <div>
          <Button>환불 / 결제취소</Button>
        </div>
      </div>
    </div>
  );
};

export default MyReceiptDetail;

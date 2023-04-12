import React from "react";

import MyStoreContentTitle from "../../components/mystore/MyStoreContentTItle";
import MyStoreReceiptList from "../../components/mystore/MyStoreReceiptList";
import { useNavigate } from "react-router-dom";

const DUMMY_RECEIPT = [
  {
    receiptId: 1,
    date: new Date("2023", "0", "30", "01", "23", "23"),
    productId: 1,
    productName: "강원도 고랭지 배추",
    orderId: 101561056,
    price: 234000,
    imgSrc: "https://via.placeholder.com/300",
  },
  {
    receiptId: 2,
    date: new Date("2023", "0", "30", "02", "42", "24"),
    productId: 2,
    productName: "제주 스윗 당근",
    orderId: 101561056,
    price: 121000,
    imgSrc: "https://via.placeholder.com/300",
  },
  {
    receiptId: 3,
    date: new Date(),
    productId: 3,
    productName: "보성 녹차 건조 차잎",
    orderId: 105641232,
    price: 126000,
    imgSrc: "https://via.placeholder.com/300",
  },
  {
    receiptId: 4,
    date: new Date("2023", "0", "30", "02", "42", "24"),
    productId: 4,
    productName: "김수미 간장 게장",
    orderId: 105616424,
    price: 336000,
    imgSrc: "https://via.placeholder.com/300",
  },
  {
    receiptId: 5,
    date: new Date("2023", "0", "30", "02", "42", "24"),
    productId: 5,
    productName: "꼬꼬댁 백숙용 1호 닭",
    orderId: 101561056,
    price: 23000,
    imgSrc: "https://via.placeholder.com/300",
  },
];

const MyStoreReceipt = () => {
  const navigate = useNavigate();
  const showReceiptDetailHandler = (receipt) => {
    console.log(receipt);
  };

  return (
    <div>
      <MyStoreContentTitle text="판매내역" />
      <MyStoreReceiptList
        list={DUMMY_RECEIPT}
        showReceiptDetailHandler={showReceiptDetailHandler}
      />
    </div>
  );
};

export default MyStoreReceipt;

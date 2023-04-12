import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import classes from "./style/Cart.module.scss";
import CartHeader from "../components/cart/CartHeader";
import CartList from "../components/cart/CartList";
import CartFooter from "../components/cart/CartFooter";
import { cartDetail } from "../utils/api/cart-http";

const DUMMY_CART_LIST = [
  {
    storeId: 1,
    storeName: "애플 인 더 청송",
    productId: 1,
    productName: "사과 1박스",
    productOption: 3,
    price: 60000,
    discount: 20,
    discountPrice: 48000,
  },
  {
    storeId: 1,
    storeName: "애플 인 더 청송",
    productId: 2,
    productName: "애플망고 1박스",
    productOption: 1,
    price: 30000,
    discount: 20,
    discountPrice: 24000,
  },
  {
    storeId: 2,
    storeName: "페어 인 더 청송",
    productId: 3,
    productName: "배 1박스",
    productOption: 1,
    price: 20000,
    discount: 20,
    discountPrice: 16000,
  },
  {
    storeId: 3,
    storeName: "퍼시먼 인 더 청송",
    productId: 4,
    productName: "감 1박스",
    productOption: 1,
    price: 20000,
    discount: 20,
    discountPrice: 16000,
  },
];

const Cart = () => {
  const navigate = useNavigate();

  useEffect(() => {
    alert("준비 중인 기능입니다.");
    navigate(-1);
  }, []);

  // const memberId = useSelector(state => state.user.value.memberId) => 오류 나는 것 같아서 뺄게요~!
  const [itemIdList, setList] = useState([]);
  const [resultPrice, setPrice] = useState(0);

  //
  const res = cartDetail();

  // 장바구니 삭제 => 주석 풀고 장바구니 번호 넣어서 사용하기
  // const ret = delteCart()
  // console.log(ret)

  const plusSetList = (Id, price) => {
    setList([...itemIdList, Id]);
    setPrice(resultPrice + price);
  };

  const minusSetList = (Id, price) => {
    setList(itemIdList.filter((id) => id !== Id));
    setPrice(resultPrice - price);
  };

  return (
    <div className={classes.container}>
      <CartHeader></CartHeader>
      <CartList
        plusSetList={plusSetList}
        minusSetList={minusSetList}
      ></CartList>
      <CartFooter
        DUMMY_CART_LIST={DUMMY_CART_LIST}
        itemIdList={itemIdList}
        resultPrice={resultPrice}
      ></CartFooter>
    </div>
  );
};

export default Cart;

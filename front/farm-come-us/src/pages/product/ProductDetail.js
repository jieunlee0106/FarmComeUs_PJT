import React, { useState, useEffect, Fragment } from "react";
import classes from "./style/ProductDetail.module.scss";
import Card from "../../components/common/Card";
import { MdShoppingCart } from "react-icons/md";
import { MdOutlineArrowBackIos } from "react-icons/md";
import { Link } from "react-router-dom";
import { useLocation, useNavigate } from "react-router-dom";
import { productDetail } from "../../utils/api/product-http";
import { useSelector } from "react-redux";
import axios from "axios";

import Loading from "../../components/common/Loading";
import useHttp from "../../hooks/use-http";

const ProductDetail = () => {
  const userId = useSelector((state) => state.userSlice.value.memberId);
  const location = useLocation();
  const navigate = useNavigate();

  let ITEM_ID = null;
  if (location.state.itemId) {
    ITEM_ID = location.state.itemId;
  }

  const [amount, setAmount] = useState(1);

  const {
    sendRequest: getItemDetail,
    status: status,
    data: itemDetail,
    error,
  } = useHttp(productDetail, true);

  useEffect(() => {
    if (!userId) {
      navigate("/login", { replace: true });
      alert("로그인 후 이용가능한 서비스 입니다.");
    }

    getItemDetail(ITEM_ID);
  }, [getItemDetail]);

  const orderProduct = async function orderProduct() {
    const data = {
      itemId: ITEM_ID,
      memberId: userId,
      oitemCount: amount,
    };

    axios
      .post(process.env.REACT_APP_API_SERVER_URL + "/api/v1/order", data)
      .then((res) => {
        let resData = res.data;
        navigate("/payment", {
          state: {
            orderId: resData,
            storename: itemDetail.item.storeName,
            productname: itemDetail.item.itemName,
            memberId: userId,
            price: resultPrice,
            amount: amount,
          },
        });
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const plusAmount = () => {
    setAmount(amount + 1);
  };
  const minusAmount = () => {
    if (amount > 1) {
      setAmount(amount - 1);
    }
  };

  const convertedPrice = (price) =>
    price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");

  const sendCartPageHandler = () => {
    alert("장바구니에 추가되었습니다.");
    return;
    /* 장바구니 추가 로직 */
  };

  let resultPrice = null;
  let discountPrice = null;
  if (itemDetail) {
    discountPrice =
      itemDetail.item.itemPrice * (1 - itemDetail.item.itemDiscount / 100);
    resultPrice = discountPrice * amount;
  }

  return (
    <div className={classes.container}>
      {status === "pending" ? (
        <Loading className={classes.loading} />
      ) : (
        <Fragment>
          <div className={classes.header}>
            <MdOutlineArrowBackIos
              onClick={() => navigate(-1)}
            ></MdOutlineArrowBackIos>
            <div className={classes.storename}>
              <Link
                to="/store"
                state={{ storeId: itemDetail ? itemDetail.item.storeId : null }}
              >
                {itemDetail.item.storeName}
                <span>스토어</span>
              </Link>
            </div>
          </div>
          <Card className={classes.imagecard}>
            <img
              src={itemDetail ? itemDetail.item.savedPath : ""}
              alt="상품이미지"
            ></img>
          </Card>
          <div className={classes.productname}>{itemDetail.item.itemName}</div>
          <p className={classes.productscript}>
            {itemDetail.item.itemDescription}
          </p>
          <div className={classes.option}>
            <div className={classes.discountspace}>
              {itemDetail.item.itemDiscount !== 0 ? (
                <Fragment>
                  <div className={classes.salepercent}>
                    {itemDetail.item.itemDiscount}%
                  </div>
                  <div className={classes.originalprice}>
                    {convertedPrice(itemDetail.item.itemPrice)}원
                  </div>
                </Fragment>
              ) : null}
            </div>
            <div className={classes.saleprice}>
              {`금액 : ${convertedPrice(discountPrice)} 원`}
            </div>
            <div className={classes.selectamount}>
              <div className={classes.firstblock} onClick={minusAmount}>
                -
              </div>
              <div className={classes.secondblock}>{amount}</div>
              <div className={classes.thirdblock} onClick={plusAmount}>
                +
              </div>
            </div>
          </div>
          <div className={classes.finalprice}>
            <div className={classes.firstblock}>총 상품 금액:</div>
            <div className={classes.secondblock}>
              {convertedPrice(resultPrice)}
            </div>
            <div className={classes.thirdblock}>원</div>
          </div>
          <div className={classes.buttonspace}>
            <div className={classes.cartbutton}>
              <MdShoppingCart
                className={classes.carticon}
                onClick={sendCartPageHandler}
              />
            </div>
            <div className={classes.buybutton}>
              <div className={classes.buybuttonlink} onClick={orderProduct}>
                <div>구매하기</div>
              </div>
            </div>
          </div>
        </Fragment>
      )}
    </div>
  );
};

export default ProductDetail;

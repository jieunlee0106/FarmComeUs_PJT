import axios from "axios";

const ORDER_API_URL = `${process.env.REACT_APP_API_SERVER_URL}/api/v1/order`;

const clientOrderObjFormatter = (obj) => {
  return {
    orderId: obj.id,
    productId: obj.item_id,
    count: obj.orderCount,
  };
};

/* 단건 주문 */
export async function orderProduct() {
  try {
    axios({
      method: "post",
      url: `${ORDER_API_URL}`,
      data: {
        itemId: 1,
        memberId: 3,
        oitemCount: 2,
        orderInfoDtoList: [null],
      },
    });
  } catch (err) {
    console.err(err);
  }
}

// 주문 조회
export async function orderList() {
  try {
    axios({
      method: "get",
      url: ORDER_API_URL,
      params: { member: 2 },
    });
  } catch (err) {
    console.error(err);
  }
}

/* 주문 취소 */
export async function updateOrder(orderId) {
  try {
    axios({
      method: "put",
      url: ORDER_API_URL,
      params: {
        orderId: orderId,
      },
    });
  } catch (err) {
    console.err(err);
  }
}

import axios from "axios";

const DUMMY_SERVER_URL = "https:localhost:3000";
const CART_API_URL = `${process.env.REACT_APP_API_SERVER_URL}/api/v1/cart`;

 /* 장바구니  조회 */
export async function cartDetail() {
  try {
    const response = axios({
        method: "get",
        url: CART_API_URL ,
        params: {member : 2}
    });
    console.log((await response).data.cartList);
  } catch (err) {
    console.err(err);
  }
}

// 장바구니 삭제 => Cart.js 에 import 함! 
export async function delteCart() {
  try {
    const response = axios({
      method: "delete",
      url: CART_API_URL,
      params: {
        // 
        cartId: 1
      },
    });
    console.log(response.success);
  } catch (err) {
    console.err(err);
  }
}



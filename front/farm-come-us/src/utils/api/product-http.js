import axios from "axios";

const PRODUCT_API_URL = `${process.env.REACT_APP_API_SERVER_URL}/api/v1/item`;

/* 상품 등록 */
export async function createProduct(productInfo) {
  const formData = new FormData();

  const data = {
    storeId: productInfo.storeId,
    titleCategoryName: productInfo.categoryTitle,
    detailCategoryName: productInfo.categoryDetail,
    itemName: productInfo.itemName,
    itemDescription: productInfo.itemDescription,
    itemPrice: productInfo.itemPrice,
    itemStock: productInfo.itemStock,
    imgSrc: productInfo.imgSrc,
  };
  formData.append("uploadFile", productInfo.uploadFile);

  formData.append(
    "item",
    new Blob([JSON.stringify(data)], {
      type: "application/json",
    })
  );

  const config = {
    headers: {
      "Content-Type": "multipart/form-data",
      "Access-Control-Allow-Origin": "*",
      Authorization: { token: sessionStorage.getItem("accessToken") },
      token: sessionStorage.getItem("accessToken"),
    },
  };

  return axios.post(
    `${process.env.REACT_APP_API_SERVER_URL}/api/v1/item`,
    formData,
    config
  );
}

/* 상품 상세 조회 */
export async function productDetail(productId) {
  try {
    const response = await axios({
      method: "get",
      url: `${process.env.REACT_APP_API_SERVER_URL}/api/v1/item`,
      params: {
        itemId: productId,
      },
    });
    const data = response.data;
    return data;
  } catch (err) {
    console.log(err);
  }
}

/* 상품 목록 조회 */
export function fetchProductList(fetchInfo) {
  const data = {
    titleCategoryName: fetchInfo["category"],
    itemName: fetchInfo["itemName"],
    detailCategoryName: fetchInfo["subCategory"],
  };

  const config = { "Content-Type": "application/json" };

  const params = {
    page: fetchInfo["page"],
    size: fetchInfo["size"],
  };

  return axios
    .post(
      `${process.env.REACT_APP_API_SERVER_URL}/api/v1/item/keyword/`,
      data,
      { config, params }
    )
    .then((res) => {
      return res.data;
    })
    .catch((err) => {
      console.error(err);
    });
}

/* 스토어 상품 조회 */
export async function fetchStoreProducts(storeId, page, size) {
  if (!page) page = 0;
  const params = {
    storeId,
    page,
    size: 100,
  };

  return await axios
    .get(`${PRODUCT_API_URL}/store`, { params })
    .then((res) => {
      return res.data;
    })
    .catch((err) => {
      console.error(err);
    });
}

/* 등록 상품 삭제 */
export async function deleteProduct(productId) {
  try {
    axios({
      method: "delete",
      url: PRODUCT_API_URL,
      params: {
        itemId: productId,
      },
    });
  } catch (err) {
    console.err(err);
  }
}

/* 등록 상품 수정 */
export async function updateProduct(product) {
  try {
    const response = axios({
      method: "put",
    });
    console.log(response.success);
  } catch (err) {
    console.err(err);
  }
}

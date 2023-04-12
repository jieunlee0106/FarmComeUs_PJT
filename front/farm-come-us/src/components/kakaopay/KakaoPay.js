import axios from "axios";

const kakaoClick = async () => {
    const setUrl = ""
    try {
        const response = await axiosInstance.get(
            `api/api/v1/pay/kakaoreq?orderId=35?memberId=1?itemCount=10`,
        );
        const setUrl = response.data.next_redirect_pc_url;
        
        if (setUrl) {
            const kakapGo = async() => {
                try {
                    window.open(setUrl)
                } catch(err) {
                    console.err(err);
                }
            } 
        } else {
            console.log("안됨")
        }
        
        setPayModal(true);


      } catch (err) {
        console.err(err);
      }
};

export default KakaoPayment;

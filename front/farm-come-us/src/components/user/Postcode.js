import DaumPostcode from "react-daum-postcode";
import { useState } from "react";

function Postcode(props) {
  const [openPostcode, setOpenPostcode] = useState(false);
  // 기본값이 false임.
  // useState의 변수값은 openPostcode
  // 변수의 set함수는 setOpenPostcode
  // 변수의 set함수는 콜백함수를 param으로 받는데,
  // 해당 콜백함수는 이전(openPostcode로 지정된/ 변수이름이 뭐든 no 상관)값을 받아서,
  // 로직처리 이후 return값을 반환한다.
  // 여기서 callback의 return값(콜백함수 말고 정적인자를 써도 됨)이 새로운 openPostcode의 값이 됨.

  function clickEmptyPostbar() {
    setOpenPostcode((current) => !current);
  }
  // current를 받아서 반대로 토글값을 return해주는 콜백함수.
  // setOpenPostcode를 토글로 실행시켜서 openPostcode를 false/true 토글해줌.

  function selectAddress(data) {
    console.log(`
        주소: ${data.roadAddress}
        우편번호: ${data.zonecode}
        `); // 일단 콘솔에 찍는걸로 해놨음. 상세주소까지 받아서 DB에 보내야함.
    console.log(data);
    setOpenPostcode(false);
  }

  return (
    <div>
      <button onClick={clickEmptyPostbar}>주소찾기</button>

      {openPostcode && (
        <DaumPostcode
          onComplete={selectAddress} // 값을 선택할 경우 실행되는 이벤트
          autoClose={false} // 값을 선택할 경우 사용되는 DOM을 제거하여 자동 닫힘 설정
          defaultQuery="동서대로 98-39" // 팝업을 열때 기본적으로 입력되는 검색어. 대전캠주소 해놨음.
        />
      )}
    </div>
  );
}

export default Postcode;

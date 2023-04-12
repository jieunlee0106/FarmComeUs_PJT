import axios from "axios";
import { useState, useEffect } from "react";
import DaumPostcodeEmbed from "react-daum-postcode";
import { MdSearch, MdPhoneIphone } from "react-icons/md";
import classes from "./style/AdditionalInfo.module.scss";
import { useNavigate } from "react-router-dom";
import _ from "lodash";

// 수정필요 - 카카오로그인을 하면 모든 필드가 다 주어지지않는데, 회원수정에서 모든걸 required
// ㅈ
const AdditionalInfo = () => {
  // let nickname = new URL(window.location.href).searchParams.get("code");
  // let accessToken = new URL(window.location.href).searchParams.get("token");
  const navigate = useNavigate();
  const [openModal, setOpenModal] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const [streetAddr, setStreetAddr] = useState("");
  const [zipcode, setZipcode] = useState("");
  const [detailAddr, setDetailAddr] = useState("");

  const [isStreetAddr, setIsStreetAddr] = useState(false);
  const [isPhoneNumber, setIsPhoneNumber] = useState(false);

  const onBlurPhoneNumber = (e) => {
    if (e.target.value !== "") {
      setPhoneNumber(e.target.value);
      setIsPhoneNumber(true);
    }
  };
  const onChangestreetAddr = (e) => {
    if (e.target.value !== "") {
      setStreetAddr(e.target.value);
      setIsStreetAddr(true);
    }
  };

  const selectAddress = (data) => {
    setIsStreetAddr(true);
    setStreetAddr(data.roadAddress);
    setZipcode(data.zonecode);
    setOpenModal(!openModal);
  };

  // 카카오 로그인하면 토큰이랑 userId 준다고 했나?
  async function modifyUserInfo() {
    const adjArr = [
      "귀여운 ",
      "새콤 ",
      "부끄러운 ",
      "아삭한 ",
      "보은 ",
      "지친 ",
      "착한 ",
      "매운 ",
    ];
    const vegeArr = [
      "양파",
      "상추",
      "사과",
      "배추",
      "자몽",
      "포도",
      "양배추",
      "고구마",
      "쪽파",
      "달걀",
    ];
    let nickname = _.sample(adjArr) + _.sample(vegeArr);

    const formData = new FormData();
    // formData.append("uploadFile", storeInfo.uploadFile);
    formData.append("uploadFile", ""); //😀프로필사진 보낼생각없는데?

    // 😀 4개만 보내도 되는지 확인.
    const userInfo = {
      phoneNumber,
      streetAddr,
      detailAddr,
      zipcode,
      nickname,
    };

    formData.append(
      "memberUpdateReq",
      new Blob([JSON.stringify(userInfo)], {
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
      withCredentials: false,
    };

    axios
      .put(
        process.env.REACT_APP_API_SERVER_URL + "/api/v1/member",
        formData,
        config
      )
      .then((res) => {
        navigate("/oauthRedirect");
      })
      .catch((err) => console.log(err));
  }

  const submitHandler = async (e) => {
    e.preventDefault();
    // 보내는 로직 수정 필요함.
    // const data = { phoneNumber, streetAddr, zipcode, detailAddr };
    // axios.put(process.env.REACT_APP_API_SERVER_URL + "/api/v1/member");
    modifyUserInfo();
  };

  useEffect(() => {
    let givenNickname = new URL(window.location.href).searchParams.get(
      "nickname"
    );
    let accessToken = new URL(window.location.href).searchParams.get("token");
    sessionStorage.setItem("accessToken", accessToken);

    if (!(givenNickname === null || givenNickname === "")) {
      navigate("/oauthRedirect");
    }
  }, []);

  return (
    <form className={classes.container} onSubmit={submitHandler}>
      <div className={classes.headerTxt}>추가정보 입력</div>
      <div className={classes.subcontainer}>
        <div className={classes.formbox}>
          <MdPhoneIphone className={classes.icon} />
          <input
            className={classes.outerInput}
            type="phoneNumber"
            // class="form-control m-input"
            text="전화번호"
            placeholder="전화번호"
            typename="phoneNumber"
            pattern="[0-9]{11}"
            maxLength="13"
            onBlur={onBlurPhoneNumber}
          />
        </div>
        <div className={classes.formbox}>
          <div>
            <MdSearch className={classes.icon} />
            <input
              onFocus={() => {
                setOpenModal(!openModal);
              }}
              onClick={() => {
                setOpenModal(!openModal);
              }}
              className={classes.outerInput}
              onChange={onChangestreetAddr}
              addresstext=" "
              placeholder="주소를 검색해주세요."
              typetitle="streetAddr"
              value={streetAddr}
            />
          </div>
        </div>

        <div className={classes.formbox}>
          <input
            className={classes.outerInput}
            onChange={(e) => setZipcode(e.target.value)}
            passwordtext=" "
            placeholder="우편번호"
            typetitle="zipcode"
            value={zipcode}
          />
        </div>

        <div className={classes.formbox}>
          <input
            className={classes.outerInput}
            onChange={(e) => {
              setDetailAddr(e.target.value);
            }}
            passwordtext=" "
            placeholder="상세주소"
            typetitle="specificstreetAddr"
          />
        </div>

        {/* 이름, 이메일, 패스워드, 패스워드 확인, 주소가 다 맞다면 주황버튼으로 */}
        <button
          className={`${classes.button} ${
            !(isPhoneNumber && isStreetAddr) ? classes.disabled : ""
          }`}
          type="submit"
          disabled={!(isPhoneNumber && isStreetAddr)}
        >
          다음
        </button>
      </div>

      {openModal && (
        <div className={`${classes.modal} ${classes.openModal}`}>
          <DaumPostcodeEmbed
            onComplete={selectAddress} // 값을 선택할 경우 실행되는 이벤트
            autoClose={false} // 값을 선택할 경우 사용되는 DOM을 제거하여 자동 닫힘 설정
            defaultQuery="동서대로 98-39" // 팝업을 열때 기본적으로 입력되는 검색어. 대전캠주소 해놨음.
          />
        </div>
      )}
      {openModal && (
        <div
          className={classes.backdrop}
          onClick={() => {
            setOpenModal(false);
          }}
        />
      )}
    </form>
  );
};

export default AdditionalInfo;

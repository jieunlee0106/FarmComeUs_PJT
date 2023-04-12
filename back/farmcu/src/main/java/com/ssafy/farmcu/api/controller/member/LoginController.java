package com.ssafy.farmcu.api.controller.member;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.farmcu.oauth.dto.TokenVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
/**
 * 소셜 회원 정보 받아오기
 */


@Slf4j
@RestController
@RequestMapping("api/v1/login")
public class LoginController {

    @Value("#{config['KAKAO_CLIENT_ID']}")
    private static String CLIENT_ID;
    @Value("#{config['KAKAO_CLIENT_SECRET']}")
    private static String CLIENT_SECRET;

    @GetMapping("/oauth")
    public String kakaoConnect() throws UnsupportedEncodingException {
        log.info("kakao 로그인 바로가기-  여기 뜨나요:?");

        String REDIRECT_URL = URLEncoder.encode("https://localhost:3000/login/oauth2/code/kakao", "UTF-8");
        SecureRandom secureRandom = new SecureRandom();
        String state = new BigInteger(130, secureRandom).toString();
        String apiURL = "https://kauth.kakao.com/oauth/authorize?response_type=code";
        apiURL += "&client_id=" + CLIENT_ID;
        apiURL += "&redirect_uri=" + REDIRECT_URL;
        apiURL += "&state=" + state;

        // kakao 로그인 화면으로 리다이렉트
        return "redirect:"+apiURL;
    }

    @RequestMapping(value = "/callback", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json")
    public void getAccessToken(@RequestParam(value = "code") String code, @RequestParam(value = "state") String state, HttpServletResponse response) throws UnsupportedEncodingException {
        log.info("토큰 발급/갱신/삭제 요청 URL");

        String REDIRECT_URL = URLEncoder.encode("https://localhost:3000/login/oauth2/code/kakao", "UTF-8");
        String apiURL = "https://kauth.kakao.com/oauth/token?grant_type=authorization_code&";
        apiURL += "client_id=" + CLIENT_ID;
        apiURL += "&client_secret=" + CLIENT_SECRET;
        apiURL += "&redirect_uri=" + REDIRECT_URL;
        apiURL += "&code=" + code;
        apiURL += "&state=" + state;

        try {
            URL url = new URL(apiURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            int responseCode = conn.getResponseCode();
            log.info("responseCode = "+responseCode);
            BufferedReader br;
            if (responseCode == 200) {
                // 정상 호출
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            }else {
                // 에러 발생
                br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }

            String inputLine;
            StringBuffer res = new StringBuffer();
            while ((inputLine = br.readLine()) != null){
                res.append(inputLine);
            }
            br.close(); // autoCloser

            // String to token
            TokenVo token = new ObjectMapper().readValue(res.toString(), TokenVo.class);
            log.info("1.token.getAccess_token(): " + token.getAccess_token());
            log.info("2.toke.getToken_type() "+token.getToken_type());

            response.setHeader("Authorization", "Bearer " + token.getAccess_token());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @GetMapping("/getProfile")
    public void apiExamMemberProfile(){
        // 헤더에서 토큰 줘야함
    }
}

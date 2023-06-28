

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


import java.io.InputStreamReader;
import java.net.*;
import java.io.BufferedReader;


public class ApiExplorer {
    DateNow date = new DateNow();
    TimeNow time = new TimeNow();

    String serviceKey = "M7UdcUfNMFBu8D3ng0rZrilA8oNgv1Sfr3kT%2BdeJphKw5BlLPTksBL2suXd1hMK5hQ5XMr5hCsgsFDNzfQ7UUg%3D%3D";
    String pageNo = "1";
    String numOfRows = "10";
    String dataType = "json";
    String base_date = date.DateNow();
    String base_time = time.TimeNow();
    String nx ;
    String ny ;

    String PTY;
    String RN1;
    String REH;
    String T1H;

    public void ApiExplorer(String NX,String NY) throws Exception {

        this.nx=NX;
        this.ny=NY;

        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + serviceKey); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode(pageNo, "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode(numOfRows, "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("dataType", "UTF-8") + "=" + URLEncoder.encode(dataType, "UTF-8")); /*요청자료형식(XML/JSON) Default: XML*/
        urlBuilder.append("&" + URLEncoder.encode("base_date", "UTF-8") + "=" + URLEncoder.encode(base_date, "UTF-8")); /*‘21년 6월 28일 발표*/
        urlBuilder.append("&" + URLEncoder.encode("base_time", "UTF-8") + "=" + URLEncoder.encode(base_time, "UTF-8")); /*06시 발표(정시단위) */
        urlBuilder.append("&" + URLEncoder.encode("nx", "UTF-8") + "=" + URLEncoder.encode(nx, "UTF-8")); /*예보지점의 X 좌표값*/
        urlBuilder.append("&" + URLEncoder.encode("ny", "UTF-8") + "=" + URLEncoder.encode(ny, "UTF-8")); /*예보지점의 Y 좌표값*/


        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
//        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        String result = sb.toString();
//        System.out.println(result);


        // Json parser를 만들어 만들어진 문자열 데이터를 객체화
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(result);

        // response 키를 가지고 데이터를 파싱
        JSONObject parse_response = (JSONObject) obj.get("response");

        // response 로 부터 body 찾기
        JSONObject parse_body = (JSONObject) parse_response.get("body");

        // body 로 부터 items 찾기
        JSONObject parse_items = (JSONObject) parse_body.get("items");
        JSONArray parse_item = (JSONArray) parse_items.get("item");
        //JSONObject item = (JSONObject) parse_item.get("item");


        for (int i = 0; i < parse_item.size(); i++) {
//            System.out.println(parse_item.get(i));
            JSONObject item = (JSONObject) parse_item.get(i);
            String category = (String) item.get("category");
            String obsrValue = (String) item.get("obsrValue");
//
            //강수상태
            if (category.equals("PTY")) {
                PTY=obsrValue;
            }

            //1시간 강수량
            else if (category.equals("RN1")) {
                RN1 = obsrValue;
            }

            //습도 출력
            else if (category.equals("REH")) {
                REH = obsrValue;
            }

            //기온 출력
            else if (category.equals("T1H")) {
                T1H = obsrValue;
            }
//
        }


    }


}
/*
         * 항목값	항목명	단위
         * POP	강수확률	 %
         * RN1  1시간 강수량
         * PTY	강수형태	코드값
         * 강수형태(PTY) 코드 : (초단기) 없음(0), 비(1), 비/눈(2), 눈(3), 빗방울(5), 빗방울눈날림(6), 눈날림(7)
                      (단기) 없음(0), 비(1), 비/눈(2), 눈(3), 소나기(4)
         * R06	6시간 강수량	범주 (1 mm)
         * T1H  기온
         * REH	습도	 %
         * S06	6시간 신적설	범주(1 cm)
         * SKY	하늘상태	코드값
         * 하늘상태(SKY) 코드 : 맑음(1), 구름많음(3), 흐림(4)
         * T3H	3시간 기온	 ℃
         * TMN	아침 최저기온	 ℃
         * TMX	낮 최고기온	 ℃
         * UUU	풍속(동서성분)	 m/s
         * VVV	풍속(남북성분)	 m/s
         * WAV	파고	 M
         * VEC	풍향	 m/s
         * WSD	풍속	1
         */
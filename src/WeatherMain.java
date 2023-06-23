
import org.json.simple.JSONObject;

public class WeatherMain {
    public static void main(String[] args) throws Exception {
        ApiExplorer weatherAPI=new ApiExplorer();
        weatherAPI.ApiExplorer();



        //강수상태 출력
        System.out.print("강수상태: ");
        if (weatherAPI.PTY.equals("0"))
            System.out.println("강수X");
        else if (weatherAPI.PTY.equals("1"))
            System.out.println("비");
        else if (weatherAPI.PTY.equals("2"))
            System.out.println("비/눈");
        else if (weatherAPI.PTY.equals("3"))
            System.out.println("눈");
        else if (weatherAPI.PTY.equals("5"))
            System.out.println("빗방울");
        else if (weatherAPI.PTY.equals("6"))
            System.out.println("빗방울/눈날림");
        else if (weatherAPI.PTY.equals("7"))
            System.out.println("눈날림");


        //1시간 강수량
        System.out.print("1시간 강수량: ");
        System.out.println(weatherAPI.RN1 + " mm");

        //습도 출력
        System.out.print("습도");
        System.out.println(weatherAPI.REH + " %");

        //기온 출력
        System.out.print("기온");
        System.out.println(weatherAPI.T1H + " ℃");

    }


}

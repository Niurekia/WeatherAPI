

import java.time.LocalTime;

public class TimeNow{

    String TimeNow() {

        LocalTime time=LocalTime.now();
        int hour=time.getHour()-1;
        int minute= time.getMinute();
        if(hour<0)
            hour=0;
        String strHour=hour+"";
        String strMinute=minute+"";
        if(hour<10)
            strHour="0"+hour;


        String strTime=strHour.concat(strMinute);
        return strTime;
    }
}
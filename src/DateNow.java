
import java.time.LocalDate;

public class DateNow {


        String DateNow(){
            LocalDate date=LocalDate.now();

            int year=date.getYear();
            int month=date.getMonthValue();
            int day=date.getDayOfMonth();
            String strYear=year+"";
            String strMonth;
            String strDay=day+"";
            if(month<10)
                strMonth="0"+month;
            else
                strMonth=month+"";

            String strDate=strYear.concat(strMonth);
            strDate=strDate.concat((strDay));

            return strDate;
        }

}

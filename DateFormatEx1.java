import java.util.*;
import java.text.*;

// DateFormat SimpleDateFormat 예제

public class DateFormatEx1 {
    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();
        
        Date day = cal.getTime();

        SimpleDateFormat sdf1, sdf2, sdf3, sdf4;

        sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        sdf2 = new SimpleDateFormat("yyyy-MM-dd E요일");
        sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        sdf4 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");

        System.out.println(sdf1.format(day));
        System.out.println(sdf2.format(day));
        System.out.println(sdf3.format(day));
        System.out.println(sdf4.format(day));

        DateFormat df, df2;

        df = new SimpleDateFormat("yyyy년 MM월 dd일");
        df2 = new SimpleDateFormat("yyyy//MM//dd");

        try{
            Date d = df.parse("2015년 11월 23일");
            System.out.println(df2.format(d));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

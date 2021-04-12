import java.util.Calendar;

// calendar 기본  + 날짜 &  시간 차이 구하기
import static java.lang.System.*;

public class CalendarEx1 {
    public static void main(String[] args) {

        // Calendar 기본
        Calendar today = Calendar.getInstance();
        out.println("이 해의 연도  : " + today.get(Calendar.YEAR));
        out.println("월 : " + today.get(Calendar.MONTH));
        out.println("이 해의 몇 째 주 : " + today.get(Calendar.WEEK_OF_YEAR));
        out.println("이 달의 몇 째 주 : " + today.get(Calendar.WEEK_OF_MONTH));

        out.println("이 달의 몇 일 : " + today.get(Calendar.DATE));
        out.println("이 달의 몇 일 : " + today.get(Calendar.DAY_OF_MONTH));
        out.println("이 해의 몇 일 : " + today.get(Calendar.DAY_OF_YEAR));

        out.println("요일 (1=일요일) : "  + today.get(Calendar.DAY_OF_WEEK));  
        out.println("이 달의 몇 째 요일 : "  + today.get(Calendar.DAY_OF_WEEK_IN_MONTH));
        out.println("오전0_오후1 : " + today.get(Calendar.AM_PM));
        out.println("시간 : " + today.get(Calendar.HOUR));
        out.println("시간24 : " + today.get(Calendar.HOUR_OF_DAY));
        out.println("분 : " + today.get(Calendar.MINUTE));
        out.println("초 : " + today.get(Calendar.SECOND));
        out.println("1000의 1초 " + today.get(Calendar.MILLISECOND));
        out.println("타임존 : " + today.get(Calendar.ZONE_OFFSET));
        out.println("이 달의 마지막 날 : " + today.getActualMaximum(Calendar.DATE));

        //일짜 차이 구하고 상황별로 표시

        Calendar date1 = Calendar.getInstance();
        Calendar date2 = Calendar.getInstance();

        date1.set(2021, 7, 15); // 2021. 8. 15

        long difference = date1.getTimeInMillis() - date2.getTimeInMillis();

        out.println("몇 일이 지났습니까? : " +  difference/(24*60*60*1000)); 

        //시간 차이

        final int [] TIME_UNIT = { 3600, 60, 1};
        final String[] TIME_UNIT_NAME = {"시간 ", "분 ", "초 "};
        Calendar date3 = Calendar.getInstance();
        Calendar date4 = Calendar.getInstance();

        date3.set(Calendar.HOUR_OF_DAY, 10);
        date3.set(Calendar.MINUTE , 20);
        date3.set(Calendar.SECOND , 30);

        date4.set(Calendar.HOUR_OF_DAY, 20);
        date4.set(Calendar.MINUTE, 30);
        date4.set(Calendar.SECOND, 10);

        out.println("차이는 몇 초입니다. :" + Math.abs(date3.getTimeInMillis()-date4.getTimeInMillis()));

        long difference2 = Math.abs(date3.getTimeInMillis()-date4.getTimeInMillis())/1000;

        String tmp = "";

        for(int i = 0 ; i < TIME_UNIT.length; i++){
            tmp += difference2/TIME_UNIT[i] + TIME_UNIT_NAME[i];
            difference2 %= TIME_UNIT[i];
        }
        out.println("시분초 변환은 " +tmp);

    }
}

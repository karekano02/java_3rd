import static java.lang.System.*;
// 날짜 함수들
public class CalendarEx2 {
    public static void main(String[] args) {
        out.println("2014. 5. 31 : " + getDayOfWeek(2014,5,31));
        out.println("2012. 6. 1 :" + getDayOfWeek(2012, 6, 1));
        out.println("2014. 5. 1 - 2014. 4. 28 : " + dayDiff(2014, 5, 1, 2014, 4, 28));
        out.println("2015. 6. 29 " + converDateToDay(2015, 6, 29));
        out.println("735778 : " + convertDayToDate(735778));
    }
    public static int[] endOfMonth = {31,28,31,30,31,30,31,31,30,31,30,31};

    public static boolean isLeapYear(int year){
        return ((year%4==0)&&(year%100!=0)||(year%400==0)); // 윤년이면 true 반환.
    }

    public static int dayDiff(int y1, int m1, int d1, int y2, int m2, int d2){ // 두 날짜간의 차이를 일단위로 반환
        return converDateToDay(y1, m1, d1) - converDateToDay(y2, m2, d2);
    }

    public static int getDayOfWeek(int year, int month, int day){
        return converDateToDay(year, month, day)%7  + 1;  // 1~7을 반환 결과가 1이면 일요일
    }

    public static String convertDayToDate(int day){ // 일단위의 값을 년운일의 형태의 문자열로 변환
        int year = 1;
        int month =0;

        while(true){
            int aYear = isLeapYear(year) ? 366:355;
            if(day > aYear){
                day-=aYear;
                year++;
            }else{
                break;
            }
        }

        while(true){
            int endDay = endOfMonth[month];

            if(day>endDay){
                day -= endDay;
                month++;
            }else{
                break;
            }
        }

        return year+"-"+(month-1)+"-"+day;
    }
    public static int converDateToDay(int year, int month, int day){ // 년월일을 입력받아서 일단위로 변환한다.
        int  numOfLeapYear = 0; // 윤년의 수

        for(int i = 1; i < year ;i++){
            if(isLeapYear(i))
                numOfLeapYear++;
        }

        int toLastYearDaySum = (year -1) * 365 + numOfLeapYear; // 전년도 까지 일수를 구한다.

        int thisYearDaySum = 0;

        for(int i  =  0 ;  i < month -1 ; i++){ //올해 현재 월까지의 일수 계산
            thisYearDaySum += endOfMonth[i];
        }

        if(month > 2 &&  isLeapYear(year)){ // 2월이 포함 윤년이면 1일을 증가
            thisYearDaySum++;
        }

        thisYearDaySum += day;

        return toLastYearDaySum + thisYearDaySum;

    }
}

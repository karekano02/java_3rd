import java.time.*;
import java.time.format.DateTimeFormatter;

// DateFormatter java(1.8)

public class DateFormatterEx1 {
    public static void main(String[] args) {

        // 예제1 파싱과 포멧
        ZonedDateTime zdateTime = ZonedDateTime.now();
        
        String[] patternArr = {

            "yyyy-MM-dd HH:mm:ss",
            "''yy년 MMM dd일 E요일",
            "yyyy-MM-dd HH:mm:ss.SSS Z VV",
            "yyyy-MM-dd hh:mm:ss a",
            "오늘은 올 해의 D번째 날입니다.",
            "오늘은 이 달의 d번째 날입니다.",
            "오늘은 올 해의 w번째 주입니다.",
            "오늘은 이 달의 W번째 주입니다.",
            "오늘은 이 달의 W번째 E요일입니다."
        };

        for(String p : patternArr){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(p);

            System.out.println(zdateTime.format(formatter));
        }

        //예제2 문자열을 날짜와 시간으로 파싱하기

        LocalDate newYear = LocalDate.parse("2016-01-01", DateTimeFormatter.ISO_LOCAL_DATE);

        LocalDate date = LocalDate.parse("2001-01-01");

        LocalTime time = LocalTime.parse("23:59:59");
        
        LocalDateTime dateTime = LocalDateTime.parse("2001-01-01T23:59:59");
        
        DateTimeFormatter pattern =  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime endOfYear = LocalDateTime.parse("2015-12-31 23:59:59", pattern);

        System.out.println(newYear);
        System.out.println(date);
        System.out.println(time);
        System.out.println(dateTime);
        System.out.println(endOfYear);

    }
}

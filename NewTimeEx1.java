import java.time.*;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

//java.time java(1.8)
public class NewTimeEx1{
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();

        LocalDate birthDate = LocalDate.of(1999,12,31);
        LocalTime birthTime = LocalTime.of(23,59,59);

        System.out.println("today = " + today);
        System.out.println("now = " + now);
        System.out.println("birthDate = " + birthDate);
        System.out.println("birthTime = " + birthTime);

        System.out.println(birthDate.withYear(2000));
        System.out.println(birthDate.plusDays(1));
        System.out.println(birthDate.plus(1,ChronoUnit.DAYS));

        System.out.println(birthTime.truncatedTo(ChronoUnit.HOURS)); // 작은 단위를 0 으로 
        System.out.println(birthTime.truncatedTo(ChronoUnit.MINUTES));
        System.out.println(birthTime.truncatedTo(ChronoUnit.SECONDS));

        System.out.println(ChronoField.CLOCK_HOUR_OF_DAY.range());
        System.out.println(ChronoField.HOUR_OF_DAY.range());


        LocalDate date = LocalDate.of(2015, 12, 31);
        LocalTime time = LocalTime.of(12, 34, 56 );

        LocalDateTime dt = LocalDateTime.of(date, time);

        //LocalDateTime --> ZoneDateTime 변경
        ZoneId zid = ZoneId.of("Asia/Seoul");
        ZonedDateTime zdt = dt.atZone(zid); 
        // String strZid = zdt.getZone().getId();

        ZonedDateTime seoulTime = ZonedDateTime.now();
        ZoneId nyId = ZoneId.of("America/New_York");
        ZonedDateTime nyTime = ZonedDateTime.now().withZoneSameInstant(nyId);  // 현재 뉴욕시간

        //ZonedDatetime(ZoneId 로 표현) --> OffsetDateTime(ZonedOffset 으로 표현)    
        OffsetDateTime odt = zdt.toOffsetDateTime();

        System.out.println(dt);
        System.out.println(zid);
        System.out.println(zdt);
        System.out.println(seoulTime);
        System.out.println(nyTime);
        System.out.println(odt);

        // System.out.println(strZid);

    }
}

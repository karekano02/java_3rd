import java.time.*;
import java.time.temporal.*;
import static java.time.DayOfWeek.*;
import static java.time.temporal.TemporalAdjusters.*;

//TemporalAdjusters 날짜 계산 대신 해주는 메서드

class DayAfterTomorrow implements TemporalAdjuster{ 
    @Override
    public Temporal adjustInto (Temporal temporal){
        return temporal.plus(2, ChronoUnit.DAYS);
    }
}

public class NewTimeEx2 {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        LocalDate date = today.with(new DayAfterTomorrow());  // abjustInto는 내부적으로 사용되기 때문에 대도록 이면 with를 쓰자
        
        System.out.println(today);   
        System.out.println(date);
        System.out.println(today.with(firstDayOfNextMonth()));         //다음달의 첫날
        System.out.println(today.with(firstDayOfMonth()));             //이 달의 첫날
        System.out.println(today.with(lastDayOfMonth()));              //이 달의 마지막날  
        System.out.println(today.with(firstInMonth(TUESDAY)));         //이 달의 첫번째 화요일
        System.out.println(today.with(lastInMonth(TUESDAY)));          //이 달의 마지막 화요일
        System.out.println(today.with(previous(TUESDAY)));             //지난 주 화요일
        System.out.println(today.with(previousOrSame(TUESDAY)));       //지난 주 화요일(오늘포함)
        System.out.println(today.with(next(TUESDAY)));                 //다음 주 화요일
        System.out.println(today.with(nextOrSame(TUESDAY)));           //다음 주 화요일(오늘포함)
        System.out.println(today.with(dayOfWeekInMonth(4, TUESDAY)));   //이 달의 4번째 화요일
    }
}

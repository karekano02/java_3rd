//Exact java  1.8 추가 사항  오버플로우시 예외상황
import static java.lang.System.*;

public class MathExactEx {
   public static void main(String[] args) {
        int i = Integer.MIN_VALUE;

        out.println("i =" + i );
        out.println("-i =" + (-i));

        try{
            out.printf("negateExact(%d) = %d%n", 10, Math.negateExact(10));
            out.printf("negateExact(%d) = %d%n", -10, Math.negateExact(-10));
            out.printf("negateExact(%d) = %d%n", i , Math.negateExact(i)); // 예외
        }catch(ArithmeticException e){
            out.printf("예외 사항 : negateExact(%d) = %d%n", (long)i , Math.negateExact((long)i));
        }
   } 
}

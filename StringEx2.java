//String 예제 2
public class StringEx2 {
    public static void main(String[] args) {
        int iVal = 100;
        String strVal = String.valueOf(iVal);

        double dVal = 200.0;
        String strVal2 = dVal + "";

        double sum = Integer.parseInt("+"+strVal.trim()) + Double.parseDouble(strVal2.trim());  // "+" 가 parseInt 가능한건 java 1.7 부터

        double sum1 = Integer.valueOf(strVal) + Double.valueOf(strVal2);

        System.out.println(String.join("",strVal,"+",strVal2," = ")+sum); 
        System.out.println(strVal + "+" +strVal2+" = "+sum1);
       

    }
}

import java.text.DecimalFormat;

// FormatEx1 숫자를 패턴으로 + parse 매서드

public class FormatEx1 {
    public static void main(String[] args) {

        // 숫자를 포멧으로

        double number = 1234567.89;
        String[] pattern = {
            "0",
            "#",
            "0.0",
            "#.#",
            "0000000000.0000",
            "##########.####",
            "#.#-",
            "-#.#",
            "#,###.##",
            "#,####.##",
            "#E0",
            "0E0",
            "##E0",
            "0000E0",
            "#.#E0",
            "0.0E0",
            "0.000000000E0",
            "00.00000000E0",
            "000.0000000E0",
            "#.#########E0",
            "##.########E0",
            "###.#######E0",
            "#,###.##+;#,###.##-",
            "#.#%",
            "#.#\u2030",
            "\u00A4 #,###",
            "'#'#,###",
            "''#,###"
        };

        for(int i = 0 ; i < pattern.length; i++){
            DecimalFormat df = new DecimalFormat(pattern[i]);
            DecimalFormat df2 = new DecimalFormat("#,###.##");
            System.out.printf("%19s : %s \n", pattern[i], df.format(number)); 
        }

        // 문자열을 숫자로

        DecimalFormat df = new DecimalFormat("#,###.##");
        DecimalFormat df2 = new DecimalFormat("#.###E0");


        try{
            Number num = df.parse("1,234,567.89");
            System.out.print("1,234,567.89  - >  ");

            double d = num.doubleValue();
            System.out.print(d +  "   ->  ");

            System.out.println(df2.format(num));
            System.out.println(df.format(d));
        }catch(Exception e){}
    }
}

import java.util.regex.*;

public class ReqularEx {
    public static void main(String[] args) {
        // 예제 1
        String[] data = {"bat", "baby", "bonus", "c", "cA", "ca", "co", "c.", "c0", "c#", "car", "combat", "count", "date", "disc"};

        String[] pattern = {".*", "c[a-z]*", "c[a-z]", "c[a-zA-Z]", "c.", "c.*", "c\\.", "c\\w", "c\\d", "c.*t", "[b|c].*", ".*a.*", ".*a.+"};

        for(int i = 0 ; i < pattern.length ; i++){
            Pattern p = Pattern.compile(pattern[i]);
            System.out.print("Patten : " + pattern[i] + " 결과 : ");
            for(int j = 0 ; j < data.length; j++){
                Matcher m = p.matcher(data[j]);
                if(m.matches())
                    System.out.print(data[j] + ",");
            }  
            System.out.println();
        }

        //예제2
        String source = "HP:011-1111-1111, HOME:02-999-9999 ";
        String pattern2 = "(0\\d{1,2})-(\\d{3,4})-(\\d{4})";
        
        Pattern p2 = Pattern.compile(pattern2);
        Matcher m2 = p2.matcher(source);

        int i = 0;

        while(m2.find()){
            System.out.println(++i + ": " + m2.group(0) + " --> " + m2.group(1) + " --> " + m2.group(2) + " --> " + m2.group(3));
            System.out.println(m2.group());
        }

        //예제3
        String source1 = "A broken hand works, but not a broken heart.";
        String patten3 = "broken";
        StringBuffer sb = new StringBuffer();

        Pattern p3 = Pattern.compile(patten3);
        Matcher m3 = p3.matcher(source1);
        System.out.println("source1 : " + source1 );

        int x = 0;

        while(m3.find()){
            System.out.println(++x + "번째 매칭:" + m3.start() + " ~ " + m3.end());
            m3.appendReplacement(sb, "drunken");    // source 의 시작 위치 부터 broken 을 찾을 위치까지의 내용을 drunken을 더해서 저장한다. A drunken -> A drunken hand works, but not a drunken     
        }

        m3.appendTail(sb);

        System.out.println("Replacement count : " + x);
        System.out.println("result : " + sb.toString());
    }
}

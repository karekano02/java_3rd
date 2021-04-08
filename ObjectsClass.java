// java.util.Object 클래스
import java.util.*;
// import static java.util.Objects.*;
import static java.lang.System.*;

public class ObjectsClass {
    public static void main(String[] args) {
       String[][] str2D = new String[][]{{"aaa","bbb"},{"AAA","BBB"}};
       String[][] str2D_2 = new String[][]{{"aaa","bbb"},{"AAA","BBB"}};

       out.print("str2D = {");

       for(String[] tmp : str2D)
            out.print(Arrays.toString(tmp));
        out.println("}");

        out.print("str2D_2 = {");

        for(String[] tmp : str2D_2)
            out.print(Arrays.toString(tmp));
        out.println("}");

        out.println("equals(str2D, str2D_2) = " + Objects.equals(str2D, str2D_2) ); //null을 검사안해도 된다
        out.println("deepEquals(str2D,str2D_2) = " + Objects.deepEquals(str2D , str2D_2)); //다차원 배열 비교
        out.println("isNull = " + Objects.isNull(null));
        out.println("hashCodd = " + Objects.hashCode(null));
        out.println("toSting =  " + Objects.toString(null));
        out.println("toSting = \"\"" + Objects.toString(null,""));

        Comparator c = String.CASE_INSENSITIVE_ORDER; // 대소문자 구분 안하는 비교 

        out.println("compare(\"aa\",\"bb\") = " + Objects.compare("aa","bb",c));
        out.println("compare(\"bb\",\"aa\") = " + Objects.compare("bb","aa",c));
        out.println("compare(\"ab\",\"AB\") = " + Objects.compare("ab","AB",c));
    }
}

class Objects1{
    String name;
    String name1;

    Objects1(String name, String name1){
        this.name = name;
        this.name1 = name1;
    }

     void setName(String name){
        this.name = Objects.requireNonNull(name, "name must not be null.");  // null이면 Exception 발생 

        /* 
            if(name == null){
                throw new NullPointerException("2313");
            }

            this.name = name;

        */


        if(Objects.equals(name, name1)){} // if(name != null && name.equals(name1)){} null을 검사안해도 된다

        Objects.deepEquals(name, name1); // 다차배열 비교 할땐 이것
    }
}

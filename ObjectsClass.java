// java.util.Object 클래스
import java.util.*;

public class ObjectsClass {
    public static void main(String[] args) {
        Objects1 o1 = new Objects1("name");
    }
}

class Objects1{
    String name;
    String name1;

    Objects1(String name){
        this.name = name;
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

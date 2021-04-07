import java.util.StringJoiner;

// join StringJoiner    java 1.8
public class StringEx1 {
    public static void main(String[] args) {
        String animals = "dog,cat,bear";
        String[] arr = animals.split(",");

        System.out.println(String.join("-",arr));

        StringJoiner sj = new StringJoiner("/","[","]");
        for(String tmp : arr){
            sj.add(tmp);
        }

        System.out.println(sj.toString());


    }
}

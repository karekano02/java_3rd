import java.util.Arrays;
import java.util.Comparator;

// ComparatorEx 정렬관련  기본 정렬 및 다른 기준으로 정렬 하고자 할때 
public class ComparatorEx {
    public static void main(String[] args) {
        String[] strArr = {"cat", "Dog", "lion", "tiger"};

        Arrays.sort(strArr);
        System.out.println("strArr = " + Arrays.toString(strArr));

        Arrays.sort(strArr, String.CASE_INSENSITIVE_ORDER );
        System.out.println("strArr = " + Arrays.toString(strArr));

        Arrays.sort(strArr, new Descending());
        System.out.println("strArr = " + Arrays.toString(strArr));
    }
}


class Descending implements Comparator{
    public int compare(Object o1, Object o2){
        if(o1 instanceof Comparable && o2 instanceof Comparable){
            Comparable c1 = (Comparable)o1;
            Comparable c2 = (Comparable)o2;
            return c1.compareTo(c2)* -1; // -1을 곱해서 기본 정렬방식의 역으로 변경한다.
                                        // 또는 c2.compareTo(c1)와 같이 순서를 변경해도 된다.
        }
        return -1 ;
    }
}
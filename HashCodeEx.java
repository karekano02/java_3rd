// HashCode 예제
public class HashCodeEx {
    public static void main(String agr[]){
        String st1 = "abc";
        String st2 = "abc";

        System.out.println(st1.equals(st2));
        System.out.println(st1.hashCode());
        System.out.println(st2.hashCode());
        System.out.println(System.identityHashCode(st1));
        System.out.println(System.identityHashCode(st2));
    }
}

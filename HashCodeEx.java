// HashCode 예제
public class HashCodeEx {
    public static void main(String agr[]){
        String st1 = new String("abc");
        String st2 = new String("abc");

        // String st1 = "as";
        // String st2 = "as";

        System.out.println(st1.equals(st2));
        System.out.println(st1.hashCode());  // String 문자열 내용이 같으면 동일한 값을 보내도록 오버라이딩 되어있다.
        System.out.println(st2.hashCode());
        System.out.println(System.identityHashCode(st1)); // 객체의 주소값을 주기 때문에 다르다  단 new 로 만들어야 한다.
        System.out.println(System.identityHashCode(st2));
    }
}

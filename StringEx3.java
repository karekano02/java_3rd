//String 기능 substring stringbuffer
public class StringEx3 {
    public static void main(String[] args) {
        String fullName = "hello.java";

        int index = fullName.indexOf(".");

        String fileName = fullName.substring(0, index);

        String ext = fullName.substring(index+1);

        System.out.println(fileName);
        System.out.println(ext);

        StringBuffer sb = new StringBuffer("abc");
        StringBuffer sb2 = new StringBuffer("abc");

        System.out.println("sb = sb2 ::" + (sb==sb2));
        System.out.println("sb.equals(sb2) ::" + sb.equals(sb2));

        String s = sb.toString();
        String s1 = sb2.toString();

        System.out.println(s.equals(s1));

    }
}

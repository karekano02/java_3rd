//String 기능 substring
public class StringEx3 {
    public static void main(String[] args) {
        String fullName = "hello.java";

        int index = fullName.indexOf(".");

        String fileName = fullName.substring(0, index);

        String ext = fullName.substring(index+1);

        System.out.println(fileName);
        System.out.println(ext);

    }
}

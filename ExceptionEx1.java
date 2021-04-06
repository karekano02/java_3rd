// Exception 고의로 발생
public class ExceptionEx1 {
    public static void main(String[] args) {
        try{
            Exception e = new Exception("고의로 발생");
            throw e ; // 발생
            // throw new Exception("고의로 발생"); // 한줄로 표현
        }catch(Exception e){
            System.out.println("에러메세지 : " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("프로그램이 종료되었습니다.");
    }
}

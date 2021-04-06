// 예외 되던지기
public class ExceptionRe {
    public static void main(String[] args) {
        try{
            method1();
        }catch(Exception e){
            System.out.println("main에서 발생");
        }
    }
    static void method1() throws Exception{
        try{
            throw new Exception();
            // return 0; 반환값이 있으면
        }catch(Exception e){
            System.out.println("method1에서 발생");
            // return 0; catch문에도 있어야 한다. 단 던지기가 있으면 안해도 된다. 
            throw e;  //다시 발생
        }
    }
}

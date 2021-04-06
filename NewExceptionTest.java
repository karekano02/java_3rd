// 새롭게 발전하는 Exception 예외처리의 필요에 따라 선택 변경
public class NewExceptionTest {

    public static void main(String[] args) {
        try{
            startInstall();
            copyFile();
        }catch(SpaceException e){
            System.out.println("에러 메시지 : " + e.getMessage());
            e.printStackTrace();
            System.out.println("공간을 확보한 후에 다시 설치하지기 바랍니다.");   
        }catch(MemoryException e){
            System.out.println("에러 메시지 : " + e.getMessage());
            e.printStackTrace();
            System.gc();
            System.out.println("다시 설치를 시도하세요.");
        }finally{
            deleteTempFiles();
        }
    }

    static void startInstall() throws SpaceException, MemoryException{
        if(!enoughspace())
            throw new SpaceException("설치할 공간이 부족합니다.");
        if(!enoughMemory())
            throw new MemoryException("메모리가 부족합니다.");
    }
    static void copyFile(){}
    static void deleteTempFiles(){}
    static boolean enoughspace(){
        return true;
    }
    static boolean enoughMemory(){
        return false;
    }
}

class SpaceException extends Exception{
    SpaceException(String msg){
        super(msg);
    }
}

class MemoryException extends Exception{
    MemoryException(String msg){
        super(msg);
    }
}
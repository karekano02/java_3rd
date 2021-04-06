import javax.management.RuntimeErrorException;

// 새롭게 발전하는 Exception 예외처리의 필요에 따라 선택 변경 + 연결된 예외
public class NewExceptionTest {

    public static void main(String[] args) {
        try{
            install();
        }catch(InstallException e){
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            deleteTempFiles();
        }
    } 

    static void install() throws InstallException{
        try{
            startInstall();
            copyFile();  
        }catch(SpaceException e){
            // System.out.println("에러 메시지 : " + e.getMessage());
            // e.printStackTrace();
            // System.out.println("공간을 확보한 후에 다시 설치하지기 바랍니다.");             
            InstallException ie = new InstallException("설치 중 예외 발생");
            ie.initCause(e);
            throw ie;
        }catch(MemoryException e){
        // }catch(RuntimeErrorException e){
            // System.out.println("에러 메시지 : " + e.getMessage());
            // e.printStackTrace();
            // System.gc();
            // System.out.println("다시 설치를 시도하세요.");
            InstallException ie = new InstallException("설치 중 예외 발생");
            ie.initCause(e);
            throw ie;
        }finally{
            deleteTempFiles();
        }
    }

    static void startInstall() throws SpaceException, MemoryException{
    // static void startInstall() throws SpaceException{
        if(!enoughspace())
            throw new SpaceException("설치할 공간이 부족합니다.");
        if(!enoughMemory())
            throw new MemoryException("메모리가 부족합니다.");
            // throw new RuntimeException(new MemoryException("메모리가 부족합니다."));
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

class InstallException extends Exception{
    InstallException(String msg){
        super(msg);
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
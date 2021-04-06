//Exception main 인지
import java.io.*;
public class ExceptionEx3 {
    public static void main(String[] args) {
        try{
            File f  = createFile("첫파일");
            System.out.println(f.getName() + "파일이 성공적으로 생성되었습니다.");
        }catch(Exception e){

        }
    }

    static File createFile(String fileName)throws Exception{
        if(fileName == null || fileName.equals(""))
            throw new Exception("파일이름이 유효하지 않습니다.");
        File f = new File(fileName);
        f.createNewFile();
        return f;
    }
}

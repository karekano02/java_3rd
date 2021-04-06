//Exception main 인지 X
import java.io.*;
public class ExceptionEx2 {
    public static void main(String[] args) {
        File f =  createFile(args[0]);
        System.out.println(f.getName() + "파일이 성공적으로 생성되었습니다.");
    }

    static File createFile(String fileName){
        try{
            if(fileName == null || fileName.equals(""))
                throw new Exception("파일 이름이 유효하지 않습니다.");
        }catch(Exception e){
            fileName = "제목없음.txt";
        }finally{
            File f = new File(fileName);
            createNewFile(f);           //생성된 객체를 가지고 파일생성
            return f;
        }
    }

    static void createNewFile(File f){
        try{
            f.createNewFile();
        }catch(Exception e){

        }
        
    }
}

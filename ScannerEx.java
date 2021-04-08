import java.util.Scanner;
import java.io.File;

/*
txt
    100,100,100
    200,200,200
    300,300,300

*/

//텍스트 파일 읽어와서 입력
public class ScannerEx {
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(new File("aaaa.txt"));
        int cnt = 0;
        int totalSum = 0;

        while(sc.hasNextLine()){
            String line = sc.nextLine();
            Scanner sc2  = new Scanner(line).useDelimiter(",");
            int sum = 0;

            while(sc2.hasNextLine()){
                sum += sc2.nextInt();
            }

            System.out.println(line + ", sum = " + sum );

            totalSum += sum;
            cnt++;
        }
        System.out.println("Line: " + cnt + ", Total : " + totalSum);

    }
}

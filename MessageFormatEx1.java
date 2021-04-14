import java.text.MessageFormat;

// MessageFormat 

public class MessageFormatEx1 {
    public static void main(String[] args) {

        // EX1 format
        String tableName = "CUST_INFO";
        String msg = "INSERT INTO " + tableName + " VALUES (''{0}'',''{1}'',''{2}'',''{3}'')";

        Object[][] arguments = {
            {"이자바", "02-111-1234", "26", "04-29"},
            {"김프로", "02-123-4325", "27", "02-21"}
        };

        for(int i = 0; i < arguments.length; i++){
            String result = MessageFormat.format(msg, arguments[i]);
            System.out.println(result);
        }


        // EX2 parse

        String[] data = {
            "INSERT INTO CUST_INFO VALUES ('이자바', '02-111-1234', '26', '04-29');",
            "INSERT INTO CUST_INFO VALUES ('김프로', '02-113-1235', '36', '02-11');"
        };

        String pattern = "INSERT INTO CUST_INFO VALUES ({0},{1},{2},{3});";
        MessageFormat mf = new MessageFormat(pattern);

        try{
            for(int i = 0 ; i < data.length ; i++){
                Object[] obs = mf.parse(data[i]);
                for(int j = 0 ; j < obs.length ; j++)
                    System.out.print(obs.length-1 == j ? obs[j] : obs[j]+",");
                System.out.println();
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        // EX3 파일에서 입력 받고 출력하기 

        /*
            파일에서 한줄 가져오기 ...

            String fileName = "data.txt";

            Scanner s = new Scanner(new File(fileName));

            while(s.hasNextLine()){
                String line = s.nextLine();
            }
        */

        String line = "'자바', '02-111-1234', 56, '04-29'"; // 파일 라인
        String msg1 =  "INSERT INTO CUST_INFO VALUES ({0},{1},{2},{3});";
        String pattern1 = "{0},{1},{2},{3}";
        MessageFormat mf1 = new MessageFormat(pattern1); 

        try {
            Object[] obs1 = mf1.parse(line);  // 라인에서 패턴에 맞는 값 추출...
            System.out.println(MessageFormat.format(msg1, obs1));
        } catch (Exception e) {}

    }
}

import java.text.ChoiceFormat;

// ChoiceFormatEx 예제

public class ChoiceFormatEx1 {
    public static void main(String[] args) {
        int[] scores = {91, 90, 80, 88, 70, 52, 60};

        String pattern  = "60#D|70#C|80<B|90#A";  // # 포함 < 불포함
        ChoiceFormat form = new ChoiceFormat(pattern);

        /*
            double[] limit = {60, 70, 80 90};
            String[] grades = {"D", "C", "B", "A"};  // limit 와 grades 갯수 맞추기
            ChoiceFormat form = new ChoiceFormat(limit, grades);
        */
        

        for(int i = 0; i < scores.length ; i++){
            System.out.println(scores[i] + " : " + form.format(scores[i]));
        }
    }    
}

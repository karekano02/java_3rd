import java.util.Random;

// 랜덤 그래프 그리기
public class RandomEx1 {
    public static void main(String[] args) {
        Random rand = new Random();
        int[] number = new int[100];
        int[] count = new int[10];

        for(int i = 0; i < number.length  ; i++){
            System.out.print(number[i] = rand.nextInt(10)); // 0<= x  <10
        }

        System.out.println();

        for(int i = 0; i < number.length; i++){
            count[number[i]]++;
        }

        for(int i = 0; i < count.length; i++){
            System.out.println(i + "의 개수 : " +printGraph(count[i]) +" " + count[i] );
        }

    }

    public static String printGraph(int value){
        char[] bar = new char[value];
        for(int i=0;i<value;i++){
            bar[i] = '#';
        }

        return new String(bar);
    }
}

import java.util.Arrays;

// clone 배열
public class CloneEx1 {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};
        int[] arrClone  = arr.clone();
        arrClone[0]  = 0;

        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(arrClone));
    }
}

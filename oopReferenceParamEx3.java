public class oopReferenceParamEx3 {
    public static void main(String[] args){
        int[] arr = {3,2,1,6,5,4};

        printArr(arr);
        System.out.println("sum = " +sumArr(arr));
        sortArr(arr);
        printArr(arr);

    }

    static void printArr(int[] arr){
        System.out.print("[");

        for(int a : arr){
            System.out.print(a+", ");
        }
        System.out.println("]");
    }

    static int sumArr(int[] arr){

        int sum = 0;

        for(int a = 0 ; a < arr.length ; a ++){
            sum += arr[a];    
        }

        return sum;
    }

    static void sortArr(int[] arr){
        for(int i = 0; i < arr.length -1 ; i++){
            for(int j = 0 ; j < arr.length -1 -i ; j++){
                if(arr[j] > arr[j+1]){
                    int tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
                }
            }
        }
    }

}



public class FactorialTest {
    public static void main(String[] args){
        int result = factorial(4);

        System.out.println("result = " + result);
    }

    static int factorial(int a){

        int result = 0 ;
        if(a == 0){
            result = 1;
        }else{
            result = a * factorial(a - 1);
        }
        return result;
    }
}

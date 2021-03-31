class MyMath2{
    static long a, b, c;

    // 인스턴스변수와 관계없이 매개변수만으로 작업이 가능하다.
    static long add(long a, long b) { 
        System.out.println("this.a >> " + a ); //2
        System.out.println("this.b >> " + b ); //3
        System.out.println("this.c >> " + c ); //4
        return a + b + c;}
}

public class MyMathTest2 {
    public static void main(String[] args){
        
        MyMath2.a = 3;
        MyMath2.c = 4;

        System.out.println("MyMath2.a >>" + MyMath2.a);   // 1
        System.out.println("MyMath2.add() >> " + MyMath2.add(1, 2)); //5
        System.out.println("MyMath2.a >>" + MyMath2.a ); //6
    }
}

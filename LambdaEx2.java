//LambdaEx2 형변환 및 외부변수 참조
@FunctionalInterface
interface MyFunction{
    void myMethod(); // public abstract void myMethod()
}

@FunctionalInterface
interface MyFunction2{
    void myMethod(); // public abstract void myMethod()
}

class Outer{
    int val = 10; // Outer.this.val

    class Inner{
        int val = 20; //this.val

        void method(int i){  // final int i
            int val = 30; // final int val = 30
            // i = 10; 

            MyFunction2 f = () -> {
                System.out.println("i : " + i);
                System.out.println("val :" + val);
                System.out.println("this.val :" + ++this.val);
                System.out.println("Outer.this.val :"  + ++Outer.this.val);
            };
            
            f.myMethod();
        }
    } // End of Inner
} //End of Outer

public class LambdaEx2 {
    public static void main(String[] args) {
        MyFunction f = () -> {}; // MyFunction f = (MyFunvction)() -> {}
        Object obj = (MyFunction)(() -> {});
        String str = ((Object)(MyFunction)(()->{})).toString();

        System.out.println(f);
        System.out.println(obj);
        System.out.println(str);

        // System.out.println(()->{}); // 에러 람다식은 Object타입으로 형변환이 안됨
        System.out.println((MyFunction)(()->{}));
        // System.out.prinlnt((MyFunction)(()->{}).toString()); //에러
        System.out.println(((Object)(MyFunction)(()->{})).toString());

        System.out.println();


        // Ex 3
        
        Outer outer = new Outer();
        Outer.Inner inner = outer.new Inner();
        inner.method(100);
    }
}

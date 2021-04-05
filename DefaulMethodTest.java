// DefaulMethod java 1.8 
class Child extends Parent implements MyInterface, MyInterface2{
    public void method1(){ // 오버라이딩
        System.out.println("method1() in Child");
    }
}

class Parent{
    public void method2(){
        System.out.println("method2() in Parent");
    }
}
interface MyInterface{
    default void method1(){
        System.out.println("method1() in Myinterface");
    }

    default void method2(){
        System.out.println("method2() in Myinterface");
    }

    static void staticMethod(){
        System.out.println("staticMethod() in Myinterface");
    }
}

interface MyInterface2{
    default void method1(){
        System.out.println("method1() in Myinterface2");
    }
    static void staticmethod(){
        System.out.println("method2() in Myinterface2");
    }
}

public class DefaulMethodTest {
    public static void main(String[] args) {
        Child c = new Child();
        c.method1();
        c.method2();
        MyInterface.staticMethod();
        MyInterface2.staticmethod();
    }


}

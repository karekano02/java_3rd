//inner class 정리
class InnerClassEx{
    class InstanceInner{}
    static class StaticInner{}

    // 인스턴스멤버 간에는 직접 접근이 가능
    InstanceInner is = new InstanceInner();
    StaticInner ss = new StaticInner();

    static void staticMethod(){
        // InstanseInner is = new InstanceInner(); // static 은 instanse에 접근 X
        StaticInner ss = new StaticInner();

        //접근방법
        InnerClassEx ie = new InnerClassEx();
        InstanceInner is = ie.new InstanceInner();
    }

    void instanceMethod(){
        //인스턴스에서는 다 접근 가능
        InstanceInner is = new InstanceInner();
        StaticInner ss = new StaticInner();

        // LocalInner li = new LocalInner();  지역적으로 선언된 클래스 는 외부에서 접근 불가 
    }

    void MyMethod(){
        class LocalInner{}
        LocalInner li = new LocalInner();
    }

}
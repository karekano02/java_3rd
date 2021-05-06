// Thread 기본 예제 (우선순위 포함)
public class ThreadEx {
    public static void main(String[] args){
        Thread1_1 t1 = new Thread1_1();

        Runnable r = new Thread1_2();
        Thread t2 = new Thread(r);

        t2.setPriority(7);   // main 은 default 5  높을 수록 우선순위 
        t1.start();
        t2.start();
    }
}

class Thread1_1 extends Thread{
    public void run(){
        for(int i = 0 ; i < 5 ; i++){
            System.out.println(getName()); //조상인 Thread의getName()을 호출
        }
    }
}

class Thread1_2 implements Runnable{
    public void run(){
        for(int i = 0 ; i < 5 ; i++){
            System.out.println(Thread.currentThread().getName());
        }
    }
}
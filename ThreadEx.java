// Thread 기본 예제
public class ThreadEx {
    public static void main(String[] args){
        Thread1_1 t1 = new Thread1_1();

        Runnable r = new Thread1_2();
        Thread t2 = new Thread(r);

        t1.start();
        try {
            Thread.sleep(10);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
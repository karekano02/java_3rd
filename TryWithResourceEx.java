// TryWithResource 예제 java 1.7 
public class TryWithResourceEx {
    public static void main(String[] args) {
        try (ClosebleResource cr = new ClosebleResource()){
            cr.exceptionWork(false); //예외가 발생하지 않는다.
        }catch(WorkException e){
            e.printStackTrace();
        }catch(CloseException e){
            e.printStackTrace();
        }
        
        try(ClosebleResource cr = new ClosebleResource()){
            cr.exceptionWork(true);
        }catch(WorkException e){
            e.printStackTrace();
        }catch(CloseException e){
            e.printStackTrace();
        }
    }
}


class ClosebleResource implements AutoCloseable{    //try-with-resource 구문은 AutoCloseable이 implements 되어있어야 한다.
    public void exceptionWork(boolean exception) throws WorkException{
        System.out.println("exceptionWork("+ exception + ")가 호출됨");

        if(exception)
            throw new WorkException("WorkException발생!!!");
    }

    public void close() throws CloseException{
        System.out.println("close()가 호출됨");
        throw new CloseException("CloseException발생!!!");
    }


}

class WorkException extends Exception{
    WorkException(String msg){super(msg);}
}
class CloseException extends Exception{
    CloseException(String msg){super(msg);}
}

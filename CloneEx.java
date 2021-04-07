// clone 예제 + 공변 반환 타입

class Point implements Cloneable{
    int x, y;

    Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    public String toString(){
        return "x = " + x + ", y = " + y; 
    }

    // public Object clone(){
    public Point clone(){   // 공변 반환타입 자손 객체로 반환 가능 
        Object obj = null;

        try {
            obj = super.clone();
        }catch(CloneNotSupportedException e){}

        return (Point)obj;
    }
}
public class CloneEx {
    public static void main(String[] args) {
        Point original = new Point(3, 5);

        // Point temp = (Point)original.clone();
        Point temp = original.clone();

        System.out.println(original);
        System.out.println(temp);
    }
}

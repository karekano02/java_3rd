//instanceof 연산자
public class InstanseofTest {
    public static void main(String[] args) {
        Point3D p3D = new Point3D();
        Point t = new Point(1,2);
        // point3D p3D1 = new Point(1,2); // 
       Point t1 = new Point3D();

       System.out.println((t instanceof Object));

       System.out.println( t.getClass().getName());

       System.out.println((t1 instanceof Point3D));

       System.out.println(t1.getClass().getName());

        System.out.println("p3d.x >> " + p3D.x);
        System.out.println("p3d.y >> " + p3D.z);
    }
}

class Point{
    int x=10;
    int y=20;

    Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}

class Point3D extends Point{
    int z = 30;

    Point3D(){
        this(100, 200, 300);
    }

    Point3D(int x, int y, int z){
        super(x, y);
        this.z = z;
    }
}


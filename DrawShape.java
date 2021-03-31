// 상속관계 
public class DrawShape {
    public static void main(String[] args) {
        Point[] p = {
            new Point(100,100),
            new Point(140,50),
            new Point(200, 100)
        };
        
        Triangle t = new Triangle(p);

        t.draw();
        
    }
}

class Shape{
    String color = "black";
    void draw(){
        System.out.printf("[color= %s]%n", color);
    }
}

class Point{
    int x;
    int y;

    Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    Point(){
        this(0,0);
    }

    String getXY(){
        return "["+x+"]"+"["+y+"]";
    }
}

class Triangle extends Shape{
    Point[] p = new Point[3];

    Triangle(Point[] p){
        this.p = p;
    }

    void draw(){
        System.out.printf("[p1=%s, p2=%s, p3=%s, color=%s]%n", p[0].getXY(), p[1].getXY(), p[2].getXY(), color);
    }
}
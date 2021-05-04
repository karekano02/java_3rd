// Enum 열거형 예제들 
enum Direction {EAST, SOUTH,WEST,NORTH}

enum Direction_1{
    EAST(1,">"), SOUTH(2, "V"), WEST(3,"<"), NORTH(4,"^");

    private static final Direction_1[] DIR_ARR = Direction_1.values();
    private final int value;
    private final String symbol;

    Direction_1(int value, String symbol){
        this.value = value;
        this.symbol = symbol;
    }

    public int getValue(){return value;}
    public String getSymbol(){return symbol;}

    public static Direction_1 of(int dir){
        if(dir< 1|| dir > 4){
            throw new IllegalArgumentException("Invalid value :" + dir);
        }
        return DIR_ARR[dir-1];
    }

    // 방향을 회전시키는 메서드. num의 값만큼 90도씩 시계방향으로 회전한다.
    public Direction_1 rotate(int num){
        num = num % 4;

        if(num < 0) num +=4;

        return DIR_ARR[(value-1+num)%4];

    }

    public String toString(){
        return name()+getSymbol();
    }
}
public class EnumEx1 {
    public static void main(String[] args) {
        Direction d1 = Direction.EAST;
        Direction d2 = Direction.valueOf("WEST");
        Direction d3 = Enum.valueOf(Direction.class, "EAST");

        System.out.println("d1 = " + d1);
        System.out.println("d2 = " + d2);
        System.out.println("d3 = " + d3);

        System.out.println("d1 == d2 : " + (d1==d2));
        System.out.println("d1 == d3 : " + (d1==d3));
        System.out.println("d1.equals(d3) : " + d1.equals(d3) ); // > 연산자는 에러
        System.out.println("d1.compareTo(d3) ? " + d1.compareTo(d3));
        System.out.println("d1.compareTo(d2) ? " + d1.compareTo(d2));

        switch(d1){
            case EAST:
                System.out.println("The direction is EAST."); break;
            case WEST:
                System.out.println("The direction is WEST."); break;
            case SOUTH:
                System.out.println("The direction is SOUTH."); break;
            case NORTH:
                System.out.println("The direction is NORTH."); break;
        }

        Direction[] dArr = Direction.values();

        for(Direction d : dArr){
            System.out.println(d.name() + " = " + d.ordinal());
        }

        System.out.println();

        //Ex 2

        for (Direction_1 d : Direction_1.values())
            System.out.println(d.name() + " : " + d.getValue());

        System.out.println();
        Direction_1 d1_1 = Direction_1.EAST;
        Direction_1 d2_1 = Direction_1.of(2);
            
        System.out.println(d1_1.name() + " : " + d1_1.getValue());
        System.out.println(d2_1.name() + " : " + d2_1.getValue());
        System.out.println(Direction_1.EAST.rotate(1));
        System.out.println(Direction_1.EAST.rotate(2));
        System.out.println(Direction_1.EAST.rotate(-1));
        System.out.println(Direction_1.EAST.rotate(-2));
    }
}

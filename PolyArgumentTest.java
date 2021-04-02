//다형성 (매개변수)
class Product{
    int price;
    int bounsPoint;
    
    Product(int price){
        this.price = price;
        bounsPoint = (int)(price/10.0);
    }
}

class Tv extends Product{
    Tv(){
        super(100);
    }

    public String toString(){return "Tv";}
}

class Computer extends Product{
    Computer(){
        super(200);
    }

    public String toString(){return "Computer";}
}

class Buyer{
    int money = 1000;
    int bounsPoint = 0;

    void buy(Product p){
        if(money < p.price ){
            System.out.println("잔액이 부족합니다.");
            return;
        }

        money -= p.price;
        bounsPoint += p.bounsPoint;
        System.out.println(p + "을/를 구입하셧습니다.");
    }
}

public class PolyArgumentTest {
    public static void main(String[] args) {
        Buyer b = new Buyer();
        b.buy(new Tv()); // 자동형 변환
        b.buy(new Computer());

        System.out.println("현재 남은 돈은 " + b.money + "만원입니다.");
        System.out.println("현재 남은 포인트는 " + b.bounsPoint + "점입니다.");
    }
}

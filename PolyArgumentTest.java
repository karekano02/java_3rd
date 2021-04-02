//다형성 (매개변수)  + 여러종류 객체를 배열에 
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

class Audio extends Product{
    Audio(){
        super(50);
    }

    public String toString(){return "Audio";}
}

class Buyer{
    int money = 1000;
    int bounsPoint = 0;
    Product[] pArr = new Product[10];
    int count = 0;

    void buy(Product p){
        if(money < p.price ){
            System.out.println("잔액이 부족합니다.");
            return;
        }

        money -= p.price;
        bounsPoint += p.bounsPoint;
        pArr[count++] = p;
        System.out.println(p + "을/를 구입하셧습니다.");
    }

    void Sum(){
        int sum = 0;    // 지역변수는 반드시 초기화
        String itemList = "";

        for(Product item : pArr){
            if(item == null) break;
            sum += item.price;
            itemList += item.toString() + ", ";
        }

        System.out.println("구입하신 물품의 총금액은 " + sum + "만원입니다. ");
        System.out.println("구입하신 제품은 " + itemList + "입니다.");
    }
}

public class PolyArgumentTest {
    public static void main(String[] args) {
        Buyer b = new Buyer();
        b.buy(new Tv()); // 자동형 변환
        b.buy(new Computer());
        b.buy(new Audio());

        b.Sum();

        System.out.println("현재 남은 돈은 " + b.money + "만원입니다.");
        System.out.println("현재 남은 포인트는 " + b.bounsPoint + "점입니다.");
    }
}

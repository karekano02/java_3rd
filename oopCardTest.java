public class oopCardTest {
    public static void main(String[] args){
        System.out.println("Card.width = " + Card.width);
        System.out.println("Card.height = " + Card.height);

        Card card1 = new Card();
        card1.kind = "heart";
        card1.number = 1;

        System.out.println("Card.width >> " + card1.width + "| Card.kind >> " + card1.kind + "| Card.height >> " + card1.height + "| Card.number >> " +  card1.number);

        Card.height = 50;
        Card.width = 25;

        Card card2 = new Card();
        card2.kind = "space";
        card2.number = 2;

        System.out.println("Card.width >> " + card2.width + "| Card.kind >> " + card2.kind + "| Card.height >> " + card2.height + "| Card.number >> " +  card2.number);

    }
}

class Card{
    String kind;
    int number;
    static int width = 100;
    static int height = 250;
}


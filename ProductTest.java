//멤버변수 초기화 순서
class Product{
    static int count = 0;
    int serialNo;

    {
        count++;
        serialNo = count;
    }

    public Product(){}
}

public class ProductTest {
    public static void main(String[] args) {
        Product p1 = new Product();
        Product p2 = new Product();
        Product p3 = new Product();

        System.out.println("p1 제품번호 >> " + p1.serialNo);
        System.out.println("p2 제품번호 >> " + p2.serialNo);
        System.out.println("p3 제품번호 >> " + p3.serialNo);
    }
}

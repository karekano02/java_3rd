//멤버변수 초기화 일련번호 예제
class Document{
    static int count = 0;
    String name;

    Document(){
        this("제목없음" + ++count);
    }
    Document(String name){
        this.name = name;
        System.out.println("문서" + this.name + "생성 되었습니다.");
    }
}

public class DocumentTest {
    public static void main(String[] args) {
        new Document();
        new Document("자바");
        new Document(); 
    }
}

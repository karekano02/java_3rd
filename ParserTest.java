//인터페이스를 이용한 다양성
//리턴타입이 인터페이스라는 것은 메서드가 해당 인터페이스를 구현한 클래스의 인스턴스를 반환한다는 것을 의미한다. 
interface Parseable{
    public abstract void parse(String fileName);
}

//리턴타입 인터페이스
class ParserManager{
    public static Parseable getParser(String type){
        if(type.equals("XML")){
            return new XMLParser();
        }else{
            return new HTMLParser();
        }
    }
}

class XMLParser implements Parseable{
    public void parse(String fileName){
        System.out.println(fileName + "- XML parsing completed.");
    }
}

class HTMLParser implements Parseable{
    public void parse(String fileName){
        System.out.println(fileName + "- HTML parsing completed.");
    }
}

public class ParserTest {
    public static void main(String[] args) {
        Parseable parse = ParserManager.getParser("XML");
        parse.parse("document.xml");
        parse = ParserManager.getParser("HTML");
        parse.parse("document.xml");
    }
}

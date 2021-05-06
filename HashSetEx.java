import java.util.*;

// HashSet Ex  로또, 빙고, 교집합 차집합 , 그 외 
public class HashSetEx {
    public static void main(String[] args) {
        //로또
        Set set = new HashSet<>();
        

        for(int i = 0 ; set.size() < 6; i++){
            int num = (int)(Math.random()*45) + 1;
            set.add(new Integer(num));
        }

        List list = new LinkedList(set);
        Collections.sort(list);
        System.out.println(list);

        //빙고
        // Set set1 = new HashSet();
        Set set1 = new LinkedHashSet();   //저장순서 위치 고정 

        int[][] board  = new int[5][5];

        for(int i = 0 ; set1.size() < 25; i++){
            set1.add((int)(Math.random()*50)+1);
        }

        Iterator it = set1.iterator();

        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                board[i][j] = Integer.parseInt(String.valueOf(it.next()));  // next의 반환값이 Object 타입으므로 변환  (String)형변환은 next가 문자열이 아니기때문에 Exception 발생
                System.out.print((board[i][j] < 10 ? "  " : " ") + board[i][j]);
            }
            System.out.println();
        }

        //class 같은 사람으로 

        HashSet set2 = new HashSet();    // HashSet의 add메서드는 새로운 요소를 추가하기 전에 기존에 저장된 요소와 같은 것인지 판별 --> equals() 와 hashCode()를 호출 그래서 목적에 맞게 오버라이딩

        set2.add(new String("abc"));
        set2.add(new String("abc"));
        set2.add(new Person("aa",10));
        set2.add(new Person("aa",10));

        System.out.println(set2);

        //교집합 차집합 등등

        HashSet setA = new HashSet();
        HashSet setB = new HashSet();
        HashSet setHap = new HashSet();
        HashSet setKyo = new HashSet();
        HashSet setCha = new HashSet();
        
        setA.add("1");
        setA.add("2");
        setA.add("3");
        setA.add("4");
        setA.add("5");

        System.out.println("A = " + setA);

        setB.add("4");
        setB.add("5");
        setB.add("6");
        setB.add("7");
        setB.add("8");

        System.out.println("B = " + setB);

        Iterator it1 = setB.iterator();
        while(it1.hasNext()){
            Object tmp = it1.next();
            if(setA.contains(tmp)){
                setKyo.add(tmp);
            }
        }

        it1 = setA.iterator();
        while(it1.hasNext()){
            Object tmp = it1.next();
            if(!setB.contains(tmp)){
                setCha.add(tmp);
            }
        }

        it1 = setA.iterator();
        while(it1.hasNext()){
            setHap.add(it1.next());
        }

        it1 = setB.iterator();
        while(it1.hasNext()){
            setHap.add(it1.next());
        }

        System.out.println("교집합 =  " + setKyo);
        System.out.println("합집합 =  " + setHap);
        System.out.println("A - B =  "  + setCha);

    }
}

class Person{
    String name;
    int age;

    Person(String name, int age){
        this.name = name;
        this.age = age;
    }

    public boolean equals(Object obj){
        if(obj instanceof Person){
            Person tmp = (Person)obj;
            return name.equals(tmp.name) && age == tmp.age;
        }

        return false;
    } // add시 판별 하기 위한 오버라이딩

    public int hashCode(){
        return (name+age).hashCode();   // JDK 1.8 Objects.hash(Object... values);
    } // add시 판별 하기 위한 오버라이딩

    public String toString(){
        return name + ":" + age;
    }
} 

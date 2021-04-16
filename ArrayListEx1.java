import java.util.*;
//ArrayList 예제
public class ArrayListEx1 {
    public static void main(String[] args) {

        // 예제1
        ArrayList list1 = new ArrayList(10);

        list1.add(new Integer(5));
        list1.add(new Integer(4));
        list1.add(new Integer(2));
        list1.add(new Integer(0));
        list1.add(new Integer(1));
        list1.add(new Integer(3));

        ArrayList list2 = new ArrayList(list1.subList(1,4));

        print(list1, list2);

        Collections.sort(list1);
        Collections.sort(list2);

        print(list1, list2);

        System.out.println("list1.containsAll(list2) : " + list1.containsAll(list2));

        list2.add("B");
        list2.add("C");
        list2.add(3, "A");
        print(list1, list2);
        
        list2.add(3,"AA");
        print(list1, list2);

        //list1에서 list2와 겹치는 부분만 남기고 나머지는 삭제한다.
        System.out.println("list.retainAll(list2) : " + list1.retainAll(list2)); 
        print(list1, list2);

        //list2에서 list1에 포함된 객체들을 삭제한다.
        for(int i = list2.size()-1 ; i >= 0 ; i--){
            if(list1.contains(list2.get(i))){
                list2.remove(i);
            }
        }

        print(list1, list2);

        //예제2

        final int LIMIT = 10;
        String source = "0123456789abcdefghijABCDEFGHIJ!@#$%^&*()ZZZ";
        int length = source.length();

        List list_1 = new ArrayList(length/LIMIT + 10);

        for(int i = 0 ; i < length; i+=LIMIT){
            if(i+LIMIT< length)
                list_1.add(source.substring(i, i+LIMIT));
            else    
                list_1.add(source.substring(i));
        }

        for(int i = 0 ; i < list_1.size() ; i++){
            System.out.println(list_1.get(i));
        }



    }

    public static void print(ArrayList list1, ArrayList list2){
        System.out.println("list1 =  " + list1);
        System.out.println("list2 =  " + list2);
        System.out.println();
    }
}
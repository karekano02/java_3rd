import java.util.*;
/*
    HashMap은 entrySet으로 키와 값을 동시에 가져올수도 있고 keySet values로 각자 가져 올 수도 있다.
*/
// HashMap Ex HashMap 활용, HashMap 속에 HashMap 포함, bar 출력
public class HashMapEx1 {
    public static void main(String[] args) {

        // Ex1

        HashMap map = new HashMap();
        map.put("김", new Integer(100));
        map.put("이", new Integer(100));
        map.put("박", new Integer(50));
        map.put("나", new Integer(30));

        Set set = map.entrySet();
        Iterator it = set.iterator();

        while(it.hasNext()){
            Map.Entry e = (Map.Entry)it.next();
            System.out.println("이름 : " + e.getKey() + ", 점수 : " + e.getValue());
        }

        set = map.keySet();
        System.out.println("참가자 명단 : " + set);

        Collection values = map.values(); 
        it = values.iterator();

        int total = 0;

        while(it.hasNext()){
            Integer i = (Integer)it.next();
            total += i.intValue();
        }

        System.out.println("총점 : " + total);
        System.out.println("평균 : " + (float)total/set.size());
        System.out.println("최고점수 :" + Collections.max(values));
        System.out.println("최하점수 :" + Collections.min(values));

       
        //Ex 2 해시맵 속에 해시맵

        addPhoneNo("친구", "김자바", "010-1111-2222");
        addPhoneNo("친구", "이자바", "010-1111-3333");

        printList();

        //Ex 3  # 형태로 출력하기

        String[] data = {"A","K","A","B","C","C","B","B"};

        HashMap map1 = new HashMap();

        for(int i = 0 ; i < data.length ; i++){
            if(map1.containsKey(data[i])){
                Integer value = (Integer)map1.get(data[i]);
                map1.put(data[i],new Integer(value.intValue()+1));
            }else{
                map1.put(data[i], new Integer(1));
            }
        }

        Iterator it1 = map1.entrySet().iterator();

        while(it1.hasNext()){
            Map.Entry e  = (Map.Entry)it1.next();
            int value =((Integer)e.getValue()).intValue();
            System.out.println(e.getKey() + "  :  " + printBar('#', value )+ " " + value);
        }
    }

    static HashMap phoneBook = new HashMap();

    //그룹을 추가하는 메서드
    static  void addGroup(String groupName){
        if(!phoneBook.containsKey(groupName)){
            phoneBook.put(groupName, new HashMap());
        }
    }

    //그룹에 전화번호를 추가하는 메서드
    static void addPhoneNo(String groupName , String name , String tel){
        addGroup(groupName);
        HashMap group = (HashMap)phoneBook.get(groupName);
        group.put(tel, name);
    }

    static void addPhoneNo(String name, String tel){
        addPhoneNo("기타", name, tel);
    }

    //전화번호부 전체를 출력하는 메서드
    static void printList(){
        Set set = phoneBook.entrySet();
        Iterator it = set.iterator();

        while(it.hasNext()){
            Map.Entry e = (Map.Entry)it.next();

            Set subSet = ((HashMap)e.getValue()).entrySet();

            Iterator subIt = subSet.iterator();

            System.out.println("* " + e.getKey() + " [" + subSet.size() + "]");

            while(subIt.hasNext()){
                Map.Entry e1 = (Map.Entry)subIt.next();
                String telNo = (String)e1.getKey();
                String name = (String)e1.getValue();

                System.out.println(name + " " + telNo);
            }

            System.out.println();
        }
    }

    //bar 출력 함수
    static String printBar(char ch , int value){
        char[] bar = new char[value];

        for(int i = 0 ; i < bar.length ; i++){
            bar[i] = ch;
        }

        return new String(bar);
    }
}

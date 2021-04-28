import java.util.TreeSet;

// TreeSet Ex 검색하기 , 큰 값 작은값 얻기 
/*
    중복된 데이터의 저장을 허용하지 않으며 정렬된 위치에 저장하므로 저장순서를 유지하지도 않는다.
*/
public class TreeSetEx1 {

    public static void main(String[] args) {
               // 검색하기
               TreeSet set1 = new TreeSet();
               String from = "b";
               String to = "d";
       
               set1.add("abc");
               set1.add("alien");
               set1.add("bat");
               set1.add("car");
               set1.add("Car");
               set1.add("disc");
               set1.add("dance");
               set1.add("dzzz");
               set1.add("dZZZZ");
               set1.add("elephant");
               set1.add("elevator");
               set1.add("fan");

               System.out.println(set1);
               System.out.println("range search : from " + from + " to " + to );
               System.out.println("result1 : " + set1.subSet(from, to));
               System.out.println("result2 : " + set1.subSet(from, to + "zzz"));  // to는 포함되지 않기 때문에 zzz 붙여서 
                
               // 큰값 작은값 얻기
               TreeSet set = new TreeSet();
               int[] score = {80, 95, 50, 35, 45, 65, 10, 100};
       
               for(int i = 0; i < score.length; i++){
                   set.add(new Integer(score[i]));
               }
       
               System.out.println("50보다 작은값 : "  + set.headSet(new Integer(50)));
               System.out.println("50보다 큰 값 : " + set.tailSet(new Integer(50))); 
    }  
}

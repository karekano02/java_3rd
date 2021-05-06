import java.lang.annotation.*;

// Annotition 예제 정의한 

@Deprecated
@SuppressWarnings("1111") // 유효하지 않은 애너테이션은 무시된다.
@TestInfo(testedBy = "aaa", testDate=@DateTime(yymmdd="160101",hhmmss="235959"))  // 사용할 때는 값 다 입력
public class AnnotationEx1 {

    public static void main(String[] args) {
        //AnnotationEx1 의 Class 객체를 얻는다.
        Class<AnnotationEx1> cls = AnnotationEx1.class;
        //클래스 객체를 의미한다. 모든 클래스 파일은 Classloader에 의해 메모리에 올라갈 때, 클래스에 대한 정보가 담긴 객체를 생성하는데 이 객체를 클래스 객체라 한다.
        // 이 객체를 참조 할 때는 '클래스이름.class'의 형식을 사용한다.

        TestInfo anno = (TestInfo)cls.getAnnotation(TestInfo.class);

        System.out.println("anno.testedBy() = " + anno.testedBy());
        System.out.println("anno.testDate().yymmdd() = " + anno.testDate().yymmdd());
        System.out.println("anno.teatDate().hhmmss() = " + anno.testDate().hhmmss());

        for(String str : anno.testTools()){
            System.out.println("testTools = " + str);
        }
        System.out.println();

        //AnnotationEx1 에 적용된 모든 애너테이션을 가져온다.
        Annotation[] annoArr = cls.getAnnotations();
        for(Annotation a : annoArr){
            System.out.println(a);
        }
    }
  
}

@Retention(RetentionPolicy.RUNTIME) // 실행 시에 사용가능하도록 지정
@interface TestInfo{
    int count() default 1;
    String testedBy();
    String[] testTools() default "JUnit";
    TestType testType() default TestType.FIRST;
    DateTime testDate();
}

@Retention(RetentionPolicy.RUNTIME)
@interface DateTime{
    String yymmdd();
    String hhmmss();
}

enum TestType{FIRST, FINAL}

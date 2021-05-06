import java.lang.annotation.*;

// Annotition 예제 정의한 

@Deprecated
@SuppressWarnings("1111") // 유효하지 않은 애너테이션은 무시된다.
@TestInfo(testedBy = "aaa", testDate=@DateTime(yymmdd="160101",hhmmss="235959"))  // 사용할 때는 값 다 입력
public class AnnotationEx1 {

    public static void main(String[] args) {
        //AnnotationEx1 의 Class 객체를 얻는다.
        Class<AnnotationEx1> cls = AnnotationEx1.class;

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

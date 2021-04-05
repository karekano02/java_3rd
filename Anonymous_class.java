import java.awt.*;
import java.awt.event.*;

//익명 클래스
public class Anonymous_class {
    public static void main(String[] args) {
            Button b = new Button("Strat");
            b.addActionListener(new ActionListener(){ // 조상클래스 or 구현인터페이스이름
                public void actionPerformed(ActionEvent e){ 
                    System.out.println("ActionEvent occurred!!!");
                }
            }
        );   
    }
}

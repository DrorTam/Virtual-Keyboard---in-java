import javax.swing.*;

public class KeyboardTest {


    public static void main(String[]args){
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(900,500);
        KeyboardPanel panel = new KeyboardPanel();
        window.add(panel);
        window.setVisible(true);
    }


}

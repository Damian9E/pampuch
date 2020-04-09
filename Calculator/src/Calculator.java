import java.awt.*;

public class Calculator {

    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CalculatorFrame();
            }
        });

    }
}
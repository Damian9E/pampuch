import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class CalculatorFrame extends JFrame {
    private JTextField equationTF, numberTF;
    private JTextArea memoryTA;
    protected double first, answer;
    protected String operation;

    private static final int FRAME_WIDTH =  800, FRAME_HEIGHT =  600, FRAME_LOCATION_X =  10, FRAME_LOCATION_Y =  10;
    private static final int LABEL_WIDTH = 400, LABEL_HEIGHT = 50, LABEL_LOCATION_X = 2, LABEL_LOCATION_Y = 2;
    private static final int TF_WIDTH = 397, TF_HEIGHT = 30, TF_LOCATION_X = 2, TF_LOCATION_Y = 72;
    private static final int TFB_WIDTH = 397, TFB_HEIGHT = 100, TFB_LOCATION_X = 2, TFB_LOCATION_Y = 95;
    private static final int BUTTON_LOCATION_X_INCREASE = 99, BUTTON_LOCATION_Y_INCREASE = 49, BUTTON_LOCATION_X_DEFAULT = 2, BUTTONM_LOCATION_X_INCREASE = 79;
    private static final int SCROLL_PANE_WIDTH = 386, SCROLL_PANE_HEIGHT = 467;
    private static final int FIRST_M = 24, LAST_M = 29;

    private static int locationXB = 2, locationYB = 244, widthB = 100, lengthB = 50;
    private static int locationXLabelMemory = 400, locationYLabelMemory = 2, widthLabelMemory = 150, heightLabelMemory = 50;
    private static int locationXBM = 2, locationYBM = 195 , widthBM = 80, lengthBM = 50;
    private static int countB = 0;
    private ArrayList<JButton> jButtonArrayList;

    public CalculatorFrame() {
        super("Kalkulator");

        initDefaultSettings();
        buildAppMenu();
        buildJLabel();
        buildTextField();
        buildButton();
        buildTextArea();
    }

    private void initDefaultSettings() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setResizable(false);
        setVisible(true);
        setLayout(null);
        setLocation(FRAME_LOCATION_X, FRAME_LOCATION_Y);
    }

    private void buildAppMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem mStandard = new JMenuItem("Standardowy");
        JMenuItem mScientific = new JMenuItem("Naukowy");
        JMenuItem mProgrammer = new JMenuItem("Programisty");
        JMenuItem mDate = new JMenuItem("Obliczanie daty");

        setJMenuBar(menuBar);
        menuBar.add(menu);
        menu.add(mStandard);
        menu.addSeparator();
        menu.add(mScientific);
        menu.addSeparator();
        menu.add(mProgrammer);
        menu.addSeparator();
        menu.add(mDate);
    }

    private void buildTextArea() {
        memoryTA = new JTextArea();
        memoryTA.setFont(new Font("Arial", Font.PLAIN, 18));
        memoryTA.setEditable(false);
        memoryTA.setLineWrap(true);
        JScrollPane memorySP = new JScrollPane(memoryTA);
        memorySP.setBounds(locationXLabelMemory, TF_LOCATION_Y, SCROLL_PANE_WIDTH, SCROLL_PANE_HEIGHT);
        memorySP.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        memorySP.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        add(memorySP);
    }

    private void buildJLabel() {
        JLabel labelSelectedMode = new JLabel("Standardowy");
        labelSelectedMode.setBounds(LABEL_LOCATION_X, LABEL_LOCATION_Y, LABEL_WIDTH, LABEL_HEIGHT);
        add(labelSelectedMode);

        JLabel labelMemory = new JLabel("Pamięć");
        labelMemory.setBounds(locationXLabelMemory, locationYLabelMemory, widthLabelMemory, heightLabelMemory);
        add(labelMemory);
    }

    private void buildTextField() {
        equationTF = new JTextField();
        equationTF.setBounds(TF_LOCATION_X, TF_LOCATION_Y, TF_WIDTH, TF_HEIGHT);
        equationTF.setFont(new Font("Arial", Font.PLAIN, 10));
        add(equationTF);

        numberTF = new JTextField();
        numberTF.setBounds(TFB_LOCATION_X, TFB_LOCATION_Y, TFB_WIDTH, TFB_HEIGHT);
        numberTF.setFont(new Font("Arial", Font.PLAIN, 35));
        add(numberTF);
    }

    void createButton(String text) {
        JButton button = new JButton(text);

        try {
            button.addActionListener(new ButtonNumberListener(Integer.parseInt(text)));
        } catch (Exception e){
            button.addActionListener(new ButtonListener(text));
        }

        jButtonArrayList.add(button);
    }

    private void buildButton() {
        jButtonArrayList = new ArrayList<>();

        String[] nameButton = new String[] {"%", "CE", "C", "<-", "1/x", "x^2", "x^(1/2)", "/", "7", "8", "9", "*", "4", "5", "6", "-", "1", "2", "3", "+", "+/-", "0", ",", "=", "MC", "MR", "M+", "M-", "MS"};

        for (int i=0; i<29; i++) {
            createButton(nameButton[i]);
        }

        for (int i = 0; i<FIRST_M; i++) {
            jButtonArrayList.get(i).setBounds(locationXB, locationYB, widthB, lengthB);
            locationXB += BUTTON_LOCATION_X_INCREASE;
            countB++;
            if (countB % 4 == 0) {
                locationYB += BUTTON_LOCATION_Y_INCREASE;
                locationXB = BUTTON_LOCATION_X_DEFAULT;
            }
        }

        for (int i=FIRST_M; i<LAST_M; i++) {
            jButtonArrayList.get(i).setBounds(locationXBM, locationYBM, widthBM, lengthBM);
            locationXBM += BUTTONM_LOCATION_X_INCREASE;
        }

        for (int i=0; i<LAST_M; i++) {
            jButtonArrayList.get(i).setFont(new Font("Arial", Font.PLAIN, 20));
            add(jButtonArrayList.get(i));
        }
    }

     class ButtonNumberListener implements ActionListener {
          private int x;
          public ButtonNumberListener(int x) {
              this.x = x;
          }
          @Override
          public void actionPerformed(ActionEvent e) {
              findParenthese(this.x);
          }
     }

    class ButtonListener implements ActionListener {
        public ButtonListener(String t) { }

        @Override
        public void actionPerformed(ActionEvent e) {
            Object sourceOfAction = e.getActionCommand();

            if (sourceOfAction == "+") {
                setJTextFieldOperation(operation = "+");

            } else if (sourceOfAction == "-") {
                setJTextFieldOperation(operation = "-");

            } else if (sourceOfAction == "*"){
                setJTextFieldOperation(operation = "*");

            } else if (sourceOfAction == "/") {
                setJTextFieldOperation(operation = "/");

            } else if (sourceOfAction == "=") {
                switch (operation){
                    case "+" :
                        setJTextFieldSolution(answer = first + Double.parseDouble(numberTF.getText()));
                        break;
                    case "-" :
                        setJTextFieldSolution(answer = first - Double.parseDouble(numberTF.getText()));
                        break;
                    case "*" :
                        setJTextFieldSolution(answer = first * Double.parseDouble(numberTF.getText()));
                        break;
                    case "/" :
                        setJTextFieldSolution(answer = first / Double.parseDouble(numberTF.getText()));
                        break;
                }

            } else if (sourceOfAction == "+/-") {
                double beforeN = Double.parseDouble(numberTF.getText());
                double negate = beforeN*(-1);
                numberTF.setText(Double.toString(negate));
                equationTF.setText( "negate(" + beforeN + ")");

            } else if (sourceOfAction == ",") {
                equationTF.setText(equationTF.getText() + ",");
                numberTF.setText(numberTF.getText() + ",");

            } else if (sourceOfAction == "1/x") {
                double beforeF = Double.parseDouble(numberTF.getText());
                double fraction = 1/beforeF;
                numberTF.setText(Double.toString(fraction));
                equationTF.setText( "1/(" + beforeF + ")");

            } else if (sourceOfAction == "x^2") {
                double beforePwr = Double.parseDouble(numberTF.getText());
                double pwr = Math.pow(beforePwr, 2);
                numberTF.setText(Double.toString(pwr));
                equationTF.setText( "pow(" + beforePwr + ")");

            } else if (sourceOfAction == "x^(1/2)") {
                double beforeSqrt = Double.parseDouble(numberTF.getText());
                double sqrt = Math.sqrt(beforeSqrt);
                numberTF.setText(Double.toString(sqrt));
                equationTF.setText( "(" + beforeSqrt + ")^(1/2)");

            } else if (sourceOfAction == "%") {
                equationTF.setText(" "); // UZUPEŁNIĆ

            } else if (sourceOfAction == "CE") {
                int length = equationTF.getText().length();
                if (length>0) {
                    StringBuilder dBuilder = new StringBuilder(equationTF.getText());
                    dBuilder.deleteCharAt(length-1);
                    equationTF.setText(dBuilder.toString());
                }
                numberTF.setText("0"); // UZUPEŁNIĆ

            } else if (sourceOfAction == "C") {
                equationTF.setText(""); numberTF.setText("0");

            } else if (sourceOfAction == "<-") {
                int lengthF = equationTF.getText().length();
                if (lengthF>0) {
                    StringBuilder dBuilder = new StringBuilder(equationTF.getText());
                    dBuilder.deleteCharAt(lengthF-1);
                    equationTF.setText(dBuilder.toString());
                }
                int lengthFB = numberTF.getText().length();
                if (lengthFB>0) {
                    StringBuilder dBuilder = new StringBuilder(numberTF.getText());
                    dBuilder.deleteCharAt(lengthFB-1);
                    numberTF.setText(dBuilder.toString());
                }

            } else if (sourceOfAction == "MC") {
                memoryTA.setText("Brak elementów zapisanych w pamięci");

            } else if (sourceOfAction == "MR") {                  // UZUPEŁNIĆ!!!!!
                String text = memoryTA.getText();
                String str[] = text.split("\n");
                List<String> strArr = new ArrayList<String>( Arrays.asList(str));
                int i = 0;
                String firstNumInMem = strArr.get(i);
                numberTF.setText(firstNumInMem);
                strArr.remove(i);

            } else if (sourceOfAction == "M+") {
                if (memoryTA.getText().length()>0) {
                    memoryTA.setText(String.valueOf(Integer.parseInt(memoryTA.getText()) + Integer.parseInt(numberTF.getText())));
                } else {
                    memoryTA.setText(numberTF.getText());
                }

            } else if (sourceOfAction == "M-") {
                memoryTA.setText(String.valueOf(Integer.parseInt(memoryTA.getText()) - Integer.parseInt(numberTF.getText())));

            } else if (sourceOfAction == "MS") {
                String tmp = memoryTA.getText();
                for (int i=0; i<25; i++){
                    memoryTA.setText(numberTF.getText() + "\n" + tmp);
                }
            }
        }
    }

    public void findParenthese(int x) {
        int length = equationTF.getText().length();
        if (length>0){
            StringBuilder builder = new StringBuilder(equationTF.getText());
            Character c = builder.charAt(length-1);
            if (c.equals(')')){
                equationTF.setText(Integer.toString(x));
                numberTF.setText(Integer.toString(x));
            } else {
                equationTF.setText(equationTF.getText() + x);
                numberTF.setText(numberTF.getText() + x);
            }
        } else {
            equationTF.setText(Integer.toString(x));
            numberTF.setText(Integer.toString(x));
        }
    }

    public void setJTextFieldOperation(String operation) {
        first = Double.parseDouble(numberTF.getText());
        equationTF.setText(equationTF.getText() + operation);
        numberTF.setText("");
    }

    public void setJTextFieldSolution(Double answer) {
        numberTF.setText(Double.toString(answer));
        equationTF.setText(equationTF.getText() + "=" + answer);
    }
}
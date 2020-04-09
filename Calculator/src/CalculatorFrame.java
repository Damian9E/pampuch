import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CalculatorFrame extends JFrame {
    private JTextField textField, textFieldBig;
    private JTextArea textAreaMemory;
    protected double first, answer;
    protected String operation;
    private static final int FRAME_WIDTH =  800, FRAME_HEIGHT =  600, FRAME_LOCATION_X =  10, FRAME_LOCATION_Y =  10;
    private static final int LABEL_WIDTH = 400, LABEL_HEIGHT = 50, LABEL_LOCATION_X = 2, LABEL_LOCATION_Y = 2;
    private static final int TF_WIDTH = 397, TF_HEIGHT = 30, TF_LOCATION_X = 2, TF_LOCATION_Y = 72;
    private static final int TFB_WIDTH = 397, TFB_HEIGHT = 100, TFB_LOCATION_X = 2, TFB_LOCATION_Y = 95;
    private static int BUTTON_LOCATION_X = 2, BUTTON_LOCATION_Y = 244, BUTTON_WIDTH = 100, BUTTON_LENGTH = 50;
    private static int LABEL_MEMORY_LOCATION_X = 400, LABEL_MEMORY_LOCATION_Y = 2, LABEL_MEMORY_WIDTH = 150, LABEL_MEMORY_HEIGHT = 50;
    private static final int BUTTON_LOCATION_X_INCREASE = 99, BUTTON_LOCATION_Y_INCREASE = 49, BUTTON_LOCATION_X_DEFAULT = 2, BUTTONM_LOCATION_X_INCREASE = 79;
    private static final int SCROLL_PANE_WIDTH = 386, SCROLL_PANE_HEIGHT = 467;
    private static int BUTTONM_LOCATION_X = 2, BUTTONM_LOCATION_Y = 195 , BUTTONM_WIDTH = 80, BUTTONM_LENGTH = 50;
    private static int COUNT_BUTTON = 0;
    private static final int FIRST_M = 24, LAST_M = 29;
    private ArrayList<JButton> jButtonArrayList;

    public CalculatorFrame(){
        super("Kalkulator");

        initDefaultSettings();
        buildAppMenu();
        buildJLabel();
        buildTextField();
        buildButton();
        buildTextArea();
    }

    private void initDefaultSettings(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(FRAME_WIDTH,FRAME_HEIGHT);
        setResizable(false);
        setVisible(true);
        setLayout(null);
        setLocation(FRAME_LOCATION_X,FRAME_LOCATION_Y);
    }

    private void buildAppMenu(){
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
        textAreaMemory = new JTextArea();
        textAreaMemory.setFont(new Font("Arial", Font.PLAIN, 18));
        textAreaMemory.setEditable(false);
        textAreaMemory.setLineWrap(true);
        JScrollPane scrollPaneMemory = new JScrollPane(textAreaMemory);
        scrollPaneMemory.setBounds(LABEL_MEMORY_LOCATION_X, TF_LOCATION_Y, SCROLL_PANE_WIDTH, SCROLL_PANE_HEIGHT);
        scrollPaneMemory.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPaneMemory.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPaneMemory);
    }

    private void buildJLabel(){
        JLabel labelSelectedMode = new JLabel("Standardowy");
        labelSelectedMode.setBounds(LABEL_LOCATION_X, LABEL_LOCATION_Y, LABEL_WIDTH, LABEL_HEIGHT);
        add(labelSelectedMode);

        JLabel labelMemory = new JLabel("Pamięć");
        labelMemory.setBounds(LABEL_MEMORY_LOCATION_X, LABEL_MEMORY_LOCATION_Y, LABEL_MEMORY_WIDTH, LABEL_MEMORY_HEIGHT);
        add(labelMemory);
    }

    private void buildTextField(){
        textField = new JTextField();
        textField.setBounds(TF_LOCATION_X, TF_LOCATION_Y, TF_WIDTH, TF_HEIGHT);
        textField.setFont(new Font("Arial", Font.PLAIN, 10));
        add(textField);

        textFieldBig = new JTextField();
        textFieldBig.setBounds(TFB_LOCATION_X, TFB_LOCATION_Y, TFB_WIDTH, TFB_HEIGHT);
        textFieldBig.setFont(new Font("Arial", Font.PLAIN, 35));
        add(textFieldBig);
    }

    void createButton(String text){
        JButton button = new JButton(text);
        if(text.equals("0") || text.equals("1") || text.equals("2") || text.equals("3") || text.equals("4") || text.equals("5") || text.equals("6") || text.equals("7") || text.equals("8") || text.equals("9")){
            button.addActionListener(new ButtonNumberListener(Integer.parseInt(text)));
        } else {
            button.addActionListener(new ButtonListener(text));
        }
        jButtonArrayList.add(button);
    }

    private void buildButton() {
        jButtonArrayList = new ArrayList<>();

        createButton("%");
        createButton("CE");
        createButton("C");
        createButton("<-");
        createButton("1/x");
        createButton("x^2");
        createButton("x^(1/2)");
        createButton("/");
        createButton("7");
        createButton("8");
        createButton("9");
        createButton("*");
        createButton("4");
        createButton("5");
        createButton("6");
        createButton("-");
        createButton("1");
        createButton("2");
        createButton("3");
        createButton("+");
        createButton("+/-");
        createButton("0");
        createButton(",");
        createButton("=");
        createButton("MC");
        createButton("MR");
        createButton("M+");
        createButton("M-");
        createButton("MS");

        for(int i = 0; i<FIRST_M; i++){
            jButtonArrayList.get(i).setBounds(BUTTON_LOCATION_X, BUTTON_LOCATION_Y, BUTTON_WIDTH, BUTTON_LENGTH);
            BUTTON_LOCATION_X += BUTTON_LOCATION_X_INCREASE;
            COUNT_BUTTON++;
            if(COUNT_BUTTON%4 == 0){
                BUTTON_LOCATION_Y += BUTTON_LOCATION_Y_INCREASE;
                BUTTON_LOCATION_X = BUTTON_LOCATION_X_DEFAULT;
            }
        }

        for(int i=FIRST_M; i<LAST_M; i++){
            jButtonArrayList.get(i).setBounds(BUTTONM_LOCATION_X, BUTTONM_LOCATION_Y, BUTTONM_WIDTH, BUTTONM_LENGTH);
            BUTTONM_LOCATION_X += BUTTONM_LOCATION_X_INCREASE;
        }

        for(int i=0; i<LAST_M; i++){
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

    class ButtonListener implements ActionListener{
        public ButtonListener(String t) { }

        @Override
        public void actionPerformed(ActionEvent e) {
            Object sourceOfAction = e.getActionCommand();

            if(sourceOfAction == "+") {
                first = Double.parseDouble(textFieldBig.getText());
                operation = "+";
                setJTextFieldOperation(operation);

            } else if (sourceOfAction == "-") {
                first = Double.parseDouble(textFieldBig.getText());
                operation = "-";
                setJTextFieldOperation(operation);

            } else if (sourceOfAction == "*"){
                first = Double.parseDouble(textFieldBig.getText());
                operation = "*";
                setJTextFieldOperation(operation);

            } else if (sourceOfAction == "/") {
                first = Double.parseDouble(textFieldBig.getText());
                operation = "/";
                setJTextFieldOperation(operation);

            } else if (sourceOfAction == "=") {
                switch (operation){
                    case "+" :
                        answer = first + Double.parseDouble(textFieldBig.getText());
                        setJTextFieldSolution(answer);
                        break;
                    case "-" :
                        answer = first - Double.parseDouble(textFieldBig.getText());
                        setJTextFieldSolution(answer);
                        break;
                    case "*" :
                        answer = first * Double.parseDouble(textFieldBig.getText());
                        setJTextFieldSolution(answer);
                        break;
                    case "/" :
                        answer = first / Double.parseDouble(textFieldBig.getText());
                        setJTextFieldSolution(answer);
                        break;
                }

            } else if (sourceOfAction == "+/-") {
                double beforeN = Double.parseDouble(textFieldBig.getText());
                double negate = beforeN*(-1);
                textFieldBig.setText(Double.toString(negate));
                textField.setText( "negate(" + beforeN + ")");

            } else if (sourceOfAction == ",") {
                textField.setText(textField.getText() + ",");
                textFieldBig.setText(textFieldBig.getText() + ",");

            } else if (sourceOfAction == "1/x") {
                double beforeF = Double.parseDouble(textFieldBig.getText());
                double fraction = 1/beforeF;
                textFieldBig.setText(Double.toString(fraction));
                textField.setText( "1/(" + beforeF + ")");

            } else if (sourceOfAction == "x^2"){
                double beforePwr = Double.parseDouble(textFieldBig.getText());
                double pwr = Math.pow(beforePwr, 2);
                textFieldBig.setText(Double.toString(pwr));
                textField.setText( "pow(" + beforePwr + ")");

            } else if (sourceOfAction == "x^(1/2)") {
                double beforeSqrt = Double.parseDouble(textFieldBig.getText());
                double sqrt = Math.sqrt(beforeSqrt);
                textFieldBig.setText(Double.toString(sqrt));
                textField.setText( "(" + beforeSqrt + ")^(1/2)");

            } else if (sourceOfAction == "%") {
                textField.setText(" "); // UZUPEŁNIĆ

            } else if (sourceOfAction == "CE") {
                int length = textField.getText().length();
                if(length>0){
                    StringBuilder dBuilder = new StringBuilder(textField.getText());
                    dBuilder.deleteCharAt(length-1);
                    textField.setText(dBuilder.toString());
                }
                textFieldBig.setText("0"); // UZUPEŁNIĆ

            } else if (sourceOfAction == "C") {
                textField.setText(""); textFieldBig.setText("0");

            } else if (sourceOfAction == "<-") {
                int lengthF = textField.getText().length();
                if(lengthF>0){
                    StringBuilder dBuilder = new StringBuilder(textField.getText());
                    dBuilder.deleteCharAt(lengthF-1);
                    textField.setText(dBuilder.toString());
                }
                int lengthFB = textFieldBig.getText().length();
                if(lengthFB>0){
                    StringBuilder dBuilder = new StringBuilder(textFieldBig.getText());
                    dBuilder.deleteCharAt(lengthFB-1);
                    textFieldBig.setText(dBuilder.toString());
                }

            } else if (sourceOfAction == "MC"){
                textAreaMemory.setText("Brak elementów zapisanych w pamięci");

            } else if (sourceOfAction == "MR"){ // UZUPEŁNIĆ!!!!!
                String text = textAreaMemory.getText();
                String str[] = text.split("\n");
                List<String> strArr = new ArrayList<String>( Arrays.asList(str));
                int i =0;
                String firstNumInMem = strArr.get(i);
                textFieldBig.setText(firstNumInMem);
                strArr.remove(i);

            } else if (sourceOfAction == "M+"){
                if(textAreaMemory.getText().length()>0){
                    textAreaMemory.setText(String.valueOf(Integer.parseInt(textAreaMemory.getText()) + Integer.parseInt(textFieldBig.getText())));
                } else {
                    textAreaMemory.setText(textFieldBig.getText());
                }

            } else if (sourceOfAction == "M-"){
                textAreaMemory.setText(String.valueOf(Integer.parseInt(textAreaMemory.getText()) - Integer.parseInt(textFieldBig.getText())));

            } else if (sourceOfAction == "MS"){
                String tmp = textAreaMemory.getText();
                for(int i=0; i<25; i++){
                    textAreaMemory.setText(textFieldBig.getText() + "\n" + tmp);
                }
            }
        }
    }

    public void findParenthese(int x){
        int length = textField.getText().length();
        if(length>0){
            StringBuilder builder = new StringBuilder(textField.getText());
            Character c = builder.charAt(length-1);
            if(c.equals(')')){
                textField.setText(Integer.toString(x));
                textFieldBig.setText(Integer.toString(x));
            } else {
                textField.setText(textField.getText() + x);
                textFieldBig.setText(textFieldBig.getText() + x);
            }
        } else {
            textField.setText(Integer.toString(x));
            textFieldBig.setText(Integer.toString(x));
        }
    }

    public void setJTextFieldOperation(String operation) {
        textField.setText(textField.getText() + operation);
        textFieldBig.setText("");
    }

    public void setJTextFieldSolution(Double answer) {
        textFieldBig.setText(Double.toString(answer));
        textField.setText(textField.getText() + "=" + answer);
    }
}
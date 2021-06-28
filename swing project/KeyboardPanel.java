import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KeyboardPanel extends JPanel {
	
	//creating components
    private boolean capsMode;
    private JTextArea text;
    private JButton[] letters;
    private JButton[] numbers;
    private JButton backspace,enter,tab,space;
    private JButton sWave,sPlus,sMinus,sLBracket,sRBracket,sBackSlash,sQuestion,sQuotation,sLArrow,sRArrow,sColon;
    private JButton lShift,rShift,caps;
    //constructor
    public KeyboardPanel(){
        capsMode=false; //default
        this.setLayout(new BorderLayout());
        text=new JTextArea();
        SignButtonListener signButtonListener=new SignButtonListener();

        //creating the buttons for letters
        letters=new JButton[26];
        char lower='a';
        char upper='A';
        for(int i=0;i<letters.length;i++){
            String str=lower+"/"+upper;
            letters[i]=new JButton(String.valueOf(upper));
            letters[i].setActionCommand(str);
            lower++;
            upper++;
            letters[i].addActionListener(signButtonListener);
        }


        //creating the buttons for numbers
        numbers=new JButton[10];
        numbers[1]=new JButton("1/!");
        numbers[2]=new JButton("2/@");
        numbers[3]=new JButton("3/#");
        numbers[4]=new JButton("4/$");
        numbers[5]=new JButton("5/%");
        numbers[6]=new JButton("6/^");
        numbers[7]=new JButton("7/&");
        numbers[8]=new JButton("8/*");
        numbers[9]=new JButton("9/(");
        numbers[0]=new JButton("0/)");
        for (int i=0;i<numbers.length;i++){
            numbers[i].addActionListener(signButtonListener);
        }

        //creating the buttons for signs
        sWave=new JButton("`/~");
        sWave.addActionListener(signButtonListener);
        sMinus=new JButton("-/_");
        sMinus.addActionListener(signButtonListener);
        sPlus=new JButton("=/+");
        sPlus.addActionListener(signButtonListener);
        sLBracket=new JButton("[/{");
        sLBracket.addActionListener(signButtonListener);
        sRBracket=new JButton("]/}");
        sRBracket.addActionListener(signButtonListener);
        sColon=new JButton(";/:");
        sColon.addActionListener(signButtonListener);
        sQuotation=new JButton("'/");
        sQuotation.addActionListener(signButtonListener);
        sBackSlash=new JButton("\\/|");
        sBackSlash.addActionListener(signButtonListener);
        sLArrow=new JButton(",/<");
        sLArrow.addActionListener(signButtonListener);
        sRArrow=new JButton("./>");
        sRArrow.addActionListener(signButtonListener);
        sQuestion=new JButton("//?");
        sQuestion.addActionListener(signButtonListener);

        SpecialButtonListener specialButtonListener=new SpecialButtonListener();
        //creating the special buttons
        backspace=new JButton("Backspace");
        backspace.addActionListener(specialButtonListener);
        enter=new JButton("Enter");
        enter.addActionListener(specialButtonListener);
        tab=new JButton("Tab");
        tab.addActionListener(specialButtonListener);
        //could set size but wanted to keep the default height and didn't find it.
        space=new JButton("   ");
        space.setPreferredSize(new Dimension(500,26));
        space.addActionListener(signButtonListener);
        lShift=new JButton("Shift");
        rShift=new JButton("Shift");
        lShift.addActionListener(specialButtonListener);
        rShift.addActionListener(specialButtonListener);
        caps=new JButton("Caps");
        caps.addActionListener(specialButtonListener);

        JPanel line1=new JPanel();
        line1.add(sWave);
        for (int i=1;i<numbers.length;i++){
            line1.add(numbers[i]);
        }
        line1.add(numbers[0]);
        line1.add(sMinus);
        line1.add(sPlus);
        line1.add(backspace);

        JPanel line2=new JPanel();
        line2.add(tab);
        line2.add(letters['Q'-'A']);
        line2.add(letters['W'-'A']);
        line2.add(letters['E'-'A']);
        line2.add(letters['R'-'A']);
        line2.add(letters['T'-'A']);
        line2.add(letters['Y'-'A']);
        line2.add(letters['U'-'A']);
        line2.add(letters['I'-'A']);
        line2.add(letters['O'-'A']);
        line2.add(letters['P'-'A']);
        line2.add(sLBracket);
        line2.add(sRBracket);
        line2.add(sBackSlash);

        JPanel line3=new JPanel();
        line3.add(caps);
        line3.add(letters['A'-'A']);
        line3.add(letters['S'-'A']);
        line3.add(letters['D'-'A']);
        line3.add(letters['F'-'A']);
        line3.add(letters['G'-'A']);
        line3.add(letters['H'-'A']);
        line3.add(letters['J'-'A']);
        line3.add(letters['K'-'A']);
        line3.add(letters['L'-'A']);
        line3.add(sColon);
        line3.add(sQuotation);
        line3.add(enter);

        JPanel line4=new JPanel();
        line4.add(lShift);
        line4.add(letters['Z'-'A']);
        line4.add(letters['X'-'A']);
        line4.add(letters['C'-'A']);
        line4.add(letters['V'-'A']);
        line4.add(letters['B'-'A']);
        line4.add(letters['N'-'A']);
        line4.add(letters['M'-'A']);
        line4.add(sLArrow);
        line4.add(sRArrow);
        line4.add(sQuestion);
        line4.add(rShift);

        JPanel line5=new JPanel();
        line5.add(space);


        JPanel lines=new JPanel(new GridLayout(5,1));
        lines.add(line1);
        lines.add(line2);
        lines.add(line3);
        lines.add(line4);
        lines.add(line5);

        this.add(text,BorderLayout.CENTER);
        this.add(lines,BorderLayout.SOUTH);


    }
    private class SpecialButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String btn=e.getActionCommand();
            switch (btn){
                case "Enter":
                    text.append("\n");
                    break;
                case "Tab":
                    text.append("    ");
                    break;
                case "Backspace":

                    text.replaceRange("",text.getCaretPosition()-1,text.getCaretPosition());
                    break;
                case "Shift": case "Caps":
                    if(!capsMode) {
                        lShift.setBackground(Color.LIGHT_GRAY);
                        rShift.setBackground(Color.LIGHT_GRAY);
                        caps.setBackground(Color.LIGHT_GRAY);
                    }
                    else {
                        Color c=new JButton().getBackground(); // to get default background color
                        lShift.setBackground(c);
                        rShift.setBackground(c);
                        caps.setBackground(c);
                    }

                    capsMode=!capsMode;
                    break;
            }

        }
    }
    private class SignButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String btn=e.getActionCommand();
            String lower=String.valueOf(btn.charAt(0));
            String upper=String.valueOf(btn.charAt(2)+"");
            if (capsMode) text.append(upper);
            else text.append(lower);



        }
    }

}




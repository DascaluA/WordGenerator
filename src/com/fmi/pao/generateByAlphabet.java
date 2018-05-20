package com.fmi.pao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

public class generateByAlphabet extends JFrame implements ActionListener{
    private JPanel mainContainer;
    private JPanel alphabetContainer;
    private JButton saveBtn;
    private JPanel lengthContainer;
    private JPanel outputContainer;
    private JScrollPane genText;
    private JButton generateBtn;
    private JButton browseBtn;
    private JScrollPane lengthInput;
    private JComboBox lengthTypeSelector;
    private JScrollPane alphInput;
    private JLabel infoLabel1;
    private JLabel infoLabel2;
    private JLabel infoLabel3;
    private JScrollBar scrollBar1;
    private JScrollBar scrollBar2;
    private JScrollBar scrollBar3;


    private int lengthType;
    private int textLength;

    public generateByAlphabet() {
        System.out.println("Entered alph constructor");
        setElements();
        lengthType = 0;
        textLength = 1;
        saveBtn.addActionListener(this);
        browseBtn.addActionListener(this);
        generateBtn.addActionListener(this);



    }

    private void setElements(){
        mainContainer = new JPanel();
        alphabetContainer = new JPanel();
        lengthContainer = new JPanel();
        outputContainer = new JPanel();

        alphInput = new JScrollPane(new JTextArea());
        infoLabel1 = new JLabel();
        infoLabel1.setText("Input the alphabet:");
        alphabetContainer.add(infoLabel1);
        alphabetContainer.add(alphInput);

        infoLabel2 = new JLabel();
        infoLabel2.setText("Input the text length");
        lengthInput = new JScrollPane( new JTextArea());
        lengthTypeSelector = new JComboBox();
        lengthTypeSelector.addItem("Words");
        lengthTypeSelector.addItem("Characters");
        lengthContainer.add(infoLabel2);
        lengthContainer.add(lengthInput);
        lengthContainer.add(lengthTypeSelector);


        infoLabel3 = new JLabel();
        JTextArea exmp = new JTextArea();
        exmp.setText(" Swing has plenty of layout managers available — both built-in and third-party. However, most of the managers are not suitable in modern UI creation.\n" +
                "\n" +
                "There are three layout managers that can do the job properly:\n" +
                "\n" +
                "    MigLayout\n" +
                "    GroupLayout\n" +
                "    FormLayout\n" +
                "\n" +
                "MigLayout, GroupLayout, and FormLayout are powerful, flexible layout managers that can cope with most layout requirements. In this tutorial, we use GroupLayout manager to get design the user interface. ");
        genText = new JScrollPane(exmp);
        genText.setMaximumSize(new Dimension(300,150));
        genText.setWheelScrollingEnabled(true);
        browseBtn = new JButton();
        browseBtn.setText("Browse");
        saveBtn = new JButton();
        saveBtn.setText("Save");
        outputContainer.add(infoLabel3);
        outputContainer.add(genText);
        outputContainer.add(browseBtn);
        outputContainer.add(saveBtn);

        generateBtn = new JButton();
        generateBtn.setText("Generate");

        alphabetContainer.setPreferredSize(new Dimension(500,150));
        lengthContainer.setPreferredSize(new Dimension(500,150));
        outputContainer.setPreferredSize(new Dimension(500,150));


        mainContainer.add(alphabetContainer);
        mainContainer.add(lengthContainer);
        mainContainer.add(generateBtn);
        mainContainer.add(outputContainer);
        mainContainer.setLayout(new GridLayout());

//        SpringLayout layout = new SpringLayout();
//        layout.addLayoutComponent("firstCtnr", alphabetContainer);
//        layout.addLayoutComponent("secondCtnr", lengthContainer);
//        layout.addLayoutComponent("thirdCtnr", generateBtn);
//        layout.addLayoutComponent("fourthCtnr", outputContainer);
//        layout.putConstraint(SpringLayout.NORTH);



    }

    public JPanel getMainContainer() {
        return mainContainer;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Entered actionListener");
        if(e.getSource() == saveBtn){
            System.out.println("Save Button Pressed");
        }
        else if(e.getSource() == generateBtn){
            System.out.println("Generate Button Pressed");
        }
        else if(e.getSource() == browseBtn){
            System.out.println("Browse Button Pressed");
        }
    }

    private String generateWords(Set<Character> alphabet){

        System.out.println("Entered the generator");
        String ret = new String();
        if(!alphabet.isEmpty()) {
            ArrayList<Character> alphabetAsList = new ArrayList<Character>(alphabet.size());
            for (Character character : alphabet) {
                alphabetAsList.add(character);
            }
            int alphabetLength = alphabetAsList.size();
            if(lengthType == 0){
                ret = generateByWordNum(alphabetAsList);
            }
            else{
                ret = generateByCharNum(alphabetAsList);
            }
        }
        return ret;
    }

    private String generateByWordNum(ArrayList<Character> alphabetAsList){

        System.out.println("Entered the genByWordNum");
        ArrayList<String> words = new ArrayList<String>(textLength);
        int alphabetLength = alphabetAsList.size();

        for(int i = 0; i < textLength; i++){
            Random random = new Random();
            int randomLength = new Random().nextInt(20);
            char[] word = new char[randomLength];

            for(int j = 0; j < randomLength; j++) {
                word[j] = alphabetAsList.get(random.nextInt(alphabetLength-1));
            }
            words.add(word.toString() + ' ');
        }
        System.out.println(words.toString());
        return words.toString();
    }

    private String generateByCharNum(ArrayList<Character> alphabetAsList){
        ArrayList<String> words = new ArrayList<String>();
        int alphabetLength = alphabetAsList.size();
        int strLen = 0;

        while(strLen < textLength){
            Random random = new Random();
            int randomLength = new Random().nextInt(20);
            char[] word = new char[randomLength];

            for(int j = 0; j < randomLength; j++)
            {
                word[j] = alphabetAsList.get(random.nextInt(alphabetLength-1));
                if(strLen >= textLength)
                    break;
                strLen++;
            }
            words.add(word.toString() + ' ');
            strLen++;
        }
        System.out.println(words.toString());
        return words.toString();
    }


    public JPanel getAlphabetContainer() {
        return alphabetContainer;
    }

    public JPanel getLengthContainer() {
        return lengthContainer;
    }

    public JPanel getOutputContainer() {
        return outputContainer;
    }

    public JButton getGenerateBtn() {
        return generateBtn;
    }
}

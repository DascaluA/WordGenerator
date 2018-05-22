package com.fmi.pao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class generateByAlphabet extends JFrame implements ActionListener{
    private JPanel mainContainer;
    private JPanel alphabetContainer;
    private JButton saveBtn;
    private JPanel lengthContainer;
    private JPanel outputContainer;
    private JTextPane generatedTextTP;
    private JButton generateBtn;
    private JButton browseBtn;
    private JScrollPane lengthSP;
    private JComboBox lengthTypeSelector;
    private JScrollPane alphabetSP;
    private JLabel infoLabel1;
    private JLabel infoLabel2;
    private JLabel infoLabel3;
    private JTextPane alphabetTP;
    private JTextPane lengthTP;
    private JScrollPane generatedTextSP;
    private JScrollBar scrollBar1;
    private JScrollBar scrollBar2;
    private JScrollBar scrollBar3;

    public generateByAlphabet() {
        System.out.println("Entered alph constructor");
        setElements();
        saveBtn.addActionListener(this);
        browseBtn.addActionListener(this);
        generateBtn.addActionListener(this);

        alphabetTP.setText("abcdefghijklmn");
        lengthTP.setText("50");

        System.out.println(generatedTextTP);

    }

    private void setElements(){
        mainContainer = new JPanel();
        alphabetContainer = new JPanel();
        lengthContainer = new JPanel();
        outputContainer = new JPanel();

        alphabetTP = new JTextPane();
        alphabetSP = new JScrollPane(alphabetTP);
        infoLabel1 = new JLabel();
        scrollBar1 = new JScrollBar();
        infoLabel1.setText("Input the alphabet:");
        alphabetContainer.add(infoLabel1);
        alphabetContainer.add(alphabetSP);
        alphabetContainer.add(scrollBar1);

        infoLabel2 = new JLabel();
        infoLabel2.setText("Input the text length");
        lengthTP = new JTextPane();
        lengthSP = new JScrollPane(lengthTP);
        lengthTypeSelector = new JComboBox();
        lengthTypeSelector.addItem("Words");
        lengthTypeSelector.addItem("Characters");
        scrollBar2 = new JScrollBar();
        lengthContainer.add(infoLabel2);
        lengthContainer.add(lengthSP);
        lengthContainer.add(lengthTypeSelector);
        lengthContainer.add(scrollBar2);


        infoLabel3 = new JLabel();
        generatedTextTP = new JTextPane();
        generatedTextSP = new JScrollPane(generatedTextTP,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                                                            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollBar3 = new JScrollBar();
        generatedTextTP.setMaximumSize(new Dimension(200,10));
        browseBtn = new JButton();
        browseBtn.setText("Browse");
        saveBtn = new JButton();
        saveBtn.setText("Save");
        outputContainer.add(infoLabel3);
        outputContainer.add(generatedTextSP);
        outputContainer.add(scrollBar3);
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
        mainContainer.setLayout(new FlowLayout());
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
            String generatedText = generateWords(getAlphabet());
            generatedTextTP.setText(generatedText);
            System.out.println("Generate Button Pressed");
        }
        else if(e.getSource() == browseBtn){
            System.out.println("Browse Button Pressed");
        }
    }

    private Set<Character> getAlphabet(){
        Set<Character> alphabet = new HashSet<>();
        char input [] = alphabetTP.getText().toCharArray();
        for (char c : input) {
            alphabet.add(c);
        }
        return alphabet;
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
            if(lengthTypeSelector.getSelectedItem().toString().equalsIgnoreCase("words")){
                ret = generateByWordNum(alphabetAsList);
            }
            else if(lengthTypeSelector.getSelectedItem().toString().equalsIgnoreCase("characters")){
                ret = generateByCharNum(alphabetAsList);
            }
        }
        return ret;
    }

    private String generateByWordNum(ArrayList<Character> alphabetAsList){

        System.out.println("Entered the genByWordNum");
        String words = new String();
        int alphabetLength = alphabetAsList.size();
        int textLength = Integer.parseInt(lengthTP.getText());
        for(int i = 0; i < textLength; i++){
            Random random = new Random();
            int randomLength = new Random().nextInt(20);
            char[] word = new char[randomLength];
            ArrayList<Character> myWord = new ArrayList<>();
            for(int j = 0; j < randomLength; j++) {
                word[j] = alphabetAsList.get(random.nextInt(alphabetLength-1));
            }
            String myword = new String();
            for (char c : word) {
                words += c;
            }
            words += ' ';
        }
        return words;
    }

    private String generateByCharNum(ArrayList<Character> alphabetAsList){
        String words = new String();
        int alphabetLength = alphabetAsList.size();
        int strLen = 0;
        int textLength = Integer.parseInt(lengthTP.getText());
        while(strLen < textLength){
            Random random = new Random();
            int randomLength = new Random().nextInt(20);
                        for(int j = 0; j < randomLength; j++)
            {
                words += alphabetAsList.get(random.nextInt(alphabetLength-1));
                if(strLen >= textLength)
                    break;
                strLen++;
            }
            words += ' ';
            strLen++;
        }
        words.trim();
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

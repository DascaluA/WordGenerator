package com.fmi.pao;

import jdk.internal.org.objectweb.asm.tree.MultiANewArrayInsnNode;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class generatedByFile implements ActionListener{

    File selectedFile;
    ArrayList<String> inputWords;

    private JPanel mainContainer;

    private JButton chooseFileButton;
    private JFormattedTextField generationLength;
    private JTextArea generatedTextTp;
    private JScrollPane generatedTextSP;
    private JLabel outputLabel;
    private JButton generateBtn;

    public generatedByFile(){

        setElements();

        chooseFileButton.addActionListener(this);
        generateBtn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == chooseFileButton){
            JFileChooser fc = new JFileChooser();
            int returnVal = fc.showOpenDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                selectedFile = fc.getSelectedFile();
            }
            inputWords = readWords(selectedFile);
        }
        else if(e.getSource() == generateBtn){
            String output = generateWords(inputWords);
            generatedTextTp.setText(output);
        }
    }

    private void setElements() {
        mainContainer = new JPanel();



        mainContainer.setLayout(new GridLayout());
        mainContainer.add(chooseFileButton);
//        generatedTextSP.add(generatedTextTp);
        mainContainer.add(generatedTextSP);
        mainContainer.add(outputLabel);
        mainContainer.add(generateBtn);
        generateBtn.setText("Generate");
        outputLabel.setText("Output:");


        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(1);
        formatter.setMaximum(Integer.MAX_VALUE);
        formatter.setAllowsInvalid(false);
        formatter.setCommitsOnValidEdit(true);
        generationLength.setFormatterFactory(new DefaultFormatterFactory(formatter));


        mainContainer.add(generationLength);
    }

    public JPanel getMainContainer() {
        return mainContainer;
    }

    private ArrayList<String> readWords(File input) {
        ArrayList<String> words = new ArrayList<>();
        Scanner sc = null;
        try {
            sc = new Scanner(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while(sc.hasNext()){
            String word = sc.next();
            words.add(word);
        }

        System.out.println(words);
        return words;
    }

    private String generateWords(ArrayList<String> alphabet){

        String words = new String();
        String length = (String) generationLength.getText();
        length = length.isEmpty() ? "0" : length;
        int resultSize = Integer.parseInt(length);

        for(int i = 0; i < resultSize; i++){
            Random random = new Random();
            int randomLength = new Random().nextInt(20);
            String myword = alphabet.get(random.nextInt(alphabet.size() - 1));

            words += ' ';
            words += myword;
        }
        return words;
    }
}

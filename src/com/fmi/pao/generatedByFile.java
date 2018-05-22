package com.fmi.pao;

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

public class generatedByFile {

    File selectedFile;

    private JPanel mainContainer;

    private JButton chooseFileButton;
    private JFormattedTextField generationLength;

    public generatedByFile() {

        setElements();

        chooseFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                int returnVal = fc.showOpenDialog(null);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    selectedFile = fc.getSelectedFile();
                }
            }
        });
    }

    private void setElements() {
        mainContainer = new JPanel();
        mainContainer.setLayout(new GridLayout());
        mainContainer.add(chooseFileButton);

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

        return words;
    }

    private String generateWords(ArrayList<String> alphabet){

        String words = new String();
        int resultSize = Integer.parseInt((String) generationLength.getValue());

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

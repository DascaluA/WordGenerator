package com.fmi.pao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FirstPage extends JFrame implements ActionListener{
    private JPanel mainContainer;
    private JPanel optionsContainer;
    private JComboBox methodOfGeneration;
    private JButton confirmButton;
    private JLabel infoLabel;
    private static generateByAlphabet alph = new generateByAlphabet();

    public FirstPage() {
        super.setTitle("Welcome");
        System.out.println("Starting generation");
        setElements();
        confirmButton.addActionListener(this);

        System.out.println("Entered Constructor");
    }

    private void setElements(){
        mainContainer = new JPanel();
        optionsContainer = new JPanel();
        methodOfGeneration = new JComboBox();
        methodOfGeneration.addItem("From File");
        methodOfGeneration.addItem("Alphabet");
        confirmButton = new JButton();
        confirmButton.setText("Confirm");
        infoLabel = new JLabel();
        infoLabel.setText("Choose your preferred generating method");

        this.setSize(new Dimension(450,150));
//        super.setResizable(false);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLocation(200,200);
        mainContainer.setVisible(true);

        optionsContainer.add(infoLabel);
        optionsContainer.add(methodOfGeneration);
        optionsContainer.add(confirmButton);

        mainContainer.add(optionsContainer);

        add(mainContainer);
        this.setLayout(mainContainer.getLayout());
        this.pack();

    }

    public static void main(String[] args) {
        FirstPage fp = new FirstPage();
        fp.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == confirmButton){
            if(methodOfGeneration.getSelectedItem().toString().equalsIgnoreCase("alphabet")) {
                System.out.println("Alphabet generation method selected");
                setSize(new Dimension(900,600));
                this.remove(mainContainer);
                this.setLayout(alph.getMainContainer().getLayout());
                mainContainer = alph.getMainContainer();
                this.add(mainContainer);
                this.setLayout(new SpringLayout());
            }
            else {
                System.out.println("Alphabet generation method selected");

            }

        }
    }
}

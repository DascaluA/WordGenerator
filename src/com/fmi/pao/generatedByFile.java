package com.fmi.pao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;

public class generatedByFile {

    File selectedFile;

    private JPanel mainContainer;

    private JButton chooseFileButton;

    public generatedByFile() {

        setElements();

        chooseFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                fc.showOpenDialog(null);
                int returnVal = fc.showOpenDialog(frame);
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
    }

    public JPanel getMainContainer() {
        return mainContainer;
    }
}

package com.myApp.view;

import com.myApp.controller.Controller;
import com.myApp.model.BuddyInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

//@Component
public class View implements ActionListener {

    private Controller c;
    JTextField tfName, tfNumber, tfAddress, tfAge;

//    @Autowired
    public View(Controller c) {
        this.c = c;
        System.out.println(c);
        JFrame frame = new JFrame("This sucks");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,300);
        JPanel panel = new JPanel();
        JPanel panel1 = new JPanel(new FlowLayout());
        JPanel panel2 = new JPanel(new FlowLayout());
        JPanel panel3 = new JPanel(new FlowLayout());
        JPanel panel4 = new JPanel(new FlowLayout());
        JLabel label1 = new JLabel("Name");
        tfName = new JTextField(20);
        JLabel label2 = new JLabel("Number");
        tfNumber = new JTextField(20);
        JLabel label3 = new JLabel("Address");
        tfAddress = new JTextField(20);
        JLabel label4 = new JLabel("Age");
        tfAge = new JTextField(2);
        JButton button = new JButton("Submit");
        button.addActionListener(this);

        panel1.add(label1);
        panel1.add(tfName);
        panel2.add(label2);
        panel2.add(tfNumber);
        panel3.add(label3);
        panel3.add(tfAddress);
        panel4.add(label4);
        panel4.add(tfAge);

        panel.add(panel1);
        panel.add(panel2);
        panel.add(panel3);
        panel.add(panel4);
        panel.add(button);

        frame.getContentPane().add(panel); // Adds Button to content pane of frame
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        c.addBuddy(new BuddyInfo(tfName.getText(), tfAddress.getText(), tfNumber.getText(), Integer.parseInt(tfAge.getText())));
        tfName.setText("");
        tfAddress.setText("");
        tfNumber.setText("");
        tfAge.setText("");
    }
}

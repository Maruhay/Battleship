package com.srccodes;

import java.awt.Dimension;

import javax.swing.*;

public class Game {
 
    public static void main(String[] args) {
        JFrame frame = new JFrame("HelloWorldSwing");
        frame.setMinimumSize(new Dimension(400, 400));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
 
        //Add the ubiquitous "Hello World" label.
        JLabel label = new JLabel("KOKOKO");
        frame.getContentPane().add(label);
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
}

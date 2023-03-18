package com.sicelo;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class DecryptMessage extends JFrame implements ActionListener{
    private JPanel panelNorth, panelCenter, panelSouth;
    private JLabel lblImage;
    private JLabel lblHeading;
    private JTextArea txtMessage;
    private JScrollPane scrollPane;
    private JButton btnDecrypt, btnClear, btnQuit;
    private Font ft;

    public DecryptMessage() {
        super("My Decryption Application ver 1.0");
        panelNorth = new JPanel();
        panelCenter = new JPanel();
        panelSouth = new JPanel();
        lblImage = new JLabel(new ImageIcon("duke.running.gif"));
        lblHeading = new JLabel("Decrypt It!");
        txtMessage = new JTextArea();
        scrollPane = new JScrollPane(txtMessage);
        btnDecrypt = new JButton("Decrypt");
        btnClear = new JButton("Clear");
        btnQuit = new JButton("Quit");
        ft = new Font("Arial", Font.PLAIN, 24);
    }

    public void setGUI() {
        panelNorth.setLayout(new FlowLayout());
        panelCenter.setLayout(new GridLayout(1, 1));
        panelSouth.setLayout(new GridLayout(1, 3));

        lblHeading.setFont(ft);
        txtMessage.setFont(ft);
        btnDecrypt.setFont(ft);
        btnClear.setFont(ft);
        btnQuit.setFont(ft);

        panelNorth.setBackground(Color.white);
        lblHeading.setForeground(Color.red);
        scrollPane.setPreferredSize(new Dimension(800, 300));
        txtMessage.setBackground(Color.LIGHT_GRAY);
        txtMessage.setLineWrap(true);
        txtMessage.setWrapStyleWord(true);

        panelNorth.add(lblImage);
        panelNorth.add(lblHeading);
        panelCenter.add(scrollPane);
        panelSouth.add(btnDecrypt);
        panelSouth.add(btnClear);
        panelSouth.add(btnQuit);

        this.add(panelNorth, BorderLayout.NORTH);
        this.add(panelCenter, BorderLayout.CENTER);
        this.add(panelSouth, BorderLayout.SOUTH);

        btnDecrypt.addActionListener(this);
        btnClear.addActionListener(this);
        btnQuit.addActionListener(this);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Decrypt")) {
            performDecryption();
        }
        else if (e.getActionCommand().equals("Clear")) {
            txtMessage.setText("");
        }
        else if (e.getActionCommand().equals("Quit")) {
            System.exit(0);
        }
    }

    public void close(Closeable c) {
        if (c == null)
            return;
        try {
            c.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Couldn't close");
        }
    }

    public void performDecryption() {
        FileReader inFile = null;
        BufferedReader reader = null;
        FileWriter outFile = null;
        PrintWriter writer = null;
        int c1, c2, c3;
        String s;
        char data;
        String message = "";

        try {
            inFile = new FileReader("encryptedmessage.txt");
            reader = new BufferedReader(inFile);

            while ((c1 = reader.read()) != -1) {
                c2 = reader.read();
                c3 = reader.read();
                s = "" + (char)c1 + (char)c2 + (char)c3;
                data = (char) Integer.parseInt(s);
                message = "" + data;
                txtMessage.append(message);
            }
        }
        catch (Exception fe) {
            JOptionPane.showMessageDialog(null, fe.getMessage());
        }
        finally {
            close(reader);
            close(inFile);
            close(writer);
            close(outFile);
        }
    }

    public static void main(String[] args) {
        new DecryptMessage().setGUI();
    }
}

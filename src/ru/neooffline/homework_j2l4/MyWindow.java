package ru.neooffline.homework_j2l4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class MyWindow extends JFrame {
    private StringBuilder stringBuilder = new StringBuilder();
    JPanel chat = new JPanel(new FlowLayout());
    JPanel inputSend = new JPanel(new BorderLayout());
    JTextArea chatMessages = new JTextArea(getHeight() / 50, getWidth() / 50);
    JTextField inputMessage = new JTextField(30);
    JScrollPane scrollPane = new JScrollPane(chatMessages);
    JButton sendButton = new JButton("send");

    public MyWindow() {
        setWindow();
        setVisible(true);
    }

    private void setWindow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(300, 300, 480, 640);
        setTitle("Чат " + getDateTime("full"));
        chatMessages.setText("Добро пожаловать в чат!!!");
        chat.add(scrollPane);
        inputMessage.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    appendText(inputMessage.getText());
                    inputMessage.setText("");
                    inputMessage.requestFocus();
                }
            }
        });
        inputSend.add(inputMessage, BorderLayout.CENTER);
        sendButton.addActionListener(e -> {
            appendText(inputMessage.getText());
            inputMessage.setText("");
            inputMessage.requestFocus();
        });
        inputSend.add(sendButton, BorderLayout.EAST);
        add(scrollPane, BorderLayout.CENTER);
        add(inputSend, BorderLayout.SOUTH);
    }

    private void appendText(String text) {
        chatMessages.setText(stringBuilder.append(getDateTime("time") + ": " + text + "\n").toString());
    }

    private String getDateTime(String type) {
        String pattern = type.equals("full") ? "HH:mm yyyy-MM-dd" : "HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.getDefault());
        GregorianCalendar calendar = new GregorianCalendar(TimeZone.getTimeZone("GMT3+"));
        calendar.setTimeInMillis(System.currentTimeMillis());
        return sdf.format(calendar.getTime());
    }
}

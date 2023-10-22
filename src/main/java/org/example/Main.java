package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static Logic gameLogica;
    public static JTextField textField;

    public static void main(String[] args) {


//Костыль для UI

        gameLogica = new Logic();
        JFrame frame = new JFrame("Окно с полем ввода");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        textField = new JTextField(20);
        JButton button = new JButton("Нажми меня");
        panel.add(textField);
        panel.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainLogic();
            }
        });
        frame.add(panel);
        frame.setSize(300, 100);
        frame.setVisible(true);
    }

    public static void MainLogic() {
        String inputText = textField.getText(); // Получаем текст из поля ввода
        gameLogica.SortWordUser(inputText);
        int returncode = gameLogica.getReturnCode();

        gameLogica.getScore();
        if (returncode == 0) {
            System.out.println("all good");
            textField.setText("");
        } else if (returncode == 1) {
            System.out.println("word less than 3 latters");
            textField.setText("");
        } else if (returncode == 2) {
            System.out.println("you write здаюсь ");
            textField.setText("");
        } else if (returncode == 4) {
            System.out.println("word is used");
            textField.setText("");
        } else if (returncode == 5) {
            System.out.println("Later is not correct. last word ended on the auther later");
            textField.setText("");
        } else if (returncode == 6) {
            System.out.println("this is not a city from world");
            textField.setText("");
        } else if (returncode == 6) {
            System.out.println("Computer loser");
            textField.setText("");
        }
    }
}
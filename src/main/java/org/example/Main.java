package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    private static UI ui = new UI();
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
        String inputText = UI.getUserCityTxt(); // Получаем текст из поля ввода
        gameLogica.SortWordUser(inputText); /// Отправляем текст в сортировку
        int returnCode = gameLogica.getReturnCode();

        gameLogica.returnLastWord(); // Что сказал компьютер
        gameLogica.resultCount(); // Возврат результатов игры

        if (returnCode == 0) {
            System.out.println("all good");
            textField.setText("");
        } else if (returnCode == 1) {
            System.out.println("word less than 3 latters. result " + gameLogica.resultCount());
            textField.setText("");
        } else if (returnCode == 2) {
            System.out.println("you write Здаюсь ");
            System.out.println("result" + gameLogica.resultCount());
            textField.setText("");
        } else if (returnCode == 3) {
            System.out.println("word is used");
            System.out.println("result" + gameLogica.resultCount());
            textField.setText("");
        }else if (returnCode == 4) {
            System.out.println("this is not a city from world");
            System.out.println("result" + gameLogica.resultCount());
            textField.setText("");
        } else if (returnCode == 5) {
            System.out.println("Later is not correct. last word ended on the auther later");
            System.out.println("result" + gameLogica.resultCount());
            textField.setText("");
        }  else if (returnCode == 7) {
            System.out.println("Computer loser");
            System.out.println("result" + gameLogica.resultCount());
            textField.setText("");
        }
    }
}
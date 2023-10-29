package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI {
    static Logic logic = new Logic();
    static private JFrame startFrame = new JFrame("Вітання!");// Начальный экран
    static private JFrame mainFrame = new JFrame("Міста");// Экран игры
    static Font font = new Font("Arial", Font.PLAIN, 15);// Выбор шрифта
    static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();// Определение размера экрана
//    final Label computerLabel = new Label();



    private static String userCityTxt;

    public static void main(String[] args) {
        int startFrameWidth = 400;
        int startFrameHeight = 100;
        int xStart = (screenSize.width - startFrameWidth) / 2;
        int yStart = (screenSize.height - startFrameHeight) / 2;
        startFrame.setSize(startFrameWidth, startFrameHeight);
        Label startLabel = new Label("Вітаю у грі міста. Для продовження натисність СТАРТ");// Приветственная надпись
        startLabel.setBounds(3, 1, 400, 20);
        startLabel.setFont(font);
        Button startButton = new Button("Старт");// Кнопка Старт
        startButton.setBounds(160, 20, 50, 40);
        startButton.setFont(font);
        startFrame.add(startLabel);
        startFrame.add(startButton);
        startFrame.setLayout(null);
        startFrame.setLocation(xStart, yStart); // Вывод окна по центру экрана
        startFrame.setVisible(true);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startFrame.dispose();// Закрытие начального окна
                int mainFrameWidth = 400;
                int mainFrameHeight = 275;
                int xMain = (screenSize.width - mainFrameWidth) / 2;
                int yMain = (screenSize.height - mainFrameHeight) / 2;
                mainFrame.setSize(mainFrameWidth, mainFrameHeight); // Основной экран игры
                Label playLabel = new Label("Введіть назву міста");
                JTextField userCity = new JTextField(10);
                userCity.setBounds(210, 40, 150, 40);
                userCity.setFont(font);
                playLabel.setBounds(10, 40, 150, 50);
                playLabel.setFont(font);
                Button playButton = new Button("ХІД");

                playButton.setBounds(210, 100, 150, 40);
                playButton.setFont(font);
                 Label computerLabel = new Label("Комп'ютер:");
                computerLabel.setBounds(10, 100, 200, 20);
                computerLabel.setFont(font);
                playButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String userCityTxt = userCity.getText();
                        logic.SortWordUser(userCityTxt);
                        int code = logic.getReturnCode();
                        if (code == 0) {
                            computerLabel.setText("Комп'ютер: " + logic.returnLastWord());
                        } else if (code == 4 ) {
                            computerLabel.setText("Комп'ютер: Вибачте");
                            Label label1 = new Label("я не знаю такого міста");
                            label1.setFont(font);
                            label1.setBounds(10, 120, 200, 20);
                            mainFrame.add(label1);
                        } else if (code == 7) {
                            Frame endFrame = new Frame("Міста");
                            int endFrameWidth = 400;
                            int endFrameHeight = 100;
                            int xEnd = (screenSize.width - startFrameWidth) / 2;
                            int yEnd = (screenSize.height - startFrameHeight) / 2;
                            endFrame.setSize(startFrameWidth, startFrameHeight);
                            Label endLabel = new Label("Вітаю! Ви Перемогли!");// Приветственная надпись
                            endLabel.setBounds(150, 40, 400, 20);
                            endLabel.setFont(font);
                            Label label1 = new Label("Ви заробили " + logic.getCounterUser());
                            label1.setBounds(150, 60, 400, 20);
                            label1.setFont(font);
                            endFrame.add(endLabel);
                            endFrame.add(label1);
                            endFrame.setLayout(null);
                            endFrame.setLocation(xStart, yStart); // Вывод окна по центру экрана
                            endFrame.setVisible(true);
                        } else if (userCityTxt.equals("Здаюсь")) {
                            Frame endFrame = new Frame("Міста");
                            int endFrameWidth = 400;
                            int endFrameHeight = 100;
                            int xEnd = (screenSize.width - startFrameWidth) / 2;
                            int yEnd = (screenSize.height - startFrameHeight) / 2;
                            endFrame.setSize(startFrameWidth, startFrameHeight);
                            Label endLabel = new Label("Нажаль ви програли");// Приветственная надпись
                            endLabel.setBounds(145, 40, 400, 20);
                            endLabel.setFont(font);
                            Label label1 = new Label("Ви заробили " + logic.getCounterUser() + " очка");
                            label1.setBounds(150, 60, 400, 20);
                            label1.setFont(font);
                            endFrame.add(endLabel);
                            endFrame.add(label1);
                            endFrame.setLayout(null);
                            endFrame.setLocation(xStart, yStart); // Вывод окна по центру экрана
                            endFrame.setVisible(true);
                        }


                    }
                });

                mainFrame.add(playLabel);
                mainFrame.add(computerLabel);
                mainFrame.add(userCity);
                mainFrame.add(playButton);
                mainFrame.setLayout(null);
                mainFrame.setLocation(xMain, yMain);
                mainFrame.setVisible(true);

            }
        });
    }
    public static String getUserCityTxt() {
        return userCityTxt;
    }

}

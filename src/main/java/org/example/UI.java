package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI {
    static private JFrame startFrame = new JFrame("Вітання!");// Начальный экран
    static private JFrame mainFrame = new JFrame("Міста");// Экран игры
    static Font font = new Font("Arial", Font.PLAIN, 15);// Выбор шрифта
    static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();// Определение размера экрана

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
                playLabel.setBounds(10, 40, 150, 50);
                playLabel.setFont(font);
                Label computerLabel = new Label("Комп'ютер:" + "город из логики");
                computerLabel.setBounds(10, 100, 200, 50);
                computerLabel.setFont(font);
                JTextField userCity = new JTextField(10);
                userCity.setBounds(210, 40, 150, 40);
                userCity.setFont(font);
                Button playButton = new Button("ХІД");
                playButton.setBounds(210, 100, 150, 40);
                playButton.setFont(font);
                mainFrame.add(playLabel);
                mainFrame.add(computerLabel);
                mainFrame.add(userCity);
                mainFrame.add(playButton);
                mainFrame.setLayout(null);
                mainFrame.setLocation(xMain, yMain);
                mainFrame.setVisible(true);
                playButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String userCityTxt = userCity.getText();
                        System.out.println(userCityTxt);
                    }
                });
            }
        });
    }

}

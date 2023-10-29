package org.example;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Для ментора.  Не хочу писать else if() для более легкой прочитки кода
public class Logic {
    private int returnCode = 0;
    ArrayList<String> listOfUsedCity;
    ArrayList<String> listOfAllCity;

    private int counterUser = 0;
    private int numberOfCountryThatComputerKnow = 0;

    public Logic() {
        listOfUsedCity = new ArrayList<>(); // Место где храним использованые города за всю игру
        listOfAllCity = getCapitalsList();
        Random random = new Random(); // Генерация того на каком ходу проиграет компьютер
        int randomInt = random.nextInt(0, 6); // Сколько ходов сможет сделать компьютер
        setNumberOfCountryThatComputerKnow(randomInt);
    }

    public int getCounterUser() {
        return counterUser;
    }

    public void setCounterUser() {
        this.counterUser++;

    }

    public int getNumberOfCountryThatComputerKnow() {
        return numberOfCountryThatComputerKnow;
    }

    public void setNumberOfCountryThatComputerKnow(int numberOfCountryThatComputerKnow) {
        this.numberOfCountryThatComputerKnow = numberOfCountryThatComputerKnow;
    }

    public int getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }

    public void SortWordUser(String wordUser) {

        //setReturnCode(Число); Используем для того чтобы понять что пошло не так или так

        // 1 Проверяем длинну; - Если слово состоит из малого количества букв = Код возврата = 1
        // 2 НЕ = ли здаюсь; - Если слово здаюсь - Код возврата = 2
        // 3 Не в списке использованых; - Слово было использовано - Код возврата = 3
        // 4 а город ли это; Игрок ввел НЕ город - Код возврата = 4
        // 5 1-буква введенного города не= последней букве ранее введенного; 1 буква слова что ввел игрок НЕ соответствует последней букве компьютера - Код возврата = 5
        // 6 добавление всего в случае прохождения проверок; Все проверки пройдены игра продолжаеться - Код возврата = 0
        // 7 Компьютер сдался

        setReturnCode(0);

        wordUser = wordUser.substring(0, 1).toUpperCase() + wordUser.substring(1).toLowerCase().strip();
        // Делаем так чтобы слово приняло облик "Слово". Пример - пришло "ТоРОнтО" - Возврат "Торонто"

        // 1 Проверяем длинну
        if (wordUser.length() < 3) {
            this.setReturnCode(1);

        }
        // 2 НЕ = ли слово здаюсь
        if (wordUser.equals("Здаюсь") && getReturnCode() == 0) {
            this.setReturnCode(2);

        }
        // 3 Не в списке использованых
        if (this.listOfUsedCity.contains(wordUser)) {
            this.setReturnCode(3);

        }
        // 4 А город ли это во всем мире
        if (!this.listOfAllCity.contains(wordUser) && getReturnCode() == 0) {
            this.setReturnCode(4);

        }

        // 5 1-буква введенного города не= последней букве ранее введенного + делаем приводим их в один регистр
        if (this.listOfUsedCity.size() > 0 && getReturnCode() == 0) {
            String lastWord = String.valueOf(this.listOfUsedCity.get(listOfUsedCity.size() - 1));
            String lastLetter = String.valueOf(lastWord.charAt(lastWord.length() - 1)).toUpperCase();
            String firstLatter = String.valueOf(wordUser.charAt(0)).toUpperCase();

            if (!lastLetter.equals(firstLatter) && getReturnCode() == 0) {
                this.setReturnCode(5);

            }
        }

        // 7 Компьютер проиграл
        if (listOfUsedCity.size() * 2 > getNumberOfCountryThatComputerKnow() && getReturnCode() == 0) {
            setCounterUser();
            this.setReturnCode(7);
        }

        // 6 добавление всего в случае прохождения всех проверок
        if (returnCode == 0 && getReturnCode() == 0) {
            listOfUsedCity.add(wordUser);
            setCounterUser();
            listOfAllCity.remove(wordUser);

            stepComputer();
        }
    }

    // Шаг компьютера
    public void stepComputer() {
        String lastWord = String.valueOf(this.listOfUsedCity.get(listOfUsedCity.size() - 1));
        String founder = String.valueOf(lastWord.charAt(lastWord.length() - 1)).toUpperCase();
        boolean flagLoser = false;

        for (String wordFromList : listOfAllCity) {
            if (wordFromList.contains(founder)) {
                flagLoser = true;
                System.out.println("Computer said + " + wordFromList);
                listOfUsedCity.add(wordFromList);
                listOfAllCity.remove(wordFromList);
                setNumberOfCountryThatComputerKnow(getNumberOfCountryThatComputerKnow() - 1);
                break;
            }
        }
        if (flagLoser == false) {
            setReturnCode(7);
        }
    }

    public String returnLastWord() {
//        ListOfUsedCity.add("a");
        String valuedOf = String.valueOf(this.listOfUsedCity.get(listOfUsedCity.size() - 1)); // Слово что вывел компьютер
        System.out.println(valuedOf);
        return valuedOf;
    }


    // Вывод счета
    public String resultCount() {
        int finalCount = 0;
        String answer = "";

        finalCount = getCounterUser();

        if (this.getReturnCode() == 7) {
            answer = "User выиграл. Названо стран " + finalCount;
        } else {
            answer = " Компьютер выиграл. Названо стран" + finalCount;
        }
        return answer;
    }

    public static ArrayList<String> getCapitalsList() {

        ArrayList<String> capitals = new ArrayList<>();
        try (BufferedReader csvReader = new BufferedReader(new FileReader("Data.txt"))) {
            String row;

            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(";");
                capitals.add(data[1]);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return capitals;

    }
}


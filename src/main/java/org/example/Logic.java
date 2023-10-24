package org.example;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Для ментора.  Не хочу писать else if() для более легкой прочитки кода
public class Logic {
    private int ReturnCode = 0;
    ArrayList<String> ListOfCity;
    ArrayList<String> ListOfUsedCity;
    ArrayList<String> ListOfAllCity;

    private int NumberOfCountryThatComputerKnow = 0;
    public Logic() {
        ListOfCity = new ArrayList<>(List.of(new String[]{"Kiev", "Valenm", "Mamoa", "Sierra"})); // Города с которыми мы играем
        ListOfUsedCity = new ArrayList<>(); // Место где храним использованые города за всю игру
        ListOfAllCity = new ArrayList<>(List.of(new String[]{"Kiev", "Valenm", "Mamoa", "Sierra"})); // Все города мира чтобы делать проверку на User написал правильный город мира
        Random random = new Random(); // Генерация того на каком ходу проиграет компьютер
        int randomInt = random.nextInt(0, 15);
        setNumberOfCountryThatComputerKnow(randomInt);
    }

    public int getNumberOfCountryThatComputerKnow() {
        return NumberOfCountryThatComputerKnow;
    }

    public void setNumberOfCountryThatComputerKnow(int numberOfCountryThatComputerKnow) {
        NumberOfCountryThatComputerKnow = numberOfCountryThatComputerKnow;
    }

    public int getReturnCode() {
        return ReturnCode;
    }

    public void setReturnCode(int returnCode) {
        ReturnCode = returnCode;
    }

    public void SortWordUser(String WordUser) {
        setReturnCode(0);

        WordUser = WordUser.substring(0, 1).toUpperCase() + WordUser.substring(1).toLowerCase();
        // Делаем так чтобы слово приняло облик "Слово". Пример - пришло "ТоРОнтО" - Возврат "Торонто"


        /*
        1 Сделать counter ++
        2 сделать Логику компьютера

        *Дополнительно можно сделать ArrayList в котором будут города под рандомными индексами
        */

        //setReturnCode(Число); Используем для того чтобы понять что пошло не так или так

        // 1 Проверяем длинну; - Если слово состоит из малого количества букв = Код возврата = 1
        // 2 НЕ = ли здаюсь; - Если слово здаюсь - Код возврата = 2
        // 3 Не в списке использованых; - Слово было использовано - Код возврата = 3
        // 4 а город ли это; Игрок ввел НЕ город - Код возврата = 4
        // 5 1-буква введенного города не= последней букве ранее введенного; 1 буква слова что ввел игрок НЕ соответствует последней букве компьютера - Код возврата = 5
        // 6 добавление всего в случае прохождения проверок; Все проверки пройдены игра продолжаеться - Код возврата = 0
        // 7 Компьютер сдался

        // 1 Проверяем длинну
        if (WordUser.length() < 3) {
            this.setReturnCode(1);

        }
        // 2 НЕ = ли слово здаюсь
        if (WordUser.equals("здаюсь") && getReturnCode() == 0) {
            this.setReturnCode(2);

        }
        // 3 Не в списке использованых
        if (this.ListOfUsedCity.contains(WordUser)) {
            this.setReturnCode(3);

        }
        // 4 А город ли это во всем мире
        if (this.ListOfUsedCity.size() > 0 && this.ListOfAllCity.contains(WordUser) && getReturnCode() == 0) {
            this.setReturnCode(4);

        }
        // 5 1-буква введенного города не= последней букве ранее введенного + делаем приводим их в один регистр !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        if (this.ListOfUsedCity.size() > 0 && getReturnCode() == 0) {
            String LastWord = String.valueOf(this.ListOfUsedCity.get(ListOfUsedCity.size() - 1));
            String LastLetter = String.valueOf(LastWord.charAt(LastWord.length() - 1)).toUpperCase();
            String FirstLatter = String.valueOf(WordUser.charAt(0)).toUpperCase();

            if (LastLetter != FirstLatter && getReturnCode() == 0) {
                this.setReturnCode(5);

            }
        }

        // 7 Компьютер проиграл
        if (ListOfUsedCity.size() * 2 > getNumberOfCountryThatComputerKnow()){
            this.setReturnCode(7);
        }

        // 6 добавление всего в случае прохождения всех проверок
        if (ReturnCode == 0 && getReturnCode() == 0) {
            ListOfUsedCity.add(WordUser);
            ListOfCity.remove(WordUser);

            StepComputer();
        }
    }
        // Шаг компьютера
    public void StepComputer() {
        String LastWord = String.valueOf(this.ListOfUsedCity.get(ListOfUsedCity.size() - 1));
        String founder = String.valueOf(LastWord.charAt(LastWord.length() - 1)).toUpperCase();
        boolean flagLoser = false;

        for (String wordFromList : ListOfCity) {
            if (wordFromList.contains(founder)) {
                flagLoser = true;
                System.out.println("Computer said + " + wordFromList);
                ListOfUsedCity.add(wordFromList);
                ListOfCity.remove(wordFromList);
                break;
            }
        }
        if (flagLoser = false){
            setReturnCode(7);
        }
    }

    public String ReturnLastWord(){
        String LastWord = String.valueOf(ListOfUsedCity.size() - 1); // Слово что вывел компьютер
        return LastWord;
    }

    // Вывод счета
    public String ResultCount(){
        int CountOfAllCountry = ListOfUsedCity.size();
        int FinalCount = 0;
        String Answer = "";
        if (CountOfAllCountry % 2 == 0){ // Четное число
            FinalCount = CountOfAllCountry / 2;
            Answer = "User выиграл. Названо стран" + FinalCount;
        }else {             // НЕ Четное число
            FinalCount = (CountOfAllCountry + 1) / 2;
            Answer = " Компьютер выиграл. Названо стран" + FinalCount;
        }
        return Answer;
    }
}


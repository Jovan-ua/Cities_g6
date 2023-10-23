package org.example;
import java.util.ArrayList;
import java.util.List;
// Для ментора.  Не хочу писать else if() для более легкой прочитки кода
public class Logic {
    private int ReturnCode = 0;
    ArrayList<String> ListOfCity;
    ArrayList<String> ListOfUsedCity;
    ArrayList<String> ListOfAllCity;
    private int score;

    public Logic() {
        ListOfCity = new ArrayList<>(List.of(new String[]{"Cameroon", "Solomon Islands", "Mamoa", "Sierra Leone"})); // Города с которыми мы играем
        ListOfUsedCity = new ArrayList<>(); // Место где храним использованые города за всю игру
        ListOfAllCity = new ArrayList<>(List.of(new String[]{"Kiev", "Cameroon", "Mamoa", "Sierra Leone"})); // Все города мира чтобы делать проверку на User написал правильный город мира
        score = 0;
    }

    public int getScore() {
        return score;
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
        1 Сделать counter
        2 сделать Логику компьютера
        3 Сменить города на украинский текст

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
        if (this.ListOfUsedCity.size() > 0 && this.ListOfUsedCity.contains(WordUser) && getReturnCode() == 0) {
            this.setReturnCode(4);

        }
        // 5 1-буква введенного города не= последней букве ранее введенного + делаем приводим их в один регистр
        if (this.ListOfUsedCity.size() > 0 && getReturnCode() == 0) {
            String LastWord = String.valueOf(ListOfUsedCity.size() - 1);
            String LastLetter = String.valueOf(LastWord.charAt(LastWord.length() - 1)).toUpperCase();
            String FirstLatter = String.valueOf(WordUser.charAt(0)).toUpperCase();

            if (LastLetter != FirstLatter && getReturnCode() == 0) {
                this.setReturnCode(5);

            }
        }
        // 6 добавление всего в случае прохождения всех проверок
        if (ReturnCode == 0 && getReturnCode() == 0) {
            ListOfUsedCity.add(WordUser);
            ListOfCity.remove(WordUser);

            StepComputer();
        }


        // 7
//        if (ListOfCity.size() == 0){
//            setReturnCode(7);
//        }
    }

    public void StepComputer() {
        String LastWord = String.valueOf(this.ListOfUsedCity.size() - 1);
        String founder = String.valueOf(LastWord.charAt(LastWord.length() - 1));

        for (String wordFromList : ListOfCity) {
            if (wordFromList.contains(founder)) {
                System.out.println("Computer said + " + wordFromList);
                ListOfUsedCity.add(wordFromList);
                ListOfCity.remove(wordFromList);
                break;
            }
        }
    }

    public String ReturnLastWord(){
        String LastWord = String.valueOf(ListOfUsedCity.size() - 1); // Слово что вывел компьютер
        return LastWord;
    }
}


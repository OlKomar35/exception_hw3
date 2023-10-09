package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Main {
    public static final int NUMBER_OF_ITEMS = 6;

    public static void main(String[] args) {
        System.out.println("Введите свои данные в произвольном порядке: ");
        System.out.println("Фамилия Имя Отчество дата рождения( формате dd.mm.yyyy) номер телефона(в формате 7835931) пол(f или m)");

        Scanner scanner = new Scanner(System.in, "UTF-8");
        String msg = scanner.nextLine();

        String[] listWithData = msg.split(" ");

        int numException = correctInput(listWithData);

        if (numException == 0) {


            String personName = searchSurnameFirstNamePatronymic(listWithData);
            String dateOfBirth = searchDateOfBirth(listWithData);
            int phoneNumber = searchPhoneNumber(listWithData);
            char gender = searchGender(listWithData);

            String fileName =  personName.split(" ")[0].toLowerCase()+".txt";
            try(FileWriter fw = new FileWriter(fileName,true)){
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(personName);
                stringBuilder.append(dateOfBirth);
                stringBuilder.append(" ");
                stringBuilder.append(phoneNumber);
                stringBuilder.append(" ");
                stringBuilder.append(gender);

                fw.write(stringBuilder.toString());
                fw.append('\n');
                fw.flush();
                System.out.printf("Данные успешно сохранены в файл %s", fileName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } else if (numException > 0) {
            System.out.printf("Введено не верное количество данных, не хватает %d компонента(ов)", numException);
        } else {
            System.out.printf("Введено не верное количество данных, введено лишнее количество компонентов: %d" +
                    "", Math.abs(numException));
        }


        scanner.close();


    }

    public static int correctInput(String[] list) {
        if (list.length == NUMBER_OF_ITEMS) {
            return 0;
        } else {
            return NUMBER_OF_ITEMS - list.length;
        }
    }

    public static String searchSurnameFirstNamePatronymic(String[] dataList) {
        StringBuilder stringBuilder = new StringBuilder("");
        int count = 0;
        for (int i = 0; i < dataList.length; i++) {
            if (dataList[i].matches("[a-zA-Z]+") || dataList[i].matches("[а-яА-Я]+")) {
                if (dataList[i].length() >= 3) {
                    stringBuilder.append(dataList[i]);
                    stringBuilder.append(" ");
                    count++;
                }
            }
        }
        if (stringBuilder.toString() == "" || count != 3) {
            throw new ConsistsOfLettersException();
        }
        return stringBuilder.toString();
    }

    public static String searchDateOfBirth(String[] dataList) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        sdf.setLenient(false);
        int i = 0;
        while (i < dataList.length) {
            try {
                sdf.parse(dataList[i]);
                return dataList[i];
            } catch (ParseException e) {
                i++;
            }
        }
        return null;
    }

    public static int searchPhoneNumber(String[] dataList) {
        int num = -1;
        for (int i = 0; i < dataList.length; i++) {
            if (dataList[i].matches("[0-9]+")) {
                num = Integer.parseInt(dataList[i]);
                return num;
            }
        }
        if (num == -1) {
            throw new ConsistsOfNumbersException();
        }
        return num;
    }

    public static char searchGender(String[] dataList){
        char c = '0';
        for (String item: dataList){
            if(item.equalsIgnoreCase("f")||item.equalsIgnoreCase("m")){
                c = item.charAt(0);
                return c;
            }
        }
        if(c == '0'){
            throw new ConsistsOfOneLatterException();
        }
        return c;
    }
}
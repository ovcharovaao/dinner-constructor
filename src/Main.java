import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static DinnerConstructor dc;
    static Scanner scanner;

    public static void main(String[] args) {
        dc = new DinnerConstructor();
        scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            String command = scanner.nextLine();

            switch (command) {
                case "1":
                    addNewDish();
                    break;
                case "2":
                    generateDishCombo();
                    break;
                case "3":
                    return;
            }
        }
    }

    private static void printMenu() {
        System.out.println("Выберите команду:");
        System.out.println("1 - Добавить новое блюдо");
        System.out.println("2 - Сгенерировать комбинации блюд");
        System.out.println("3 - Выход");
    }

    private static void addNewDish() {
        System.out.println("Введите тип блюда:");
        String dishType = scanner.nextLine();
        while (dishType.isEmpty()) {
            System.out.println("Вы ввели пустую строку. Пожалуйста, введите тип блюда:");
            dishType = scanner.nextLine();
        }

        System.out.println("Введите название блюда:");
        String dishName = scanner.nextLine();
        while (dishName.isEmpty()) {
            System.out.println("Вы ввели пустую строку. Пожалуйста, введите название блюда:");
            dishName = scanner.nextLine();
        }

        // добавьте новое блюдо
        dc.saveAllDishes(dishType, dishName);
    }

    private static void generateDishCombo() {
        if (dc.menuByType.keySet().isEmpty()) {
            System.out.println("Нет возможности сгенерировать комбинации, так как меню пустое.");
            System.out.println();
        } else {
            System.out.println("Начинаем конструировать обед...");

            int numberOfCombos = checkNumberOfCombos();

            ArrayList<String> selectedTypes = checkSelectedTypes();

            // сгенерируйте комбинации блюд и выведите на экран
            dc.generateDishCombo(numberOfCombos, selectedTypes);
        }
    }

    public static int checkNumberOfCombos() {
        System.out.println("Введите количество наборов, которые нужно сгенерировать:");

        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.println("Пожалуйста, введите количество наборов (целое положительное число):");
        }

        int numberOfCombos = scanner.nextInt();
        scanner.nextLine();

        while (true) {
            if (numberOfCombos < 1) {
                System.out.println("Невозможно составить " + numberOfCombos + " наборов.");
                System.out.println("Пожалуйста, введите количество наборов (целое положительное число):");
                while (!scanner.hasNextInt()) {
                    scanner.next();
                    System.out.println("Пожалуйста, введите количество наборов (целое положительное число):");
                }
                int attemptToInput = scanner.nextInt();
                scanner.nextLine();
                numberOfCombos = attemptToInput;
            } else {
                break;
            }
        }
        System.out.println();
        return numberOfCombos;
    }


    public static ArrayList<String> checkSelectedTypes() {
        System.out.println("Вводите типы блюда (можно повторять), разделяя символом переноса строки (enter)." +
                " Для завершения ввода введите пустую строку.");
        System.out.println("Сейчас в меню доступны следующие типы блюда: " +
                        dc.menuByType.keySet() + ".");

        String nextItem = scanner.nextLine();

        while (nextItem.isEmpty()) {
            System.out.println("Введите хотя бы один тип блюда из представленных в меню: " +
                    dc.menuByType.keySet() + ".");
            nextItem = scanner.nextLine();
        }

        ArrayList<String> selectedTypes = new ArrayList<>();

        //реализуйте ввод типов блюд
        while (!nextItem.isEmpty()) {
            while (!dc.checkType(nextItem)) {
                System.out.println("Такого типа блюд нет в меню. Пожалуйста, выберете любые из имеющихся в меню " +
                        "типов: " + dc.menuByType.keySet() + ".");
                nextItem = scanner.nextLine();
            }
            selectedTypes.add(nextItem);
            nextItem = scanner.nextLine();
        }
        return selectedTypes;
    }
}
package Animal_Demykin;

import Animal_Demykin.animals.Animal;
import Animal_Demykin.data.Command;
import Animal_Demykin.factory.AnimalFactory;
import Animal_Demykin.animals.Cat;
import Animal_Demykin.animals.Dog;
import Animal_Demykin.animals.Duck;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Animal> animals = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Добро пожаловать в зоопарк!");
        showMenu();
    }

    private static void showMenu() {
        while (true) {
            System.out.println("\n=== Главное меню ===");
            System.out.println("Доступные команды: add, list, exit");
            System.out.print("Введите команду: ");

            String input = scanner.nextLine();
            Command command = Command.fromString(input);

            if (command == null) {
                System.out.println("Неизвестная команда. Попробуйте снова.");
                continue;
            }

            switch (command) {
                case ADD:
                    addAnimal();
                    break;
                case LIST:
                    listAnimals();
                    break;
                case EXIT:
                    System.out.println("Выход из программы. До свидания!");
                    scanner.close();
                    return;
            }
        }
    }

    private static void addAnimal() {
        System.out.println("\n=== Добавление животного ===");
        System.out.println("Выберите тип животного: cat, dog, duck");

        //валидация тип животного
        String type = "";
        while (true) {
            System.out.print("Тип: ");
            type = scanner.nextLine().trim().toLowerCase();

            if (type.equals("cat") || type.equals("dog") || type.equals("duck")) {
                break;
            } else {
                System.out.println("Неизвестный тип животного! Введите cat, dog или duck.");
            }
        }

        System.out.print("Имя: ");
        String name = scanner.nextLine().trim();

        //ввод и валидация возраста
        int age = 0;
        boolean validAge = false;
        while (!validAge) {
            System.out.print("Возраст (лет): ");
            String ageInput = scanner.nextLine().trim();

            try {
                age = Integer.parseInt(ageInput);
                if (age >= 0) { // Дополнительная проверка на неотрицательность
                    validAge = true;
                } else {
                    System.out.println("Ошибка: возраст не может быть отрицательным. Введите положительное число.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите целое число для возраста. Например: 3, 5, 10");
            }
        }

        //ввод и валидация веса

        double weight = 0;
        while (true) {
            System.out.print("Вес (кг): ");
            String weightInput = scanner.nextLine().trim().replace(',', '.');
            try {
                weight = Double.parseDouble(weightInput);
                if (weight > 0) {
                    break;
                } else {
                    System.out.println("Ошибка: вес должен быть положительным.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите число для веса (можно с десятичной точкой).");
            }
        }

        System.out.print("Цвет: ");
        String color = scanner.nextLine().trim();

        Animal animal;
        try {
            animal = AnimalFactory.createAnimal(type, name, age, weight, color);

            // Дополнительная логика для утки
            if (type.equalsIgnoreCase("duck")) {
                System.out.println("Я лечу");
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Неизвестный тип животного!");
            return;
        }

        animals.add(animal);
        System.out.print("Животное добавлено! Оно говорит: ");
        animal.say();

    }

    private static void listAnimals() {
        if (animals.isEmpty()) {
            System.out.println("Список животных пуст.");
            return;
        }

        System.out.println("\n=== Список всех животных ===");
        for (Animal animal : animals) {
            System.out.println(animal.toString());
        }
    }
}

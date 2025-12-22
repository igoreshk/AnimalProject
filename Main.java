import Animal_Demykin.animals.Animal;
import Animal_Demykin.animals.Cat;
import Animal_Demykin.animals.Dog;
import Animal_Demykin.animals.Duck;
import java.util.ArrayList;
import java.util.Scanner;

// Enum для команд меню
enum Command {
    ADD("add"),
    LIST("list"),
    EXIT("exit");

    private final String command;

    Command(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    // Метод для получения команды из строки
    public static Command fromString(String text) {
        for (Command cmd : Command.values()) {
            if (cmd.command.equalsIgnoreCase(text.trim())) {
                return cmd;
            }
        }
        return null;
    }
}

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
        System.out.print("Тип: ");
        String type = scanner.nextLine().trim().toLowerCase();

        System.out.print("Имя: ");
        String name = scanner.nextLine().trim();

        System.out.print("Возраст (лет): ");
        int age = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("Вес (кг): ");
        double weight = Double.parseDouble(scanner.nextLine().trim());

        System.out.print("Цвет: ");
        String color = scanner.nextLine().trim();

        Animal animal;
        switch (type) {
            case "cat":
                animal = new Cat(name, age, weight, color);
                break;
            case "dog":
                animal = new Dog(name, age, weight, color);
                break;
            case "duck":
                animal = new Duck(name, age, weight, color);
                break;
            default:
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

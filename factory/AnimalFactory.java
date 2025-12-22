package Animal_Demykin.factory;
import Animal_Demykin.animals.Animal;
import Animal_Demykin.animals.Cat;
import Animal_Demykin.animals.Dog;
import Animal_Demykin.animals.Duck;


public class AnimalFactory {
    public static Animal createAnimal(String type, String name, int age, double weight, String color) {
        switch (type.toLowerCase()) {
            case "cat":
                return new Cat(name, age, weight, color);
            case "dog":
                return new Dog(name, age, weight, color);
            case "duck":
                return new Duck(name, age, weight, color);
            default:
                return null; // Или бросать исключение
        }
    }

    /**
     * Проверяет, поддерживается ли тип животного
     */
    public static boolean isAnimalTypeSupported(String type) {
        return type != null &&
                (type.equalsIgnoreCase("cat") ||
                        type.equalsIgnoreCase("dog") ||
                        type.equalsIgnoreCase("duck"));
    }
}

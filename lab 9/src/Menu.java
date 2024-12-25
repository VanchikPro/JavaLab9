import java.util.Scanner;
import java.util.HashMap;

public class Menu {
    private final Scanner scanner;

    public Menu(Scanner scanner) {
        this.scanner = scanner;
    }

    public void displayMenu() {
        System.out.println("\n--- Главное меню ---");
        System.out.println("1. Выполнить расчет");
        System.out.println("2. Информация о программе");
        System.out.println("3. Информация о разработчике");
        System.out.println("4. Поиск расчета по ID");
        System.out.println("5. Выход");
        System.out.print("Выберите опцию: ");
    }

    public int getChoice() {
        while (true) {
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 1 && choice <= 5) {
                    return choice;
                } else {
                    throw new IllegalArgumentException("Выберите опцию от 1 до 5."); //выбрасывание исключения
                }
            } catch (NumberFormatException e) {
                System.out.print("Ошибка: введите число. \nПовторите ввод: ");
            }
        }
    }

    public double getValidMass() throws Exclusion {
        System.out.print("Введите массу объекта в килограммах: ");
        while (!scanner.hasNextDouble()) {
            System.out.print("Ошибка: введите положительное число. \nПовторите ввод: ");
            scanner.next();
        }
        double mass = scanner.nextDouble();
        scanner.nextLine(); // очистка буфера
        if (mass > 0) {
            return mass;
        } else {
            throw new Exclusion("Масса должна быть положительным числом."); //выбрасывание исключения
        }
    }

    public double getValidSpeed() throws Exclusion {
        System.out.print("Введите скорость объекта в метрах в секунду: ");
        while (!scanner.hasNextDouble()) {
            System.out.print("Ошибка: введите положительное число. \nПовторите ввод: ");
            scanner.next();
        }
        double speed = scanner.nextDouble();
        scanner.nextLine(); // очистка буфера
        return speed;
    }

    public void displayProgramInfo() {
        System.out.println("Эта программа рассчитывает кинетическую энергию объекта по формуле: E = 0.5 * m * v^2.");
    }

    public void displayDeveloperInfo() {
        System.out.println("Разработчик программы: Дорофиенко Иван Сергеевич РИМ-140970.");
    }

    public void findID(HashMap<Integer, KineticEnergy> calculations) {
        if (calculations.isEmpty()) {
            System.out.println("Расчетов еще не было");
            return; // Возврат в меню
        }
        while (true) {
            System.out.print("Введите ID расчета: ");
            try {
                // считывание ID расчета
                int id = Integer.parseInt(scanner.nextLine());
                // проверка, существует ли расчет с данным ID
                if (calculations.containsKey(id)) {
                    KineticEnergy result = calculations.get(id);
                    System.out.println("Найден расчет " + result.getId() + ":");
                    System.out.println(result);
                    break; //
                } else {
                    System.out.println("Расчет с ID " + id + " не найден.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите корректное число для ID.");
            }
        }
    }
}
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu(scanner);
        boolean exit = false;

        // HashMap для хранения расчетов
        HashMap<Integer, KineticEnergy> calculations = new HashMap<>();
        int calculationCount = 0; // счетчик расчетов

        while (!exit) {
            try {
                menu.displayMenu();
                int choice = menu.getChoice();

                switch (choice) {
                    case 1:
                        try {
                            double mass = menu.getValidMass();
                            double speed = menu.getValidSpeed();
                            calculationCount++;
                            KineticEnergy object = new KineticEnergy(calculationCount, mass, speed);
                            calculations.put(calculationCount, object); // добавление в HashMap
                            System.out.printf("Кинетическая энергия объекта: %.2f Джоулей\n", object.calculate());
                        } catch (Exclusion e) { //перехват исключения
                            System.out.println("Ошибка создания объекта: " + e.getMessage()); //обработка исключения
                        }
                        break;
                    case 2:
                        menu.displayProgramInfo();
                        break;
                    case 3:
                        menu.displayDeveloperInfo();
                        break;
                    case 4: // поиск расчета по ID
                        menu.findID(calculations);
                        break;
                    case 5: // Выход
                        System.out.println("Выход из программы...");
                        exit = true;
                        break;
                }
            } catch (Exception e) { //перехват исключения
                System.out.println("Произошла ошибка: " + e.getMessage()); //обработка исключения
            }
        }

        if (calculations.isEmpty()) {
            System.out.println("Программа завершена без ввода данных.");
        } else {
            System.out.println("\nРезультаты работы программы:");
            for (KineticEnergy obj : calculations.values()) {
                System.out.println("Расчет " + obj.getId());
                System.out.println(obj);
                scanner.close();
            }
        }

    }
}
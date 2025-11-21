
import java.util.ArrayList;
import java.util.List;

/**
 * Главный класс для демонстрации работы с пиццами
 */
public class PizzaDemo {

    /**
     * Считает сколько пицц весят больше заданного значения
     */
    public static int countHeavyPizzas(List<Food> pizzas, double minWeight) {
        int count = 0;
        for (Food pizza : pizzas) {
            if (pizza.getWeight() > minWeight) {
                count++;
            }
        }
        return count;
    }

    /**
     * Показывает цены больших пицц (диаметр больше заданного)
     */
    public static void showBigPizzaPrices(List<Food> pizzas, double minDiameter) {
        System.out.println("\n🗳️ ПИЦЦЫ БОЛЬШЕ " + minDiameter + " СМ:");
        for (Food pizza : pizzas) {
            if (pizza.getDiameter() > minDiameter) {
                String pizzaType = pizza.getClass().getSimpleName();
                System.out.println("  " + pizzaType + " - " + pizza.getPrice() + " руб.");
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("🍕 ДЕМОНСТРАЦИЯ РАБОТЫ С ПИЦЦАМИ!");

        // Создаем список для хранения всех пицц
        List<Food> pizzaList = new ArrayList<>();

        // 📝 СОЗДАЕМ ПИЦЦЫ ПЕППЕРОНИ
        System.out.println("\n1. СОЗДАЕМ ПИЦЦЫ ПЕППЕРОНИ:");
        Pepperoni pep1 = new Pepperoni(450.0, 800.0, 30.0, 1200.0, 3);
        Pepperoni pep2 = new Pepperoni(550.0, 1000.0, 35.0, 1500.0, 4);
        Pepperoni pep3 = new Pepperoni(350.0, 600.0, 25.0, 900.0, 2);

        pizzaList.add(pep1);
        pizzaList.add(pep2);
        pizzaList.add(pep3);

        System.out.println("   " + pep1);
        System.out.println("   " + pep2);
        System.out.println("   " + pep3);

        // 📝 СОЗДАЕМ СЫРНЫЕ ПИЦЦЫ
        System.out.println("\n2. СОЗДАЕМ СЫРНЫЕ ПИЦЦЫ:");
        Cheese cheese1 = new Cheese(400.0, 750.0, 30.0, 1100.0, "Моцарелла");
        Cheese cheese2 = new Cheese(500.0, 900.0, 35.0, 1300.0, "Чеддер");
        Cheese cheese3 = new Cheese(300.0, 550.0, 25.0, 800.0, "Пармезан");

        pizzaList.add(cheese1);
        pizzaList.add(cheese2);
        pizzaList.add(cheese3);

        System.out.println("   " + cheese1);
        System.out.println("   " + cheese2);
        System.out.println("   " + cheese3);

        // 📝 СОЗДАЕМ МЯСНЫЕ ПИЦЦЫ
        System.out.println("\n3. СОЗДАЕМ МЯСНЫЕ ПИЦЦЫ:");
        Meat meat1 = new Meat(480.0, 850.0, 30.0, 1400.0, "Ветчина");
        Meat meat2 = new Meat(580.0, 1100.0, 35.0, 1700.0, "Салями");
        Meat meat3 = new Meat(380.0, 650.0, 25.0, 1000.0, "Курица");

        pizzaList.add(meat1);
        pizzaList.add(meat2);
        pizzaList.add(meat3);

        System.out.println("   " + meat1);
        System.out.println("   " + meat2);
        System.out.println("   " + meat3);

        // 📊 ВЫПОЛНЯЕМ ЗАДАНИЯ ИЗ ЛАБОРАТОРНОЙ
        // Задание 4: Подсчитать количество пицц весом больше 700г
        System.out.println("\n📊 ЗАДАНИЕ 4: ПИЦЦЫ ТЯЖЕЛЕЕ 700г");
        int heavyCount = countHeavyPizzas(pizzaList, 700.0);
        System.out.println("   Количество пицц тяжелее 700г: " + heavyCount);

        // Задание 5: Вывести цены пицц диаметром больше 28см
        showBigPizzaPrices(pizzaList, 28.0);

        // 📈 ДОПОЛНИТЕЛЬНАЯ СТАТИСТИКА
        System.out.println("\n📈 ОБЩАЯ СТАТИСТИКА:");
        System.out.println("   Всего пицц создано: " + pizzaList.size());

        double totalCost = 0;
        for (Food pizza : pizzaList) {
            totalCost += pizza.getPrice();
        }
        System.out.println("   Общая стоимость всех пицц: " + totalCost + " руб.");
        System.out.println("   Средняя цена пиццы: " + (totalCost / pizzaList.size()) + " руб.");

        System.out.println("\n🎉 ПРОГРАММА ЗАВЕРШЕНА!");
    }
}

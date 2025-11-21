
import java.util.ArrayList;
import java.util.List;

/**
 * Класс-репозиторий для управления коллекцией пицц Добавлен по требованию
 * лабораторной работы 5
 */
class PizzaRepository {

    private List<Food> pizzas = new ArrayList<>();

    // Методы для работы с коллекцией
    public void addPizza(Food pizza) {
        pizzas.add(pizza);
    }

    public boolean removePizza(int index) {
        if (index >= 0 && index < pizzas.size()) {
            pizzas.remove(index);
            return true;
        }
        return false;
    }

    public boolean updatePizza(int index, Food newPizza) {
        if (index >= 0 && index < pizzas.size()) {
            pizzas.set(index, newPizza);
            return true;
        }
        return false;
    }

    public List<Food> getAllPizzas() {
        return new ArrayList<>(pizzas);
    }

    public int getCount() {
        return pizzas.size();
    }
}

/**
 * Главный класс для демонстрации работы с пиццами Отрефакторен: добавлен
 * репозиторий, улучшена структура
 */
public class PizzaDemo {

    private static PizzaRepository pizzaRepo = new PizzaRepository();

    /**
     * Считает сколько пицц весят больше заданного значения Рефакторинг: теперь
     * работает с репозиторием
     */
    public static int countHeavyPizzas(double minWeight) {
        int count = 0;
        for (Food pizza : pizzaRepo.getAllPizzas()) {
            if (pizza.getWeight() > minWeight) {
                count++;
            }
        }
        return count;
    }

    /**
     * Показывает цены больших пицц (диаметр больше заданного) Рефакторинг:
     * использует данные из репозитория
     */
    public static void showBigPizzaPrices(double minDiameter) {
        System.out.println("\n🗳️ ПИЦЦЫ БОЛЬШЕ " + minDiameter + " СМ:");

        for (Food pizza : pizzaRepo.getAllPizzas()) {
            if (pizza.getDiameter() > minDiameter) {
                String pizzaType = pizza.getClass().getSimpleName();
                System.out.println("  " + pizzaType + " - " + pizza.getPrice() + " руб.");
            }
        }
    }

    /**
     * Создает демонстрационные пиццы Рефакторинг: вынесен в отдельный метод для
     * чистоты main
     */
    private static void createDemoPizzas() {
        // Пепперони пиццы
        pizzaRepo.addPizza(new Pepperoni(450.0, 800.0, 30.0, 1200.0, 3));
        pizzaRepo.addPizza(new Pepperoni(550.0, 1000.0, 35.0, 1500.0, 4));
        pizzaRepo.addPizza(new Pepperoni(350.0, 600.0, 25.0, 900.0, 2));

        // Сырные пиццы
        pizzaRepo.addPizza(new Cheese(400.0, 750.0, 30.0, 1100.0, "Моцарелла"));
        pizzaRepo.addPizza(new Cheese(500.0, 900.0, 35.0, 1300.0, "Чеддер"));
        pizzaRepo.addPizza(new Cheese(300.0, 550.0, 25.0, 800.0, "Пармезан"));

        // Мясные пиццы
        pizzaRepo.addPizza(new Meat(480.0, 850.0, 30.0, 1400.0, "Ветчина"));
        pizzaRepo.addPizza(new Meat(580.0, 1100.0, 35.0, 1700.0, "Салями"));
        pizzaRepo.addPizza(new Meat(380.0, 650.0, 25.0, 1000.0, "Курица"));
    }

    /**
     * Демонстрирует работу репозитория Рефакторинг: добавлена демонстрация CRUD
     * операций
     */
    private static void demonstrateRepository() {
        System.out.println("\n🔧 ДЕМОНСТРАЦИЯ РЕПОЗИТОРИЯ:");

        // Показываем начальное состояние
        System.out.println("   Начальное количество пицц: " + pizzaRepo.getCount());

        // Демонстрация обновления
        pizzaRepo.updatePizza(0, new Pepperoni(500.0, 900.0, 32.0, 1300.0, 4));
        System.out.println("   Обновили первую пиццу");

        // Демонстрация удаления
        pizzaRepo.removePizza(1);
        System.out.println("   Удалили вторую пиццу");

        System.out.println("   Конечное количество пицц: " + pizzaRepo.getCount());
    }

    public static void main(String[] args) {
        System.out.println("🍕 ДЕМОНСТРАЦИЯ РАБОТЫ С ПИЦЦАМИ И РЕПОЗИТОРИЕМ!");

        // Создаем пиццы через репозиторий
        createDemoPizzas();

        // Демонстрируем работу репозитория
        demonstrateRepository();

        // 📊 ВЫПОЛНЯЕМ ЗАДАНИЯ ИЗ ЛАБОРАТОРНОЙ
        // Задание 4: Подсчитать количество пицц весом больше 700г
        System.out.println("\n📊 ЗАДАНИЕ 4: ПИЦЦЫ ТЯЖЕЛЕЕ 700г");
        int heavyCount = countHeavyPizzas(700.0);
        System.out.println("   Количество пицц тяжелее 700г: " + heavyCount);

        // Задание 5: Вывести цены пицц диаметром больше 28см
        showBigPizzaPrices(28.0);

        // 📈 ОБЩАЯ СТАТИСТИКА (работает с репозиторием)
        System.out.println("\n📈 ОБЩАЯ СТАТИСТИКА:");
        System.out.println("   Всего пицц в репозитории: " + pizzaRepo.getCount());

        double totalCost = 0;
        for (Food pizza : pizzaRepo.getAllPizzas()) {
            totalCost += pizza.getPrice();
        }
        System.out.println("   Общая стоимость всех пицц: " + totalCost + " руб.");
        System.out.println("   Средняя цена пиццы: " + (totalCost / pizzaRepo.getCount()) + " руб.");

        System.out.println("\n🎉 ПРОГРАММА ЗАВЕРШЕНА!");
    }
}

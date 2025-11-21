
/**
 * Абстрактный класс, представляющий пищу
 * Это как шаблон для всех пицц
 */
public abstract class Food {

    // Эти переменные будут у ВСЕХ пицц
    protected double price;    // цена
    protected double weight;   // вес
    protected double diameter; // диаметр
    protected double calories; // калории

    /**
     * Конструктор по умолчанию - создает "пустую" пиццу
     */
    public Food() {
        this.price = 0.0;
        this.weight = 0.0;
        this.diameter = 0.0;
        this.calories = 0.0;
    }

    /**
     * Конструктор с параметрами - создает пиццу с конкретными значениями
     */
    public Food(double price, double weight, double diameter, double calories) {
        this.price = price;
        this.weight = weight;
        this.diameter = diameter;
        this.calories = calories;
    }

    // Геттеры (получить значения)
    public double getPrice() {
        return price;
    }

    public double getWeight() {
        return weight;
    }

    public double getDiameter() {
        return diameter;
    }

    public double getCalories() {
        return calories;
    }

    // Сеттеры (установить значения)
    public void setPrice(double price) {
        this.price = price;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setDiameter(double diameter) {
        this.diameter = diameter;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    /**
     * Преобразует информацию о пицце в строку для вывода
     */
    @Override
    public String toString() {
        return String.format("Цена: %.2f руб., Вес: %.2f г, Диаметр: %.2f см, Калории: %.2f ккал",
                price, weight, diameter, calories);
    }
}

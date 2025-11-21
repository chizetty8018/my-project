
/**
 * Класс для пиццы Пепперони - наследуется от Food
 */
public class Pepperoni extends Food {

    private int spicyLevel; // уровень остроты (1-5)

    /**
     * Конструктор по умолчанию
     */
    public Pepperoni() {
        super(); // вызываем конструктор родителя (Food)
        this.spicyLevel = 1;
    }

    /**
     * Конструктор с параметрами
     */
    public Pepperoni(double price, double weight, double diameter,
            double calories, int spicyLevel) {
        super(price, weight, diameter, calories); // передаем параметры в родительский класс
        this.spicyLevel = spicyLevel;
    }

    // Геттер и сеттер для уровня остроты
    public int getSpicyLevel() {
        return spicyLevel;
    }

    public void setSpicyLevel(int spicyLevel) {
        this.spicyLevel = spicyLevel;
    }

    /**
     * Выводит информацию о пицце Пепперони
     */
    @Override
    public String toString() {
        return "Пепперони: " + super.toString() + ", Острота: " + spicyLevel + "/5";
    }
}

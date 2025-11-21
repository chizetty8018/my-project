
/**
 * Класс для сырной пиццы
 */
public class Cheese extends Food {

    private String cheeseType; // тип сыра

    public Cheese() {
        super();
        this.cheeseType = "Моцарелла";
    }

    public Cheese(double price, double weight, double diameter,
            double calories, String cheeseType) {
        super(price, weight, diameter, calories);
        this.cheeseType = cheeseType;
    }

    public String getCheeseType() {
        return cheeseType;
    }

    public void setCheeseType(String cheeseType) {
        this.cheeseType = cheeseType;
    }

    @Override
    public String toString() {
        return "Сырная: " + super.toString() + ", Сыр: " + cheeseType;
    }
}

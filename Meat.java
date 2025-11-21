
/**
 * Класс для мясной пиццы
 */
public class Meat extends Food {

    private String meatType; // тип мяса

    public Meat() {
        super();
        this.meatType = "Ветчина";
    }

    public Meat(double price, double weight, double diameter,
            double calories, String meatType) {
        super(price, weight, diameter, calories);
        this.meatType = meatType;
    }

    public String getMeatType() {
        return meatType;
    }

    public void setMeatType(String meatType) {
        this.meatType = meatType;
    }

    @Override
    public String toString() {
        return "Мясная: " + super.toString() + ", Мясо: " + meatType;
    }
}

public class Candy extends DessertItem {

    private double weight;
    private int unitPrice;
    private int cost;

    public Candy(String name, double weight, int unitPrice) {
        super(name);
        this.weight = weight;
        this.unitPrice = unitPrice;
        this.cost = (int) Math.round(weight * unitPrice);
    }

    @Override
    public int getCost() {
        return cost;
    }

    public double getWeight() {
        return weight;
    }

    public int getUnitPrice() {
        return unitPrice;
    }
}

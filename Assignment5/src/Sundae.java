public class Sundae extends IceCream {

    private String toppingName;
    private int toppingPrice;

    public Sundae(String name, int cost, String toppingName, int toppingPrice) {
        super(name, cost);
        this.toppingName = toppingName;
        this.toppingPrice = toppingPrice;
    }

    @Override
    public int getCost() {
        return super.getCost() + toppingPrice;
    }

    public String getToppingName() {
        return toppingName;
    }

    public int getToppingPrice() {
        return toppingPrice;
    }

}

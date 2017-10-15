public class Cookie extends DessertItem {

    private int number;
    private int dozenPrice;
    private int cost;

    public Cookie(String name, int number, int dozenPrice) {
        super(name);
        this.number = number;
        this.dozenPrice = dozenPrice;
        this.cost = (int) Math.round(dozenPrice / (12 / number));
    }

    @Override
    public int getCost() {
        return cost;
    }

    public int getNumber() {
        return number;
    }

    public int getDozenPrice() {
        return dozenPrice;
    }
}

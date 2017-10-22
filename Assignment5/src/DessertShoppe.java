public class DessertShoppe {
    protected static final double TAX_RATE = 0.065;
    protected static final String STORE_NAME = "M & M Dessert Shoppe";
    protected static final int MAX_NAME_SIZE = 22;
    protected static final int MAX_ROW_WIDTH = 35;

    public static String cents2dollarsAndCentsmethod(int cents) {
        double dollar = (double) cents/100;
        return Double.toString(dollar);
    }
}

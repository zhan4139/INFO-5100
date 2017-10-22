import java.util.Arrays;
import java.util.Vector;

public class Checkout {

    private Vector<DessertItem> items;

    public Checkout() {
        items = new Vector<>();
    }

    public int numberOfItems() {
        return items.size();
    }

    public void enterItem(DessertItem item) {
        items.add(item);
    }

    public void clear() {
        items.clear();
    }

    public int totalCost() {
        int sum = 0;
        for (DessertItem i : items) {
            sum += i.getCost();
        }
        return sum;
    }

    public int totalTax() {
        return (int) Math.round(totalCost() * DessertShoppe.TAX_RATE);
    }

    @Override
    public String toString() {
        int padSize = DessertShoppe.MAX_ROW_WIDTH - DessertShoppe.STORE_NAME.length();
        int padStart = DessertShoppe.STORE_NAME.length() + padSize/2;

        char[] headerLine = new char[DessertShoppe.STORE_NAME.length()];
        Arrays.fill(headerLine, '-');
        String line = new String (headerLine);

        String header = String.format("\n%" + (padStart + 1) + "s", DessertShoppe.STORE_NAME);
        line = String.format("%" + (padStart + 1) + "s", line);
        header = header + "\n" + line + "\n\n";

        StringBuilder receipt = new StringBuilder();
        receipt.append(header);

        for (DessertItem item : items) {
            String name = item.getName();
            int cost = item.getCost();
            String itemFormat = String.format("%1$-" + DessertShoppe.MAX_NAME_SIZE + "s %2$"
                            + (DessertShoppe.MAX_ROW_WIDTH -  DessertShoppe.MAX_NAME_SIZE) + "s"
                    , name, DessertShoppe.cents2dollarsAndCentsmethod(cost));

            if (item instanceof Candy) {
                receipt.append(((Candy) item).getWeight()).append(" lbs. @ ")
                        .append(DessertShoppe.cents2dollarsAndCentsmethod(((Candy) item).getUnitPrice()))
                        .append(" /lb.\n");
            } else if (item instanceof Cookie) {
                receipt.append(((Cookie) item).getNumber()).append(" @ ")
                        .append(DessertShoppe.cents2dollarsAndCentsmethod(((Cookie) item).getDozenPrice()))
                        .append(" /dz.\n");
            } else if (item instanceof Sundae) {
                receipt.append(((Sundae) item).getToppingName()).append(" Sundae with\n");
            }

            receipt.append(itemFormat).append("\n");
        }

        //append tax and total cost
        String tax = String.format("%1$-" + DessertShoppe.MAX_NAME_SIZE + "s %2$"
                        + (DessertShoppe.MAX_ROW_WIDTH -  DessertShoppe.MAX_NAME_SIZE) + "s"
                , "Tax", DessertShoppe.cents2dollarsAndCentsmethod(totalTax()));
        String totalCost = String.format("%1$-" + DessertShoppe.MAX_NAME_SIZE + "s %2$"
                        + (DessertShoppe.MAX_ROW_WIDTH -  DessertShoppe.MAX_NAME_SIZE) + "s"
                , "Total Cost", DessertShoppe.cents2dollarsAndCentsmethod(totalCost() + totalTax()));

        receipt.append("\n").append(tax).append("\n").append(totalCost).append("\n\n");

        return receipt.toString();
    }
}

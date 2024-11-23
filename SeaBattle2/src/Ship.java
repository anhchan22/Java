public class Ship {
    private String name;
    private int size;
    private String symbol;
    public Ship(String name, int size, String symbol) {
        this.name = name;
        this.size = size;
        this.symbol = symbol;
    }
    public String getName() {
        return name;
    }
    public int getSize() {
        return size;
    }
    public String getSymbol() {
        return symbol;
    }
}

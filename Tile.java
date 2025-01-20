public class Tile {
    private String type;
    private int number;

    public Tile(String type, int number) {
        this.type = type;
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return type + number;
    }
}

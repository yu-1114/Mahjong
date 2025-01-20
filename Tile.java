public class Tile implements Comparable<Tile> {
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
        if (this instanceof HonorTile) {
            return ((HonorTile) this).getName();
        }
        return number + type;
    }

    @Override
    public int compareTo(Tile other) {
        int typeOrder = getTypeOrder(this.type) - getTypeOrder(other.type);
        if (typeOrder != 0) {
            return typeOrder;
        }

        if (!this.type.equals("字") && !other.type.equals("字")) {
            return this.number - other.number;
        }

        if (this.type.equals("字") && other.type.equals("字")) {
            return getHonorOrder(this.number) - getHonorOrder(other.number);
        }

        return this.type.equals("字") ? 1 : -1;
    }

    private int getTypeOrder(String type) {
        switch (type) {
            case "萬": return 1;
            case "筒": return 2;
            case "索": return 3;
            case "字": return 4;
            default: throw new IllegalArgumentException("Unknown type: " + type);
        }
    }

    protected int getHonorOrder(int number) {
        switch (number) {
            case 1: return 1;
            case 2: return 2;
            case 3: return 3;
            case 4: return 4;
            case 5: return 5;
            case 6: return 6;
            case 7: return 7;
            default: throw new IllegalArgumentException("Unknown honor number: " + number);
        }
    }

    protected int getHonorName(String name) {
        switch (name) {
            case "東": return 1;
            case "南": return 2;
            case "西": return 3;
            case "北": return 4;
            case "白": return 5;
            case "發": return 6;
            case "中": return 7;
            default: throw new IllegalArgumentException("Unknown honor name: " + name);
        }
    }
}

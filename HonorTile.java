public class HonorTile extends Tile {
    private String name;

    public HonorTile(String name) {
        super("字", 0);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(Tile other) {
        if (other instanceof HonorTile) {
            HonorTile otherHonor = (HonorTile) other;
            return getHonorName(this.name) - getHonorName(otherHonor.name);
        }
        return super.compareTo(other);
    }
}

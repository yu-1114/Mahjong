package jp.ac.uryukyu.ie.e205704;
import java.util.Objects;

public class Tile implements Comparable<Tile> {
    private String type;
    protected int number;

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

    public String getName() {
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (this instanceof HonorTile && obj instanceof HonorTile) {
            HonorTile otherHonorTile = (HonorTile) obj;
            return this.getName().equals(otherHonorTile.getName());//getHonorOrder(this.number) == getHonorOrder(otherHonorTile.number);
        }
        Tile otherTile = (Tile) obj;
        if (this.type.equals("字") && otherTile.type.equals("字")) {
            return this.getName().equals(((HonorTile) otherTile).getName());//getHonorOrder(this.number) == getHonorOrder(otherTile.number);
        } else {
            return this.type.equals(otherTile.type) && this.number == otherTile.number;
        }
    }


    @Override
    public int hashCode() {
        return Objects.hash(type, number);
    }

    @Override
    public String toString() {
        if (this instanceof HonorTile) {
            return ((HonorTile) this).getName();
        }
        return getNumberInKanji(number) + type;
    }

    private String getNumberInKanji(int number) {
        switch (number) {
            case 1: return "一";
            case 2: return "二";
            case 3: return "三";
            case 4: return "四";
            case 5: return "五";
            case 6: return "六";
            case 7: return "七";
            case 8: return "八";
            case 9: return "九";
            default: throw new IllegalArgumentException("数字は1～9の範囲である必要があります。");
        }
    }

    @Override
    public int compareTo(Tile other) {
        if (this instanceof HonorTile && other instanceof HonorTile) {
            return getHonorOrder(this.number) - getHonorOrder(other.number);
        }

        int typeOrder = getTypeOrder(this.type) - getTypeOrder(other.type);
        if (typeOrder != 0) {
            return typeOrder;
        }

        if (!this.type.equals("字") && !other.type.equals("字")) {
            return this.number - other.number;
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
        if (this instanceof HonorTile) {
            return getHonorName(((HonorTile) this).getName()); // nameで判断
        }

        if (number == 0) {
            throw new IllegalArgumentException("Honor tile number cannot be 0.");
        }
        
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

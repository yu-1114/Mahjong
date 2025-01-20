import java.util.ArrayList;
import java.util.List;

public class Tiles {
    public static List<Tile> createFullDeck() {
        List<Tile> deck = new ArrayList<>();

        for (String type : new String[]{"萬", "筒", "索"}) {
            for (int number = 1; number <= 9; number++) {
                for (int i = 0; i < 4; i++) {
                    deck.add(new Tile(type, number));
                }
            }
        }

        String[] honorNames = {"東", "南", "西", "北", "白", "發", "中"};
        for (String name : honorNames) {
            for (int i = 0; i < 4; i++) {
                deck.add(new HonorTile(name));
            }
        }

        return deck;
    }
}

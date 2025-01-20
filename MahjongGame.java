import java.util.Collections;
import java.util.List;

public class MahjongGame {
    public static void main(String[] args) {
        List<Tile> deck = Tiles.createFullDeck();

        Collections.shuffle(deck);
        System.out.println("山牌: " + deck);

        List<Tile> hand = deck.subList(0, 14);

        Collections.sort(hand);
        System.out.println("手牌: " + hand);
    }
}

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MahjongGame {
    public static void main(String[] args) {
        List<Tile> deck = Tiles.createFullDeck();

        Collections.shuffle(deck);
        //System.out.println("山牌: " + deck);
        //System.out.println();

        List<Tile> hand = new ArrayList<>(deck.subList(0, 14));
        deck = deck.subList(14, deck.size());

        List<Tile> discardPile = new ArrayList<>();

        Collections.sort(hand);
        System.out.println("配牌: " + hand);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("ツモるには Enter を押してください。ゲームを終了するには 'end' と入力してください。");

            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("end")) {
                System.out.println("ゲーム終了");
                break;
            }

            for (int i = 0; i < hand.size(); i++) {
                System.out.print(i + ": " + hand.get(i) +" ");
            }
            System.out.println();
            System.out.println("捨てる牌を選んでください(0~13):");
            int discardIndex = scanner.nextInt();
            scanner.nextLine();

            if (discardIndex >= 0 && discardIndex < hand.size()) {
                Tile discardedTile = hand.remove(discardIndex);
                discardPile.add(discardedTile);
                System.out.println("捨てた牌: " + discardedTile);
            } else {
                System.out.println("無効な数字です");
            }

            Tile drawnTile = deck.remove(0);
            hand.add(drawnTile);

            System.out.println("ツモ牌: " + drawnTile);

            Collections.sort(hand);
            System.out.println("手牌: " + hand);

            System.out.println("捨て牌: " + discardPile);
        }

        scanner.close();
    }
}   

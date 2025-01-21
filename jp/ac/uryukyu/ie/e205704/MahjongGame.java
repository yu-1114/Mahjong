package jp.ac.uryukyu.ie.e205704;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class MahjongGame{
    public static void main(String[] args) {
        List<Tile> deck = Tiles.createFullDeck();
        Collections.shuffle(deck);
        List<Tile> hand = new ArrayList<>(deck.subList(0, 14));
        deck = deck.subList(14, deck.size());
        List<Tile> discardPile = new ArrayList<>();
        List<Tile> kanTiles = new ArrayList<>();

        Collections.sort(hand);
        //System.out.println("配牌: " + hand);

        Scanner scanner = new Scanner(System.in);
        gameLoop(scanner, hand, deck, discardPile, kanTiles);

        scanner.close();
    }

    private static void gameLoop(Scanner scanner, List<Tile> hand, List<Tile> deck, List<Tile> discardPile, List<Tile> kanTiles) {
        while (true) {
            displayGameStatus(hand, kanTiles, discardPile);
            String input = getPlayerInput(scanner);

            if (input.equalsIgnoreCase("end")) {
                System.out.println("ゲーム終了");
                break;
            }

            discardTile(scanner, hand, discardPile);
            drawTile(deck, hand);

            handleKanOption(scanner, hand, deck, kanTiles);
        }
    }

    private static void displayGameStatus(List<Tile> hand, List<Tile> kanTiles, List<Tile> discardPile) {
        System.out.println("手牌: " + hand);
        if (!kanTiles.isEmpty()) {
            System.out.println("カン牌: " + kanTiles);
        }
        System.out.println("捨て牌: " + discardPile);
    }

    private static String getPlayerInput(Scanner scanner) {
        System.out.println("ツモるには Enter を押してください。ゲームを終了するには 'end' と入力してください。");
        return scanner.nextLine();
    }

    private static void discardTile(Scanner scanner, List<Tile> hand, List<Tile> discardPile) {
        for (int i = 0; i < hand.size(); i++) {
            System.out.print(i + ": " + hand.get(i) + " ");
        }
        System.out.println();
        System.out.println("捨てる牌を選んでください(0~" + (hand.size() - 1) + "):");
        int discardIndex = scanner.nextInt();
        scanner.nextLine();

        if (discardIndex >= 0 && discardIndex < hand.size()) {
            Tile discardedTile = hand.remove(discardIndex);
            discardPile.add(discardedTile);
            System.out.println("捨てた牌: " + discardedTile);
        } else {
            System.out.println("無効な数字です");
        }
    }

    private static void drawTile(List<Tile> deck, List<Tile> hand) {
        Tile drawnTile = deck.remove(0);
        hand.add(drawnTile);
        System.out.println("ツモ牌: " + drawnTile);
        Collections.sort(hand);
    }

    private static void handleKanOption(Scanner scanner, List<Tile> hand, List<Tile> deck, List<Tile> kanTiles) {
        Map<Tile, Integer> tileCount = countTiles(hand);
        for (Map.Entry<Tile, Integer> entry : tileCount.entrySet()) {
            if (entry.getValue() == 4) {
                System.out.println("カンできる牌があります: " + entry.getKey());
                System.out.println("この牌をカンしますか？ (yes/no):");
                String kanInput = scanner.nextLine();
                if (kanInput.equalsIgnoreCase("yes")) {
                    performKan(hand, kanTiles, entry.getKey(), deck);
                }
            }
        }
    }

    private static Map<Tile, Integer> countTiles(List<Tile> hand) {
        Map<Tile, Integer> tileCount = new HashMap<>();
        for (Tile tile : hand) {
            tileCount.put(tile, tileCount.getOrDefault(tile, 0) + 1);
        }
        return tileCount;
    }

    private static void performKan(List<Tile> hand, List<Tile> kanTiles, Tile tile, List<Tile> deck) {
        hand.removeIf(t -> t.equals(tile));
        for (int i = 0; i < 4; i++) {
            kanTiles.add(tile);
        }

        System.out.println(tile + "をカンしました。");
        Tile newTile = deck.remove(0);
        hand.add(newTile);
        System.out.println("ツモ牌: " + newTile);
        Collections.sort(hand);
    }
}

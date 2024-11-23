import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class GameController {
    private SeaBattleGame player1;
    private SeaBattleGame player2;
    private Scanner scanner;
    private int currentPlayer;

    public GameController() {
        this.player1 = new SeaBattleGame();
        this.player2 = new SeaBattleGame();
        this.scanner = new Scanner(System.in);
        this.currentPlayer = 1;
    }

    public void startGame() {
        System.out.println("Chào mừng đến với trò chơi Sea Battle!");
        System.out.println("Người chơi 1 đặt tàu:");
        player1.placeShips();
        System.out.println("Người chơi 2 đặt tàu:");
        player2.placeShips();

        while (true) {
            SeaBattleGame currentGame = (currentPlayer == 1) ? player2 : player1;
            System.out.println("Lượt của Người chơi " + currentPlayer);
            currentGame.printBoard(true);

            System.out.print("Nhập vị trí bắn (ví dụ: A1): ");
            String position = scanner.next().toUpperCase();

            if (!isValidPosition(position)) {
                System.out.println("Tọa độ không hợp lệ. Vui lòng nhập lại.");
                continue;
            }

            int x = position.charAt(0) - 'A';
            int y = Integer.parseInt(position.substring(1)) - 1;

            boolean hit = currentGame.shootAt(x, y);

            if (hit) {
                System.out.println("Bắn trúng!");
                if (!currentGame.hasShipsLeft()) {
                    System.out.println("Người chơi " + currentPlayer + " đã chiến thắng!");
                    break;
                }
            } else {
                System.out.println("Bắn trượt!");
                switchTurn();
            }
        }
    }

    private boolean isValidPosition(String position) {
        if (position.length() < 2 || position.length() > 3) return false;
        char row = position.charAt(0);
        String col = position.substring(1);

        return row >= 'A' && row <= 'J' && col.matches("[1-9]|10");
    }

    private void switchTurn() {
        currentPlayer = (currentPlayer == 1) ? 2 : 1;
    }
}
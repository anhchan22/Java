import java.util.*;

public class BattleshipGame {
    private Grid player1;
    private Grid player2;
    private Scanner scanner;

    public BattleshipGame() {
        player1 = new Grid();
        player2 = new Grid();
        scanner = new Scanner(System.in);
    }


    public void placeBoat(Boat boat, int player) {
        Grid grid = (player == 1) ? player1 : player2;
        boolean check = false;
        while (!check) {
            System.out.println(boat.getName() + " (Size: " + boat.getSize() + ")");
            System.out.println("Nhập tọa độ bắt đầu (vidu: A1):");
            String position = scanner.next().toUpperCase();

            System.out.println("Nhập chiều đặt tàu (H: ngang, V: Dọc):");
            String direction = scanner.next().toUpperCase();
            while (!direction.equals("H") && !direction.equals("V")) {
                System.out.println("Chiều đặt tàu không hợp lệ! Vui lòng nhập lại (H hoặc V).");
                direction = scanner.next().toUpperCase();
            }

            int x = position.charAt(0) - 'A';
            int y = Integer.parseInt(position.substring(1)) - 1;

            check = grid.addBoat(boat, x, y, direction.equals("H"));
            if (check) {
                System.out.println(boat.getName() + " đã được đặt thành công!");
            } else {
                System.out.println("Vị trí không hợp lệ, thử lại!");
            }
        }
    }

    public boolean shootAt(int player, int x, int y) {
        Grid targetGrid = (player == 1) ? player2 : player1;
        return targetGrid.shootAt(x, y);
    }

    public void printBoard(int player) {
        System.out.println("Player " + player + "'s Board:");
        Grid grid = (player == 1) ? player1 : player2;
        grid.printBoard();
    }

    public boolean checkGameOver() {
        if (player1.isGameOver()) {
            System.out.println("Người chơi 2 chiến thắng!");
            return true;
        }
        if (player2.isGameOver()) {
            System.out.println("Người chơi 1 chiến thắng!");
            return true;
        }
        return false;
    }
}

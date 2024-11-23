import java.util.*;
public class GameSystem {
    private BattleshipGame game = new BattleshipGame();
    private Scanner scanner = new Scanner(System.in);
    private Grid grid = new Grid();
    private List<Boat> player1Boats = new ArrayList<>();
    private List<Boat> player2Boats = new ArrayList<>();

    public void GameSystem() {
        System.out.println("Welcome to SeaBattle!");
        grid.printEmptyBoard();

        Boat battleship = new Battleship();
        Boat destroyer = new Destroyer();
        Boat submarine = new Submarine();
        Boat patrolBoat1 = new PatrolBoat1();
        Boat patrolBoat2 = new PatrolBoat2();

        // Người chơi 1 đặt các tàu
        System.out.println("Người chơi 1 đặt tàu");
        game.placeBoat(battleship, 1);
        player1Boats.add(battleship);
        game.printBoard(1);
        game.placeBoat(destroyer, 1);
        player1Boats.add(destroyer);
        game.printBoard(1);
        game.placeBoat(submarine, 1);
        player1Boats.add(submarine);
        game.printBoard(1);
        game.placeBoat(patrolBoat1, 1);
        player1Boats.add(patrolBoat1);
        game.printBoard(1);
        game.placeBoat(patrolBoat2, 1);
        player1Boats.add(patrolBoat2);
        game.printBoard(1);

        // Người chơi 2 đặt các tàu
        System.out.println("Người chơi 2 đặt tàu");
        game.placeBoat(battleship, 2);
        player2Boats.add(battleship);
        game.printBoard(2);
        game.placeBoat(destroyer, 2);
        player2Boats.add(destroyer);
        game.printBoard(2);
        game.placeBoat(submarine, 2);
        player2Boats.add(submarine);
        game.printBoard(2);
        game.placeBoat(patrolBoat1, 2);
        player2Boats.add(patrolBoat1);
        game.printBoard(2);
        game.placeBoat(patrolBoat2, 2);
        player2Boats.add(patrolBoat2);
        game.printBoard(2);

        // Vòng lặp trò chơi
        int number = 1;
        while (true) {
            System.out.println("\nNgười chơi " + number + " đến lượt bắn!");
            System.out.print("Nhập vị trí bắn: ");
            String idx = scanner.next().toUpperCase();

            int x = idx.charAt(0) - 'A';
            int y = Integer.parseInt(idx.substring(1)) - 1;

            boolean hit = game.shootAt(number, x, y);
            if (hit) {
                System.out.println("Người chơi " + number + " bắn trúng!\n");
            } else {
                System.out.println("Người chơi " + number + " bắn trượt!\n");
                number = (number == 1) ? 2 : 1;
            }

            // In lại bảng sau khi bắn
            game.printBoard(1);
            game.printBoard(2);

            // Kiểm tra nếu một người thắng cuộc
            if (game.checkGameOver()) {
                System.out.println("Hẹn gặp lại!");
                break;
            }
        }
    }
}
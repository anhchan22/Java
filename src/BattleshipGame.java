import java.util.Scanner;


public class BattleshipGame {
    private Grid player1;
    private Grid player2;
    private Grid grid;
    private Scanner scanner;
    private int dem1;
    private int dem2;


    public BattleshipGame() {
        player1 = new Grid();
        player2 = new Grid();
        grid = new Grid();
        dem1 = 0;
        dem2 = 0;
        scanner = new Scanner(System.in);
    }

    // In bảng trống
    public void printEmptyBoard() {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for (char i = 'A'; i <= 'G'; i++) {
            System.out.print(i + " ");
            for (int j = 1; j <= 10; j++) {
                System.out.print(". ");
            }
            System.out.println();
        }
    }

    // Đặt tàu vào bảng
    public void placeBoat(Boat boat, int number) {
        boolean validPlacement = false;
        if(number==1)  grid = player1;
        else grid = player2;
        while (!validPlacement) {
            System.out.println(boat.getName() + " (Size: " + boat.getSize() + ")");
            System.out.println("Nhập tọa độ bắt đầu (eg, A1):");

            String position = scanner.next().toUpperCase();

            System.out.println("Nhập chiều đặt tàu (H: ngang, V: Dọc):");
            String direction = scanner.next().toUpperCase();

            int x = position.charAt(0) - 'A';
            int y = Integer.parseInt(position.substring(1)) - 1;

            validPlacement = grid.addBoat(boat, x, y, direction.equals("H"));
            if (validPlacement) {
                System.out.println(boat.getName() + " được đặt thành công!");
            }
            else {
                System.out.println("Lỗi, vui lòng thử lại!");
            }
        }
    }

    // In bảng sau khi đặt tàu
    public void printBoard(int player) {
        System.out.println("Player " + player + "'s Board:");
        Grid grid = (player == 1) ? player1 : player2;
        grid.printBoard();
    }

    // Bắn vào tàu
    public boolean shootAt(int player, int x, int y) {
        Grid targetGrid = (player == 1) ? player2 : player1; // Lưới đối thủ
        boolean hit = targetGrid.shootAt(x, y);

        // Kiểm tra nếu có thuyền chìm
        for (Boat boat : targetGrid.getBoats()) { // Thêm phương thức getBoats() trong Grid nếu chưa có
            if (boat.isSunk() && !boat.isCounted()) { // Kiểm tra thuyền đã chìm và chưa được đếm
                boat.setCounted(true); // Đánh dấu là đã đếm
                if (player == 1) dem2++; // Thuyền của đối thủ bị chìm
                else dem1++;
            }
        }

        return hit;
    }

    public boolean checkGameOver() {
        if (dem1 == 5) { // Người chơi 1 chìm hết 5 tàu
            System.out.println("Người chơi 2 đã chiến thắng!");
            return true;
        }
        if (dem2 == 5) { // Người chơi 2 chìm hết 5 tàu
            System.out.println("Người chơi 1 đã chiến thắng!");
            return true;
        }
        return false;
    }


}
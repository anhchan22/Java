import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class SeaBattleGame {
    private List<Ship> ships;
    private String[][] board;
    private Scanner scanner;

    public SeaBattleGame() {
        this.ships = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.board = new String[10][10];
        initializeBoard();
        initializeShips();
    }

    private void initializeBoard() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                board[i][j] = ".";
            }
        }
    }

    private void initializeShips() {
        ships.add(new Ship("Patrol Boat", 2, "A"));
        ships.add(new Ship("Destroyer", 4, "A"));
        ships.add(new Ship("Submarine", 3, "A"));
        ships.add(new Ship("Battleship", 5, "A"));
    }

    public void printBoard(boolean hideShips) {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for (int i = 0; i < 10; i++) {
            System.out.print((char) ('A' + i) + " ");
            for (int j = 0; j < 10; j++) {
                if (hideShips && !board[i][j].equals(".") && !board[i][j].equals("M") && !board[i][j].equals("X")) {
                    System.out.print(". ");
                } else {
                    System.out.print(board[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public void placeShips() {
        for (Ship ship : ships) {
            boolean validPlacement = false;
            while (!validPlacement) {
                System.out.println("Đặt tàu: " + ship.getName() + " (Kích thước: " + ship.getSize() + ")");
                System.out.print("Nhập vị trí bắt đầu (ví dụ: A1): ");
                String position = scanner.next().toUpperCase();

                System.out.print("Nhập hướng (H: ngang, V: dọc): ");
                String direction = scanner.next().toUpperCase();

                if (!isValidPosition(position)) {
                    System.out.println("Tọa độ không hợp lệ. Vui lòng nhập lại.");
                    continue;
                }

                int x = position.charAt(0) - 'A';
                int y = Integer.parseInt(position.substring(1)) - 1;

                validPlacement = isValidPlacement(ship, x, y, direction.equals("H"));
                if (validPlacement) {
                    for (int i = 0; i < ship.getSize(); i++) {
                        if (direction.equals("H")) {
                            board[x][y + i] = ship.getSymbol();
                        } else {
                            board[x + i][y] = ship.getSymbol();
                        }
                    }
                    System.out.println("Đã đặt tàu thành công!");
                } else {
                    System.out.println("Vị trí không hợp lệ. Vui lòng thử lại.");
                }
            }
            printBoard(true);
        }
    }

    private boolean isValidPosition(String position) {
        if (position.length() < 2 || position.length() > 3) return false;
        char row = position.charAt(0);
        String col = position.substring(1);

        return row >= 'A' && row <= 'J' && col.matches("[1-9]|10");
    }

    private boolean isValidPlacement(Ship ship, int x, int y, boolean isHorizontal) {
        if (isHorizontal && (y + ship.getSize() > 10)) return false;
        if (!isHorizontal && (x + ship.getSize() > 10)) return false;

        for (int i = 0; i < ship.getSize(); i++) {
            if (isHorizontal && !board[x][y + i].equals(".")) return false;
            if (!isHorizontal && !board[x + i][y].equals(".")) return false;
        }
        return true;
    }

    public boolean shootAt(int x, int y) {
        if (board[x][y].equals(".")) {
            board[x][y] = "M"; // Miss
            return false;
        } else if (!board[x][y].equals("X") && !board[x][y].equals("M")) {
            board[x][y] = "X"; // Hit
            return true;
        }
        return false;
    }

    public boolean hasShipsLeft() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (!board[i][j].equals(".") && !board[i][j].equals("M") && !board[i][j].equals("X")) {
                    return true;
                }
            }
        }
        return false;
    }
}
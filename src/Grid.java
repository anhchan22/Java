import java.util.*;

class Grid {
    private List<Boat> boats;
    private String[][] board;
    public void printEmptyBoard() {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for (int i = 0; i < 10; i++) {
            System.out.print((char)('A' + i) + " ");
            for (int j = 0; j < 10; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
    public Grid() {
        this.boats = new ArrayList<>();
        this.board = new String[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                board[i][j] = ".";
            }
        }
    }

    public boolean addBoat(Boat boat, int x, int y, boolean isHorizontal) {
        if (checkPlace(boat, x, y, isHorizontal)) {
            for (int i = 0; i < boat.getSize(); i++) {
                if (isHorizontal) {
                    board[x][y + i] = boat.getSymbol();
                } else {
                    board[x + i][y] = boat.getSymbol();
                }
            }
            boats.add(boat);
            return true;
        }
        return false;
    }

    private boolean checkPlace(Boat boat, int x, int y, boolean isHorizontal) {
        if (x < 0 || y < 0 || x >= 10 || y >= 10 || (isHorizontal && y + boat.getSize() > 10) || (!isHorizontal && x + boat.getSize() > 10)) {
            return false;
        }
        for (int i = 0; i < boat.getSize(); i++) {
            if (isHorizontal && !board[x][y + i].equals(".")) return false;
            if (!isHorizontal && !board[x + i][y].equals(".")) return false;
        }
        return true;
    }

    public boolean shootAt(int x, int y) {
        boolean hit = false;
        for (Boat boat : boats) {
            if (board[x][y].equals(boat.getSymbol())) {
                boat.hit();
                board[x][y] = "X";
                hit = true;
                if (boat.isSunk() && !boat.isCounted()) {
                    System.out.println(boat.getName() + " đã bị đánh chìm!");
                    boat.setCounted(true);
                    boats.remove(boat);
                } else if (!boat.isSunk()) {
                    System.out.println(boat.getName() + " đã bị bắn trúng!");
                }
                break;
            }
        }
        if (!hit) {
            board[x][y] = "M";
            System.out.println("Không bắn trúng!");
        }
        return hit;
    }

    public boolean isGameOver() {
        return boats.isEmpty();
    }

    public void printBoard() {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for (int i = 0; i < 10; i++) {
            System.out.print((char)('A' + i) + " ");
            for (int j = 0; j < 10; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

//    public List<Boat> getBoats() {
//        return boats;
//    }
}

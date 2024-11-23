import java.util.Scanner;

public class Menu {
    private Scanner scanner;

    public Menu() {
        this.scanner = new Scanner(System.in);
    }

    // Hiển thị menu chính
    public int displayMainMenu() {
        System.out.println("1. Bắt đầu trò chơi");
        System.out.println("2. Thoát");
        return scanner.nextInt();
    }

    // Hiển thị menu lựa chọn trong lượt chơi
    public int displayTurnMenu(String playerName) {
        System.out.println("\n" + playerName + ", hãy chọn một hành động:");
        System.out.println("1. Xem bảng của bạn");
        System.out.println("2. Khai hỏa vào đối phương");
        System.out.println("3. Kết thúc lượt");
        return scanner.nextInt();
    }



    // Hiển thị kết quả khi một người thắng
    public void displayGameOver(String playerName) {
        System.out.println("\nGame Over! " + playerName + " thắng!");
    }
}

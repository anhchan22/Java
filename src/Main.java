import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GameSystem game = new GameSystem();
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Bắt đầu trò chơi");
        System.out.println("2. Thoát");
        int choice = sc.nextInt();
        switch (choice){
            case 1:
                game.GameSystem();
            case 2:
                break;
        }
    }
}

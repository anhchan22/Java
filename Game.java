import java.lang.Runtime.Version;
import java.util.Scanner;

public class Game {
    public void startGame(){
        System.out.println("Welcome to Sea Battle");
        System.out.println("Một số kí tự trong game:");
        System.out.println("o: Bắn trượt");
        System.out.println("x: Bắn trúng");
        System.out.println("~: Vị trí có tàu");
    }
    public void playGame(){
        Scanner input = new Scanner(System.in);
        Player first = new Player();
        Player second = new Player();
        System.out.println("Xin chào 1st_Player! Hãy đặt các thuyền của bạn.");
        first.setUpBoat();
        System.out.println("Press any key to continue!");
        String c = input.next();
        System.out.println("Xin chào 2nd_Player! Hãy đặt các thuyền của bạn.");
        second.setUpBoat();
        int turns = 1;
        while(first.check == 0 && second.check == 0){
            if(turns % 2 == 1){
                System.out.println("Press any key to continue!");
                c = input.next();
                System.out.println("Đến lượt 1st_Player");
                menuGame(first, second);
            }
            else{
                System.out.println("Press any key to continue!");
                c = input.next();
                System.out.println("Đến lượt 2nd_Player");
                menuGame(second, first);
            }
            turns++;
        }
    }
    public void menuGame(Player tmp1, Player tmp2){
        System.out.println("Số ô đã bắn ở mặt trận địch: " + tmp2.numberOfHits);
        System.out.println("Số tàu đã phá được: " + (5 - tmp2.numberOfBoats));
        System.out.println("Số tàu còn lại của bản thân: " + tmp1.numberOfBoats);
        System.out.println("Chọn 1 trong số các chức năng sau:");
        System.out.println("1. Xem bảng");
        System.out.println("2. Tấn công");
        System.out.println("3. Kết thúc lượt");
        Scanner input = new Scanner(System.in);
        int option = input.nextInt();
        switch (option){
            case 1:
                System.out.println("Tình trạng hiện tại của bạn: ");
                tmp1.showBoard();
                System.out.println("Press any key to continue!");
                String c = input.next();
            case 2:
                tmp2.beAttacked();
            case 3:
                break;
        }
    }
}

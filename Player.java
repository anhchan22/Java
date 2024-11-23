import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Player extends BattleField{
    public Boat[] boats = new Boat[5];
    public int numberOfBoats = 0, check = 0, numberOfHits = 0;
    public void setUpBoat() {
        while (numberOfBoats < 5) {
            showBoard();
            Scanner input = new Scanner(System.in);
            String name;
            int sizeBoat;
            if(numberOfBoats < 2){
                name = "Thuyển Tuần Tra";
                sizeBoat = 2;
            }
            else if(numberOfBoats == 2){
                name = "Tàu Khu Trục";
                sizeBoat = 4;
            }
            else if(numberOfBoats == 3){
                name = "Tàu Ngầm";
                sizeBoat = 3;
            }
            else {
                name = "Thiết Chiến Hạm";
                sizeBoat = 5;
            }
            int checkSet = 1;
            while(checkSet == 1){
                System.out.println("Thông tin " + name + " kích cỡ 1x" + sizeBoat);
                System.out.print("Tọa độ đầu: ");
                int x_begin = input.nextInt();
                int y_begin = input.nextInt();
                System.out.print("Tọa độ cuối: ");
                int x_end = input.nextInt();
                int y_end = input.nextInt();
                if(x_begin > 10 || x_end > 10 || y_end > 10 || y_begin > 10){
                    System.out.println("Tọa độ mốn đặt đã có tàu! Vui lòng đặt lại tàu!");
                    continue;
                }
                int count = 0;
                for (int i = min(x_begin, x_end); i <= max(x_end, x_begin); i++) {
                    for (int j = min(y_begin, y_end); j <= max(y_end, y_begin); j++) {
                        if(getBoard(i, j) == ' ') count++;
                        else break;
                    }
                }
                if(count == sizeBoat) {
                    Boat tmp = new Boat(name, x_begin, y_begin, x_end, y_end, sizeBoat);
                    boats[numberOfBoats] = tmp;
                    for (int i = min(x_begin, x_end); i <= max(x_end, x_begin); i++) {
                        for (int j = min(y_begin, y_end); j <= max(y_end, y_begin); j++) {
                            setBoard(i, j, '~');
                        }
                    }
                    checkSet = 0;
                }
                else{
                    System.out.println("Tọa độ mốn đặt đã có tàu! Vui lòng đặt lại tàu!");
                }
            }
            numberOfBoats++;
        }
        showBoard();
    }

    public void beAttacked(){
        while(true){
            showBlindBoard();
            Scanner input = new Scanner(System.in);
            System.out.print("Nhập tọa độ tấn công: ");
            int x = input.nextInt();
            int y = input.nextInt();
            if(getBoard(x, y) == 'o' || getBoard(x, y) == 'x'){
                System.out.println("Tọa độ này đã bắn!");
                continue;
            }
            checkHit(x, y);
            if(getBoard(x, y) == 'o' || check == 1) break;
            else{
                numberOfHits++;
                continue;
            }
        }
    }
    public void checkHit(int x, int y){
        if(getBoard(x, y) == '~'){
            System.out.println("Đã bắn trúng tàu!");
            setBoard(x, y, 'x');
            checkBoat(x, y);
        }
        else{
            setBoard(x, y, 'o');
            System.out.println("Đã bắn trượt!");
        }
    }
    public void checkBoat(int x, int y){
        for(Boat tmp: boats){
            if(x >= tmp.getX_begin() && x <= tmp.getX_end() && y >= tmp.getY_begin() && y <= tmp.getY_end()){
                tmp.setSizeBoat(tmp.getSizeBoat() - 1);
                if(tmp.getSizeBoat() == 0){
                    numberOfBoats--;
                    System.out.println(tmp.getName() + " đã bị bắn hạ!");
                    checkWinner();
                }
            }
        }
    }
    public void checkWinner(){
        if(numberOfBoats == 0){
            System.out.println("Bạn là người chiến thắng!");
            System.out.println("Trò chơi kết thúc!");
            check = 1;
        }
    }
}

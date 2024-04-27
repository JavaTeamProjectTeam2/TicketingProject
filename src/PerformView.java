package src;

import java.io.File;
import java.io.IOException;

import static src.SimpleInput.sc;
import static src.MainView.ROOT_PATH;

public class PerformView {
    public static void getTicket() {
        makePerformFile();

        System.out.println("<=== 공연 조회 및 예매 ===>");
        System.out.println("# 1. 현재 하는 행사 보여줘");
        System.out.println("# 2. 카테고리 별로 보여줘");
        System.out.println("# 0. 뒤로가기");
        System.out.println("======================");
        System.out.print(">>> ");

        int option = sc.nextInt();
        switch (option) {
            case 1:
                System.out.println("카테고리별로 추천");
                break;
            case 0:
                System.out.println("뒤로가기");
                break;
            default:
                System.out.println("잘못 입력했습니다");
                break;
        }
    }




    private static void makePerformFile(){
        File directory = new File(ROOT_PATH + "/PerformRepository");

        // 폴더 생성
        if(!directory.exists()){
            directory.mkdir();
        }
        //파일 생성하기
        File newFile = new File(ROOT_PATH + "/PerformRepository/workout.txt");
        if(!newFile.exists()){
            try {
                newFile.createNewFile();
            } catch (IOException e) {
                System.out.println("파일 생성에 실패했습니다.");
            }
        }
    }
}

package src;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static src.Category.*;
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
                System.out.println("현재 진행 중인 행사 보여줘");
                showTheseDaysEvent();
                break;
            case 2:
                System.out.println("카테고리별로 추천");
                showOptions();
                break;
            case 0:
                System.out.println("뒤로가기");
                break;
            default:
                System.out.println("잘못 입력했습니다");
                break;
        }
    }

    private static void showTheseDaysEvent() {
        PerformRepository.theseDaysEvent();
    }

    private static void showOptions() {
        System.out.println("======카테고리 별 추천 파트======");
        System.out.println("## 뮤지컬, 콘서트, 전시회, 가족컨텐츠 중 선택하세요 ##");
        System.out.println("# 1." + CONCERT.getContentName() );
        System.out.println("# 2." + MUSICAL.getContentName());
        System.out.println("# 3." + EXHIBIT.getContentName());
        System.out.println("# 4." + FAMILY.getContentName());
        System.out.println("# 0. 뒤로가기");
        System.out.print(">>>>> ");
        
        int option = sc.nextInt();
        if(option == 0){
            getTicket();
        }else{
//            List<String> strings = PerformRepository.showContentByCategory(option);
            Perform content = PerformRepository.returnPerformContent();
            booking(content);
        }
    }

    private static void booking(Perform content) {

    }

    //공연정보 리스트 파일 생성
    private static void makePerformFile(){
        File directory = new File(ROOT_PATH + "/PerformRepository");

        // 폴더 생성
        if(!directory.exists()){
            directory.mkdir();
        }
        //파일 생성하기
        File newFile = new File(ROOT_PATH + "/PerformRepository/PerformList.txt");
        if(!newFile.exists()){
            try {
                newFile.createNewFile();
            } catch (IOException e) {
                System.out.println("파일 생성에 실패했습니다.");
            }
        }
    }
}

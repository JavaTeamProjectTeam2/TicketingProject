package src;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static src.BookingView.*;
import static src.BookingView.booking;
import static src.BookingView.thread;
import static src.Category.*;
import static src.SimpleInput.input;
import static src.SimpleInput.sc;
import static src.MainView.ROOT_PATH;

public class PerformView {
    public static void getTicket() {
//        makePerformFile();

        System.out.println("----------------------------------------");
        System.out.println("         🎤 공연 조회 및 예매 🎨");
        System.out.println("---------------------------------------- *");
        System.out.println("\t1️⃣ 이 주의 공연/전시 목록");
        System.out.println("\t2️⃣ 카테고리 별 공연/전시 목록");
        System.out.println("\t0️⃣ 뒤로가기");
        System.out.println("----------------------------------------");

        String option = input(">> ");
        switch (option) {
            case "1":
//                System.out.println("현재 진행 중인 행사 보여줘");
                showTheseDaysEvent();
                break;
            case "2":
//                System.out.println("카테고리별로 추천");
                showOptions();
                break;
            case "0":
                System.out.println("뒤로가기");
                break;
            default:
                System.out.println("잘못 입력하였습니다.");
                break;
        }
    }

    private static void showTheseDaysEvent() {
//        PerformRepository.theseDaysEvent();

        String selectedTitle = null;
        shownum: while (true) {
            List<String> recentTitles = PerformRepository.theseDaysEvent();
            System.out.println("----------------------------------------");
            System.out.println("📢 예매하고 싶으신 공연/전시의 번호를 입력해주세요.");
//        System.out.print(">> ");
            String option =input(">> ");

            selectedTitle = "";
            switch (option) {
                case "1":
                    selectedTitle = recentTitles.get(0);
                    break shownum;
                case "2":
                    selectedTitle = recentTitles.get(1);
                    break shownum;
                case "3":
                    selectedTitle = recentTitles.get(2);
                    break shownum;
                default:
                    System.out.println("🚨 옵션의 번호를 입력하세요 🚨");
            }
        }

        Perform selectedPerform = PerformRepository.getSelectedTitle(selectedTitle);
        // 예매 시작
        booking(selectedPerform);
        waitForEnter();
//        MainView.start();
    }

    private static void showOptions() {
        System.out.println("----------------------------------------");
        System.out.println("         카테고리 별 추천 파트");
        System.out.println("---------------------------------------- *");
        System.out.println("📢 예매하고 싶으신 카테고리 번호를 입력해주세요.");
        System.out.println("\t1️⃣ " + CONCERT.getContentName() );
        System.out.println("\t2️⃣ " + MUSICAL.getContentName());
        System.out.println("\t3️⃣ " + EXHIBIT.getContentName());
        System.out.println("\t4️⃣ " + FAMILY.getContentName());
        System.out.println("\t0️⃣ 뒤로가기");
        System.out.print(">>> ");

        int option = sc.nextInt();
        if(option == 0){
            getTicket();
        }else{
            List<String> titleByCategory = PerformRepository.ContentByCategory(option);
            String selectedTitle = showContentByCategory(titleByCategory);
            Perform selectedPerform = PerformRepository.getSelectedTitle(selectedTitle);

            booking(selectedPerform);
        }
    }

    private static String showContentByCategory(List<String> titleByCategory) {
        if (!thread.isAlive()) {
            thread = new MyThread(); // 새로운 스레드 객체 생성
            thread.start(); // 스레드 시작
        }
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("----------------------------------------");
        System.out.printf("     카테고리 별 추천           \n");
        System.out.println("---------------------------------------- *");
        int count = 0;
        for (String title : titleByCategory) {
            count++;
            System.out.println("# " + count +". "+ title);
        }
        System.out.println("-----------------------------------------");
        System.out.println("📢 예매하고싶은 공연/전시의 번호를 입력해주세요.");
        System.out.print(">> ");
//        int option = Integer.parseInt(sc.nextLine());
//        String sOption = (input(">> "));
//        int option = Integer.parseInt(sOption);
        int option = sc.nextInt();
        sc.nextLine();
        String selectedTitle = titleByCategory.get(option-1);
        return selectedTitle;
    }
}
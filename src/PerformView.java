package src;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static src.BookingRepository.isNumber;
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
        while (true){
        System.out.println("----------------------------------------");
        System.out.println("       공연 조회 및 예매");
        System.out.println("---------------------------------------- *");
        System.out.println("\t1️⃣ 금주의 공연/전시 목록");
        System.out.println("\t2️⃣ 카테고리 별 공연/전시 목록");
        System.out.println("\t0️⃣ 뒤로가기");
        System.out.println("----------------------------------------");

        String option = input(">> ");
        switch (option) {
            case "1":
                showTheseDaysEvent();
                break;
            case "2":
//                System.out.println("카테고리별로 추천");
                showOptions();
                break;
            case "0":
//                System.out.println("뒤로가기");
                MainView.start();
                break;
            default:
                System.out.println("🚨 옵션의 번호를 입력하세요 🚨");
                break;
            }
        }
    }

    private static void showTheseDaysEvent() {
//        PerformRepository.theseDaysEvent();
        boolean flag;
        while (true) {
            List<String> recentTitles = PerformRepository.theseDaysEvent();
            System.out.println("----------------------------------------");
            System.out.println("📢 예매하고 싶으신 공연/전시의 번호를 입력해주세요");
            System.out.println("0️⃣ 뒤로가기");
//        System.out.print(">> ");
            String option = input(">> ");

            String selectedTitle = "";
            switch (option) {
                case "1":
                    selectedTitle = recentTitles.get(0);
                    flag = true;
                    break;
                case "2":
                    selectedTitle = recentTitles.get(1);
                    flag = true;
                    break;
                case "3":
                    selectedTitle = recentTitles.get(2);
                    flag = true;
                    break;

                case "0":
                    System.out.println(" 뒤로~~~ ");
                    flag = false;
                    PerformView.getTicket();
                    break;
//                    return;
                default:
                    flag = false;
                    System.out.println("🚨 옵션의 번호를 입력하세요 🚨");
                    System.out.println(" ");
                    System.out.println(" ");
//                    break;
            }

            Perform selectedPerform = PerformRepository.getSelectedTitle(selectedTitle);
            if(flag && selectedPerform != null){
                booking(selectedPerform);
                waitForEnter();
            }
            // 예매 시작
        }
    }

    public static void showOptions() {
        while (true) {
            System.out.println("----------------------------------------");
            System.out.println("     카테고리 별 추천 파트");
            System.out.println("---------------------------------------- *");
            System.out.println("📢 뮤지컬, 콘서트, 전시회, 가족컨텐츠 중 선택하세요");
            System.out.println("\t1️⃣ " + CONCERT.getContentName());
            System.out.println("\t2️⃣ " + MUSICAL.getContentName());
            System.out.println("\t3️⃣ " + EXHIBIT.getContentName());
            System.out.println("\t4️⃣ " + FAMILY.getContentName());
            System.out.println("\t0️⃣ 뒤로가기");


            String input = input(">> ");

            if(!BookingRepository.isNumber(input)) {
                System.out.println("🚨 옵션의 번호를 입력하세요 🚨");
            }else{
            int option = Integer.parseInt(input);
            if (option == 0){
                getTicket();
                break;
            }
            else if (option == 1 || option == 2 || option == 3 || option == 4) {
                List<String> titleByCategory = PerformRepository.ContentByCategory(option);
                String selectedTitle = showContentByCategory(titleByCategory);
                if (selectedTitle != null) {
                    Perform selectedPerform = PerformRepository.getSelectedTitle(selectedTitle);
                    booking(selectedPerform);
                    break;
                }else{
                    showContentByCategory(titleByCategory);
                    break;
                }


            } else{
                System.out.println("🚨 옵션의 번호를 입력하세요 🚨");
            }
            }
        }
    }

    private static String showContentByCategory(List<String> titleByCategory) {
        String selectedTitle;
        while (true) {
            System.out.println("----------------------------------------");
            System.out.printf("     카테고리 별 추천           \n");
            System.out.println("---------------------------------------- *");
            int count = 0;
            for (String title : titleByCategory) {
                count++;
                System.out.println("# " + count +". "+ title);
            }
            System.out.println("-----------------------------------------");
            System.out.println("📢 예매하고싶은 공연/전시의 번호를 입력해주세요");
            System.out.println("0️⃣ 뒤로가기 ");

//        int option = Integer.parseInt(sc.nextLine());
//        String sOption = (input(">> "));
//        int option = Integer.parseInt(sOption);
            try{
                String input = input(">> ");
                int option = Integer.parseInt(input);
                    if (option == 0) {
                        System.out.println(" ");
                        System.out.println(" 공연 조회 화면으로 ... ");
                        showOptions();
                        selectedTitle = null;
                        break;
                    } else if (!(option < titleByCategory.size() + 1) || option < 0) {
                        System.out.println("🚨 옵션의 번호를 입력하세요 🚨");
                        selectedTitle = null;
                        break;
                    } else {
                        selectedTitle = titleByCategory.get(option - 1);
                        break;
                    }
            }catch (NumberFormatException e){
                    System.out.println("🚨 옵션의 번호를 입력하세요 🚨");
            }
        }
            return selectedTitle;
    }
}
package src;

import src.login.LoginView;
import src.login.MemberRepository;
import src.login.MemberView;
import src.login.MypageView;

import java.sql.SQLOutput;
import java.util.InputMismatchException;

import static src.SimpleInput.input;
import static src.SimpleInput.sc;

public class MainView {
    public static final String ROOT_PATH = "/Users/jeongin/Desktop/javaTicketing";
    public static void start() {
        System.out.println("공연 관람을 위한 티켓팅입니다~~~");

        while (true){
            System.out.println("----------------------------");
            System.out.println("           티켓팅");
            System.out.println("---------------------------- *");
            if(MemberRepository.getLoginMember() != null) {
                System.out.println("1️⃣ 마이페이지");
            } else {
                System.out.println("1️⃣ 회원 정보");
            }
            System.out.println("2️⃣ 공연 조회 및 예매");
            System.out.println("3️⃣ 프로그램 종료");
            System.out.print(">> ");
            int option = Integer.parseInt(sc.nextLine());


            switch (option){
                case 1:
                    if(MemberRepository.getLoginMember() != null) {
                        MypageView mv = new MypageView(MemberRepository.getLoginMember());
                    } else {
                        MemberView memberView = new MemberView();
                        memberView.showLoginMenu();
                    }
                    break;
                case 2:
                    System.out.println("공연 예매를 시작합니다");
                    PerformView.getTicket();
                    break;
                case 3:
                    System.out.println("프로그램을 종료합니다");
                    System.exit(0);
                default:
                    System.out.println("제대로 입력하세요");
                    break;
            }
        }
    }
}

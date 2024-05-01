package src;

import src.login.LoginView;
import src.login.MemberRepository;
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
            System.out.println("===========티켓팅============");
            if(MemberRepository.getLoginMember() != null) {
                System.out.println(" #1. 마이페이지");
            } else {
                System.out.println(" #1. 로그인");
            }
            System.out.println(" #2. 공연 조회 및 예매");
            System.out.println(" #3. 프로그램 종료");
            System.out.print(">> ");
            int option = sc.nextInt();


            switch (option){
                case 1:
                    if(MemberRepository.getLoginMember() != null) {
                        MypageView mv = new MypageView(MemberRepository.getLoginMember());
                    } else {
                        System.out.println("로그인을 시작합니다.");
                        LoginView lv = new LoginView();
                        lv.showLogIn();
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

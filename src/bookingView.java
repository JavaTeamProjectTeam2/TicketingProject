package src;

import src.login.Member;
import src.userJoin.UserJoinRepository;
import src.login.LoginView;
import static src.SimpleInput.sc;

public class bookingView {
    public static void booking() {
        UserJoinRepository ur = new UserJoinRepository();
        System.out.println("#======= 예약을 시작합니다 =======#");
        System.out.println("# 예매를 위해 로그인이 필요합니다");
        System.out.println("# 비회원예매를 원하신다면 '비회원'을 입력해주세요");
        System.out.print(">>> ");

        String input = sc.nextLine();

        if(input.equals("비회원")){
            bookingRepository.nonMemberBooking();
        }else {
            LoginView lv = new LoginView();
            lv.showLogIn();
            //로그인이 성공적으로 됐다면...
            if (true){
                Member member = new Member();
                Perform title =
                bookingRepository.memberBooking(member, title);
            }
        }

    }

}
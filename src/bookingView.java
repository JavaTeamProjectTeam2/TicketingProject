package src;

import src.login.Member;
import src.userJoin.UserJoinRepository;
import src.login.LoginView;
import static src.SimpleInput.sc;
//import static src.bookingRepository;

public class bookingView {
    public static void booking(Perform performContent) {
        UserJoinRepository ur = new UserJoinRepository();
        System.out.printf("#======= <%s> 예약을 시작합니다 =======# \n", makeTitleShort(performContent.getTitle()));
        System.out.println("# 예매를 위해 로그인이 필요합니다");
        System.out.println("# 비회원예매를 원하신다면 '비회원'을 입력해주세요");
        System.out.print(">>> ");

        String input = sc.nextLine();

        if(input.equals("비회원")) {
            nonMemberBooking();
        }
        else {
            LoginView lv = new LoginView();
            lv.showLogIn();
            //로그인이 성공적으로 됐다면...
            if (true){
                //로그인 시 로그인한 객체 데이터 받아오기
//                Member member = new Member();
//                //PerformRepository 부터 Perform 객체로 받아옴
//
//                //받아온 로그인 객체와 제목을 토대로 회원 부킹 시작
//                memberBooking(member, performContent);
            }
        }

    }

    private static void nonMemberBooking() {
        while (true) {
            System.out.println("==== 비회원 예매를 시작합니다. ====");
            System.out.print("# 이름을 입력해주세요: ");
            String name = sc.nextLine();
            if(name.isEmpty()){
                System.out.println("# 허용되지 않은 이름입니다");
            }else{
                System.out.println("# 나이를 입력해주세요");
            }
        }
    }

    private static void memberBooking(Member member, Perform perform) {
        System.out.printf("안녕하세요 %s님, 예매하고자 하는 공연이 %s 맞습니까?", member.getName(), perform.getTitle());
    }

    public static String makeTitleShort(String title) {
        if(title.length() > 10){
            return title.substring(0, 10);
        }else{
            return title;
        }
    }


}
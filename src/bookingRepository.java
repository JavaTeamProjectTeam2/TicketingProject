package src;
import src.login.Member;

public class bookingRepository {
    public static void nonMemberBooking() {
        System.out.println("비회원 예매를 시작합니다.");
        System.out.println("");
    }

    public static void memberBooking(Member member) {

        System.out.printf("안녕하세요 %s님, 예매하고자 하는 공연이 %s 맞습니까?", member.getName(), );

    }
}

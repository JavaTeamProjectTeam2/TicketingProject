package src;

import src.login.Member;

import java.util.HashMap;
import java.util.Map;

import static src.AgeRating.*;
import static src.Category.*;

public class BookingRepository {

    //예매 시작
    public static void allSelectedBooking(Perform perform, Member member, Map<String, Integer> party, String section) {
        // 나이 제한에 걸리는 사람 있는지 확인 후 예매 시작
        if(checkAge(perform, party)){
            if (perform.getCategory().equals(CONCERT)){
                concertBooking(perform, member, party);
            }else{
                performBooking(perform, member, party, section);
            }
        }else{
            System.out.println("나이제한으로 예매 불가능합니다");
            //공연 조회 화면으로 이동
            PerformView.getTicket();
        }
    }

    //관람등급 확인
    private static boolean checkAge(Perform perform, Map<String, Integer> party) {
        //작품 관람등급
        int contentAge= perform.getAge();
        //고른 공연이 14세 이상이고, 사용자 일행에 미취학아동이 있을경우 제한
        if(contentAge >= FOURTEEN.getAge() && party.get(SEVEN.getAgeOption()) > 0){
            System.out.println("<"+perform.getTitle()+">"+"은/는 미취학아동이 관람할 수 없습니다.");
            System.out.println("다른 공연/전시를 선택해주세요!!");
            return false;
        }
        if(contentAge == ALL.getAge()){
            return true;
        }
        return true;
    }


    //콘서트 제외한 공연들 예매
    private static void performBooking(Perform perform, Member member, Map<String, Integer> party, String section) {
        Map<String, Integer> totalPrice = getPerformPrice(perform, member, party, section);

    }
    //콘서트 예매
    private static void concertBooking(Perform perform, Member member, Map<String, Integer> party) {
        //좌석 선택
        //랜덤하게 자리 보여주기
        //7x7 matrix
        for (int i = 0; i < 10 ; i++) {
            for (int j = 0; j < 15; j++) {
                System.out.print("◾️");
            }
            System.out.print("\n");
        }

    }

    private static Map<String, Integer> getPerformPrice(Perform perform, Member member, Map<String, Integer> party,String section) {
        //가족, 전시 성인, 아이 가격
        //얼마 내야하는지 알려줌
        Map<String, Integer> ticketInfo = new HashMap<>();
        int totalPrice = 0;
        int ticketCount = party.get(FOURTEEN.getAgeOption()) + party.get(SEVEN.getAgeOption());
        //section이 필요없는 가족, 전시, 콘서트일 때
        if(!(perform.getCategory().equals(MUSICAL))){
            //콘서트
            if(perform.getCategory().equals(CONCERT)){
                totalPrice += 138000*party.get(FOURTEEN.getAgeOption()) + 138000*party.get(SEVEN.getAgeOption());
            }else{
                totalPrice += 55000* party.get(FOURTEEN.getAgeOption()) + 35000*party.get(SEVEN.getAgeOption());
            }
        }else{
            if(section.equalsIgnoreCase("VIP")){
//                totalPrice += 198000 * party.get(FOURTEEN.getAgeOption())
//                        + 198000 * party.get(SEVEN.getAgeOption());
                totalPrice = ticketCount*198000;
            } else if (section.equalsIgnoreCase("R")) {
                totalPrice = ticketCount*154000;
//                return 154000;
            } else if (section.equalsIgnoreCase("S")) {
                totalPrice = ticketCount*132000;
            }else if (section.equalsIgnoreCase("A")) {
                totalPrice = ticketCount*99000;
            }
        }
        System.out.println("totalPrice: "+totalPrice);
        ticketInfo.put("totalPrice", totalPrice);
        ticketInfo.put("ticketCount", ticketCount);
        System.out.println(ticketInfo);
        return ticketInfo;
    }
}

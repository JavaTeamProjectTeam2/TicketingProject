package src;

import src.login.Member;
import src.login.MemberRepository;
import src.userJoin.UserJoinRepository;
import src.login.LoginView;

import java.time.LocalDateTime;
import java.util.*;

import static src.AgeRating.*;
import static src.SimpleInput.input;
import static src.SimpleInput.sc;
//import static src.bookingRepository;

public class BookingView {

    public static void booking(Perform performContent) {
        UserJoinRepository ur = new UserJoinRepository();
        System.out.printf("#======= <%s> 예약을 시작합니다 =======# \n", makeTitleShort(performContent.getTitle()));
        System.out.println("# 예매를 위해 로그인이 필요합니다");
        System.out.println("# 비회원예매를 원하신다면 '비회원'을 입력해주세요");
        System.out.print(">>> ");

        String input = sc.next();

        if(input.equals("비회원")) {
            nonMemberBooking(performContent);
        } else {
                LoginView lv = new LoginView();
                lv.showLogIn();

                MemberRepository mr = MemberRepository.getInstance();
                // Check if login is successful
                Member loginMember = MemberRepository.getLoginMember();
                if ((loginMember) != null) {
                    memberBooking(loginMember, performContent);
                } else {
                    // Handle unsuccessful login
                    nonMemberBooking(performContent);
                }
        }

    }

    private static void nonMemberBooking(Perform performContent) {
        boolean flag = false;
        while (true) {
            System.out.println(".");
            System.out.println(".");
            System.out.println(".");
            System.out.println("==== 비회원 예매를 시작합니다. ====");
            System.out.println("--------------------------------");
            // 이름 입력
            System.out.print("# 이름을 입력해주세요: ");
            String name = sc.next();
            // 이름이 비어있는지 확인
            if (name.isEmpty()) {
                System.out.print("# 이름을 정확히 입력해주세요.");
//                System.out.println(">> ");
//                name = sc.next();
                return;
            }
            // 나이 입력
            System.out.println("# 나이를 입력해주세요: ");
            int age = Integer.parseInt(sc.next());
            try {
                if(age<1 || age > 150) System.out.println("비회원 예약 시작");
                flag = true;
            } catch (NumberFormatException e) {
                System.out.println("# 올바른 나이를 입력해주세요.");
                return;
            }

            // 전화번호 입력
            System.out.print("# 전화번호를 입력해주세요: ");
            String phoneNumber = sc.next();
            if (phoneNumber.isEmpty()) {
                System.out.println("올바른 번호를 입력해주세요!!");
            }

            // 입력된 정보 출력
            System.out.println("\n==== 입력된 정보 ====");
            System.out.println("이름: " + name);
            System.out.println("나이: " + age);
            System.out.println("전화번호: " + phoneNumber);

            if(!name.isEmpty() && !phoneNumber.isEmpty() && flag) {
                ArrayList<String> nonMember = new ArrayList<>();
                nonMember.add(name);
                nonMember.add(phoneNumber);
                nonMember.add(String.valueOf(age));

                nonMemberBookingStart(performContent, nonMember);
                break;
            }
        }
    }

    private static void nonMemberBookingStart(Perform perform,  ArrayList<String> nonMember) {

    }

    private static void memberBooking(Member member, Perform perform) {
        System.out.println(".");
        System.out.println(".");
        System.out.println(".");
        System.out.printf("안녕하세요 %s님, 예매하고자 하는 공연이 %s 맞습니까?\n", member.getName(), perform.getTitle());
        System.out.println("예 / 아니오");
        String input = input(">> ");

        if (input.equals("예")||input.equalsIgnoreCase("Y")|| input.equals("yes")) {
            System.out.println(".");
            System.out.println(".");
            System.out.println(".");
            System.out.println("------------------------");
            System.out.println("회차를 선택해주세요");
            System.out.println("------------------------");
            int count = 1;
            for (LocalDateTime localDateTime : perform.getDate().getShowTime()) {
                System.out.printf("# %d. %s\n", count++, localDateTime);
            }
            System.out.println("------------------------");
            System.out.print(">> ");
            int option = Integer.parseInt(sc.next());
            LocalDateTime selectedShowTime = perform.getDate().getShowTime().get(option - 1);

            System.out.println(selectedShowTime + " 해당일 "+perform.getCategory().getContentName()+"을/를 예매하겠습니다.");
            System.out.println(".");
            System.out.println(".");
            System.out.println(".");
            Map<String,Integer> party = getParty();
//            int totalPrice = BookingRepository.getPerformPrice();
            String section = "";
            if(!(perform.getCategory().equals(Category.MUSICAL))){
                section = null;
            }else{
                System.out.println("==========================");
                System.out.println("# 좌석 등급을 선택해주세요");
                System.out.println("----------------------");

                int cnt = 1;
                for (Section value : Section.values()) {
                    System.out.printf("# %d. %s\n",cnt,value.toString());
                    cnt++;
                }
                System.out.println("-----------------------");
                System.out.print(">> ");
                int option2 = Integer.parseInt(sc.next());
                section = Section.values()[option2-1].toString();
                System.out.println(section);
            }

            BookingRepository.allSelectedBooking(perform, member, party, section);
        }
    }

    private static Map<String, Integer> getParty() {
        Map<String, Integer> party = new HashMap<>();

        System.out.println("=== # 관람 인원을 선택해주세요 ===");
        System.out.printf("# %s (만 14세 이상): ", FOURTEEN.getAgeOption());
        Integer adult = Integer.parseInt(sc.next());
        party.put(FOURTEEN.getAgeOption(), adult);
        System.out.printf("# %s (만 7세 이하): ", SEVEN.getAgeOption());
        Integer child = Integer.parseInt(sc.next());
        party.put(SEVEN.getAgeOption(), child);

        return party;
    }

    public static String makeTitleShort(String title) {
        if(title.length() > 10){
            return title.substring(0, 10)+" ...";
        }else{
            return title;
        }
    }
}
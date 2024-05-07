package src.login;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static src.MainView.start;
import static src.SimpleInput.*;

public class MypageView {

    private Member logMember;
    private MemberRepository mr = MemberRepository.getInstance();
    private LoginManager loginManager = new LoginManager();


    public MypageView(Member member) {
        this.logMember = member;
        showMemberInfo();
    }

    public Member getLogMember() {
        return logMember;
    }

    public void setLogMember(Member logMember) {
        this.logMember = logMember;
    }

    public void showMemberInfo() {

        while (true) {
            System.out.println("\n----------------------------------------");
            System.out.println("            😉 마이 페이지");
            System.out.println("---------------------------------------- *");
            System.out.printf("  * 이름: %s\n", this.logMember.getName());
            System.out.printf("  * 이메일: %s\n", this.logMember.getEmail());
            System.out.printf("  * 나이: %d\n", this.logMember.getAge());
            System.out.printf("  * 휴대폰: %s\n", this.logMember.getPhone());
            System.out.printf("  * 주소: %s\n", this.logMember.getAddress());
            System.out.printf("  * 포인트: %d\n", this.logMember.getPoint());
            System.out.printf("  * 예매내역: %d건\n", this.logMember.getTicketList().size());


            myMenu: while (true) {
                System.out.println("\n 1️⃣ 비밀번호 변경 | 2️⃣ 주소 변경 | 3️⃣ 예매내역 | 4️⃣ 로그아웃 | 0️⃣ 뒤로가기");
                String menuOpt = input(">> ");
                switch (menuOpt) {
                    case "1":
                        updatePw();
                        break myMenu;
                    case "2":
                        updateAddress();
                        break myMenu;
                    case "3":
                        cancelTicket();
                        break myMenu;
                    case "4":
                        logOut();
                        break;
                    case "0":
                        start();
                        break;
                    default:
                        System.out.println("📢 메뉴 번호만 입력해주세요");
                }
            }
        }
    }

    public void updatePw() {
        if(!loginManager.isLoginEnabled(logMember)) {
            loginManager.leftTime(logMember);
        } else {
            int count = 0;
            while (true) {
                System.out.println();
                String oldPw = input(" * 기존 비밀번호 입력 >> ");
                if(!logMember.getPw().equals(oldPw)) {
                    count++;
                    System.out.printf("📢 비밀번호가 일치하지 않습니다. (%d회 오류)\n", count);
                    if(count == 3) {
                        System.out.println("📢 비밀번호를 3회 이상 틀리셨습니다. 다음에 시도해주세요.");
                        loginManager.disableLogin(logMember);
                        showMemberInfo();
                    }
                } else break;
            }
            String newPw;
            while (true) {
                newPw = input(" * 새 비밀번호 입력 >> ");
                if(!mr.passwordCheck(newPw)) {
                    System.out.println("📢 비밀번호는 4글자 이상 영문, 숫자, . ! 사용 가능합니다.");
                } else break;
            }
            logMember.setPw(newPw);
            MemberRepository.saveFile();
            System.out.println("📢 비밀번호가 변경되었습니다.");

        }
    }

    public void updateAddress() {
        String newAddress;
        while (true) {

            newAddress = input(" * 새 주소를 입력하세요. >> ");
            if (!mr.addressCheck(newAddress)) {
                System.out.println("주소를 잘못 입력하셨습니다.\nex)서울특별시 마포구 공덕동");
            } else {
                logMember.setAddress(newAddress);
                MemberRepository.saveFile();
                break;
            }
        }
        System.out.printf("\n📢 [주소: %s]가 변경되었습니다.\n", newAddress);

    }

    public void showTicketList(List<Ticket> tList) {
        System.out.println("----------------------------------------");
        System.out.printf("        🎫 %s님의 예매 내역\n", logMember.getName());
        System.out.println("---------------------------------------- *");

        if (tList.isEmpty()) {
            System.out.println("       -- 예매내역이 없습니다. --");
        } else {

            List<Ticket> sortedList = tList.stream()
                    .sorted(Comparator.comparing((Ticket t) -> t.getDate()).reversed())
                    .collect(Collectors.toList());

//            System.out.println("No.     공연명   \t|           공연일시          |    좌석   |   가격    |");
//            System.out.println("------------------------------------------------------------------------");
            for (int i = 0; i < sortedList.size(); i++) {
                Ticket t = sortedList.get(i);
                System.out.printf(" %d. %-13s\t| %s | %8s | %8s원|\n", i + 1,
                        ellipsisString(t.getTitle()), t.getDate(), checkSeat(t.getSeat()), t.getPrice());
            }
            System.out.println("\n----------------------------------------");
            logMember.setTicketList(sortedList);
        }
    }

    public void cancelTicket() {
        List<Ticket> myTicketList = logMember.getTicketList();

        showTicketList(myTicketList);

        if (!(myTicketList.isEmpty() || myTicketList == null)) {
            int tNum;
            while (true) {
                String option  = input("\n취소할 티켓 번호 (0: 뒤로가기) >> ");
                try{
                    tNum = Integer.parseInt(option);
                    if (!(tNum > 0 && tNum <= myTicketList.size())) {
                        if (tNum == 0) showMemberInfo();
                        System.out.println("📢 티켓 번호만 입력하세요.");
                    } else {
                        break;
                    }
                }catch (Exception e){
                    System.out.println("📢 티켓 번호만 입력하세요.");
                }
//                tNum = Integer.parseInt(input("\n취소할 티켓 번호 (0: 뒤로가기) >> "));
            }
            Ticket removedTicket = mr.removeTicket(logMember, tNum - 1);
            System.out.printf("\n📢 [%s] 티켓을 취소했습니다.\n", ellipsisString(removedTicket.getTitle()));
            System.out.println();
        } else {
            stopInput();
        }
    }

    public void logOut() {
        System.out.printf("📢 %s님이 로그아웃 하셨습니다.\n\n", logMember.getName());
        setLogMember(null);
        MemberRepository.setLoginMember(null);
        start();
    }

    // 공연명 말줄임표
    public String ellipsisString(String text) {
        int maxLength = 13;
//        if (text.length() >= maxLength) {
//            text = text.substring(0, maxLength) + "...";
//        }
//        return text;

        StringBuilder result = new StringBuilder();
        int currentLength = 0;

        for (char c : text.toCharArray()) {
            int charLength = Character.charCount(c) == 1 ? 1 : 2; // 영문인 경우 1, 한글인 경우 2
            if (currentLength + charLength <= maxLength) {
                result.append(c);
                currentLength += charLength;
            } else {
                break;
            }
        }

        if (currentLength >= maxLength) {
            // 말줄임 마침표 추가
            result.setLength(maxLength - 1);
            result.append("...");
        }

        return result.toString();

    }
    // 좌석 체크
    public String checkSeat(String seat) {
        if(seat == null) {
            seat = "-";
        }
        return seat;
    }


}

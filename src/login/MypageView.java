package src.login;

import src.MainView;
import java.util.List;
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
        myMenu:
        while (true) {
            System.out.println("\n----------------------------");
            System.out.println("         마이 페이지");
            System.out.println("---------------------------- *");
            System.out.printf("  * 이름: %s\n", this.logMember.getName());
            System.out.printf("  * 이메일: %s\n", this.logMember.getEmail());
            System.out.printf("  * 나이: %d\n", this.logMember.getAge());
            System.out.printf("  * 주소: %s\n", this.logMember.getAddress());
            System.out.printf("  * 포인트: %d\n", this.logMember.getPoint());
            System.out.printf("  * 예매내역: %d\n", this.logMember.getPoint());


            while (true) {
                System.out.println("\n 1️⃣ 비밀번호 수정 | 2️⃣ 주소 수정 | 3️⃣ 예매내역 취소 | 4️⃣ 로그아웃 | 0️⃣ 뒤로가기");
                String menuOpt = input(">> ");
                switch (menuOpt) {
                    case "1":
                        updatePw();
                        break;
                    case "2":
                        updateAddress();
                        break;
                    case "3":
                        cancelTicket();
                        break;
                    case "4":
                        logOut();
                        break;
                    case "0":
                        MainView mainView = new MainView();
                        break myMenu;
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
                String oldPw = input("\n * 기존 비밀번호 입력 >> ");
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
            String newPw = null;
            while (true) {
                newPw = input("\n * 새 비밀번호 입력 >> ");
                if(!mr.passwordCheck(newPw)) {
                    System.out.println("📢 비밀번호는 특수문자 ., ! 사용 가능합니다.");
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

            newAddress = input("\n * 새 주소를 입력하세요. >>");
            if (!mr.addressCheck(newAddress)) {
                System.out.println("주소를 잘 못 입력하셨습니다.\nex)서울특별시 마포구 공덕동");
            } else {
                logMember.setAddress(newAddress);
                MemberRepository.saveFile();
                break;
            }
        }
        System.out.printf("\n📢 [주소: %s]가 변경되었습니다.\n", newAddress);

    }

    public void showTicketList(List<Ticket> tList) {
        System.out.println("----------------------------");
        System.out.printf("      %s님의 예매 내역\n", logMember.getName());
        System.out.println("---------------------------- *");

        if (tList.isEmpty()) {
            System.out.println("\t  예매내역이 없습니다.");
        } else {
            for (int i = 0; i < tList.size(); i++) {
                Ticket t = tList.get(i);
                System.out.printf("  %d. %s", i + 1, t.toString());
            }
        }

    }

    public void cancelTicket() {
        List<Ticket> myTicketList = logMember.getTicketList();

        showTicketList(myTicketList);

        if (!myTicketList.isEmpty()) {
            int tNum;
            while (true) {
                tNum = Integer.parseInt(input("취소할 티켓 번호 (0: 뒤로가기) >> "));

                if (!(tNum > 0 && tNum < myTicketList.size())) {
                    System.out.println("📢 티켓 번호만 입력하세요.");
                } else {
                    if (tNum == 0) showMemberInfo();
                    break;
                }
            }
            mr.removeTicket(logMember, tNum);
        } else {
            stopInput();
        }
    }

    public void logOut() {
        System.out.printf("\n📢 %s님이 로그아웃 하셨습니다.\n", logMember.getName());
        setLogMember(null);
        MemberRepository.setLoginMember(null);
        MainView main = new MainView();
        main.start();
    }
}

package src.login;

import src.SimpleInput;

import java.util.List;

import static src.SimpleInput.*;

public class MypageView {

    private Member logMember;
    private MemberRepository mr = new MemberRepository();

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
        System.out.println("----------------------------");
        System.out.println("         마이 페이지");
        System.out.println("---------------------------- *");
        System.out.printf("  * 이름: %s\n", this.logMember.getName());
        System.out.printf("  * 이메일: %s\n", this.logMember.getEmail());
        System.out.printf("  * 나이: %d\n", this.logMember.getAge());
        System.out.printf("  * 주소: %s\n", this.logMember.getAddress());
        System.out.printf("  * 포인트: %d\n", this.logMember.getPoint());
        System.out.printf("  * 예매내역: %d\n", this.logMember.getPoint());

        System.out.println("\n 1️⃣ 비밀번호 수정 | 2️⃣ 주소 수정 | 3️⃣ 예매내역 취소 | 0️⃣ 뒤로가기");
        String menuOpt = input(">> ");
        switch (menuOpt) {
            case "1":
                updatePw();
                break;
            case "2":
                break;
            case "3":
                cancelTicket();
                break;
            case "0":
                break;
            default:
                System.out.println("📢 메뉴 번호만 입력해주세요");
        }
    }

    public void updatePw() {
        while (true) {
            String oldPw = input(" * 기존 비밀번호 입력 >> ");
            if(!logMember.getPw().equals(oldPw)) {
                System.out.println("📢 비밀번호가 일치하지 않습니다.");
            } else break;
        }
        String newPw = null;
        while (true) {
            newPw = input(" * 새 비밀번호 입력 >> ");
            if(!mr.passwordCheck(newPw)) {
                System.out.println("📢 비밀번호는 특수문자 ., ! 사용 가능합니다.");
            } else break;
        }
        logMember.setPw(newPw);
        System.out.println("📢 비밀번호가 변경되었습니다.");
    }
    public void showTicketList(List<Ticket> tList) {
        System.out.println("----------------------------");
        System.out.printf("      %s님의 예매 내역\n", logMember.getName());
        System.out.println("---------------------------- *");

        if(tList.isEmpty()) {
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

        if(!myTicketList.isEmpty()) {
            String tNum = null;
            while (true) {
                tNum = input("취소할 티켓 번호 >> ");
                if(!(Integer.parseInt(tNum) > 0 && Integer.parseInt(tNum) < myTicketList.size())) {
                    System.out.println("📢 티켓 번호만 입력하세요.");
                } else break;
            }
            Ticket removed = myTicketList.remove(Integer.parseInt(tNum) -1);
            logMember.setTicketList(myTicketList);
        } else {
            stopInput();
        }

    }
}

package src.login;

public class MypageView {

    private Member logMember;

    public MypageView(Member member) {
        this.logMember = member;
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
        System.out.printf("  * 포인트: $d\n", this.logMember.getPoint());
        System.out.printf("  * 예매내역: $d\n", this.logMember.getPoint());
    }
}

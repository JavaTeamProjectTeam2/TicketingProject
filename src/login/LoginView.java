package src.login;

import src.SimpleInput;
import src.login.MemberRepository.LogState;

public class LoginView {
    private static MemberRepository mr;

    public static void main(String[] args) {

        MemberRepository mr = new MemberRepository();
        MemberRepository.loadFile();

        System.out.println("=====");
        String email = SimpleInput.input("이메일 >> ");
        String password = SimpleInput.input("비밀번호 >> ");

        LogState state;
        state = mr.checkLogIn(email, password);

        if(state == LogState.LOG_IN) {
            System.out.println("로그인 성공하셨습니다.");
        } else if(state == LogState.WRONG_ID) {
            System.out.println("회원이 아니거나 아이디가 잘못 되었습니다.");
        } else if(state == LogState.WRONG_PW) {
            System.out.println("잘못된 비밀번호 입니다.");
        }

        // 현재 디렉토리
        System.out.println(System.getProperty("user.dir"));
        //E:\backend\java-project\TicketingProject

        // 아이디 찾기
        // 비번 찾기
        System.out.println("비밀번호 찾기 - 이메일을 입력하세요");
        String inputEmail = SimpleInput.input("이메일 >> ");

        Member foundMember = mr.findMember(inputEmail);
        if(foundMember != null) {
            System.out.printf("\n이메일: %s\t비밀번호: %s",foundMember.getEmail(), foundMember.getPw());
        } else {
            System.out.println("존재하지 않는 이메일입니다.");
            mr.makeRandomCode();
        }

    }
}

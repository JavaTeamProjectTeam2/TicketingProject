package src.login;

import src.login.MemberRepository.LogState;

import static src.SimpleInput.*;

public class LoginView {
    private static MemberRepository mr = new MemberRepository();

    public void showLogIn() {

        MemberRepository.loadFile();

        System.out.println("----------------------------");
        System.out.println("           로그인");
        System.out.println("---------------------------- *");

        String email = input("이메일 >> ");
        String password = input("비밀번호 >> ");

        LogState state = mr.checkLogIn(email, password);

        if (state == LogState.LOG_IN) {
            Member loggedMember = mr.findMember(email);
            System.out.printf("\n✨ %s님 환영합니다 ✨\n", loggedMember.getName());
        } else if (state == LogState.WRONG_ID) {
            System.out.println("회원이 아니거나 아이디가 잘못 되었습니다.");
        } else if (state == LogState.WRONG_PW) {
            System.out.println("잘못된 비밀번호입니다.");
        }
    }
    public void showFindPwView() {

        // 아이디 찾기
        // 비번 찾기
        System.out.println("----------------------------");
        System.out.println("           비밀번호 찾기");
        System.out.println("---------------------------- *");
        String inputEmail = input("이메일 >> ");

        checkCodeView(inputEmail);

    }
    public void checkCodeView(String inputEmail) {

        Member foundMember = mr.findMember(inputEmail);
        if(foundMember != null) {
            //
            while (true) {
                System.out.println("\t\t...");
                System.out.println("이메일로 인증코드를 보냈습니다\n");
                String enter = input("이메일로 이동하기(Enter)");

                String code = mr.makeRandomCode();
                System.out.println("----------------------------");
                System.out.println("           [" + inputEmail+"]");
                System.out.println("\t인증코드 | " + code);
                System.out.println("---------------------------- *");

                String inputCode = input("인증코드 >> ");

                if(code.equals(inputCode)) {
                    System.out.printf("\n%s님의 이메일: %s\t비밀번호: %s",foundMember.getName(), foundMember.getEmail(), foundMember.getPw());
                    break;
                }
            }

        } else {
            System.out.println("존재하지 않는 이메일입니다.");
        }

    }
}

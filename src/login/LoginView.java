package src.login;

import src.MainView;
import src.login.MemberRepository.LogState;

import static src.SimpleInput.*;

public class LoginView {
    private static final MemberRepository mr = MemberRepository.getInstance();
    private Member logMember;
    private LoginManager loginManager = new LoginManager();
    private MainView mainView = new MainView();

    public Member getLogMember() {
        return mr.getLoginMember();
    }


    public void setLogMember(Member logMember) {
        this.logMember = logMember;
    }

    public void showLogIn() {

        MemberRepository.loadFile();

        System.out.println("----------------------------");
        System.out.println("           로그인");
        System.out.println("---------------------------- *");


        int loginCount = 0;

        login:while (loginCount < 4) {
            if(loginCount > 0) {
                System.out.printf("\n📢 로그인 시도 %d회 (총 3회)\n", loginCount);
            }
            String email;
            while (true) {
                if(loginCount == 0) {
                    System.out.println("📢 이메일 전부 입력해주세요. (ex. xxx@xxx)");
                }
                email = input("이메일 >> ");
                if(!mr.emailCheck(email)) {
                    System.out.println("📢 이메일 형식이 아닙니다. 다시 입력해주세요. (ex. xxx@xxx)");
                } else {
                    // 이메일 형식은 일치한 상태
                    if(mr.findMember(email) == null) {
                        System.out.println("존재하지 않는 회원입니다.");
                        continue;
                    }
                    // 로그인 불가 상태면 남은 시간 출력
                    if(!loginManager.isLoginEnabled(mr.findMember(email))) {
                        loginManager.leftTime(mr.findMember(email));
                        mainView.start();
                    } else {
                        break;
                    }
                }
            }

            pw: while (true) {
                String password = input("비밀번호 >> ");


                LogState state = mr.checkLogIn(email, password);

                if (state == LogState.LOG_IN) {
                    Member loggedMember = mr.findMember(email);
                    System.out.printf("\n\t✨ %s님 환영합니다 ✨\n\n", loggedMember.getName());

                    logMember = loggedMember;
                    mr.setLoginMember(loggedMember);
                    break login;

                } else if (state == LogState.WRONG_ID) {
                    System.out.println("아이디를 찾을 수 없습니다.");
                    loginCount++;
                } else if (state == LogState.WRONG_PW) {
                    System.out.println("비밀번호가 일치하지 않습니다.");
                    loginCount++;
                    if(loginCount == 3) {
                        System.out.println("📢 로그인 시도 횟수를 초과했습니다.");
                        loginManager.disableLogin(mr.findMember(email));
                        break login;
                    }
                }
            }

        }
    }
    public void showFindPwView() {

        // 아이디 찾기
        // 비번 찾기
        System.out.println("----------------------------");
        System.out.println("        비밀번호 찾기");
        System.out.println("---------------------------- *");

        while (true) {
            String inputEmail = input("이메일 >> ");

            Member foundMember = mr.findMember(inputEmail);
            if(foundMember != null) {
                checkCodeView(foundMember);
                break;
            } else {
                System.out.println("존재하지 않는 이메일입니다.");
            }
        }

    }
    public void checkCodeView(Member member) {

        code: while (true) {
            System.out.println("\n\t loading ...\n");
            System.out.println("📢 이메일로 인증코드를 보냈습니다");
            String input = input("이메일로 이동(Enter) >> ");

            String code = mr.makeRandomCode();
            System.out.println("----------------------------");
            System.out.println("\t📧" + member.getEmail());
            System.out.println("\t인증코드 [ " + code +" ]");
            System.out.println("---------------------------- *");

            check: while (true) {
                String inputCode = null;
                while (true) {
                    inputCode = input("인증코드 >> ");
                    if(inputCode.length() != 6 || !inputCode.matches("\\d{6}")) {
                        System.out.println("\n📢 인증코드는 6자리 숫자입니다.");
                        continue check;
                    } else break;
                }

                if(code.equals(inputCode)) {
                    System.out.printf("\n📢 %s님의 이메일: %s | 비밀번호: %s\n",member.getName(), member.getEmail(), member.getPw());
                    break code;
                } else {
                    System.out.println("\n📢 인증 코드가 일치하지 않습니다.");

                    while (true) {
                        System.out.println("1️⃣ 인증코드 다시 입력 | 2️⃣ 인증코드 새로 받기 | 0️⃣ 뒤로가기");
                        String inputOpt = input(">> ");
                        if(inputOpt.equals("1")) continue check;
                        else if(inputOpt.equals("2")) {
                            continue code;
                        } else if(inputOpt.equals("0")) {
                            System.out.println("📢 메인메뉴로 이동합니다.");
                            MainView mainView = new MainView();
                            mainView.start();
                        } else {
                            System.out.println("\n📢 메뉴 숫자만 입력해주세요. (1, 2, 0 중 하나)");
                        }
                    }
                }
            }
        }

    }
}

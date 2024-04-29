package src.login;


import static src.SimpleInput.input;

public class MemberView {

    MemberRepository repository = new MemberRepository();
    LoginView loginView = new LoginView();

    public void showLoginMenu() {

        while (true) {

            // 현재 디렉토리
            System.out.println(System.getProperty("user.dir"));
            //E:\backend\java-project\TicketingProject

            MemberRepository.loadFile();

            System.out.println("----------------------------");
            System.out.println("\t1️⃣ 로그인");
            System.out.println("\t2️⃣ 회원 가입");
            System.out.println("\t3️️⃣ 아이디 찾기");
            System.out.println("\t4️⃣ 비번 찾기");
            System.out.println("\t9️⃣ 프로그램 종료");
            System.out.println("\t0️⃣ 뒤로 가기");
            System.out.println("---------------------------- *");

            String opt = input(">> ");

            switch (opt) {
                case "1":
                    loginView.showLogIn();
                    break;
                case "2":
                    join();
                    break;
                case "3":
                    break;
                case "4":
                    loginView.showFindPwView();
                    break;
                case "9":
                    System.out.println("프로그램을 종료합니다.");
                    System.exit(0);
                default:
                    System.out.println("숫자만 입력해주세요.");
            }
        }

    }



    public void join() {

        while (true) {
            System.out.println("----------------------------");
            System.out.println("           회원가입");
            System.out.println("---------------------------- *");
            String name = input("# 이름: ");
            if (name.isEmpty()) {
                System.out.println("이름 입력 부탁드립니다.");
                continue;
            } else if (!(name.matches("^[a-zA-Z가-힣]*$"))) {
                System.out.println("올바른 이름 입력 부탁드립니다.");
                continue;
            }
            String id;
            while (true) {
                id = input("# 이메일 : ");
                if (id.isEmpty()) {
                    System.out.println("이메일 입력 부탁드립니다.");
                    continue;
                }
                if (repository.containsId(id)) {
                    System.out.println("중복되는 이메일입니다. 다시 입력 바랍니다");
                } else if (!(repository.emailCheck(id))) {
                    System.out.println("유효한 이메일이 아닙니다. 다시 입력 바랍니다");
                } else break;
            }
            String pw;
            while (true) {
                pw = input("# 비밀번호 (영문 숫자 . !)\n: ");
                if (pw.isEmpty()) {
                    System.out.println("비밀번호 입력 부탁드립니다.");
                    continue;
                }
                if (!repository.passwordCheck(pw)) {
                    System.out.println("영문 숫자 . ! 만 가능합니다. 다시 입력 바랍니다");
                } else break;
            }
            Integer age = Integer.valueOf(input("# 나이: "));

            String adderess = input("# 주소: ");


            repository.addNewMember(new Member(name, id, pw, age, adderess));

            System.out.printf("** %s님 회원가입이 완료되었습니다 **\n", name);

            break;

        }
//        repository.saveTxt();


    }

}// class end


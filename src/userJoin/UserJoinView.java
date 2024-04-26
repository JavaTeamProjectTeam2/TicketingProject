package src.userJoin;

import java.util.ArrayList;
import java.util.List;

import static src.SimpleInput.*;

public class UserJoinView {

    UserJoinRepository repository = new UserJoinRepository();


    public void join() {

        repository.getUserList().add(new User("이예진", "123@naver.com", "1234", 19, "포항"));
        repository.getUserList().add(new User("이지효", "1234@gmail.com", "1234", 22, "서울"));
        repository.getUserList().add(new User("송나래", "12345@naver.com", "1234", 22, "대구"));
        repository.getUserList().add(new User("서정인", "123456@gmail.com", "1234", 26, "울산"));


        while (true) {
            System.out.println("----------------------------");
            System.out.println("           회원가입");
            System.out.println("---------------------------- *");
            String name = input("# 이름: ");
            if (name.equals("")) {
                System.out.println("이름 입력 부탁드립니다.");
                continue;
            } else if (!(name.matches("^[a-zA-Z가-힣]*$"))) {
                System.out.println("올바른 이름 입력 부탁드립니다.");
                continue;
            }
            String id = null;
            while (true) {
                id = input("# 이메일 : ");
                if (id.equals("")) {
                    System.out.println("이메일 입력 부탁드립니다.");
                    continue;
                }
                if (repository.containsId(id)) {
                    System.out.println("중복되는 이메일입니다. 다시 입력 바랍니다");
                } else if (!(repository.emailCheck(id))) {
                    System.out.println("유효한 이메일이 아닙니다. 다시 입력 바랍니다");
                } else break;
            }
            String pw = null;
            while (true) {
                pw = input("# 비밀번호 (영문 숫자 . !)\n: ");
                if (pw.equals("")) {
                    System.out.println("비밀번호 입력 부탁드립니다.");
                    continue;
                }
                if (!repository.passwordCheck(pw)) {
                    System.out.println("영문 숫자 . ! 만 가능합니다. 다시 입력 바랍니다");
                } else break;
            }
            Integer age = Integer.valueOf(input("# 나이: "));

            String adderss = input("# 주소: ");


            repository.saveUser(name, id, pw, age, adderss);

            System.out.printf("** %s님 회원가입이 완료되었습니다 **\n", name);

            break;

        }
//        repository.saveTxt();


    }

}// class end


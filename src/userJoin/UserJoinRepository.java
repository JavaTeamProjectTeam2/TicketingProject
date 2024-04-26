package src.userJoin;

import src.memberView;


import java.util.ArrayList;

import java.util.List;
import java.util.regex.Pattern;


public class UserJoinRepository {

    private List<User> userList;

    public List<User> getUserList() {
        return userList;
    }

    public UserJoinRepository() {
        this.userList = new ArrayList<>();
    }


    //정보 .txt 세이브 위치 경로
    //public static final String SAVE_PATH = memberView.ROOT_PATH + "/userJoin/userList/userJoinList.txt";



    //기존 회원 생성


    //회원 로그인
    public void saveUser(String name, String id, String pw, Integer age, String adderss) {
        if (!containsId(id)) {
            userList.add(new User(name, id, pw, age, adderss));
            //System.out.println(userList);
        }
    }

    //아이디 중복확인
    public boolean containsId(String id) {
        for (User user : userList) {
            if (user.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }


    //아이디 유효한지 확인
    public boolean emailCheck(String id) {

        return Pattern.matches("^[a-z0-9A-Z._-]*@[a-z0-9A-Z]*.[a-zA-Z.]*$", id);
    }

    //비번확인
    public boolean passwordCheck(String pw) {
        String message = "^[a-zA-Z0-9.!]*$";
        if(pw.matches(message)) {
            return true;
        }
        return false;
    }

//    public boolean address(){
//
//    }
}





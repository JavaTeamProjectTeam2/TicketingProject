package src.login;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class MemberRepository {

    private static MemberRepository instance;
    private static List<Member> members;
    private static Member loginMember;

    private MemberRepository() {
//        members = new ArrayList<>();
//        members.add(new Member("김김김","aaa@gmail.com", "1234", 12, "서울시 마포구"));
//        members.add(new Member("박박박","bbb@gmail.com", "5678", 20, "서울시 강남구"));
//        members.add(new Member("이이이","ccc@gmail.com","9876", 30, "서울시 동작구"));
//        members.add(new Member("홍홍홍","ddd@gmail.com", "5432", 40, "서울시 종로구"));
//        saveFile();
    }
    public static MemberRepository getInstance() {
        if(instance == null) {
            instance = new MemberRepository();
        }
        return  instance;
    }

    public static List<Member> getMembers() {
        return members;
    }

    public static void setMembers(List<Member> members) {
        MemberRepository.members = members;
    }

    public static Member getLoginMember() {
        return loginMember;
    }

    public static void setLoginMember(Member loginMember) {
        MemberRepository.loginMember = loginMember;
    }

    enum LogState {
        LOG_IN, WRONG_PW, WRONG_ID
    }
    enum PATH {
        MEMBER(System.getProperty("user.dir") + "/file/member.dat"),
        TICKET(System.getProperty("user.dir") + "/file/ticket.dat");

        private String desc;

        PATH(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }
    }


    public void addNewMember(Member newMember) {

        members.add(newMember);

    }

    // 멤버 리스트를 파일에 저장
    public static void saveFile() {
        List<Member> members = getMembers();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PATH.MEMBER.getDesc()))) {
            oos.writeObject(members);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void loadFile() {

        List<Member> loadedMembers = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PATH.MEMBER.getDesc()))) {
            loadedMembers = (List<Member>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        setMembers(loadedMembers);
    }

    protected LogState checkLogIn(String email, String password) {

        for(Member m : members) {
            if(m.getEmail().equals(email)) {
                if(m.getPw().equals(password)) {
                    return LogState.LOG_IN;
                } else {
                    return LogState.WRONG_PW;
                }
            } else {
                return LogState.WRONG_ID;
            }
        }
        return LogState.WRONG_ID;
    }

    /**
     * 이메일로 해당 멤버 객체 가져오기
     * @param email
     * @return email을 가진 Member 객체
     */
    public Member findMember(String email) {

        List<Member> loggedMember = members.stream()
                .filter(m -> m.getEmail().equals(email))
                .collect(Collectors.toList());
        if(loggedMember.isEmpty()) {
            return null;
        }
        return loggedMember.get(0);
        // 만약 없는 멤버일 때 어떻게 할 지 생각해보자....
    }

    public String makeRandomCode() {

        String code ="";
        for (int i = 0; i < 6; i++) {
            code += "" + (int) (Math.random() * 10);
        }
        return code;
    }

    //회원 로그인
    public void saveUser(String name, String id, String pw, Integer age, String address) {
        if (!containsId(id)) {
            members.add(new Member(name, id, pw, age, address));
            //System.out.println(userList);
        }
    }

    //아이디 중복확인
    public boolean containsId(String id) {
        for (Member m : members) {
            if (m.getEmail().equals(id)) {
                return true;
            }
        }
        return false;
    }


    //아이디 유효한지 확인
    public boolean emailCheck(String id) {

//        return Pattern.matches("^[a-z0-9A-Z._-]*@[a-z0-9A-Z]*.[a-zA-Z.]*$", id);
        return Pattern.matches("^[a-z0-9A-Z._-]*@[a-z0-9A-Z]*\\.[a-zA-Z]+(\\.[a-zA-Z]+)*$", id);
    }

    //비번확인
    public boolean passwordCheck(String pw) {
        String message = "^[a-zA-Z0-9.!]*$";
        if(pw.matches(message)) {
            return true;
        }
        return false;
    }
    public boolean ageCheck(Integer age) {
        if (300 > age && age > 0) {
            return true;
        }
        return false;
    }

    public boolean addressCheck(String address){
        String addressInputCheck = "^[가-힣\\s]+(시|군|구|읍|면|동|리)\\s?[0-9가-힣\\s]+$";
        return address.matches(addressInputCheck);
    }
}

package src.login;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MemberRepository {

    static List<Member> members;
    Member member;

    public MemberRepository() {
        this.members = new ArrayList<>();
        members.add(new Member("aaa@gmail.com", "1234"));
        members.add(new Member("bbb@gmail.com", "5678"));
        members.add(new Member("ccc@gmail.com","9876"));
        members.add(new Member("ddd@gmail.com", "5432"));
        saveFile(this.members);
    }

    static enum LogState {
        LOG_IN, WRONG_PW, WRONG_ID
    }
    static enum PATH {
        MEMBER(System.getProperty("user.dir") + "/file/member.txt");

        private String desc;

        PATH(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }
    }


    void addNewMember(Member newMember) {

        members.add(newMember);

        saveFile(newMember);
    }

    // 멤버 1명을 파일에 저장
    public static void saveFile(Member newMember) {

        try(FileWriter fw = new FileWriter(PATH.MEMBER.getDesc(), true)) {
            String newMemberInfo = String.format("%s,%s\n"
                    , newMember.getEmail(), newMember.getPw());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // 멤버 리스트를 파일에 저장
    public static void saveFile(List<Member> members) {

        try(FileWriter fw = new FileWriter(PATH.MEMBER.getDesc())) {
            for(Member m: members) {
                String mInfo = String.format("%s,%s\n"
                    , m.getEmail(), m.getPw());
                fw.write(mInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void loadFile() {

        try (FileReader fr = new FileReader(PATH.MEMBER.getDesc())) {
            BufferedReader br = new BufferedReader(fr);

            while (true) {
                String s = br.readLine();

                if (s == null) break;

                String[] split = s.split(",");

                Member member = new Member(
                        split[0],
                        split[1]
                );
                members.add(member);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public LogState checkLogIn(String email, String password) {

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
        if(loggedMember.size() == 0) {
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
        System.out.println("code = " + code);
        return code;
    }
}

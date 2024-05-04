package src.login;


public class LoginManager {
//    private boolean loginEnabled = true; // 로그인 가능 여부
    private final long DISABLE_DURATION = 30 * 1000; // 로그인 불가능 기간 (2분)
    private long lastDisableTime = 0; // 마지막으로 로그인이 불가능해진 시간
    private MemberRepository mr = MemberRepository.getInstance();

    // 로그인 불가능한 상태로 설정
    public void disableLogin(Member member) {
        member.setLoginEnabled(false);
        lastDisableTime = System.currentTimeMillis(); // 현재 시간 설정
        member.setLastDisabledTime(lastDisableTime);

        System.out.printf("📢 보안을 위해 %d초 동안 로그인이 제한됩니다. 잠시 후 다시 시도해주세요.\n", DISABLE_DURATION /1000);
        MemberRepository.saveFile();
    }

    // 로그인 가능한 상태로 설정
    public void enableLogin(Member member) {
        member.setLoginEnabled(true);
        member.setLastDisabledTime(0);
        MemberRepository.saveFile();
    }

    // 로그인 가능 여부 반환
    public boolean isLoginEnabled(Member member) {

        if (!member.isLoginEnabled()) {
            // 현재 시간과 마지막으로 로그인이 불가능해진 시간을 비교하여 로그인 가능 여부를 판단
            long currentTime = System.currentTimeMillis();
            lastDisableTime = member.getLastDisabledTime();
            if (currentTime - lastDisableTime >= DISABLE_DURATION) {
                enableLogin(member); // 일정 시간이 지나면 로그인 가능하도록 설정
                MemberRepository.saveFile();
            }
        }
        return member.isLoginEnabled();
    }
    // 로그인 잠금 남은 시간 출력
    public void leftTime(Member member) {
        if(!member.isLoginEnabled()) {
            long currentTime = System.currentTimeMillis();
            long currLeftTime = (DISABLE_DURATION - (currentTime - lastDisableTime)) / 1000;
            System.out.printf("📢 로그인 잠금 상태입니다. %d초 뒤에 다시 로그인 해주세요.\n\n", (int)currLeftTime);
        }

    }
}


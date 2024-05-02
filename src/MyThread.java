package src;

public class MyThread extends Thread{
    public void run() {
        // 스레드가 실행할 작업을 이 메서드에 작성합니다.
        for (int i = 0; i < 3; i++) {
            try {
                System.out.println("."); // 점 출력
                Thread.sleep(500); // 1초 동안 대기
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

//    public static void main(String[] args) {
////        MyThread thread = new MyThread(); // 스레드 객체 생성
////        thread.start(); // 스레드 시작
//    }
}

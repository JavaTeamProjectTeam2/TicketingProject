package src;

import java.util.Objects;

import static src.BookingView.thread;

public class MyThread extends Thread{
    private String text;

    public MyThread(String text) {
        this.text = text;
    }

    @Override
    public void run() {
        // 스레드가 실행할 작업을 이 메서드에 작성합니다.
        if(Objects.equals(text, "")){
            simpleRun();
        }else{
            runWtext(text);
        }
    }

    private void simpleRun() {
        for (int i = 0; i < 3; i++) {
            try {
                System.out.println("."); // 점 출력
                Thread.sleep(500); // 1초 동안 대기
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void runWtext(String s){
        try {
            System.out.println(" ");
            System.out.printf(". . . %s  . . .\n", s); // 점 출력
            System.out.println(" ");
            Thread.sleep(1000); // 1초 동안 대기
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
////        MyThread thread = new MyThread(); // 스레드 객체 생성
////        thread.start(); // 스레드 시작
//    }
}

package src;
import java.util.Scanner;

public class SimpleInput {
        //문자열 입력을 처리
        static Scanner sc;

        static {
            sc = new Scanner(System.in);
        }

        public static String input(String message){
            System.out.print(message);
            return sc.nextLine();
        }

        public void stopInput(){
            System.out.println("\n===========엔터를 누르면 계속…==============");
            sc.nextLine();
        }
    }

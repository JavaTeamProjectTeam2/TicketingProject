package src;

import src.login.Member;
import src.login.MemberRepository;
import src.login.Ticket;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static src.AgeRating.*;
import static src.BookingView.*;
import static src.Category.*;
import static src.SimpleInput.input;
import static src.SimpleInput.sc;

public class BookingRepository {
    //예매 시작
    public static void allSelectedBooking(Perform perform, Member member, Map<String, Integer> party, String section, LocalDateTime selectedShowTime) {
        // 나이 제한에 걸리는 사람 있는지 확인 후 예매 시작
        Ticket ticket;
        if(checkAge(perform, party)){
            if (perform.getCategory().equals(CONCERT)){
                concertBooking(perform, member, party, selectedShowTime);
            }else{
//                ticket = performBooking(perform, member, party, section, selectedShowTime);
                performBooking(perform, member, party, section, selectedShowTime);
            }
        }else{
            System.out.println("🚨나이제한으로 예매 불가능합니다 👮");
            System.out.println("🏠 초기화면으로 이동합니다 🏡");
            if (!thread.isAlive()) {
                thread = new MyThread("초기 화면"); // 새로운 스레드 객체 생성
                thread.start(); // 스레드 시작
            }
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //공연 조회 화면으로 이동
            PerformView.getTicket();
        }
    }

    //관람등급 확인
    private static boolean checkAge(Perform perform, Map<String, Integer> party) {
        //작품 관람등급
        int contentAge= perform.getAge();
        //고른 공연이 14세 이상이고, 사용자 일행에 미취학아동이 있을경우 제한
        if(contentAge >= FOURTEEN.getAge() && party.get(SEVEN.getAgeOption()) > 0){
            System.out.println("<"+perform.getTitle()+">"+"은/는 미취학아동이 일행에 포함되어 관람할 수 없습니다.");
            System.out.println("다른 공연/전시를 선택해주세요!!");
            return false;
        }
        if(contentAge == ALL.getAge()){
            return true;
        }
        return true;
    }


    // 이거 가져다 쓰면 돼요!!
    private static Ticket updateTicket(Ticket ticket){
        if(ticket == null){
//            System.out.println("예매 실패, 예매 내역 없음");
        }else{
//            System.out.println(ticket.toString());
        }
        return ticket;
    }
    //콘서트 제외한 공연들 예매
    private static void performBooking(Perform perform, Member member, Map<String, Integer> party, String section, LocalDateTime selectedShowTime) {
        Ticket ticket;
        Map<String, Integer> totalPrice = getPerformPrice(perform, member, party, section);
        boolean goThrough = payTicket(totalPrice, member.getName());
        if(goThrough){
            ticket = new Ticket(perform.getTitle(), convertFormatDate(selectedShowTime) , section, totalPrice.get("totalPrice"));
            System.out.printf("🎟️ %s님 <%s> %d매 예매되었습니다.\n",member.getName(), perform.getTitle(), totalPrice.get("ticketCount"));
            System.out.println("🎟 예매내역은 마이페이지에서 조회 가능합니다.");
            MemberRepository.addTicket(member, ticket);
            member.setPoint(totalPrice.get("totalPrice"));
        }else{
            System.out.printf("❌ %s님 결제 미진행으로 예매 실패했습니다.",member.getName());
            ticket = null;
        }
        updateTicket(ticket);
        waitForEnter();
        MainView.start();
    }

    private static boolean payTicket(Map<String, Integer> totalPrice, String name) {
        //thread
        boolean flag = false;
        while (true) {
            if (!thread.isAlive()) {
                thread = new MyThread("결제 금액 산출 중"); // 새로운 스레드 객체 생성
                thread.start(); // 스레드 시작
            }
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("🧾 " + name + "님의 총 결제 금액은: ₩" + totalPrice.get("totalPrice") + " 입니다");
            System.out.println("💰 결제 수단을 선택해주세요");
            System.out.println("------------------------------------");
            System.out.println("\t1️⃣ 카드\n\t2️⃣ 무통장입금\n\t3️⃣ 현장결제  \n\t️0️⃣ 뒤로가기");
            System.out.println("====================================");
//            System.out.print(">> ");
//            int choice = Integer.parseInt(sc.nextLine());
            String choice= input(">> ");
            switch (choice) {
                case "1":
                    System.out.println("💳 카드결제");
                    int count = 3;
                    while (count > 0) {
                        System.out.printf("👮 결제시도 남은 횟수: %d\n",count);
                        count --;
                        String cardNo = input("💳 카드번호를 입력해주세요(13자리): ");
//                        String cardNo = sc.nextLine();
                        if (cardNo.length() < 13 || !isNumber(cardNo)) {
                            System.out.println("🚨 카드번호 입력 오류 (13자리 입력) 🚨");
//                            break;
                        } else {
                            System.out.print("💳 CVC (카드 뒷면 숫자 3자리): ");
                            String cvc = sc.nextLine();
                            if (cvc.length() < 3 || !isNumber(cvc)) {
                                System.out.println("👮‍ CVC 입력오류 (숫자 3자리)");
                                continue;
                            }
                            if (cardNo.length() == 13 && cvc.length() == 3 && isNumber(cardNo) && isNumber(cvc)) {
                                if (!thread.isAlive()) {
                                    thread = new MyThread("카드 정보 조회중"); // 새로운 스레드 객체 생성
                                    thread.start(); // 스레드 시작
                                }
                                try {
                                    thread.join();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                System.out.println("\n👍 결제 완료되었습니다.");
                                flag = true;
                                return flag;
//                                break;
                            } else {
                                if (!thread.isAlive()) {
                                    thread = new MyThread("카드 정보 조회중"); // 새로운 스레드 객체 생성
                                    thread.start(); // 스레드 시작
                                }
                                try {
                                    thread.join();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
//
                            }
                        }
                                System.out.println("\n🚓🚓🚓🚓🚓🚓🚓🚓🚓🚓🚓");
                                System.out.println("🚨 잘못된 카드번호입니다 🚨");
                                System.out.println("👮 처음 화면으로 이동합니다 👮");
                                System.out.println("🚓🚓🚓🚓🚓🚓🚓🚓🚓🚓🚓\n");
                                MainView.start();
                    }

                case "2":
                    System.out.println("🏧 무통장입금");
                    System.out.printf("🏧 1002888000000 (예금주: (주)컴퍼니)로 ₩%d 입금해주세요.\n", totalPrice.get("totalPrice"));
                    System.out.printf("☑️ 입금완료시 '%s' 구매자 이름과 '%d' 입금 금액을 적어주세요.\n", name, totalPrice.get("totalPrice"));
                    System.out.println("## 송금인과 구매자 이름은 동일해야 합니다");
                    System.out.println("-------------------------------------------------------");
//                    boolean flag = false;
    //                System.out.print("🏧 1002888000000 (예금주: (주)컴퍼니) \n");
                    int count2 = 3;
                    while (count2 > 0) {
                        System.out.printf("👮 결제시도 남은 횟수: %d\n",count2);
                        count2--;
                        String accountNo = input("🧑 송금인: ");
                        if(accountNo.equals(name)){
                            String input = input("💸 입금한 금액: ");
                            try{
                                int price = Integer.parseInt(input);
                                if(price == totalPrice.get("totalPrice") && !isNumber(accountNo)) {
                                    if (!thread.isAlive()) {
                                        thread = new MyThread("입금 내역 확인 중"); // 새로운 스레드 객체 생성
                                        thread.start(); // 스레드 시작
                                    }
                                    try {
                                        thread.join();
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    System.out.println("👍 결제 완료되었습니다.");
//                                    payTicket(totalPrice, name);
                                    flag = true;
                                    return flag;
                                }else {
                                    System.out.println("🚨 입금 금액이 티켓 값과 일치하지 않습니다.");
                                }
                            }catch (Exception e){
                                System.out.println("🚨 입금한 금액을 숫자로 입력하세요.");
                            }
                        }else{
                                System.out.println("\n🚨 송금인은 구매자와 동일해아합니다.");
                        }

                    }
                        System.out.println("\n🚨 입금이 확인되지 않습니다.");
                        System.out.println("🏠 초기화면으로 돌아갑니다.\n");
                        MainView.start();


                case "3":
                    System.out.println("🎪 현장결제");
                    System.out.printf("🎪 ₩%d을 현장에서 결제해주세요.\n", totalPrice.get("totalPrice"));
                    System.out.println("☑️ 공연 시작 1시간 전까지 결제되지 않을 시 예매가 취소될 수 있습니다.");
                    System.out.println("✅ 동의한다면 '동의'라고 작성해주세요.");
                    String agree = input(">> ");
                    if(agree.equals("동의") || agree.equalsIgnoreCase("agree")){
                        if (!thread.isAlive()) {
                            thread = new MyThread(""); // 새로운 스레드 객체 생성
                            thread.start(); // 스레드 시작
                        }
                        try {
                            thread.join();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("👍 동의 확인 완료되었습니다.");
                        flag = true;
                        return flag;
//                        break;
//                        return true;
                    }else{
                        System.out.println("🚨 동의하지 않아 예매가 취소됩니다.");
                        flag = false;
                        return flag;
//                        break;
//                        return false;
                    }
    //                break;
                case "0":
                    PerformView.getTicket();
//                    return false;
                    break;
                default:
                    System.out.println("🚨 옵션의 번호를 입력하세요 🚨");
//                    return false;
                    flag = false;
                    return flag;
//                    break;
            }
//            return flag;
        }
    }

    public static boolean isNumber(String s) {
        try {
            // Try parsing the String as an integer
            Integer.parseInt(s);
            return true; // If successful, return true
        } catch (NumberFormatException e1) {
            try {
                // Try parsing the String as a decimal
                Double.parseDouble(s);
                return true; // If successful, return true
            } catch (NumberFormatException e2) {
                return false; // If neither parse is successful, return false
            }
        }
    }

    //콘서트 예매
    private static void concertBooking(Perform perform, Member member, Map<String, Integer> party, LocalDateTime selectedShowTime) {
        Ticket ticket;
        System.out.println("\n========================================================");
        System.out.println("## 예매 가능한 좌석을 입력하세요 (▫️ 빈 좌석만 예매 가능합니다) ##");
        System.out.println("## 한 좌석만 선택 가능합니다.");
        System.out.println("## 입력 형식: 2, 3");
        if (!thread.isAlive()) {
            thread = new MyThread(""); // 새로운 스레드 객체 생성
            thread.start(); // 스레드 시작
        }
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int queue = (int)(Math.random()*1000)+1;
        System.out.println("좌석 로딩 중...");
        System.out.println("대기: "+queue+"번째...");

        if (!thread.isAlive()) {
            thread = new MyThread(""); // 새로운 스레드 객체 생성
            thread.start(); // 스레드 시작
        }
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //좌석 선택
        //랜덤하게 자리 보여주기
        //7x7 matrix
        long localTime1 = System.currentTimeMillis();
        System.out.println("<========================== 🎶 콘서트 예매를 시작합니다 🎸 ==============================>");
        System.out.println("====================================================================================");
        System.out.println("---------------------------------- 🏟️무대 🏟️----------------------------------------");
        System.out.println("====================================================================================");

        //시간 받기 localTime1
        int row1 = (int) (Math.random() * 6) + 1;
        int col1 = (int) (Math.random() * 5) +1;
        int row2 = (int) (Math.random() * 4) + 6; // row2는 6부터 9까지
        int col2 = (int) (Math.random() * 14) + 6; // col2는 11부터 24까지


        System.out.println(row1 + ", " + col1 + " /// " + row2 + ", " + col2);
        for (int i = 1; i < 10 ; i++) {
            System.out.print(i); // 현재 행 출력
            for (int j = 1; j < 20; j++) {
                if((i == row1 && j == col1) || (i == row2 && j == col2)){
                    System.out.print(" ▫️ ");
                }else{
                    System.out.print(" ◾️ ");
                }
            }
            System.out.print("\n");
        }


        System.out.println("## 입력 형식: 2, 3");
        System.out.print(">> ");
        String input = sc.nextLine();
        if (!thread.isAlive()) {
            thread = new MyThread("예매 확인"); // 새로운 스레드 객체 생성
            thread.start(); // 스레드 시작
        }
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 사용자 입력 처리
        int selectedRow = Integer.parseInt(input.split(",")[0].trim()); // 입력에서 행 추출
        int selectedCol = Integer.parseInt(input.split(",")[1].trim()); // 입력에서 열 추출

        // 시간 받기
        long localTime2 = System.currentTimeMillis();

        // 시간 제한 10초 설정
        if (localTime2 - localTime1 < 10000 && (isValidSeat(row1, col1, selectedRow, selectedCol) || isValidSeat(row2, col2, selectedRow, selectedCol)))  {
            System.out.println("🎉🎉 축하합니다 🎉🎉");
            System.out.println("좌석이 성공적으로 선택되었습니다.");
            System.out.println("결제창으로 이동합니다.");
            System.out.println("----------------------------------------");
            Map<String, Integer> totalPrice = getPerformPrice(perform, member, party, null);
//            ticket = new Ticket(perform.getTitle(), selectedShowTime.toString() , "( "+selectedRow+ ", " + selectedCol+" )", totalPrice.get("totalPrice")) ;
//            System.out.println(ticket);


            boolean goThrough = payTicket(totalPrice, member.getName());
            if(goThrough){
                ticket = new Ticket(perform.getTitle(), convertFormatDate(selectedShowTime) , "( "+selectedRow+ ", " + selectedCol+" )", totalPrice.get("totalPrice")) ;
                updateTicket(ticket);
                MemberRepository.addTicket(member, ticket);
                member.setPoint(totalPrice.get("totalPrice"));
                System.out.printf("🎟️ %s님 <%s> %d매 예매되었습니다.\n",member.getName(), perform.getTitle(), totalPrice.get("ticketCount"));
                System.out.println("🎟 예매내역은 마이페이지에서 조회 가능합니다.");

            } else{
                System.out.printf("❌ %s님 결제 미진행으로 예매 실패했습니다.",member.getName());
                ticket = null;
            }
            updateTicket(ticket);
        } else {
            System.out.println("❌ 이미 선택된 좌석입니다 ❌");
            System.out.println(" 😥 티켓팅 실패 😭 다음 콘서트에서 만나요 👋");
            PerformView.getTicket();
            ticket = null;
            updateTicket(ticket);
        }

            waitForEnter();
            MainView.start();
    }

    private static boolean isValidSeat(int row, int col, int selectedRow, int selectedCol) {
        return row == selectedRow && col == selectedCol;
    }

    private static Map<String, Integer> getPerformPrice(Perform perform, Member member, Map<String, Integer> party,String section) {
        //가족, 전시 성인, 아이 가격
        //얼마 내야하는지 알려줌
        Map<String, Integer> ticketInfo = new HashMap<>();
        int totalPrice = 0;
        int ticketCount = party.get(FOURTEEN.getAgeOption()) + party.get(SEVEN.getAgeOption());
        //section이 필요없는 가족, 전시, 콘서트일 때
        if(!(perform.getCategory().equals(MUSICAL))){
            //콘서트
            if(perform.getCategory().equals(CONCERT)){
                totalPrice += 138000*party.get(FOURTEEN.getAgeOption()) + 138000*party.get(SEVEN.getAgeOption());
            }else{
                totalPrice += 55000* party.get(FOURTEEN.getAgeOption()) + 35000*party.get(SEVEN.getAgeOption());
            }
        }else{
            if(section.equalsIgnoreCase("VIP")){
//                totalPrice += 198000 * party.get(FOURTEEN.getAgeOption())
//                        + 198000 * party.get(SEVEN.getAgeOption());
                totalPrice = ticketCount*198000;
            } else if (section.equalsIgnoreCase("R")) {
                totalPrice = ticketCount*154000;
//                return 154000;
            } else if (section.equalsIgnoreCase("S")) {
                totalPrice = ticketCount*132000;
            }else if (section.equalsIgnoreCase("A")) {
                totalPrice = ticketCount*99000;
            }
        }
//        System.out.println("totalPrice: "+totalPrice);
        ticketInfo.put("totalPrice", totalPrice);
        ticketInfo.put("ticketCount", ticketCount);
//        System.out.println(ticketInfo);
        return ticketInfo;
    }

    public static LocalDateTime setSelectedShowTime(LocalDateTime selectedShowTime) {
        return selectedShowTime;
    }

    public static void nonMemberBooking(Perform perform, ArrayList<String> nonMember) {
        if (perform.getAge() > Integer.parseInt(nonMember.get(2))) {
            System.out.println("나이 제한으로 예매 불가능합니다.");
        } else {
            System.out.printf("%s님 <%s>이 예약되었습니다. \n입력하신 번호인 ☎️%s☎️로 상세내역 전달 드립니다.\n",nonMember.get(0), perform.getTitle(),nonMember.get(1));
        }
    }
}

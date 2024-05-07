package src;

import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import java.time.*;

import static java.time.format.DateTimeFormatter.ofPattern;
import static src.AgeRating.*;
import static src.Category.*;
import static src.Category.getCategoryByOption;

public class PerformRepository {
    static Map<String, Perform> performMap = new HashMap<>();
    static {
        performMap.put("SHINee WORLD Ⅵ [PERFECT ILLUMINATION] : SHINee’S BACK",
                new Perform("SHINee WORLD Ⅵ [PERFECT ILLUMINATION] : SHINee’S BACK", CONCERT, "인스파이어 아레나", SEVEN.getAge(), "140분",
                        LocalDateTime.of(2024, 5, 24, 19, 0, 0),
                        LocalDateTime.of(2024, 5, 25, 17, 0, 0),
                        LocalDateTime.of(2024,5,26,16,0,0)));
        performMap.put("2024 송가인 디너 콘서트 ＂효＂",
                new Perform("2024 송가인 디너 콘서트 ＂효＂", CONCERT, "서울 그랜드하얏트호텔 그랜드볼룸", SEVEN.getAge(), "120분",
                        LocalDateTime.of(2024, 5, 8, 18, 0, 0)));
        performMap.put("NCT WISH : SCHOOL of WISH",
                new Perform("NCT WISH : SCHOOL of WISH", CONCERT, "명화라이브홀", SEVEN.getAge(), "90분",
                        LocalDateTime.of(2024, 5, 25, 14, 0, 0),
                        LocalDateTime.of(2024, 5, 25, 19, 0, 0),
                        LocalDateTime.of(2024, 5, 26, 14, 0, 0),
                        LocalDateTime.of(2024, 5, 26, 19, 0, 0)));
        performMap.put("2024 RIIZE FAN－CON ‘RIIZING DAY’ in SEOUL",
                new Perform("2024 RIIZE FAN－CON ‘RIIZING DAY’ in SEOUL", CONCERT, "잠실실내체육관", SEVEN.getAge(), "100분",
                        LocalDateTime.of(2024, 5, 4, 18, 0, 0),
                        LocalDateTime.of(2024, 5, 5, 16, 0, 0)));
        performMap.put("LE SSERAFIM FAN MEETING ‘FEARNADA’ 2024 S/S - SEOUL",
                new Perform("LE SSERAFIM FAN MEETING ‘FEARNADA’ 2024 S/S - SEOUL", CONCERT, "잠실실내체육관", SEVEN.getAge(), "100분",
                        LocalDateTime.of(2024, 5, 11, 18, 0, 0),
                        LocalDateTime.of(2024, 5, 12, 17, 0, 0)));
        performMap.put("시카고",
                new Perform("시카고", MUSICAL, "디큐브 링크아트센터", FOURTEEN.getAge(), "145분",
                        LocalDateTime.of(2024, 6, 7, 19, 30, 0),
                        LocalDateTime.of(2024, 6, 8, 14, 0, 0),
                        LocalDateTime.of(2024, 6, 8, 18, 30, 0),
                        LocalDateTime.of(2024, 6, 9, 14, 0, 0),
                        LocalDateTime.of(2024, 6, 9, 18, 30, 0)));
        performMap.put("헤드윅",
                new Perform("헤드윅", MUSICAL, "샤롯데씨어터", FOURTEEN.getAge(), "135분",
                        LocalDateTime.of(2024, 5, 10, 19, 30, 0),
                        LocalDateTime.of(2024, 5, 11, 14, 0, 0),
                        LocalDateTime.of(2024, 5, 11, 18, 30, 0),
                        LocalDateTime.of(2024, 5, 12, 14, 0, 0),
                        LocalDateTime.of(2024, 5, 12, 18, 30, 0)));
        performMap.put("마리 앙투아네트",
                new Perform("마리 앙투아네트", MUSICAL, "디큐브 링크아트센터", SEVEN.getAge(), "180분",
                        LocalDateTime.of(2024, 5, 10, 14, 30, 0),
                        LocalDateTime.of(2024, 5, 10, 19, 30, 0),
                        LocalDateTime.of(2024, 5, 11, 14, 0, 0),
                        LocalDateTime.of(2024, 5, 11, 19, 0, 0),
                        LocalDateTime.of(2024, 5, 12, 15, 0, 0)));
        performMap.put("프랑켄슈타인",
                new Perform("프랑켄슈타인", MUSICAL, "블루스퀘어 신한카드홀", SEVEN.getAge(), "170분",
                        LocalDateTime.of(2024, 6, 7, 14, 30, 0),
                        LocalDateTime.of(2024, 6, 7, 19, 30, 0),
                        LocalDateTime.of(2024, 6, 8, 14, 0, 0),
                        LocalDateTime.of(2024, 6, 8, 18, 30, 0),
                        LocalDateTime.of(2024, 6, 9, 14, 0, 0),
                        LocalDateTime.of(2024, 6, 9, 15, 0, 0)));
        performMap.put("스웨덴국립미술관 컬렉션",
                new Perform("스웨덴국립미술관 컬렉션", EXHIBIT, "마이아트뮤지엄(자세히)", ALL.getAge(), "120분",
                        LocalDateTime.of(2024, 5, 10, 20, 0, 0)));
        performMap.put("반 고흐 인사이드: 러브, 빈센트",
                new Perform("반 고흐 인사이드: 러브, 빈센트", EXHIBIT, "그라운드시소 명동", ALL.getAge(), "50분",
                        LocalDateTime.of(2024, 5, 10, 0, 0, 0),
                        LocalDateTime.of(2024, 5, 11, 0, 0, 0),
                        LocalDateTime.of(2024, 5, 12, 0, 0, 0)));
        performMap.put("에드바르 뭉크: 비욘드 더 스크림",
                new Perform("에드바르 뭉크: 비욘드 더 스크림", EXHIBIT, "예술의전당 한가람미술관 1층", ALL.getAge(), "60분",
                        LocalDateTime.of(2024, 5, 24, 0, 0, 0),
                        LocalDateTime.of(2024, 5, 25, 0, 0, 0),
                        LocalDateTime.of(2024, 5, 26, 0, 0, 0)));
        performMap.put("캐리tv 러브콘서트 2024 타임캡슐",
                new Perform("캐리tv 러브콘서트 2024 타임캡슐", FAMILY, "연세대학교 백주년기념관 콘서트홀", ALL.getAge(), "70분",
                        LocalDateTime.of(2024, 5, 25, 11, 0, 0),
                        LocalDateTime.of(2024, 5, 25, 14, 0, 0),
                        LocalDateTime.of(2024, 5, 25, 16, 30, 0),
                        LocalDateTime.of(2024, 5, 26, 11, 0, 0),
                        LocalDateTime.of(2024, 5, 26, 14, 0, 0),
                        LocalDateTime.of(2024, 5, 26, 16, 30, 0)));
        performMap.put("캐치! 티니핑 〈두근두근 싱어롱 콘서트!〉",
                new Perform("캐치! 티니핑 〈두근두근 싱어롱 콘서트!〉", FAMILY, "광주 예술의전당 대극장", ALL.getAge(), "",
                        LocalDateTime.of(2024, 5, 25, 11, 0, 0),
                        LocalDateTime.of(2024, 5, 25, 14, 0, 0),
                        LocalDateTime.of(2024, 5, 25, 16, 30, 0),
                        LocalDateTime.of(2024, 5, 26, 11, 0, 0),
                        LocalDateTime.of(2024, 5, 26, 14, 0, 0),
                        LocalDateTime.of(2024, 5, 26, 16, 30, 0)));
        performMap.put("가족뮤지컬 〈뽀로로와 친구들의 드림콘서트 - 잃어버린 꿈을 찾아서〉",
                new Perform("가족뮤지컬 〈뽀로로와 친구들의 드림콘서트 - 잃어버린 꿈을 찾아서〉", FAMILY, "한국잡월드 나래울극장", ALL.getAge(), "70분",
                        LocalDateTime.of(2024, 5, 25, 11, 0, 0),
                        LocalDateTime.of(2024, 5, 25, 14, 0, 0),
                        LocalDateTime.of(2024, 5, 25, 16, 30, 0),
                        LocalDateTime.of(2024, 5, 26, 11, 0, 0),
                        LocalDateTime.of(2024, 5, 26, 14, 0, 0),
                        LocalDateTime.of(2024, 5, 26, 16, 30, 0)));
        performMap.put("신비아파트 〈붉은 눈의 저주〉",
                new Perform("신비아파트 〈붉은 눈의 저주〉", FAMILY, "대전 우송예술회관", ALL.getAge(), "70분",
                        LocalDateTime.of(2024, 5, 25, 11, 0, 0),
                        LocalDateTime.of(2024, 5, 25, 14, 0, 0),
                        LocalDateTime.of(2024, 5, 25, 16, 30, 0),
                        LocalDateTime.of(2024, 5, 26, 11, 0, 0),
                        LocalDateTime.of(2024, 5, 26, 14, 0, 0),
                        LocalDateTime.of(2024, 5, 26, 16, 30, 0)));
    }



    public static List<String> ContentByCategory(int option) {
        Category category = getCategoryByOption(option);
        List<String> titleByCategory = performMap.entrySet().stream()
                .filter(t -> t.getValue().getCategory() == category)
                .map(f -> f.getKey())
                .limit(5)
                .collect(Collectors.toList());

        return titleByCategory;
    }


    // 최근 하는 행사 보여줌
    public static List<String> theseDaysEvent() {
        // 오늘 날짜 가져오기
//        LocalDate nowDate = LocalDate.now();
//        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 (E)", Locale.KOREAN); // 요일 포함
//        String formattedNowDate = nowDate.format(dateFormatter);
//        System.out.println("오늘 날짜는 " + formattedNowDate + "입니다." + "\n금주의 공연/전시 목록입니다.");
        LocalDate nowDate = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 (E)", Locale.KOREAN); // 요일 포함
        String formattedNowDate = nowDate.format(dateFormatter);
        System.out.println("----------------------------------------");
        System.out.println("        📜이번주의 공연/전시 목록");
        System.out.println("     오늘 날짜: " + formattedNowDate);
        System.out.println("---------------------------------------- *");
        // 일주일 후의 날짜 계산
        LocalDate oneWeekLater = nowDate.plusWeeks(1);

        // 날짜를 원하는 형식으로 포맷
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 (E)", Locale.KOREAN); // 요일 포함

        // 결과를 저장할 리스트 생성
        List<String> eventList = new ArrayList<>();
        // 현재 날짜부터 일주일 후까지의 날짜를 순회하면서 이벤트 추출
        int count  = 0;
        for (LocalDate currentDate = nowDate; currentDate.isBefore(oneWeekLater); currentDate = currentDate.plusDays(1)) {
            final LocalDate date = currentDate; // 최종 변수로 만들기

            String dateString = date.format(formatter); // "yyyy년 MM월 dd일 (E)"

            // 해당 날짜의 이벤트 제목 출력
            List<String> collected = performMap.entrySet().stream()
                    .filter(entry -> entry.getValue().getDate().getShowTime().stream() //
                    .anyMatch(showDateTime -> showDateTime.toLocalDate().equals(date)))
                    .map(stringPerformEntry -> stringPerformEntry.getKey())
                    .limit(1)
                    .collect(Collectors.toList());

            // 이벤트가 있는 경우에만 출력
            if (!collected.isEmpty()) {
                // 개별 이벤트 출력
                System.out.printf("# %d. %s\n", ++count, dateString);
                for (String event : collected) {
                    System.out.println("- " + event);
                    eventList.add(event);
                }
            }
        }
        return eventList;
    }

//        List<String> eachContents = new ArrayList<>();
////        List<String> titleByCategory = performMap.entrySet().stream()
////                .filter(t -> t.getValue().getCategory() == )
////                .map(t -> t.getKey())
////                .limit(1)
////                .collect(Collectors.toList());
////        System.out.println("titleByCategory = " + titleByCategory);
//
//        for (int i = 1; i < 5; i++) {
//            Category category = getCategoryByOption(i);
//
//            List<Perform> collect = performMap.values().stream()
//                    .filter(t -> t.getCategory() == category)
//                    .collect(Collectors.toList());
//
//            int rn = (int) (Math.random() * collect.size());
//
//            eachContents.add(collect.get(rn).getTitle());
////
////            List<Map.Entry<String, Perform>> collect = performMap.entrySet().stream()
////                    .filter(t -> t.getValue().getCategory() == category)
////                    .limit(1)
////                    .collect(Collectors.toList());
//        }
//
//        int count = 0;
//        for (String eachContent : eachContents) {
//            count++;
//            System.out.println("# " + count +". "+ eachContent);
//        }



    public static Perform getSelectedTitle(String selectedTitle) {
        for (Perform value : performMap.values()) {
            if (value.getTitle().equals(selectedTitle)) {
                return value;
            }
        }
        return null;
    }

}
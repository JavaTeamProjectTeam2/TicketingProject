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
        performMap.put("SHINee WORLD Ⅵ [PERFECT ILLUMINATION] : SHINee’S BACK", new Perform("SHINee WORLD Ⅵ [PERFECT ILLUMINATION : SHINee’S BACK", CONCERT, "인스파이어 아레나", SEVEN.getAge(), "140분", "2024년 5월 25일(토) 오후 2시", "2024년 5월 25일(토) 오후 2시"));
        performMap.put("2024 송가인 디너 콘서트 ＂효＂", new Perform("2024 송가인 디너 콘서트 ＂효＂", CONCERT, "서울 그랜드하얏트호텔 그랜드볼룸", SEVEN.getAge(),"120분", "2024년 5월 8일(수) 오후 6시"));
        performMap.put("NCT WISH : SCHOOL of WISH", new Perform("NCT WISH : SCHOOL of WISH", CONCERT, "명화라이브홀", SEVEN.getAge(),"90분", "2024년 5월 25일(토) 오후 2시, 2024년 5월 25일(토) 오후 7시, 2024년 5월 26일(일) 오후 2시, 2024년 5월 26일(일) 오후 7시"));
        performMap.put("2024 RIIZE FAN－CON ‘RIIZING DAY’ in SEOUL", new Perform("2024 RIIZE FAN－CON ‘RIIZING DAY’ in SEOUL", CONCERT, "잠실실내체육관", SEVEN.getAge(),"100분", "2024년 5월 4일(토) 오후 6시, 2024년 5월 5일(일) 오후 4시"));
        performMap.put("LE SSERAFIM FAN MEETING ‘FEARNADA’ 2024 S/S - SEOUL", new Perform("LE SSERAFIM FAN MEETING ‘FEARNADA’ 2024 S/S - SEOUL", CONCERT, "잠실실내체육관", SEVEN.getAge(),"100분", "2024년 5월 11일(토) 오후 6시, 2024년 5월 12일(일) 오후 5시"));
        performMap.put("시카고", new Perform("시카고", MUSICAL, "디큐브 링크아트센터", FOURTEEN.getAge(), "145분", "2024년 6월 7일(금) 오후 7시 30분", "2024년 6월 8일(토) 오후 2시", "2024년 6월 8일(토) 오후 6시 30분", "2024년 6월 9일(일) 오후 2시", "2024년 6월 9일(일) 오후 6시 30분"));
        performMap.put("헤드윅", new Perform("헤드윅", MUSICAL, "샤롯데씨어터", FOURTEEN.getAge(), "135분", "2024년 5월 10일(금) 오후 7시 30분", "2024년 5월 11일(토) 오후 2시", "2024년 5월 11일(토) 오후 6시 30분", "2024년 5월 12일(일) 오후 2시", "2024년 5월 12일(일) 오후 6시 30분"));
        performMap.put("마리 앙투아네트", new Perform("마리 앙투아네트", MUSICAL, "디큐브 링크아트센터", SEVEN.getAge(), "180분", "2024년 5월 10일(금) 오후 2시 30분", "2024년 5월 10일(금) 오후 7시 30분", "2024년 5월 11일(토) 오후 2시", "2024년 5월 11일(토) 오후 7시", "2024년 5월 12일(일) 오후 3시"));
        performMap.put("프랑켄슈타인", new Perform("프랑켄슈타인", MUSICAL, "블루스퀘어 신한카드홀", SEVEN.getAge(), "170분", "2024년 6월 7일(금) 오후 2시 30분","2024년 6월 7일(금) 오후 7시 30분", "2024년 6월 8일(토) 오후 2시", "2024년 6월 8일(토) 오후 6시 30분", "2024년 6월 9일(일) 오후 2시", "2024년 6월 9일(일) 오후 3시"));
        performMap.put("스웨덴국립미술관 컬렉션", new Perform("스웨덴국립미술관 컬렉션", EXHIBIT, "마이아트뮤지엄(자세히)", ALL.getAge(), "120분", "2024년 5월 10일(금) 오후 8시"));
        performMap.put("반 고흐 인사이드: 러브, 빈센트", new Perform("반 고흐 인사이드: 러브, 빈센트", EXHIBIT, "그라운드시소 명동", ALL.getAge(), "50분", "2024년 5월 10일(금)", "2024년 5월 11일(토)", "2024년 5월 12일(일)"));
        performMap.put("에드바르 뭉크: 비욘드 더 스크림", new Perform("에드바르 뭉크: 비욘드 더 스크림", EXHIBIT, "예술의전당 한가람미술관 1층", ALL.getAge(), "60분", "2024년 5월 24일(금)", "2024년 5월 25일(토)", "2024년 5월 26일(일)"));
        performMap.put("캐리tv 러브콘서트 2024 타임캡슐", new Perform("캐리tv 러브콘서트 2024 타임캡슐", FAMILY, "연세대학교 백주년기념관 콘서트홀", ALL.getAge(), "70분", "2024년 5월 25일(토) 오전 11시", "2024년 5월 25일(토) 오후 2시", "2024년 5월 25일(토) 오후 4시 30분", "2024년 5월 26일(일) 오전 11시", "2024년 5월 26일(일) 오후 2시", "2024년 5월 26일(일) 오후 4시 30분"));
        performMap.put("캐치! 티니핑 〈두근두근 싱어롱 콘서트!〉", new Perform("캐치! 티니핑 〈두근두근 싱어롱 콘서트!〉", FAMILY, "광주 예술의전당 대극장", ALL.getAge(), "", "2024년 5월 25일(토) 오전 11시", "2024년 5월 25일(토) 오후 2시", "2024년 5월 25일(토) 오후 4시 30분", "2024년 5월 26일(일) 오전 11시", "2024년 5월 26일(일) 오후 2시", "2024년 5월 26일(일) 오후 4시 30분"));
        performMap.put("가족뮤지컬 〈뽀로로와 친구들의 드림콘서트 - 잃어버린 꿈을 찾아서〉", new Perform("가족뮤지컬 〈뽀로로와 친구들의 드림콘서트 - 잃어버린 꿈을 찾아서〉", FAMILY, "한국잡월드 나래울극장", ALL.getAge(),"70분", "2024년 5월 25일(토) 오전 11시", "2024년 5월 25일(토) 오후 2시", "2024년 5월 25일(토) 오후 4시 30분", "2024년 5월 26일(일) 오전 11시", "2024년 5월 26일(일) 오후 2시", "2024년 5월 26일(일) 오후 4시 30분"));
        performMap.put("신비아파트 〈붉은 눈의 저주〉", new Perform("신비아파트 〈붉은 눈의 저주〉", FAMILY, "대전 우송예술회관", ALL.getAge(), "70분", "2024년 5월 25일(토) 오전 11시", "2024년 5월 25일(토) 오후 2시", "2024년 5월 25일(토) 오후 4시 30분", "2024년 5월 26일(일) 오전 11시", "2024년 5월 26일(일) 오후 2시", "2024년 5월 26일(일) 오후 4시 30분"));
    }



    public static void showContentByCategory(int option) {
        Category category = getCategoryByOption(option);
        List<String> titleByCategory = performMap.entrySet().stream()
                .filter(t -> t.getValue().getCategory() == category)
                .map(f -> f.getKey())
                .limit(5)
                .collect(Collectors.toList());


        int count = 0;
        for (String title : titleByCategory) {
            count++;
            System.out.println("# " + count +". "+ title);
        }

    }


    // 최근 하는 행사 보여줌
    public static void theseDaysEvent() {
        // 현재 날짜 가져오기
        LocalDate nowDate = LocalDate.now();

        // 일주일 후의 날짜 계산
        LocalDate oneWeekLater = nowDate.plusWeeks(1);

        // 날짜를 원하는 형식으로 포맷
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");

        // 현재 날짜부터 일주일 후까지의 날짜를 순회하면서 이벤트 추출
        for (LocalDate date = nowDate; date.isBefore(oneWeekLater); date = date.plusDays(1)) {
            String dateString = date.format(formatter);
            System.out.println(dateString + "의 이벤트:");

            // 해당 날짜의 이벤트 제목 출력
            performMap.entrySet().stream()
                    .filter(entry -> entry.getValue().getDate().getShowTime().equals(dateString))
                    .map(f -> f.getKey())
                    .collect(Collectors.toList());

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

    }
}

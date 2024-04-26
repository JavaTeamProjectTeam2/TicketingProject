package src;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static src.AgeRating.*;
import static src.Category.*;

public class PerformRepository {

    static {
        Map<String, Perform> performMap = new HashMap<>();

        performMap.put("SHINee WORLD Ⅵ [PERFECT ILLUMINATION : SHINee’S BACK", new Perform("SHINee WORLD Ⅵ [PERFECT ILLUMINATION : SHINee’S BACK", CONCERT, "인스파이어 아레나", SEVEN.getAge(), new Date()));
        performMap.put("2024 송가인 디너 콘서트 ＂효＂", new Perform("2024 송가인 디너 콘서트 ＂효＂", CONCERT, "서울 그랜드하얏트호텔 그랜드볼룸", SEVEN.getAge(),  new Date()));
        performMap.put("SEVENTEEN TOUR ‘FOLLOW’ AGAIN TO SEOUL", new Perform("SEVENTEEN TOUR ‘FOLLOW’ AGAIN TO SEOUL", CONCERT, "서울월드컵경기장", SEVEN.getAge(),  new Date()));
        performMap.put("NCT WISH : SCHOOL of WISH", new Perform("NCT WISH : SCHOOL of WISH", CONCERT, "명화라이브홀", SEVEN.getAge(),  new Date()));
        performMap.put("2024 RIIZE FAN－CON ‘RIIZING DAY’ in SEOUL", new Perform("2024 RIIZE FAN－CON ‘RIIZING DAY’ in SEOUL", CONCERT, "잠실실내체육관", SEVEN.getAge(),  new Date()));
        performMap.put("LE SSERAFIM FAN MEETING ‘FEARNADA’ 2024 S/S - SEOUL", new Perform("LE SSERAFIM FAN MEETING ‘FEARNADA’ 2024 S/S - SEOUL", CONCERT, "잠실실내체육관", SEVEN.getAge(),  new Date()));
        performMap.put("시카고", new Perform("시카고", MUSICAL, "디큐브 링크아트센터", FOURTEEN.getAge(),  new Date()));
        performMap.put("헤드윅", new Perform("헤드윅", MUSICAL, "샤롯데씨어터", FOURTEEN.getAge(),  new Date()));
        performMap.put("마리 앙투아네트", new Perform("마리 앙투아네트", MUSICAL, "디큐브 링크아트센터", SEVEN.getAge(),  new Date()));
        performMap.put("프랑켄슈타인", new Perform("프랑켄슈타인", MUSICAL, "블루스퀘어 신한카드홀", SEVEN.getAge(),  new Date()));
        performMap.put("스웨덴국립미술관 컬렉션", new Perform("스웨덴국립미술관 컬렉션", EXHIBIT, "마이아트뮤지엄(자세히)", ALL.getAge(),  new Date()));
        performMap.put("반 고흐 인사이드: 러브, 빈센트", new Perform("반 고흐 인사이드: 러브, 빈센트", EXHIBIT, "그라운드시소 명동", ALL.getAge(),  new Date()));
        performMap.put("에드바르 뭉크: 비욘드 더 스크림", new Perform("에드바르 뭉크: 비욘드 더 스크림", EXHIBIT, "예술의전당 한가람미술관 1층", ALL.getAge(),  new Date()));
        performMap.put("캐리tv 러브콘서트 2024 타임캡슐", new Perform("캐리tv 러브콘서트 2024 타임캡슐", FAMILY, "연세대학교 백주년기념관 콘서트홀", ALL.getAge(),  new Date()));
        performMap.put("캐치! 티니핑 〈두근두근 싱어롱 콘서트!〉", new Perform("캐치! 티니핑 〈두근두근 싱어롱 콘서트!〉", FAMILY, "광주 예술의전당 대극장", ALL.getAge(),  new Date()));
        performMap.put("가족뮤지컬 〈뽀로로와 친구들의 드림콘서트 - 잃어버린 꿈을 찾아서〉", new Perform("가족뮤지컬 〈뽀로로와 친구들의 드림콘서트 - 잃어버린 꿈을 찾아서〉", FAMILY, "한국잡월드 나래울극장", ALL.getAge(),  new Date()));
        performMap.put("신비아파트 〈붉은 눈의 저주〉", new Perform("신비아파트 〈붉은 눈의 저주〉", FAMILY, "대전 우송예술회관", ALL.getAge(),  new Date()));
    }

}

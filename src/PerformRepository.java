package src;

public class PerformRepository {

    private static PerformList performList;

    static {
        performList = new PerformList();

        performList.push(new Perform("SHINee WORLD Ⅵ [PERFECT ILLUMINATION : SHINee’S BACK", "인스파이어 아레나", "2024년 5월 24일 (금) 7:00PM\n" + "2024년 5월 25일 (토) 5:00PM\n" + "2024년 5월 26일 (일) 4:00PM", 140, 198.000 + 154.000, 7, "VIP"));
        performList.push(new Perform("2024 송가인 디너 콘서트 ＂효＂", "서울 그랜드하얏트호텔 그랜드볼룸", "2024년 5월 8일(수) 오후 6시", 140, 198.000 + 154.000, 7, "VIP"));
    }

}

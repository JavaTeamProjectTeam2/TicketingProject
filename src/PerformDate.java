package src;

import java.util.ArrayList;
import java.util.Objects;
// 날짜 class : 공연날짜(회차 포함), 공연시간(running time)
public class PerformDate {
   private ArrayList<String> showTime; // ex) ["2024년05월15일", "2024년05년17일", ...]
   private String runningTime;

    public PerformDate(ArrayList<String> showTime, String runningTime) {
        this.showTime = showTime;
        this.runningTime = runningTime;
    }

    public ArrayList<String> getShowTime() {
        return showTime;
    }

    public void setShowTime(ArrayList<String> showTime) {
        this.showTime = showTime;
    }

    public String getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(String runningTime) {
        this.runningTime = runningTime;
    }

    @Override
    public String toString() {
        return "Date{" +
                "showTime=" + showTime +
                ", runningTime='" + runningTime + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PerformDate date = (PerformDate) o;
        return Objects.equals(showTime, date.showTime) && Objects.equals(runningTime, date.runningTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(showTime, runningTime);
    }
}

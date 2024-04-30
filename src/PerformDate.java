package src;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
// 날짜 class : 공연날짜(회차 포함), 공연시간(running time)
public class PerformDate {
   private List<LocalDateTime> showTime; // ex) ["2024년05월15일", "2024년05년17일", ...]
   private String runningTime;

    public PerformDate(List<LocalDateTime> showTime, String runningTime) {
        this.showTime = showTime;
        this.runningTime = runningTime;
    }

    public List<LocalDateTime> getShowTime() {
        return showTime;
    }

    public void setShowTime(List<LocalDateTime> showTime) {
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
        return "PerformDate{" +
                "showTime=" + showTime +
                ", runningTime='" + runningTime + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PerformDate that = (PerformDate) o;
        return Objects.equals(showTime, that.showTime) && Objects.equals(runningTime, that.runningTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(showTime, runningTime);
    }
}

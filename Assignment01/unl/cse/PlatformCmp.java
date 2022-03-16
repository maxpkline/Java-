

import java.util.Comparator;

public class PlatformCmp implements Comparator<GameReportClass>{

    @Override
    public int compare(GameReportClass o1, GameReportClass o2) {
            return o1.getGameTitle().compareTo(o2.getGameTitle());
    }


}


import java.util.Comparator;

/**
 * This class, like the GameCmp class, are used as comparator method to find if the
 * publisher has already been accounted for
 */

public class PublisherCmp implements Comparator<GameReportClass>{

    @Override
    public int compare(GameReportClass o1, GameReportClass o2) {
    	
            return o1.getPublisher().compareTo(o2.getPublisher());
            
    }

}


package monitoring;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;

@Component
public class SitesState {
    private Map<String, String> prevSitesState;
    private Map<String, String> currSitesState;

    private Set<String> getPrevSitesStateUrls() {
        return prevSitesState.keySet();
    }

    private Set<String> getCurrSitesStateUrls() {
        return currSitesState.keySet();
    }

    public SitesState () {}

    /**
     * Initialization for sites states.
     * There could be any logic.
     */
    @PostConstruct
    public void init() {
        prevSitesStateInit();
        currSitesStateInit();
    }

    private void prevSitesStateInit() {
        prevSitesState = new HashMap<>();
        prevSitesState.put("vk.com", "<html>vk</html>");
        prevSitesState.put("negina.com", "<html>negina</html>");
        prevSitesState.put("youtube.com", "<html>youtube yesterday</html>");
        prevSitesState.put("ya.com", "<html>ya</html>");
    }

    private void currSitesStateInit() {
        currSitesState = new HashMap<>();
        currSitesState.put("twitter.com", "<html>twitter</html>");
        currSitesState.put("negina.com", "<html>negina</html>");
        currSitesState.put("youtube.com", "<html>youtube today</html>");
        currSitesState.put("google.com", "<html>google</html>");
    }

    /**
     * Return all deleted urls.
     * Can be empty.
     *
     * @return Set<String>
     */
    public Set<String> getDeletedPages() {
        Set<String> deletedPages = new HashSet<>(getPrevSitesStateUrls());
        deletedPages.removeAll(getCurrSitesStateUrls());

        return deletedPages;
    }

    /**
     * Return all created urls.
     * Can be empty.
     *
     * @return Set<String>
     */
    public Set<String> getCreatedPages() {
        Set<String> createdPages = new HashSet<>(getCurrSitesStateUrls());
        createdPages.removeAll(getPrevSitesStateUrls());

        return createdPages;
    }

    /**
     * Return all changed urls.
     * Can be empty.
     *
     * @return Set<String>
     */
    public Set<String> getChangedPages() {
        Set<String> changedPages = new HashSet<>(getPrevSitesStateUrls());
        changedPages.retainAll(getCurrSitesStateUrls());

        changedPages.removeIf(url -> prevSitesState.get(url).equals(currSitesState.get(url)));

        return changedPages;
    }
}

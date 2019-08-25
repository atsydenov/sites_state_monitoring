package mailer;

import monitoring.SitesState;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

import java.util.Set;

@Component
public class MonitoringReportTemplate implements Template {
    private StringBuilder html;
    private SitesState sitesState;

    @Autowired
    public void setSitesState(SitesState sitesState) {
        this.sitesState = sitesState;
    }

    public MonitoringReportTemplate() {
        html = new StringBuilder();
    }

    @Override
    public String generateHTML() {
        html.append("<p>Dear friend,</p>");
        html.append("<p>The following changes have occurred in the sites in last 24 hours:</p>");

        addBlock(sitesState.getDeletedPages(), 1, "deleted");
        addBlock(sitesState.getCreatedPages(), 2, "created");
        addBlock(sitesState.getChangedPages(), 3, "changed");

        html.append("<br>");

        html.append("<p>Best regards,</p>");
        html.append("<p>automated monitoring system.</p>");

        return html.toString();
    }

    /**
     * Helper method for generate HTML.
     *
     * @param urls  Set<String>
     * @param order int
     * @param block String
     */
    private void addBlock(Set<String> urls, int order, String block) {
        if (!urls.isEmpty()) {
            html.append("<p>")
                .append(order)
                .append(") ")
                .append("The following pages have ")
                .append(block)
                .append(":</p>");

            for(String url: urls) {
                html.append("- ").append(url).append("<br>");
            }
        } else {
            html.append("<p>")
                .append(order)
                .append(") ")
                .append("No ")
                .append(block)
                .append(" pages.</p>");
        }
    }
}

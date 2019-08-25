package mailer;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class MonitoringReportMailer extends Mailer {
    @Autowired
    public MonitoringReportMailer(
            @Value("${mailer.username}") String username,
            @Value("${mailer.password}") String password
    ) {
        this.username = username;
        this.password = password;
    }

    public void sendMonitoringReport(String recipientEmail, String template) {
        send("Monitoring Report", template, recipientEmail);
    }
}

package softaria;

import mailer.MonitoringReportMailer;
import mailer.MonitoringReportTemplate;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    private final static String recipientMail = "testdevqwerty2018@gmail.com";

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml"
        );

        MonitoringReportTemplate template = context.getBean(MonitoringReportTemplate.class);
        MonitoringReportMailer mailer     = context.getBean(MonitoringReportMailer.class);

        mailer.sendMonitoringReport(recipientMail, template.generateHTML());

        context.close();
    }
}

package net.midgard.dummy.mta;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.subethamail.wiser.WiserMessage;

public class MailRecord {

    private Date date;
    private String from;
    private String fullText;
    private String subject;
    private String to;

    public MailRecord(WiserMessage wm) throws MessagingException, ParseException {
        this.fullText = new String(wm.getData());
        this.date = extractDate(this.fullText);
        this.from = wm.getEnvelopeSender();
        this.subject = wm.getMimeMessage().getSubject();
        this.to = wm.getEnvelopeReceiver();
    }

    public Date getDate() {
        return this.date;
    }

    public String getFrom() {
        return this.from;
    }

    public String getFullText() {
        return this.fullText;
    }

    public String getSubject() {
        return this.subject;
    }

    public String getTo() {
        return this.to;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setFullText(String fullText) {
        this.fullText = fullText;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setTo(String to) {
        this.to = to;
    }

    private Date extractDate(String text) throws ParseException {
        String truncated = text.substring(0, text.indexOf("(GMT)"));
        truncated = truncated.substring(truncated.lastIndexOf("\n"));
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss ZZZZZ");
        return sdf.parse(truncated.trim());
    }
}

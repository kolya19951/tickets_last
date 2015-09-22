package Mail;

/**
 * Created by ���� on 18.09.2015.
 */

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

// �������� �������� ��������� � ����� �������� "text/plain"
public class TestEmail {

    public static void main(String[] args) {

// ���� ���������� ���������� ����� ���������� ���������
        String to = "denis.drabchuk@gmail.com";
        String from = "";
// ���� ���������� ���������� SMTP ������, ������������ ��� ��������
        String host = "smtp.yourisp.net";

// �������� �������, ��������� ������
        Properties props = new Properties();

// ��� ������������� ������������ ������ Transport.send()
// ���������� ������� ����� ����� ���� ����� �������� ���������
        props.put("mail.smtp.host", host);
// ��������� debug-������
        props.put("mail.debug", "true");
        Session session = Session.getInstance(props);

        try {
// �������� ������� ���������
            Message msg = new MimeMessage(session);

// ��������� ��������� ���������
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject("Test E-Mail through Java");
            msg.setSentDate(new Date());

// ��������� ���� ���������
            msg.setText("This is a test of sending a " +
                    "plain text e-mail through Java.\n" +
                    "Here is line 2.");

// �������� ���������
            Transport.send(msg);
        } catch (MessagingException mex) {
// ������ ���������� �� ���������� � ������ ��� �������������
            mex.printStackTrace();
        }
    }
}

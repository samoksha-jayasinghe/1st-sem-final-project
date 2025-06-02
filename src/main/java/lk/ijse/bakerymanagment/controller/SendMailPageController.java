/*
package lk.ijse.bakerymanagment.controller;

import com.google.protobuf.Message;
import com.mysql.cj.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import lombok.Setter;

//import javax.mail.*;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
import java.net.PasswordAuthentication;
import java.util.Properties;

public class SendMailPageController {

    @FXML
    private TextArea txtMessage;

    @FXML
    private TextField txtSubject;

    @FXML
    private TextField txtTo;

    @Setter
    private String customerEmail;

    @FXML
    void btnSendOnAction(ActionEvent event) {
        String toMail = (customerEmail != null && !customerEmail.isEmpty()) ? customerEmail : txtTo.getText();
        String subject = txtSubject.getText();
        String message = txtMessage.getText();

        if (toMail == null || toMail.isEmpty() ||
                subject == null || subject.isEmpty() ||
                message == null || message.isEmpty()) {

            new Alert(Alert.AlertType.WARNING, "All fields are required!").show();
            return;
        }

        String from = "Samoksha@gmail.com"; // your sender email
        String password = "rzte ajuj jbxs esmh"; // your app password from Google

        // SMTP configuration
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Authenticator for email
        Session session;
        session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password.toCharArray());
            }
        });

        try {
            Message mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(from));
            mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toMail));
            mimeMessage.setSubject(subject);
            mimeMessage.setText(message);

            Transport.send(mimeMessage);
            new Alert(Alert.AlertType.INFORMATION, "Mail sent successfully!").show();

            // Optionally clear fields
            txtSubject.clear();
            txtMessage.clear();
            txtTo.clear();

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to send mail.").show();
        }
    }

    private class Authenticator {
    }
}
*/

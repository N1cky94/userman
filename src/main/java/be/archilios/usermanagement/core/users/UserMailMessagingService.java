package be.archilios.usermanagement.core.users;

import be.archilios.usermanagement.util.Mailable;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class UserMailMessagingService implements Mailable {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd, MMMM yyyy 'om' HH:mm");
    private final JavaMailSender mailSender;
    
    public void sendUserActivationMessage(UserInfo user) {
        SimpleMailMessage message = constructMailMessage(user.email());
        
        message.setText(
                "Beste " + user.firstName() + ", \n\n" +
                "Welkom bij Archilios! We zijn blij dat je deel uitmaakt van onze community.\n\n" +
                "Met vriendelijke groeten\n" +
                "Het Archilios team"
        );
        
        trySending(message);
    }
    
    private SimpleMailMessage constructMailMessage(String to) {
        SimpleMailMessage result = new SimpleMailMessage();
        result.setFrom(FROM_EMAIL);
        result.setReplyTo(REPLY_TO_EMAIL);
        result.setTo(to);
        result.setSubject("Archilios: Welcome to the Club!");
        
        return result;
    }
    
    private void trySending(SimpleMailMessage message) {
        try {
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

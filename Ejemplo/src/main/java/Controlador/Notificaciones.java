package Controlador;

import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class Notificaciones {
	
	
	public void SIM(String asunto, String mensajeTexto)
	
	
	{
		
		
		String remitente = "alexandraamaris02@gmail.com";
		String clave =  "ygyi tsgx szif sewu"; 
		
		String receptor= "alexandraamaris02@gmail.com";
		  //  Configuración SMTP //Protocolo de transferencia de correo
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
		
        // Crea una sesión auténticada
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remitente, clave);
            }
        });
        
        try {
            // Crear mensaje
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(remitente));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receptor));
            message.setSubject(asunto);
            message.setText(mensajeTexto);

            //  Enviar
            Transport.send(message);
            
            System.out.println("Correo enviado");
            
            //Mensaje en respuesta

		
        } catch(MessagingException e) {
        	//Mostrar error
        	System.out.println("hola");
		   
	   }
        
        

	}
        
	}
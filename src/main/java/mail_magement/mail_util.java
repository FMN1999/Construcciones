package mail_magement;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class mail_util {
	public static void Send(String receptor,String asunto,String texto) throws Exception {
		System.out.println("Iniciando envio de email");
		Properties info=new Properties();
		
		info.put("mail.smtp.auth", "true");
		info.put("mail.smtp.starttls.enable", "true");
		info.put("mail.smtp.host", "smtp.gmail.com");
		info.put("mail.smtp.port", "587");
		
		String myemail="construcciones.java.utn@gmail.com";
		String password="";
		
		Session session= Session.getInstance(info, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(myemail,password);
			}
		});
		
		try {
			Message msg= prepareMessage(session, myemail, receptor, asunto, texto);
			Transport.send(msg);
			System.out.println("Enviado!!!");
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			throw new Exception("Address Exception"+e.getMessage());
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			throw new Exception("Messaging Exception"+e.getMessage());
		}
		
		
	}

	private static Message prepareMessage(Session s, String myemail,String receptor, String asunto, String texto) throws AddressException, MessagingException {
		// TODO Auto-generated method stub
		Message m=new MimeMessage(s);
		m.setFrom(new InternetAddress(myemail));
		m.setRecipient(Message.RecipientType.TO, new InternetAddress(receptor));
		m.setSubject(asunto);
		m.setText(texto);
		return m;
	}

	public static void InformarPresupuesto(String direccion_obra,String receptor, String nombreApellido) throws Exception {
		String asunto="Nuevo presupuesto para:"+direccion_obra;
		String mensaje="Estimado "+nombreApellido+":\n\n\t"
				+ "Se le informa que hace un momento se ha\n"
				+ "registrado un nuevo presupuesto para su\n"
				+ "obra registrada en "+direccion_obra;
		Send(receptor, asunto, mensaje);
	}
}

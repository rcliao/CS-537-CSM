package org.csm.controllers;

import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.csm.models.User;
import org.csm.models.dao.UserDao;
import org.csm.models.dao.jdbc.UserDaoImpl;
import org.csm.util.BasicAuth;

@Path("/Email")
public class EmailController {
	private UserDao userDao = new UserDaoImpl();

	@POST
	@Path("/sendEmail")
	public void sendMail(@QueryParam("to") String to,
			@QueryParam("text") String text,
			@QueryParam("subject") String subject,
			@Context HttpServletRequest request) throws SQLException {
		User u = userDao.getUser(BasicAuth.decode(request.getHeader("Authorization"))[0]);

		final String username = u.getEmail();
		final String password = u.getPassword();

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "localhost");
		props.put("mail.smtp.port", "25");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});
		try {
			System.out.println("to:" + to);

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(u.getEmail()));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));
			message.setSubject(subject);
			message.setText(text, "utf-8", "html");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			System.out.println("Not Done!");
			throw new RuntimeException(e);
		}
	}
}

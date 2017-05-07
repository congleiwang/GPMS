package cn.edu.ecit.cl.wang.sys.common.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtils {
	public static void sendMail(String toMailAddr, String subject,String content) throws Exception {
		Properties propes = new Properties();
		// 开启debug调试
		propes.setProperty("mail.debug", "true");
		// 发送服务器需要身份验证
		propes.setProperty("mail.smtp.auth", "true");
		// 设置邮件服务器主机名
		propes.setProperty("mail.host", "smtp.foxmail.com");
		// 发送邮件协议名称
		propes.setProperty("mail.transport.protocol", "smtp");
		// 设定发送这者的邮箱信息
		
		// 发送者的邮箱账号及密码
		Session session = Session.getInstance(propes, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("gpms123@foxmail.com", "gpms123456");
			}
		});
		// 创建邮件对象
		Message message = new MimeMessage(session);
		// 设置邮件的发送者
		message.setFrom(new InternetAddress("gpms123@foxmail.com"));
		// 设置接收者
		// RecipientType.To:接收人
		// RecipientType.CC:抄送人
		// RecipientType.Bcc: 暗送人
		message.setRecipient(RecipientType.TO, new InternetAddress(toMailAddr));
		// 设置邮件主题
		message.setSubject(subject);
		// 设置邮件内容
		message.setContent(content, "text/html;charset=UTF-8");
		// 发送
		Transport.send(message);
	}

}

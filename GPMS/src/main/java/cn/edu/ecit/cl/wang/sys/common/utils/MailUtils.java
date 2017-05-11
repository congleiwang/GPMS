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

import org.springframework.beans.factory.annotation.Autowired;

public class MailUtils {
	
	@Autowired
	GlobalProperties globalProperties;
	
	public static void main(String[] args) throws Exception {
		sendMail("congl.wang@sunyard.com","邮件测试","内容");
	}
	
	public static void sendMail(String toMailAddr, String subject,String content) throws Exception {
		Properties props = new Properties();
		// 开启debug调试
		props.setProperty("mail.debug", "true");
		// 发送服务器需要身份验证
		props.setProperty("mail.smtp.auth", "true");
		// 设置邮件服务器主机名
		props.setProperty("mail.smtp.host", "smtp.qq.com");
		// 发送邮件协议名称
		props.put("mail.smtp.user","系统服务");
		props.setProperty("mail.transport.protocol", "smtp");
		props.put("mail.smtp.port", "25"); 
		props.put("mail.smtp.starttls.enable","true"); 
		props.put("mail.smtp.EnableSSL.enable","true");

		props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");   
		props.setProperty("mail.smtp.socketFactory.fallback", "false");   
		props.setProperty("mail.smtp.port", "465"); 
		
		
		
		// 设定发送这者的邮箱信息
		
		// 发送者的邮箱账号及密码
		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("congleiwang@foxmail.com", "jhkworsawuambefb");
			}
		});
		// 创建邮件对象
		Message message = new MimeMessage(session);
		// 设置邮件的发送者
		message.setFrom(new InternetAddress("congleiwang@foxmail.com"));
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

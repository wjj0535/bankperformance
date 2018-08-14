package org.wangjj.bankperformance.Utils;

import java.io.File;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.wangjj.bankperformance.Constant.GlobalData;

public class SendMailUtil {
	/**
	 * 发送文本邮件
	 * 
	 * @throws Exception
	 */
	public static void sendTextMail(String to, String subject, String content) throws Exception {
		String userName = GlobalData.getMailUserName();
		String userPwd = GlobalData.getMailUserPwd();

		Properties pro = System.getProperties();
		pro.put("mail.smtp.host", GlobalData.getMailSmtpHost());
		pro.put("mail.smtp.port", GlobalData.getMailSmtpPort());
		pro.put("mail.smtp.auth", "true");

		// 根据邮件会话属性和密码验证器构造一个发送邮件的session
		Session sendMailSession = Session.getDefaultInstance(pro, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, userPwd);
			}
		});
		// 根据session创建一个邮件消息
		Message mailMessage = new MimeMessage(sendMailSession);
		// 设置邮件消息的发送者
		mailMessage.setFrom(new InternetAddress(userName));
		// 创建邮件的接收者地址，并设置到邮件消息中
		mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
		// 设置邮件消息的主题
		mailMessage.setSubject(subject);
		// 设置邮件消息发送的时间
		mailMessage.setSentDate(new Date());
		// 设置邮件消息的主要内容
		mailMessage.setText(content);
		// 发送邮件
		Transport.send(mailMessage);
	}

	/**
	 * 发送Html邮件
	 * 
	 * @throws Exception
	 */
	public static void sendHtmlMail(String to, String subject, String content) throws Exception {
		String userName = GlobalData.getMailUserName();
		String userPwd = GlobalData.getMailUserPwd();
		
		Properties pro = System.getProperties();
		pro.put("mail.smtp.host", GlobalData.getMailSmtpHost());
		pro.put("mail.smtp.port", GlobalData.getMailSmtpPort());
		pro.put("mail.smtp.auth", "true");

		// 根据邮件会话属性和密码验证器构造一个发送邮件的session
		Session sendMailSession = Session.getDefaultInstance(pro, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, userPwd);
			}
		});
		// 根据session创建一个邮件消息
		Message mailMessage = new MimeMessage(sendMailSession);
		// 设置邮件消息的发送者
		mailMessage.setFrom(new InternetAddress(userName));
		// 创建邮件的接收者地址，并设置到邮件消息中
		mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
		// 设置邮件消息的主题
		mailMessage.setSubject(subject);
		// 设置邮件消息发送的时间
		mailMessage.setSentDate(new Date());

		// MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
		Multipart mainPart = new MimeMultipart();
		// 创建一个包含HTML内容的MimeBodyPart
		BodyPart html = new MimeBodyPart();
		// 设置HTML内容
		html.setContent(content, "text/html; charset=utf-8");
		mainPart.addBodyPart(html);
		// 将MiniMultipart对象设置为邮件内容
		mailMessage.setContent(mainPart);
		// 发送邮件
		Transport.send(mailMessage);
	}

	//
	// /**
	// * 发送内嵌图片邮件
	// *
	// * @throws Exception
	// */
	// public void sendImageMail() throws Exception
	// {
	// Properties pro = System.getProperties();
	// pro.put("mail.smtp.host", host);
	// pro.put("mail.smtp.port", port);
	// pro.put("mail.smtp.auth", "true");
	//
	// // 根据邮件会话属性和密码验证器构造一个发送邮件的session
	// Session sendMailSession = Session.getDefaultInstance(pro,
	// new Authenticator()
	// {
	// @Override
	// protected PasswordAuthentication getPasswordAuthentication()
	// {
	// return new PasswordAuthentication(userName, password);
	// }
	// });
	// // 根据session创建一个邮件消息
	// Message mailMessage = new MimeMessage(sendMailSession);
	// // 设置邮件消息的发送者
	// mailMessage.setFrom(new InternetAddress(userName));
	// // 创建邮件的接收者地址，并设置到邮件消息中
	// mailMessage.setRecipient(Message.RecipientType.TO,
	// new InternetAddress(to));
	// // 设置邮件消息的主题
	// mailMessage.setSubject("Test Email");
	// // 设置邮件消息发送的时间
	// mailMessage.setSentDate(new Date());
	//
	// MimeMultipart multipart = new MimeMultipart("related");
	//
	// BodyPart messageBodyPart = new MimeBodyPart();
	// String htmlText = "<html><body><img src='cid:image'/><div>this is a HTML
	// email.</div></body></html>";
	// messageBodyPart.setContent(htmlText, "text/html; charset=utf-8");
	// multipart.addBodyPart(messageBodyPart);
	//
	// MimeBodyPart imageBodyPart = new MimeBodyPart();
	// DataSource fds = new FileDataSource("1_jianggujin.jpg");
	// imageBodyPart.setDataHandler(new DataHandler(fds));
	// imageBodyPart.setContentID("image");
	// multipart.addBodyPart(imageBodyPart);
	//
	// mailMessage.setContent(multipart);
	// // 发送邮件
	// Transport.send(mailMessage);
	// }
	//
	 /**
	 * 发送附件邮件
	 *
	 * @throws Exception
	 */
	 public static void sendAttachmentMail(String to, String subject, String content, String file) throws Exception
	 {
		String userName = GlobalData.getMailUserName();
		String userPwd = GlobalData.getMailUserPwd();

		Properties pro = System.getProperties();
		pro.put("mail.smtp.host", GlobalData.getMailSmtpHost());
		pro.put("mail.smtp.port", GlobalData.getMailSmtpPort());
		pro.put("mail.smtp.auth", "true");

		// 根据邮件会话属性和密码验证器构造一个发送邮件的session
		Session sendMailSession = Session.getDefaultInstance(pro, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, userPwd);
			}
		});
		// 根据session创建一个邮件消息
		Message mailMessage = new MimeMessage(sendMailSession);
		// 设置邮件消息的发送者
		mailMessage.setFrom(new InternetAddress(userName));
		// 创建邮件的接收者地址，并设置到邮件消息中
		mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
		// 设置邮件消息的主题
		mailMessage.setSubject("Test Email");
		// 设置邮件消息发送的时间
		mailMessage.setSentDate(new Date());

		MimeMultipart multipart = new MimeMultipart("mixed");

		BodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(content, "text/html; charset=utf-8");
		multipart.addBodyPart(messageBodyPart);

		MimeBodyPart imageBodyPart = new MimeBodyPart();
		File imageFile = new File(file);
		DataSource fds = new FileDataSource(imageFile);
		imageBodyPart.setDataHandler(new DataHandler(fds));
		imageBodyPart.setFileName(MimeUtility.encodeWord(imageFile.getName()));
		multipart.addBodyPart(imageBodyPart);

		mailMessage.setContent(multipart);
		// 发送邮件
		Transport.send(mailMessage);
	 }
	//
	// /**
	// * 发送内嵌图片和附件邮件
	// *
	// * @throws Exception
	// */
	// public void sendImageAndAttachmentMail() throws Exception
	// {
	// Properties pro = System.getProperties();
	// pro.put("mail.smtp.host", host);
	// pro.put("mail.smtp.port", port);
	// pro.put("mail.smtp.auth", "true");
	//
	// // 根据邮件会话属性和密码验证器构造一个发送邮件的session
	// Session sendMailSession = Session.getDefaultInstance(pro,
	// new Authenticator()
	// {
	// @Override
	// protected PasswordAuthentication getPasswordAuthentication()
	// {
	// return new PasswordAuthentication(userName, password);
	// }
	// });
	// // 根据session创建一个邮件消息
	// Message mailMessage = new MimeMessage(sendMailSession);
	// // 设置邮件消息的发送者
	// mailMessage.setFrom(new InternetAddress(userName));
	// // 创建邮件的接收者地址，并设置到邮件消息中
	// mailMessage.setRecipient(Message.RecipientType.TO,
	// new InternetAddress(to));
	// // 设置邮件消息的主题
	// mailMessage.setSubject("Test Email");
	// // 设置邮件消息发送的时间
	// mailMessage.setSentDate(new Date());
	//
	// // 正文
	// MimeBodyPart text = new MimeBodyPart();
	// text.setContent("我的头像：<img src='cid:headImg'>",
	// "text/html;charset=UTF-8");
	//
	// // 正文图片
	// MimeBodyPart image = new MimeBodyPart();
	// image.setDataHandler(
	// new DataHandler(new FileDataSource("1_jianggujin.jpg")));
	// image.setContentID("headImg");
	//
	// // 附件
	// MimeBodyPart attach = new MimeBodyPart();
	// DataHandler dh = new DataHandler(new FileDataSource("1_jianggujin.jpg"));
	// attach.setDataHandler(dh);
	// attach.setFileName(MimeUtility.encodeWord(dh.getName()));
	//
	// // 描述关系:正文和图片
	// MimeMultipart mp1 = new MimeMultipart();
	// mp1.addBodyPart(text);
	// mp1.addBodyPart(image);
	// mp1.setSubType("related");
	//
	// // 正文
	// MimeBodyPart content = new MimeBodyPart();
	// content.setContent(mp1);
	//
	// MimeMultipart multipart = new MimeMultipart("mixed");
	// multipart.addBodyPart(content);
	// multipart.addBodyPart(attach);
	//
	// mailMessage.setContent(multipart);
	// // 发送邮件
	// Transport.send(mailMessage);
	// }
	public static void main(String[] args) {
		GlobalData.setMailSmtpHost("mail.bjsasc.com");
		GlobalData.setMailSmtpPort("25");
		GlobalData.setMailUserName("wangjunjie@bjsasc.com");
		GlobalData.setMailUserPwd("ys5793513.");
		try {
			//SendMailUtil.sendHtmlMail("179849965@qq.com", "测试html主题", "<html><body><h2>测试</h2><h1>内容</h1></body></html>");
			SendMailUtil.sendAttachmentMail("179849965@qq.com", "测试html主题", "","");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("发送成功");
	}
}

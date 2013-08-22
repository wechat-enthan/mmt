package lemon.weixin.xmlconvert;

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import lemon.weixin.bean.message.EventMessage;
import lemon.weixin.bean.message.EventType;
import lemon.weixin.bean.message.ImageMessage;
import lemon.weixin.bean.message.LinkMessage;
import lemon.weixin.bean.message.LocationMessage;
import lemon.weixin.bean.message.TextMessage;
import lemon.weixin.util.WXHelper;

import com.thoughtworks.xstream.XStream;

import static org.junit.Assert.*;

/**
 * Test XML and Object convert via xstream
 * 
 * @author lemon
 * 
 */
@RunWith(JUnit4.class)
public class WXXMLConvert {
	private XStream xStream;

	public WXXMLConvert() {
		xStream = WXHelper.createXstream();
	}

	@Test
	@Ignore
	public void testTextMessage() {
		xStream.processAnnotations(TextMessage.class);
		TextMessage txt = new TextMessage();
		txt.setToUserName("weixin");
		txt.setFromUserName("lemon");
		txt.setCreateTime(new Date().getTime());
		txt.setContent("hello,weixin, I am \"lemon\".");
		txt.setMsgId(1024102410241024L);
		String msg = xStream.toXML(txt);
		System.out.println(msg);
		System.out.println(txt.getContent());
		TextMessage txt2 = (TextMessage) xStream.fromXML(msg);
		System.out.println(txt2.getContent());
		assertTrue("Text message convert succesfully.",
				"hello,weixin, I am \"lemon\".".equals(txt2.getContent()));
	}

	@Test
	@Ignore
	public void testImageMessage() {
		xStream.processAnnotations(ImageMessage.class);
		ImageMessage img = new ImageMessage();
		img.setToUserName("weixin");
		img.setFromUserName("lemon");
		img.setCreateTime(new Date().getTime());
		img.setPicUrl("http://www.baidu.com/sadsaf");
		img.setMsgId(1024102410241024L);
		String msg = xStream.toXML(img);
		System.out.println(msg);
		System.out.println(img.getPicUrl());
		ImageMessage img2 = (ImageMessage) xStream.fromXML(msg);
		System.out.println(img2.getPicUrl());
		assertTrue("Image message convert succesfully.",
				"http://www.baidu.com/sadsaf".equals(img2.getPicUrl()));
	}
	
	@Test
	@Ignore
	public void testLocationMessage() {
		xStream.processAnnotations(LocationMessage.class);
		LocationMessage msg = new LocationMessage();
		msg.setToUserName("weixin");
		msg.setFromUserName("lemon");
		msg.setCreateTime(new Date().getTime());
		msg.setLocation_X(23.134521);
		msg.setLocation_Y(113.358803);
		msg.setScale(20);
		msg.setLabel("I am here.<xml>\"\"</xml>");
		msg.setMsgId(1024102410241024L);
		String str = xStream.toXML(msg);
		System.out.println(str);
		System.out.println(msg.getLabel());
		LocationMessage msg2 = (LocationMessage) xStream.fromXML(str);
		System.out.println(msg2.getLabel());

		assertTrue("Location X message convert faild.",
				msg.getLocation_X() == msg2.getLocation_X());
		assertTrue("Location Y message convert faild.",
				msg.getLocation_Y() == msg2.getLocation_Y());
		assertTrue("Location Label message convert faild.",
				"I am here.<xml>\"\"</xml>".equals(msg2.getLabel()));
	}
	
	@Test
	@Ignore
	public void testLinkMessage() {
		xStream.processAnnotations(LinkMessage.class);
		LinkMessage msg = new LinkMessage();
		msg.setToUserName("weixin");
		msg.setFromUserName("lemon");
		msg.setCreateTime(new Date().getTime());
		msg.setTitle("Link \"TEST\" Title");
		msg.setDescription("Link DESC");
		msg.setUrl("http://www.163.com/s/a/d/f/a");
		msg.setMsgId(1024102410241024L);
		String str = xStream.toXML(msg);
		System.out.println(str);
		System.out.println(msg.getTitle());
		LinkMessage msg2 = (LinkMessage) xStream.fromXML(str);
		System.out.println(msg2.getTitle());
		
		assertTrue("Link Title message convert faild.",
				"Link \"TEST\" Title".equals(msg2.getTitle()));
		assertTrue("Link DESC message convert faild.",
				"Link DESC".equals(msg2.getDescription()));
		assertTrue("Link URL message convert faild.",
				"http://www.163.com/s/a/d/f/a".equals(msg2.getUrl()));
	}
	
	@Test
	public void testEventMessage() {
		xStream.processAnnotations(EventMessage.class);
		EventMessage msg = new EventMessage();
		msg.setToUserName("weixin");
		msg.setFromUserName("lemon");
		msg.setCreateTime(new Date().getTime());
		msg.setEventType(EventType.SUBSCRIBE);
		msg.setEventKey("0dfsafkqwnriksdk");
		msg.setMsgId(1024102410241024L);
		String str = xStream.toXML(msg);
		System.out.println(str);
		System.out.println(msg.getEventType());
		EventMessage msg2 = (EventMessage) xStream.fromXML(str);
		System.out.println(msg2.getEventType());
		
		assertTrue("EVENT TYPE message convert faild.",
				EventType.SUBSCRIBE.equals(msg2.getEventType()));
		assertTrue("EVENT KEYS message convert faild.",
				"0dfsafkqwnriksdk".equals(msg2.getEventKey()));
	}

}
package com.sym;

import com.sym.demo.event.MemberLikeEvent;
import javafx.scene.media.MediaErrorEvent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EventApplicationTests {

	@Resource
	private ApplicationEventPublisher publisher;


	@Test
	public void contextLoads() {
		MemberLikeEvent mediaErrorEvent = new MemberLikeEvent(this, 1L, 2L);
		publisher.publishEvent(mediaErrorEvent);
	}

}

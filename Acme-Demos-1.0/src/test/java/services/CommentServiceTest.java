package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import domain.Comment;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml", "classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class CommentServiceTest extends AbstractTest {
	
	//BAsed services test---
	@Autowired
	private CommentService commentService;
	@Autowired
	private DemoService demoService;
	
	
	/*
	 * An actor who is not authenticated must be able to:
		Post a comment about a demo.
	 */
	@Test
	public void create(){
		Comment c = commentService.create();
		c.setAuthor("Peter Crouch");
		c.setDemo(demoService.findOne(17));
		c.setStars(2);
		c.setText("Cool Demo");
		
		commentService.save(c);
		
		for(Comment com : commentService.findAll())
			System.out.println(com.getAuthor() + ": " + com.getText() + " (" +com.getMoment() +")");
	}

}

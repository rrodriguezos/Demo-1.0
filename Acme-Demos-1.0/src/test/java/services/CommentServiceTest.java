package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

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
	
	
	/*
	 * An actor who is authenticated as an administrator must be able to
	 * delete a comment that he or she finds inappropriate
	 */
	@Test
	public void testPositiveDelete(){
		Integer numCommentsOld;
		Integer numCommentsNew;
		
		authenticate("admin");
		
		numCommentsOld = commentService.findAll().size();
		commentService.delete(30);
		numCommentsNew = commentService.findAll().size();
		
		Assert.isTrue(numCommentsNew == numCommentsOld - 1);
		
		unauthenticate();
	}
	
	/*
	 * An actor who is authenticated as an administrator must be able to
	 * delete a comment that he or she finds inappropriate
	 * 
	 * Error: Admin not logged
	 */
	@Test(expected = java.lang.IllegalArgumentException.class)
	public void testNegativeDeleteWithoutAdmin(){
		Integer numCommentsOld;
		Integer numCommentsNew;
		
		unauthenticate();
		
		numCommentsOld = commentService.findAll().size();
		commentService.delete(30);
		numCommentsNew = commentService.findAll().size();
		
		Assert.isTrue(numCommentsNew == numCommentsOld - 1);
		
		unauthenticate();
	}
	
	/*
	 * An actor who is authenticated as an administrator must be able to
	 * delete a comment that he or she finds inappropriate
	 * 
	 * Error: Comment not exist
	 */
	@Test(expected = java.lang.IllegalArgumentException.class)
	public void testNegativeDeleteCommentNotExist(){
		Integer numCommentsOld;
		Integer numCommentsNew;
		
		unauthenticate();
		
		numCommentsOld = commentService.findAll().size();
		commentService.delete(1000);
		numCommentsNew = commentService.findAll().size();
		
		Assert.isTrue(numCommentsNew == numCommentsOld - 1);
		
		unauthenticate();
	}
}

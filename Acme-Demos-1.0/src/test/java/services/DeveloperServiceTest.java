package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import domain.Developer;

import forms.DeveloperRegisterForm;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml", "classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class DeveloperServiceTest extends AbstractTest {
	
	//BAsed services test---
	@Autowired
	private DeveloperService developerService;
	
	
	/*
	 * An actor who is not authenticated must be able to:
		Register to the system as a developer.
	 */
	@Test
	public void register(){
		DeveloperRegisterForm df = new DeveloperRegisterForm();
		df.setName("Antonio");
		df.setAccept(true);
		df.setConfirmPassword("123456789");
		df.setEmailAddress("antonio@acmedemos.net");
		df.setPassword("123456789");
		df.setSurname("Rodríguez");
		df.setUsername("antorod");
		
		Developer d = developerService.reconstruct(df);
		developerService.save(d);
		
		for(Developer del : developerService.findAll())
			System.out.println(del.getUserAccount().getUsername());
		
	}
	
	@Test(expected = Exception.class)
	public void registerWrongPAssword(){
		DeveloperRegisterForm df = new DeveloperRegisterForm();
		df.setName("Antonio");
		df.setAccept(true);
		df.setConfirmPassword("ddwevrteg");
		df.setEmailAddress("antonio@acmedemos.net");
		df.setPassword("123456789");
		df.setSurname("Rodríguez");
		df.setUsername("antorod");
		
		Developer d = developerService.reconstruct(df);
		developerService.save(d);
	}
	
	@Test(expected = Exception.class)
	public void registerNotUnique(){
		DeveloperRegisterForm df = new DeveloperRegisterForm();
		df.setName("Antonio");
		df.setAccept(true);
		df.setConfirmPassword("12345689");
		df.setEmailAddress("antonio@acmedemos.net");
		df.setPassword("123456789");
		df.setSurname("Rodríguez");
		df.setUsername("developer1");
		
		Developer d = developerService.reconstruct(df);
		developerService.save(d);}
}

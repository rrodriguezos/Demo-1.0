package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Investment;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml", "classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class InvestmentServiceTest extends AbstractTest {
	
	//BAsed services test---
	@Autowired
	private InvestmentService investmentService;
	@Autowired
	private InvestorService investorService;
	
	
	/*
	 * An actor who is authenticated as an investor must be able to:
		Manage his or her investments, which includes listing them...
	 */
	@Test
	public void list(){
		authenticate("investor1");
		Collection<Investment>inv = investmentService.findInvestmentByInvestor(investorService.findByPrincipal().getId());
		
		for(Investment i : inv){
			System.out.println(i.getDescription());
		}
	}
	
	
	/*
	 * An actor who is authenticated as an investor must be able to
	 * create an investment regarding an existing demo
	 */
	@Test
	public void testPositiveCreateInvestment(){
		Investment investment;
		Integer numInvestmentOld;
		Integer numInvestmentNew;
		
		authenticate("investor1");
		
		investment = investmentService.create(16);
		investment.setDescription("Descripcion de prueba");
		
		
		numInvestmentOld = investmentService.findAll().size();
		investmentService.save(investment);
		numInvestmentNew = investmentService.findAll().size();
		
		Assert.isTrue(numInvestmentNew == numInvestmentOld + 1);
		
		unauthenticate();
	}
	
	/*
	 * An actor who is authenticated as an investor must be able to
	 * create an investment regarding an existing demo
	 * 
	 * Error: Fields blank
	 */
	@Test(expected = javax.validation.ConstraintViolationException.class)
	public void testNegativeCreateInvestmentFieldsBlank(){
		Investment investment;
		Integer numInvestmentOld;
		Integer numInvestmentNew;
		
		authenticate("investor1");
		
		investment = investmentService.create(16);
		
		numInvestmentOld = investmentService.findAll().size();
		investmentService.save(investment);
		numInvestmentNew = investmentService.findAll().size();
		
		Assert.isTrue(numInvestmentNew == numInvestmentOld + 1);
		
		unauthenticate();
	}
	
	/*
	 * An actor who is authenticated as an investor must be able to
	 * create an investment regarding an existing demo
	 * 
	 * Error: Investor not logged
	 */
	@Test(expected = java.lang.IllegalArgumentException.class)
	public void testNegativeCreateInvestmentWithoutInvestor(){
		Investment investment;
		Integer numInvestmentOld;
		Integer numInvestmentNew;
		
		unauthenticate();
		
		investment = investmentService.create(16);
		investment.setDescription("Prueba");
		
		numInvestmentOld = investmentService.findAll().size();
		investmentService.save(investment);
		numInvestmentNew = investmentService.findAll().size();
		
		Assert.isTrue(numInvestmentNew == numInvestmentOld + 1);
		
		unauthenticate();
	}

}

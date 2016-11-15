package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import domain.Investment;

import utilities.AbstractTest;

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

}

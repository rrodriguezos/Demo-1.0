package services;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import domain.Instalment;


import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml", "classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class InstalmentServiceTest extends AbstractTest {
	
	//BAsed services test---
	@Autowired
	private InstalmentService instalmentService;
	
	
	/*
	 * An actor who is authenticated as an investor must be able to:
	Manage his or her investments, which includes listing them, 
	modifying instalments that have not taken place...
	 */
	@Test
	public void edit(){
		authenticate("investor3");
		Instalment i = instalmentService.findOne(56);
		
		System.out.println("ANTES: "+ i.getInvestment().getDescription() + ": " + i.getAmount());
		
		i.setAmount(2800.0);
		instalmentService.save(i);

		System.out.println("DESPUES: "+ i.getInvestment().getDescription() + ": " + i.getAmount());
	}
	
	/*
	 * An actor who is authenticated as an investor must be able to:
		Manage his or her investments, which includes listing them, 
		modifying instalments that have not taken place, 
		and creating new instalments for a given investment.
	 */
	@Test 
	public void create(){
		authenticate("investor3");
		
		Instalment ins = instalmentService.create(53);
		ins.setAmount(3000.0);
		ins.setInstalmentDate(new Date(System.currentTimeMillis() - 100));
		
		instalmentService.save(ins);
		
		for(Instalment i : ins.getInvestment().getInstalments())
			System.out.println(i.getInstalmentDate() + ": " + i.getAmount());
		
	}

}

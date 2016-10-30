package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;


import domain.Instalment;
import domain.Investment;
import domain.Investor;

import repositories.InvestmentRepository;

@Service
@Transactional
public class InvestmentService {
	
	// Managed repository -------------------
	@Autowired
	private InvestmentRepository investmentRepository;
	
	// Supporting Services ------------------
	@Autowired
	private InvestorService investorService;
	
	
	//CRUD Methods-----------------------------------
	public Investment create(){
		Investor i = investorService.findByPrincipal();
		Assert.notNull(i);
		Investment res = new Investment();
		Collection<Instalment> instal = new ArrayList<Instalment>();
		res.setInvestor(i);
		res.setInstalments(instal);
		return res;
		
	}
	
	public Collection<Investment> findAll(){
		Collection<Investment> res = investmentRepository.findAll();
		Assert.notNull(res);
		return res;
	}
	
	public Investment findOne(int id){
		Investment res = investmentRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}
	
	public void save (Investment i){
		Assert.notNull(i);
		investmentRepository.saveAndFlush(i);
	}
	
	public void delete(Investment i){
		Assert.notNull(i);
		checkPrincipal(i.getInvestor());
		investmentRepository.delete(i);
	}
	
	
	//Other Bussines Methods---------------------------
	public Collection<Investment>findInvestmentByInvestor(int investorId){
		Collection<Investment> res = investmentRepository.findInvestmentByInvestor(investorId);
		Assert.notNull(res);
		return res;
	}
	
	private void checkPrincipal(Investor i) {
		Investor inv = investorService.findByPrincipal();
		Assert.isTrue(inv != null);

		Assert.isTrue(inv.equals(i));
	}

}

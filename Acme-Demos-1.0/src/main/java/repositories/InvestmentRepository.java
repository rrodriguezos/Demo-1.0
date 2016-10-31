package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Investment;

@Repository
public interface InvestmentRepository extends JpaRepository<Investment,Integer>{
	
	@Query("select i.investments from Investor i where i.id=?1")
	Collection<Investment>findInvestmentByInvestor(int investorId);

	@Query("select i from Investment i where i.investor.id = ?1")
	Collection<Investment> investmentsByInvestorLogged(int id);
	

}

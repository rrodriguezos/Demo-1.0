package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Instalment extends DomainEntity {

	
	
	//Constructor-----------------
	public Instalment() {
		super();
	}
	
	//Attributes-----------------
	private Date instalmentDate;
	private Double amount;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	public Date getInstalmentDate() {
		return instalmentDate;
	}
	
	public void setInstalmentDate(Date instalmentDate) {
		this.instalmentDate = instalmentDate;
	}
	
	@NotNull
	@Min(1)
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	
	//Relationships--------------------
	private Investment investment;
	
	
	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Investment getInvestment() {
		return investment;
	}

	public void setInvestment(Investment investment) {
		this.investment = investment;
	}
	
	
	
	
	

}

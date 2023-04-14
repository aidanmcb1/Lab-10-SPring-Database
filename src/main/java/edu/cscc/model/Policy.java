package edu.cscc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Policy {
	public Policy() {}
	
	public Policy(String companyCode, double coverageAmount) {
		this.companyCode = companyCode;
		this.coverageAmount = coverageAmount;
	}
	
	public Policy(long id, String companyCode, double coverageAmount) {
		this.id = id;
		this.companyCode = companyCode;
		this.coverageAmount = coverageAmount;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long id;
	@Column(name="cmp_code")
	public String companyCode;
	public double coverageAmount;
}

package com.example.spring.entity;



import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;



@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Audit {

	@Temporal(TemporalType.DATE)
	@CreatedDate
	@Column(name = "create_dt",nullable = false,updatable = false)
	private Date cratedDate;
	
	
	@Temporal(TemporalType.DATE)
	@LastModifiedDate
	@Column(name = "update_dt",nullable = false)
	private Date updateDate;
	
	
	public Audit() {
		// TODO Auto-generated constructor stub
	}


	public Date getCratedDate() {
		return cratedDate;
	}


	public void setCratedDate(Date cratedDate) {
		this.cratedDate = cratedDate;
	}


	public Date getUpdateDate() {
		return updateDate;
	}


	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}


	@Override
	public String toString() {
		return "Audit [cratedDate=" + cratedDate + ", updateDate=" + updateDate + "]";
	}


	public Audit(Date cratedDate, Date updateDate) {
		super();
		this.cratedDate = cratedDate;
		this.updateDate = updateDate;
	}
	
	
}

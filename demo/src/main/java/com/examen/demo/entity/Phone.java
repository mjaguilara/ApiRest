package com.examen.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="phones")
public class Phone {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@ManyToOne
    @JoinColumn(name = "FK_USER")
    private User user;
	
	@Column(name = "number")
	private String number;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "countryCode")
	private String  countryCode;

}

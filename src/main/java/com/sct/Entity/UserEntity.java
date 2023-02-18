package com.sct.Entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="userA")
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="USERNAME")
	private String userName;
	
	@Column(name="PASS")
	private String pass;
	
	@Column(name="ADDRESS")
	private String address;
	
	@Column (name = "CHECK_COUNT_LOGIN")
	private Integer check_login;
	
	@Column(name = "OPT")
	private String opt;
	
	@Column(name = "STATUS")
	private Integer status;
	
	@Column(name= "DATE")
	private Date  date;
	
	@Column(name = "IP")
	private String ip;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name="User_Role",joinColumns=@JoinColumn(name="User_id"),inverseJoinColumns =@JoinColumn(name="role_id"))
	private List<RoleEntity> roleEntity = new ArrayList<RoleEntity>();
}

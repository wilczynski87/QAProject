package com.qaproject.demo.clients;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import com.qaproject.demo.address.Address;

@MappedSuperclass
public abstract class Client {
	
		@Id
		@Column(name = "id", nullable = false) 
		private String id = UUID.randomUUID().toString();
		
		@OneToOne(cascade = CascadeType.ALL)
		@JoinColumn(name = "user_address")
		private Address address;
		
		private String firm;
		private String fullName;
		private String email;
		private String phone;
		private String password;
		
		public Client() {
		};
		
		//for unit test purposes
		public Client(String id) {
			this.id = id;
		}
		
		public String getId() {
			return this.id;
		}
		
		public void setId(String id) {
			this.id = id;
		}
		
		public void setId() {
			this.id = UUID.randomUUID().toString();
		}
		
		public String getFirm() {
			return firm;
		}
		public void setFirm(String firm) {
			this.firm = firm;
		}
		public String getFullName() {
			return fullName;
		}
		public void setFullName(String fullName) {
			this.fullName = fullName;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public Address getAddress() {
			return address;
		}
		public void setAddress(Address address) {
			this.address = address;
		}
		
}

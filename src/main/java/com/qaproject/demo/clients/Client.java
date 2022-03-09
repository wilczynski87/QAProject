package com.qaproject.demo.clients;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Client {
		
		private String firm;
		private String fullName;
		private String address;
		private String email;
		private String phone;
		private String password;
		
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
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
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
		@Override
		public String toString() {
			return "Client [firm=" + firm + ", fullName=" + fullName + ", address=" + address + ", email=" + email
					+ ", phone=" + phone + ", password=" + password + "]";
		}
}

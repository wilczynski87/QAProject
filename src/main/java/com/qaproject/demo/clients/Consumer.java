package com.qaproject.demo.clients;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.qaproject.demo.auctions.Auction;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Consumer extends Client {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToMany(mappedBy = "whoCreated", cascade = CascadeType.ALL)
	private List<Auction> listOfAuction;
	
	public Consumer() {
		super();
	}
	
	//for unit test purposes
	public Consumer(Integer id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, listOfAuction);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Consumer other = (Consumer) obj;
		return id == other.id && Objects.equals(listOfAuction, other.listOfAuction);
	}

	@Override
	public String toString() {
		return "Consumer [id=" + id + ", email= " + getEmail() + ", password= " + getPassword() + "]";
	}
	
}

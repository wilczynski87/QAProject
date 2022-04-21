package com.qaproject.demo.clients;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.qaproject.demo.auctions.Auction;

@Table(name = "consumer")
@Inheritance(
    strategy = InheritanceType.TABLE_PER_CLASS
)
@Entity
public class Consumer extends Client {

	@OneToMany(mappedBy = "whoCreated", cascade = CascadeType.ALL)
	private List<Auction> listOfAuction;
	
	public Consumer() {
		super();
	}
	
	public Consumer(String id) {
		super(id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), listOfAuction);
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
		return getId() == other.getId() && Objects.equals(listOfAuction, other.listOfAuction);
	}

	@Override
	public String toString() {
		return "Consumer [id=" + getId() + ", email= " + getEmail() + ", password= " + getPassword() + "]";
	}
	
}

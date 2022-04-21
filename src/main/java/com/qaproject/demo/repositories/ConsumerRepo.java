package com.qaproject.demo.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qaproject.demo.clients.Consumer;

@Repository
public interface ConsumerRepo extends JpaRepository<Consumer, Integer> {
	
	public Consumer findConsumerByEmailAndPassword(String email, String password);

	@Query(value = "SELECT * FROM consumer WHERE id = ?1;", nativeQuery = true)
	public Consumer getByUUID(UUID customerId);

	public Consumer getConsumerById(String customerId);
	
}

package com.qaproject.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qaproject.demo.clients.Consumer;

@Repository
public interface ConsumerRepo extends JpaRepository<Consumer, Integer> {
	
	public Consumer findConsumerByEmailAndPassword(String email, String password);
	
}

package com.qaproject.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qaproject.demo.clients.Professional;

@Repository
public interface ProfessionalRepo extends JpaRepository<Professional, Integer> {

	public Professional findProfessionalByEmailAndPassword(String email, String password);
}

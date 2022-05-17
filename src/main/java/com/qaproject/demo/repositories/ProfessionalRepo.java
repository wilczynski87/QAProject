package com.qaproject.demo.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qaproject.demo.address.ProfPos;
import com.qaproject.demo.clients.Professional;

@Repository
public interface ProfessionalRepo extends JpaRepository<Professional, Integer> {

	public Professional findProfessionalByEmailAndPassword(String email, String password);
	
	@Query(value = "SELECT TOP 1 * FROM professional WHERE id = ?1", nativeQuery = true)
	public Optional<Professional> getByUUID(UUID professionalId);

	public Professional getProfessionalById(String profId);
	
	@Query(value = "SELECT professional.email, professional.firm, address.lat, address.lng\n"
			+ "FROM professional LEFT JOIN address ON professional.user_address = address.id", nativeQuery = true)
	public List<ProfPos> getProfessionalsPos();
}

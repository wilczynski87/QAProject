package com.qaproject.demo.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qaproject.demo.clients.Professional;
import com.qaproject.demo.repositories.ProfessionalRepo;

@SpringBootTest
public class ServiceTestProfessional {

	@Autowired
	private ProfessionalService ps;
	
	@MockBean
	private ProfessionalRepo pr;
	
	@Test
	public void login() {
		//Given
		String email = "email@email.com";
		String password = "password";
		Professional Professional = new Professional();
		Professional.setEmail("email@email.com");
		Professional.setPassword("password");
		//when
		Mockito.when(this.pr.findProfessionalByEmailAndPassword(email, password)).thenReturn(Professional);
		//Then
		assertThat(this.ps.login(email, password)).isEqualTo(Professional);
		//Verify
		Mockito.verify(this.pr, Mockito.times(1)).findProfessionalByEmailAndPassword(Mockito.anyString(), Mockito.anyString());
	}
	
	@Test
	public void register() {
		//Given
		Professional consumerToSave = new Professional();
		consumerToSave.setEmail("email@email.com");
		consumerToSave.setPassword("password");
		Professional ConsumerSaved = new Professional(1);
		ConsumerSaved.setEmail("email@email.com");
		ConsumerSaved.setPassword("password");
		//When
		Mockito.when(this.pr.save(consumerToSave)).thenReturn(ConsumerSaved);
		//Then
		assertThat(this.ps.register(consumerToSave)).isEqualTo(ConsumerSaved);
		//Verify
		Mockito.verify(this.pr, Mockito.times(1)).save(Mockito.any(Professional.class));
	}
	
	@Test
	public void delete() {
		//Given
		int id = 1;
		//When
		Mockito.when(this.pr.existsById(id)).thenReturn(true);	
		//Then
		assertThat(this.ps.delete(id)).isEqualTo(true);
		//Verify
		Mockito.verify(this.pr, Mockito.times(1)).deleteById(Mockito.anyInt());
		Mockito.verify(this.pr, Mockito.times(1)).existsById(Mockito.anyInt());			
	}
	
	@Test
	public void deleteGoWrong() {
		//Given
		int id = 1;
		//When
		Mockito.when(this.pr.existsById(id)).thenReturn(false);	
		//Then
		assertThat(this.ps.delete(id)).isEqualTo(false);
		//Verify
		Mockito.verify(this.pr, Mockito.times(0)).deleteById(Mockito.anyInt());
		Mockito.verify(this.pr, Mockito.times(1)).existsById(Mockito.anyInt());			
	}
	
	@Test
	public void change() {
		//Given
		Professional consumerToChange = new Professional(1);
		consumerToChange.setEmail("email@email.com");
		consumerToChange.setPassword("password");
		Professional ConsumerChanged = new Professional(1);
		ConsumerChanged.setEmail("sssss@sssss.com");
		ConsumerChanged.setPassword("ssssssss");
		String email = "email@email.com";
		String password = "password";
		//When
		Mockito.when(this.pr.findProfessionalByEmailAndPassword(email, password)).thenReturn(consumerToChange);
		Mockito.when(this.pr.save(ConsumerChanged)).thenReturn(ConsumerChanged);
		//Then
		assertThat(this.ps.change(ConsumerChanged, email, password)).isEqualTo(ConsumerChanged);
		//Verify
		Mockito.verify(this.pr, Mockito.times(1)).findProfessionalByEmailAndPassword(email, password);
		Mockito.verify(this.pr, Mockito.times(1)).save(ConsumerChanged);
	}
}

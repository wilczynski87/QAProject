package com.qaproject.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qaproject.demo.address.Address;
import com.qaproject.demo.address.ProfPos;
import com.qaproject.demo.auctions.Bid;
import com.qaproject.demo.clients.Consumer;
import com.qaproject.demo.clients.Professional;
import com.qaproject.demo.exceptions.ClientAlredyExist;
import com.qaproject.demo.exceptions.NoClientFound;
import com.qaproject.demo.repositories.BidRepo;
import com.qaproject.demo.repositories.ProfessionalRepo;

@SpringBootTest
public class ServiceTestProfessional {

	@Autowired
	private ProfessionalService ps;
	
	@MockBean
	private ProfessionalRepo pr;
	private BidRepo br;
	
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
	public void login_throwException_becauceClientNotInDB() {
		// Given
		String email = "@dupa.com";
		String password = "password1...";
		
		// When
		Exception exception = assertThrows(NoClientFound.class, () -> {
			this.ps.login(email, password);
	    });
		
		// Then		
		String expectedMessage = "No such Professional";
	    String actualMessage = exception.getMessage();
	    assertTrue(actualMessage.contains(expectedMessage));
	    
		// Verify
		Mockito.verify(this.pr, Mockito.times(1)).findProfessionalByEmailAndPassword(Mockito.anyString(), Mockito.anyString());
	}
	
	//do not have any sense... need more work on it
	@Test
	public void register() {
		//Given
		String profId = UUID.randomUUID().toString();
		Professional consumerToSave = new Professional(profId);
		consumerToSave.setEmail("email@email.com");
		consumerToSave.setPassword("password");
		Professional ConsumerSaved = new Professional(profId);
		ConsumerSaved.setEmail("email@email.com");
		ConsumerSaved.setPassword("password");
		//When
		Mockito.when(this.pr.save(consumerToSave)).thenReturn(ConsumerSaved);
		//Then
		assertThat(this.ps.register(consumerToSave)).isEqualTo(ConsumerSaved);
		//Verify
		Mockito.verify(this.pr, Mockito.times(1)).save(Mockito.any(Professional.class));
	}
	
//	@Test
//	public void delete() {
//		//Given
//		int id = 1;
//		//When
//		Mockito.when(this.pr.existsById(id)).thenReturn(true);	
//		//Then
//		assertThat(this.ps.delete(id)).isEqualTo(true);
//		//Verify
//		Mockito.verify(this.pr, Mockito.times(1)).deleteById(Mockito.anyInt());
//		Mockito.verify(this.pr, Mockito.times(1)).existsById(Mockito.anyInt());			
//	}
	
//	@Test
//	public void deleteGoWrong() {
//		//Given
//		int id = 1;
//		//When
//		Mockito.when(this.pr.existsById(id)).thenReturn(false);	
//		//Then
//		assertThat(this.ps.delete(id)).isEqualTo(false);
//		//Verify
//		Mockito.verify(this.pr, Mockito.times(0)).deleteById(Mockito.anyInt());
//		Mockito.verify(this.pr, Mockito.times(1)).existsById(Mockito.anyInt());			
//	}
	
	@Test
	public void change() throws ClientAlredyExist {
		//Given
		String oldEmail = "email@email.com";
		String oldPassword = "password";
		String oldFirm = "Ltd...";
		String profId = UUID.randomUUID().toString();
		Professional oldClient = new Professional(profId);
		oldClient.setEmail(oldEmail);
		oldClient.setPassword(oldPassword);
		oldClient.setFirm(oldFirm);
		Professional body = new Professional(profId);
		String newEmail = "sssss@sssss.com";
		String newPassword = "ssssssss";
		body.setEmail(newEmail);
		body.setPassword(newPassword);
		body.setFirm(oldFirm);
		
		//When
		Mockito.when(this.pr.findProfessionalByEmailAndPassword(oldEmail, oldPassword)).thenReturn(oldClient);
		Mockito.when(this.pr.findProfessionalByEmailAndPassword(newEmail, newPassword)).thenReturn(null);
		Mockito.when(this.pr.save(oldClient)).thenReturn(body);
		
		//Then
		assertThat(this.ps.change(body, oldEmail, oldPassword)).isEqualTo(body);
		
		//Verify
		Mockito.verify(this.pr, Mockito.times(1)).findProfessionalByEmailAndPassword(oldEmail, oldPassword);
		Mockito.verify(this.pr, Mockito.times(1)).findProfessionalByEmailAndPassword(newEmail, newPassword);
		Mockito.verify(this.pr, Mockito.times(1)).save(oldClient);
	}
	
	@Test
	public void change_thereIsNoClientToChange_throwException() throws ClientAlredyExist { 
		//Given
		String oldEmail = "email@email.com";
		String oldPassword = "password";
		String oldFirm = "Ltd...";
		String profId = UUID.randomUUID().toString();
		Professional oldClient = new Professional(profId);
		oldClient.setEmail(oldEmail);
		oldClient.setPassword(oldPassword);
		oldClient.setFirm(oldFirm);
		Professional body = new Professional(profId);
		String newEmail = "sssss@sssss.com";
		String newPassword = "ssssssss";
		body.setEmail(newEmail);
		body.setPassword(newPassword);
		body.setFirm(oldFirm);
		
		Mockito.when(this.pr.findProfessionalByEmailAndPassword(oldEmail, oldPassword)).thenReturn(null);
		
		//When
		Exception exception = assertThrows(ClientAlredyExist.class, () -> {
			this.ps.change(body, oldEmail, oldPassword);
	    });
		
		// then		
		String expectedMessage = "I can not find client...";
	    String actualMessage = exception.getMessage();
	    assertTrue(actualMessage.contains(expectedMessage));		
	}
	
	@Test
	public void change_emailToChangeAlredyExists_throwException() throws ClientAlredyExist { 
		//Given
		String oldEmail = "email@email.com";
		String oldPassword = "password";
		String oldFirm = "Ltd...";
		String profId = UUID.randomUUID().toString();
		Professional oldClient = new Professional(profId);
		oldClient.setEmail(oldEmail);
		oldClient.setPassword(oldPassword);
		oldClient.setFirm(oldFirm);
		Professional body = new Professional(profId);
		String newEmail = "sssss@sssss.com";
		String newPassword = "ssssssss";
		body.setEmail(newEmail);
		body.setPassword(newPassword);
		body.setFirm(oldFirm);
		
		Mockito.when(this.pr.findProfessionalByEmailAndPassword(oldEmail, oldPassword)).thenReturn(oldClient);
		Mockito.when(this.pr.findProfessionalByEmailAndPassword(newEmail, newPassword)).thenReturn(body);
		
		//When
		Exception exception = assertThrows(ClientAlredyExist.class, () -> {
			this.ps.change(body, oldEmail, oldPassword);
	    });
		
		// then		
		String expectedMessage = "Client already exist";
	    String actualMessage = exception.getMessage();
	    assertTrue(actualMessage.contains(expectedMessage));		
	}
	
//	@Test
//	public void change_thenFirmIsChanging_reWriteAllBids() throws ClientAlredyExist {
//		//Given
//		String oldEmail = "email@email.com";
//		String oldPassword = "password";
//		String oldFirm = "Ltd...";
//		String profId = UUID.randomUUID().toString();
//		Professional oldClient = new Professional(profId);
//		oldClient.setEmail(oldEmail);
//		oldClient.setPassword(oldPassword);
//		oldClient.setFirm(oldFirm);
//		Professional body = new Professional(profId);
//		String newEmail = "sssss@sssss.com";
//		String newPassword = "ssssssss";
//		body.setEmail(newEmail);
//		body.setPassword(newPassword);
//		body.setFirm("other");
//		List<Bid> listOfBids = new ArrayList<>();
//		Bid myBid = new Bid();
//		myBid.setProfFirm(profId);
//		listOfBids.add(myBid);
//		
//		//When
//		Mockito.when(this.pr.findProfessionalByEmailAndPassword(oldEmail, oldPassword)).thenReturn(oldClient);
//		Mockito.when(this.pr.findProfessionalByEmailAndPassword(newEmail, newPassword)).thenReturn(null);
//		when(this.br.findAllBidByProfFirm(oldClient.getFirm())).thenReturn(listOfBids);
//		Mockito.when(this.pr.save(oldClient)).thenReturn(body);
//		
//		//Then
//		assertThat(this.ps.change(body, oldEmail, oldPassword)).isEqualTo(body);
//	}
	
	@Test
	public void delete() {
		//Given
		String email = "email@email.com";
		String password = "password";
		Professional professional = new Professional();
		professional.setEmail(email);
		professional.setPassword(password);
		//When
		Mockito.when(this.pr.findProfessionalByEmailAndPassword(email, password)).thenReturn(professional);
		//Then
		assertThat(this.ps.delete(email, password)).isFalse();
		//Verify
		Mockito.verify(this.pr, Mockito.times(1)).delete(professional);	
	}
	
//	@Test
//	public void delete_ClientNotFound_ReturnFalse() {
//		//Given
//		String email = "email@email.com";
//		String password = "password";
//		Professional professional = new Professional();
//		professional.setEmail(email);
//		professional.setPassword(password);
//		//When
//		Mockito.when(this.pr.findProfessionalByEmailAndPassword(email, password)).thenReturn(null);
//		//Then
//		Exception exception = assertThrows(NoClientFound.class, () -> {
//			this.pr.findProfessionalByEmailAndPassword(email, password);
//		});
//		
//		// Assert
//		String expectedMessage = "Can not find this professional";
//		String actualMessage = exception.getMessage();
//		assertTrue(actualMessage.contains(expectedMessage));
//	}
	
	@Test
	public void getProfessionals() {
		//Given
		String email = "email@email.com";
		String password = "password";
		double lat = 1.0001;
		double lng = 1.0001;
		Address address = new Address(lat, lng);
		Professional professional = new Professional();
		professional.setEmail(email);
		professional.setPassword(password);
		professional.setAddress(address);
		
		List<Professional> list = new ArrayList<>();
		ProfPos pp = new ProfPos(professional);
		list.add(professional);
		
		when(this.pr.findAll()).thenReturn(list);
		
		assertThat(this.ps.getProfessionals(100, 1, 1)).isNotEmpty();
	}
	
}

package com.qaproject.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qaproject.demo.clients.Consumer;
import com.qaproject.demo.exceptions.ClientAlredyExist;
import com.qaproject.demo.exceptions.NoClientFound;
import com.qaproject.demo.repositories.ConsumerRepo;

@SpringBootTest
public class ServiceTestConsumer {

	@Autowired
	private ConsumerService cs;
	
	@MockBean
	private ConsumerRepo cr;
	
	@Test
	public void login() {
		//Given
		String email = "email@email.com";
		String password = "password";
		Consumer consumer = new Consumer();
		consumer.setEmail("email@email.com");
		consumer.setPassword("password");
		//when
		Mockito.when(this.cr.findConsumerByEmailAndPassword(email, password)).thenReturn(consumer);
		//Then
		assertThat(this.cs.login(email, password)).isEqualTo(consumer);
		//Verify
		Mockito.verify(this.cr, Mockito.times(1)).findConsumerByEmailAndPassword(Mockito.anyString(), Mockito.anyString());
	}
	
	@Test
	public void loginWhenClientNotFoundReturnException() {
		//Arange
		String email = "my@email.com";
		String password = "Password...";
		when(this.cr.findConsumerByEmailAndPassword(email, password)).thenReturn(null);
		
		//Act
		Exception exception = assertThrows(NoClientFound.class, () -> {
			this.cs.login(email, password);
	    });
		
		//Assert
	    String expectedMessage = "No such Client";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	public void register() throws ClientAlredyExist {
		//Given
		String consId = UUID.randomUUID().toString();
		Consumer consumerToSave = new Consumer(consId);
		consumerToSave.setEmail("email2@email.com");
		consumerToSave.setPassword("password");
		Consumer ConsumerSaved = new Consumer(consId);
		ConsumerSaved.setEmail("email2@email.com");
		ConsumerSaved.setPassword("password");
		//When
		Mockito.when(this.cr.save(consumerToSave)).thenReturn(ConsumerSaved);
		//Then
		assertThat(this.cs.register(consumerToSave)).isEqualTo(ConsumerSaved);
		//Verify
		Mockito.verify(this.cr, Mockito.times(1)).save(Mockito.any(Consumer.class));
	}
	
	@Test
	public void registerWhenClientExistReturnException() throws ClientAlredyExist {
		// Arrange
		String consId = UUID.randomUUID().toString();
		Consumer consumer = new Consumer(consId);
		String email = "email2@email.com";
		String password = "Password...";
		consumer.setEmail("email2@email.com");
		consumer.setPassword("Password...");
//		when(this.cs.register(consumer)).thenThrow(ClientAlredyExist.class);
		when(this.cr.findConsumerByEmailAndPassword(email, password)).thenReturn(consumer);
		
		// Act
		Exception exception = assertThrows(ClientAlredyExist.class, () -> {
			this.cs.register(consumer);
		});
		
		// Assert
		String expectedMessage = "Client already exists";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}
	
//	@Test
//	public void register_bodyDoNotHaveEmail_throwException() throws Exception {
//		// Arrange
//		Consumer emptyClient = new Consumer();
//		
//		// Act
//		Exception exception = assertThrows(NoClientFound.class, () -> {
//			this.cs.register(emptyClient);
//		});
//		
//		// Assert
//		String expectedMessage = "Can not find email";
//		String actualMessage = exception.getMessage();
//		assertTrue(actualMessage.contains(expectedMessage));
//	}
	
	@Test
	public void delete() {
		//Given
		String email = "email@email.com";
		String password = "password";
		Consumer consumer = new Consumer();
		consumer.setEmail(email);
		consumer.setPassword(password);
		//When
		Mockito.when(this.cr.findConsumerByEmailAndPassword(email, password)).thenReturn(consumer);
		//Then
		assertThat(this.cs.delete(email, password)).isFalse();
		//Verify
		Mockito.verify(this.cr, Mockito.times(1)).delete(consumer);	
	}
	
	@Test
	public void deleteGoWrong() {
		//Given
		String email = "email@email.com";
		String password = "password1";
		Consumer consumer = new Consumer();
		consumer.setEmail(email);
		consumer.setPassword(password);
		//When
		Mockito.when(this.cr.findConsumerByEmailAndPassword(email, password)).thenReturn(null);
		//Then
		assertThat(this.cs.delete(email, password)).isFalse();
		//Verify
		Mockito.verify(this.cr, Mockito.times(0)).delete(consumer);		
	}

	@Test
	public void change() throws ClientAlredyExist {
		//Given
		String consId = UUID.randomUUID().toString();
		Consumer found = new Consumer(consId);
		found.setEmail("email@email.com");
		found.setPassword("password");
		Consumer body = new Consumer(consId);
		body.setEmail("sssss@sssss.com");
		body.setPassword("ssssssss");
		String oldEmail = "email@email.com";
		String oldPassword = "password";
		
		//When
		when(this.cr.findConsumerByEmailAndPassword(oldEmail, oldPassword)).thenReturn(found);
		when(this.cr.save(found)).thenReturn(body);
		when(this.cr.findConsumerByEmail(body.getEmail())).thenReturn(null);
		//Then
		assertThat(this.cs.change(body, oldEmail, oldPassword)).isEqualTo(body);
		//Verify
		Mockito.verify(this.cr, Mockito.times(1)).findConsumerByEmailAndPassword(oldEmail, oldPassword);
		Mockito.verify(this.cr, Mockito.times(1)).save(found);
	}
		//Given
	
		//When
			
		//Then
			
		//Verify
	@Test
	public void changeClient_throwError_clientEmailAlredyExistInDB() throws ClientAlredyExist {
		//Given
		String consId = UUID.randomUUID().toString();
		Consumer found = new Consumer(consId);
		found.setEmail("email@email.com");
		found.setPassword("password");
		Consumer body = new Consumer(consId);
		body.setEmail("sssss@sssss.com");
		body.setPassword("ssssssss");
		String oldEmail = "email@email.com";
		String oldPassword = "password";
		
		//When
		Mockito.when(this.cr.findConsumerByEmailAndPassword(oldEmail, oldPassword)).thenReturn(found);
//		Mockito.when(this.cr.save(found)).thenReturn(body);
		when(this.cr.findConsumerByEmail(body.getEmail())).thenReturn(body);
		
		//Then
		Exception exception = assertThrows(ClientAlredyExist.class, () -> {
			this.cs.change(body, oldEmail, oldPassword);
	    });
		String expectedMessage = "Client with this details already exist";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
			
		//Verify
	    verify(this.cr, Mockito.times(1)).findConsumerByEmailAndPassword(oldEmail, oldPassword);
	    verify(this.cr, Mockito.times(0)).save(found);
	}
			
}

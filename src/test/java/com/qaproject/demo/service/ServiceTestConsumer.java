package com.qaproject.demo.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qaproject.demo.clients.Consumer;
import com.qaproject.demo.exceptions.ClientAlredyExist;
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
		Consumer consumerToChange = new Consumer(consId);
		consumerToChange.setEmail("email@email.com");
		consumerToChange.setPassword("password");
		Consumer ConsumerChanged = new Consumer(consId);
		ConsumerChanged.setEmail("sssss@sssss.com");
		ConsumerChanged.setPassword("ssssssss");
		String email = "email@email.com";
		String password = "password";
		//When
		Mockito.when(this.cr.findConsumerByEmailAndPassword(email, password)).thenReturn(consumerToChange);
		Mockito.when(this.cr.save(ConsumerChanged)).thenReturn(ConsumerChanged);
		//Then
		assertThat(this.cs.change(ConsumerChanged, email, password)).isEqualTo(ConsumerChanged);
		//Verify
		Mockito.verify(this.cr, Mockito.times(1)).findConsumerByEmailAndPassword(email, password);
		Mockito.verify(this.cr, Mockito.times(1)).save(ConsumerChanged);
	}
		//Given
	
		//When
			
		//Then
			
		//Verify
			
}

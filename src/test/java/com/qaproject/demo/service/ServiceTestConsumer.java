package com.qaproject.demo.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qaproject.demo.clients.Consumer;
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
	public void register() {
		//Given
		Consumer consumerToSave = new Consumer();
		consumerToSave.setEmail("email@email.com");
		consumerToSave.setPassword("password");
		Consumer ConsumerSaved = new Consumer(1);
		ConsumerSaved.setEmail("email@email.com");
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
		int id = 1;
		//When
		Mockito.when(this.cr.existsById(id)).thenReturn(true);	
		//Then
		assertThat(this.cs.delete(id)).isEqualTo(true);
		//Verify
		Mockito.verify(this.cr, Mockito.times(1)).deleteById(Mockito.anyInt());
		Mockito.verify(this.cr, Mockito.times(1)).existsById(Mockito.anyInt());			
	}
	
	@Test
	public void deleteGoWrong() {
		//Given
		int id = 1;
		//When
		Mockito.when(this.cr.existsById(id)).thenReturn(false);	
		//Then
		assertThat(this.cs.delete(id)).isEqualTo(false);
		//Verify
		Mockito.verify(this.cr, Mockito.times(0)).deleteById(Mockito.anyInt());
		Mockito.verify(this.cr, Mockito.times(1)).existsById(Mockito.anyInt());			
	}
	
	@Test
	public void change() {
		//Given
		Consumer consumerToChange = new Consumer(1);
		consumerToChange.setEmail("email@email.com");
		consumerToChange.setPassword("password");
		Consumer ConsumerChanged = new Consumer(1);
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

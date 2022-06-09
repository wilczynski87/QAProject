package com.qaproject.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qaproject.demo.clients.Consumer;
import com.qaproject.demo.clients.Professional;
import com.qaproject.demo.exceptions.NoClientFound;
import com.qaproject.demo.repositories.ConsumerRepo;
import com.qaproject.demo.repositories.ProfessionalRepo;

@SpringBootTest
public class ServiceTestClient {
	
	@Autowired
	private ClientService clientS;
	
	@MockBean
	private ConsumerRepo cr;
	@MockBean
	private ProfessionalRepo pr;
	
	@Test
	public void getClient_returnConsumer_whenConsumerIs() {
		//Given
		String consId = UUID.randomUUID().toString();
		Consumer consumer = new Consumer(consId);
		consumer.setEmail("email@email.com");
		consumer.setPassword("password");
		String profId = UUID.randomUUID().toString();
		Professional professional = new Professional(profId);
		professional.setEmail("email1@email.com");
		professional.setPassword("password");
		
		// when
		when(this.cr.findConsumerByEmailAndPassword("email@email.com", "password")).thenReturn(consumer);
		
		// then
		assertThat(this.clientS.getClient("email@email.com", "password")).isEqualTo(consumer);
	}
	
	@Test
	public void getClient_returnProfessional_whenConsumerIs() {
		//Given
		String consId = UUID.randomUUID().toString();
		Consumer consumer = new Consumer(consId);
		consumer.setEmail("email@email.com");
		consumer.setPassword("password");
		String profId = UUID.randomUUID().toString();
		Professional professional = new Professional(profId);
		professional.setEmail("email1@email.com");
		professional.setPassword("password");
		
		// when
		when(this.pr.findProfessionalByEmailAndPassword("email1@email.com", "password")).thenReturn(professional);
		when(this.cr.findConsumerByEmailAndPassword("email@email.com", "password")).thenReturn(consumer);
		
		// then
		assertThat(this.clientS.getClient("email1@email.com", "password")).isEqualTo(professional);
	}
	
	@Test
	public void getClient_throwError_noClientFound() {
		//Given
		String consId = UUID.randomUUID().toString();
		Consumer consumer = new Consumer(consId);
		consumer.setEmail("email@email.com");
		consumer.setPassword("password");
		String profId = UUID.randomUUID().toString();
		Professional professional = new Professional(profId);
		professional.setEmail("email1@email.com");
		professional.setPassword("password");
		
		// when
		when(this.pr.findProfessionalByEmailAndPassword("email1@email.com", "password")).thenReturn(null);
		when(this.cr.findConsumerByEmailAndPassword("email1@email.com", "password")).thenReturn(null);
		
		// then
		Exception exception = assertThrows(NoClientFound.class, () -> {
			this.clientS.getClient("email1@email.com", "password");
	    });

	    String expectedMessage = "No Professional or Consumer Found";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
	}

}

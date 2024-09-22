package com.centroinformacion.kafka.service;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.centroinformacion.entity.Ejemplo;
import com.centroinformacion.kafka.config.Event;
import com.centroinformacion.kafka.config.EventType;
import com.centroinformacion.kafka.entity.EjemploCreateEvent;

@Component
public class EjemploEventService {

	@Autowired
	private KafkaTemplate<String, Event<?>> producer;
	
	@Value("${topic.customer.name:topic-ejemplo-Jacinto-G1}")
	private String topic;
	
	public void publish(Ejemplo obj) {
	
		EjemploCreateEvent event = new EjemploCreateEvent();
		event.setId(UUID.randomUUID().toString());
		event.setDate(new Date());
		event.setType(EventType.CREATED);
		event.setData(obj);
		
		this.producer.send(topic, event);
	}
}

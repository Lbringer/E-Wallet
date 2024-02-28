package com.walletService.user;

import java.util.Properties;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class UserConfig {

	//Add kafka config
	//Add kafka config to kafka template
	//publish the message
	//user acts as only producer 
	
	public Properties getProducerConfigs() {
		Properties prop = new Properties();
		prop.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		prop.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		prop.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		return prop;
	}
	
	ProducerFactory<String, String> getProducerFactory(){
		return new DefaultKafkaProducerFactory(getProducerConfigs());
	}
	
	@Bean
	KafkaTemplate<String, String> getKafkaTemplate(){
		return new KafkaTemplate<>(getProducerFactory());
	}
	
	
	@Bean
	ObjectMapper getMapper() {
		return new ObjectMapper();
	}
}

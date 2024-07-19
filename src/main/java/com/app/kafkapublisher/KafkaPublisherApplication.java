package com.app.kafkapublisher;

import com.app.kafkapublisher.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class KafkaPublisherApplication {
@Autowired
	private KafkaTemplate<String,Object> kafkaTemplate;
private String topic="techjava";
@GetMapping("/publish/{name}")
public String publishMessage(@PathVariable String name){
	kafkaTemplate.send(topic,"Hi " +name+ " welcome to techjava");
	return "message published";
}
	@GetMapping("/publishJson")
	public String publishMessage() {
		User user = new User(2532, "User88", new String[] { "Bangalore", "BTM", "house 90" });
		kafkaTemplate.send(topic, user);
		return "Json Data published";
	}

	public static void main(String[] args) {
		SpringApplication.run(KafkaPublisherApplication.class, args);
	}

}

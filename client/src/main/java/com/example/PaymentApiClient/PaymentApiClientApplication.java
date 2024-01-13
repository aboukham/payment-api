package com.example.PaymentApiClient;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class PaymentApiClientApplication implements CommandLineRunner {
	private RestTemplate restTemplate;

	public PaymentApiClientApplication(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public static void main(String[] args) {
		SpringApplication.run(PaymentApiClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String url = "http://localhost:8080/api/payment";

		List<PaymentDto> paymentDtos = Arrays.asList(
				new PaymentDto(1, new BigDecimal(100.00)),
				new PaymentDto(2, new BigDecimal(200.00)),
				new PaymentDto(3, new BigDecimal(300.00)),
				new PaymentDto(4, new BigDecimal(400.00)),
				new PaymentDto(5, new BigDecimal(500.00)),
				new PaymentDto(6, new BigDecimal(600.00)),
				new PaymentDto(7, new BigDecimal(700.00))
		);
		String email = "aboukhameabdelaziz@gmail.com";

		//create payments and send notifications
		paymentDtos.forEach(paymentDto -> {
			restTemplate.postForLocation(url + "/create?email={email}", paymentDto, email);
		});

		//return payments and send notifications
		restTemplate.put(url + "/return?email={email}", paymentDtos.get(2), email);
		restTemplate.put(url + "/return?email={email}", paymentDtos.get(3), email);

		//show payments
		paymentDtos.forEach(paymentDto -> {
			String paymentState = restTemplate.getForObject(url + "/{id}", String.class, paymentDto.getId());
			System.out.println(paymentState);
		});




	}
}

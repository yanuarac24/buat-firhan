package com.yanuar.exercise;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ExerciseApplication implements CommandLineRunner {
	String urlGet = "https://60803cdea5be5d00176dd5e8.mockapi.io/api/v1/articles";
	String urlPost = "https://60803cdea5be5d00176dd5e8.mockapi.io/api/v1/articles";
	String urlPut = "https://60803cdea5be5d00176dd5e8.mockapi.io/api/v1/articles/1";
	String urlDelete = "https://60803cdea5be5d00176dd5e8.mockapi.io/api/v1/articles/1";

	public static void main(String[] args) {
		SpringApplication.run(ExerciseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		RestTemplate restTemplate = new RestTemplate();

		// GET Request
		ResponseEntity<String> response = restTemplate.getForEntity(urlGet, String.class);
		System.out.println("Status Response : " + response.getStatusCode());
		System.out.println("Response Body : " + response.getBody());

		// POST Request
//		PostDao postDao = new PostDao();
//		postDao.setTitle("Testing");
//		postDao.setBody("Testing REST Template");
//
//		HttpEntity<PostDao> request = new HttpEntity<>(postDao);
//		ResponseEntity<String> postResponse = restTemplate.postForEntity(urlPost, request, String.class);
//		System.out.println("Status Response : " + postResponse.getStatusCode());
//		System.out.println("Response Body : " + postResponse.getBody());

		// PUT Request
//		HttpEntity<PostDao> putRequest = new HttpEntity<>(postDao);
//		ResponseEntity<String> putResponse = restTemplate.exchange(urlPut, HttpMethod.PUT, putRequest, String.class);
//		System.out.println("Status Response : " + putResponse.getStatusCode());
//		System.out.println("Response Body : " + putResponse.getBody());

		// DELETE Request
//		restTemplate.delete(urlDelete);
	}
}

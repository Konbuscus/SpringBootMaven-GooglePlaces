package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;
import java.util.Scanner;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		System.out.println("Veuillez saisir l'url souhaitée : ");
		Scanner sc = new Scanner(System.in);
		String url = sc.nextLine();

		MessageParser parser = new MessageParser(url);
		Map<String, String> values = parser.ParseMe(url);
		RestoController ctrl = new RestoController(values);

	}



}

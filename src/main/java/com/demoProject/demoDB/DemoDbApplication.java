package com.demoProject.demoDB;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import com.demoProject.demoDB.model.Customer;
import com.demoProject.demoDB.repo.CustomerRepository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class DemoDbApplication implements CommandLineRunner {

	@Autowired
	private CustomerRepository customerRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoDbApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		// delete all documents
		customerRepository.deleteAll();

		// save Customer to Couchbase
		customerRepository.save(Arrays.asList(new Customer("01", "Jack", "Smith"),
				new Customer("02", "Adam", "Johnson"), new Customer("03", "Kim", "Smith"),
				new Customer("04", "David", "Williams"), new Customer("05", "Peter", "Davis")));

		System.out.println("Saved to Database successfully!");

		System.out.println("=============Find All Customer=============");
		List<Customer> custs = (List<Customer>) customerRepository.findAll();
		custs.forEach(System.out::println);

		System.out.println("=============findByLastName('Smith')=============");
		custs = customerRepository.findByLastName("Smith");
		custs.forEach(System.out::println);

	}

}

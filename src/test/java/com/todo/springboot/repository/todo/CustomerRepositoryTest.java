package com.todo.springboot.repository.todo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.todo.springboot.model.AuthenticationType;
import com.todo.springboot.model.Customer;
import com.todo.springboot.repository.CustomerRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class CustomerRepositoryTest {

	@Autowired
	private CustomerRepository repo;
	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void testCreateCustomer1() {

		Customer customer = new Customer();
		customer.setFirstName("David");
		customer.setLastName("Fountaine");
		customer.setPassword("password123");
		customer.setEmail("david.s.fountaine@gmail.com");

		Customer savedCustomer = repo.save(customer);

		assertThat(savedCustomer).isNotNull();
		assertThat(savedCustomer.getId()).isGreaterThan(0);
	}

	@Test
	public void testCreateCustomer2() {

		Customer customer = new Customer();
		customer.setFirstName("Sanya");
		customer.setLastName("Lad");
		customer.setPassword("password456");
		customer.setEmail("sanya.lad2020@gmail.com");

		Customer savedCustomer = repo.save(customer);

		assertThat(savedCustomer).isNotNull();
		assertThat(savedCustomer.getId()).isGreaterThan(0);
	}

	@Test
	public void testCreateCustomer3() {

		Customer customer = new Customer();
		customer.setFirstName("Olivera");
		customer.setLastName("Kostadinoska");
		customer.setPassword("oli2021");
		customer.setEmail("olivera@gmail.com");
		customer.setEnabled(true);

		Customer savedCustomer = repo.save(customer);

		assertThat(savedCustomer).isNotNull();
		assertThat(savedCustomer.getId()).isGreaterThan(0);
	}

	@Test
	public void testListCustomers() {
		Iterable<Customer> customers = repo.findAll();
		customers.forEach(System.out::println);

		assertThat(customers).hasSizeGreaterThan(1);
	}

	@Test
	public void testUpdateCustomer() {
		Integer customerId = 2;
		String lastName = "Lad";

		Customer customer = repo.findById(customerId).get();
		customer.setLastName(lastName);
		customer.setEnabled(true);

		Customer updatedCustomer = repo.save(customer);
		assertThat(updatedCustomer.getLastName()).isEqualTo(lastName);
	}

	@Test
	public void testUpdateAuthenticationType() {
		Integer id = 1;
		repo.updateAuthenticationType(id, AuthenticationType.DATABASE);

		Customer customer = repo.findById(id).get();

		assertThat(customer.getAuthenticationType()).isEqualTo(AuthenticationType.DATABASE);

	}

}

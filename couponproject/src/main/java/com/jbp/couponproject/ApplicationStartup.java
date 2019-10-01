package com.jbp.couponproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.jbp.couponproject.enums.Roles;
import com.jbp.couponproject.models.Profit;
import com.jbp.couponproject.models.UserDTO;
import com.jbp.couponproject.repos.ProfitRepo;
import com.jbp.couponproject.service.JwtUserDetailsService;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {
	/*
	 * This class will build a test database.
	 */

	@Autowired
	JwtUserDetailsService register = new JwtUserDetailsService();

	@Autowired
	ProfitRepo profitRepo;

	@Override
	public void onApplicationEvent(final ApplicationReadyEvent event) {

		UserDTO adminSlava = new UserDTO();
		UserDTO adminViktor = new UserDTO();

		UserDTO managerBob = new UserDTO();
		UserDTO managerJay = new UserDTO();
		UserDTO managerRudy = new UserDTO();

		UserDTO customerPatrick = new UserDTO();
		UserDTO customerMoshe = new UserDTO();
		UserDTO customerPeter = new UserDTO();

		adminSlava.setName("slava");
		adminSlava.setRole(Roles.ADMIN);
		adminSlava.setUsername("slava");
		adminSlava.setPassword("123");

		adminViktor.setName("viktor");
		adminViktor.setRole(Roles.ADMIN);
		adminViktor.setUsername("viktor");
		adminViktor.setPassword("123");

		managerBob.setName("Silent Bob");
		managerBob.setUsername("bob");
		managerBob.setPassword("123");
		managerBob.setRole(Roles.MANAGER);

		managerJay.setName("Simple Jay");
		managerJay.setUsername("jay");
		managerJay.setPassword("123");
		managerJay.setRole(Roles.MANAGER);

		managerRudy.setName("Double Rudy");
		managerRudy.setUsername("rudy");
		managerRudy.setPassword("123");
		managerRudy.setRole(Roles.MANAGER);

		customerMoshe.setName("Legendary Moshe");
		customerMoshe.setUsername("moshe");
		customerMoshe.setPassword("123");
		customerMoshe.setRole(Roles.CUSTOMER);

		customerPatrick.setName("Patrick Star");
		customerPatrick.setUsername("Patrick");
		customerPatrick.setPassword("123");
		customerPatrick.setRole(Roles.CUSTOMER);

		customerPeter.setName("Unknown Peter");
		customerPeter.setUsername("peter");
		customerPeter.setPassword("123");
		customerPeter.setRole(Roles.CUSTOMER);

		try {
			register.save(adminSlava);
			register.save(adminViktor);
			register.save(managerBob);
			register.save(managerJay);
			register.save(managerRudy);
			register.save(customerMoshe);
			register.save(customerPatrick);
			register.save(customerPeter);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Profit profit = new Profit();
		profit.setIncome(0);
		profit.setTransactions(0);
		profit.setWalletID(451);
		profitRepo.save(profit);
		
	}

}
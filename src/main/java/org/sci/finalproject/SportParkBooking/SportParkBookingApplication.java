package org.sci.finalproject.SportParkBooking;

import org.sci.finalproject.SportParkBooking.model.User;
import org.sci.finalproject.SportParkBooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SportParkBookingApplication implements CommandLineRunner {
	@Autowired
	private UserService userService;

	public static void main(String[] args) {

		SpringApplication.run(SportParkBookingApplication.class, args);
	}

//	spring.datasource.url=jdbc:h2:file:../java/org/sci/finalproject/SportParkBooking/database/bookingDb;DB_CLOSE_ON_EXIT=FALSE

		@Override
	public void run(String... args) throws Exception {
		actualRun();
	}

	private void actualRun() {
		User user1 = new User("Ioan Farcas", "nelutufarcas", "adresaMail@ceva.com", "0763532299",
				"parola.mea");
		User user2 = new User("Daniel Szocs", "daniszocs", "adresaMail2@ceva.com", "0763532222",
				"parola.ta");


		userService.register(user1);
		userService.register(user2);


		User testUser = new User();
		testUser.setUserName("flo");
		testUser.setPassword("12345f");
		System.out.println("Testing user login: " + userService.loginUser(testUser));

	}



}

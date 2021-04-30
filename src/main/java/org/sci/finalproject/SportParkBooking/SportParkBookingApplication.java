package org.sci.finalproject.SportParkBooking;

import org.sci.finalproject.SportParkBooking.model.*;
import org.sci.finalproject.SportParkBooking.service.BookingService;
import org.sci.finalproject.SportParkBooking.service.PlayGroundService;
import org.sci.finalproject.SportParkBooking.service.SportService;
import org.sci.finalproject.SportParkBooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SportParkBookingApplication implements CommandLineRunner {
	@Autowired
	private UserService userService;
	@Autowired
	private SportService sportService;
	@Autowired
	private PlayGroundService playGroundService;
	@Autowired
	private BookingService bookingService;

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

		Sport football = new Sport("Football");
		Sport tennis = new Sport("Tennis");

		/*long sportId = sportService.returnSportId("Football"); */
		/**
		 * @brief Cautarea sportID in table Sport da eroare; Probabil pentru ca
		 * la acest moment JAVA nu vede ce s-a adaugat in table. Trebuie sa intrebam...
		 */
		PlayGround playGround1 = new PlayGround("FootballField1",3, 100 );
		PlayGround playGround2 = new PlayGround("FootballField2",3, 80 );

//		sportId = sportService.returnSportId("Tennis");
		PlayGround playGround3 = new PlayGround("TennisField1",4, 50 );


		userService.registerUser(user1);
		userService.registerUser(user2);


//		User testUser = new User();
//		testUser.setUserName("flo");
//		testUser.setPassword("12345f");
//		System.out.println("Testing user login: " + userService.loginUser(testUser));

		sportService.registerSport(football);
		sportService.registerSport(tennis);

		playGroundService.registerPlayGround(playGround1);
		playGroundService.registerPlayGround(playGround2);
		playGroundService.registerPlayGround(playGround3);

		/**
		 * @brief: Before creating of newBooking:
		 *         bookingTotalPrice - should be calculated based on chosen bookingDuration and playGround
		 *         userID / playGroundID should be "find..." based on logged userName / chosen playGround
		 */

		Booking newBooking = new Booking("01/05/2021", "18:00", 1,
										 50, 2, 3, BookingStatusEnum.ACTIVE);

		bookingService.saveNewBooking(newBooking);
	}



}

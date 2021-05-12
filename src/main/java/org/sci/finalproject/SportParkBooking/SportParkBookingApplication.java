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

import java.sql.Date;
import java.sql.Time;

@SpringBootApplication
public class SportParkBookingApplication implements CommandLineRunner {
	@Autowired
	private SportService sportService;
	@Autowired
	private PlayGroundService playGroundService;
	@Autowired
	private UserService userService;
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

//		/**This should be taken from Web Interface*/
//		//****************************************
//		String name = "Ioan Farcas";
//		String userName = "nelutufarcas";
//		String userEmail = "nelutu@nelutu.ro";
//		String userPhone = "0763532299";
//		String password = "parola.nelutu";
//		//************************************************************************
//		User newUser = new User(name, userName, userEmail, userPhone, password);
//		userService.registerUser(newUser);
//
//		/**This should be taken from Web Interface*/
//		//****************************************
//		name = "Daniel Szocs";
//		userName = "danielszocs";
//		userEmail = "daniel@daniel.ro";
//		userPhone = "0761222222";
//		password = "parola.daniel";
//		//************************************************************************
//		newUser = new User(name, userName, userEmail, userPhone, password);
//		userService.registerUser(newUser);
//
//
//		/**sportName should be taken from Web Interface*/
//		String  sportName = "Football";
//		Sport newSport = new Sport(sportName);
//		sportService.registerSport(newSport);
//
//		/**sportName should be taken from Web Interface*/
//		sportName = "Tennis";
//		newSport = new Sport(sportName);
//		sportService.registerSport(newSport);
//
//
//		/**following data should be taken from Web Interface*/
//		//*************************************************
//		String playGroundName = "FootballField1";
//		sportName = "Football";
//		int pricePerHour = 100;
//		//*******************************************************************************
//		Long sportId = sportService.returnSportId(sportName);
//		PlayGround newPlayGround = new PlayGround(playGroundName, sportId, pricePerHour );
//		playGroundService.registerPlayGround(newPlayGround);
//
//		/**following data should be taken from Web Interface*/
//		//*************************************************
//		playGroundName = "FootballField2";
//		sportName = "Football";
//		pricePerHour = 80;
//		//*******************************************************************************
//		sportId = sportService.returnSportId(sportName);
//		newPlayGround = new PlayGround(playGroundName, sportId, pricePerHour );
//		playGroundService.registerPlayGround(newPlayGround);
//
//		/**following data should be taken from Web Interface*/
//		//*************************************************
//		playGroundName = "TennisField1";
//		sportName = "Tennis";
//		pricePerHour = 50;
//		//*******************************************************************************
//		sportId = sportService.returnSportId(sportName);
//		newPlayGround = new PlayGround(playGroundName, sportId, pricePerHour );
//		playGroundService.registerPlayGround(newPlayGround);
//
//
//		/**@brief: following should be taken from Web Interface*/
//		//BEGIN ********************************************************
//		Date bDate = Date.valueOf("2021-05-05");
//		Time bTime = Time.valueOf("21:00:00");
//		userName = "nelutufarcas";
//		playGroundName = "FootballField2"/*should get value from Web Interface*/;
////		int bookingDuration = 1;
//		// END ******************************************************************************
//		Long userID = userService.returnUserID(userName);
//		Long playGroundID = playGroundService.returnPlayGroundID(playGroundName);
//		/*calculate based on bookingDuration and pricePerHour*/
////		int bookingTotalPrice = bookingDuration * playGroundService.returnPricePerHour(playGroundName);
////		Booking newBooking = new Booking(bDate, bTime, bookingDuration, bookingTotalPrice, userID, playGroundID, BookingStatusEnum.ACTIVE);
//		int bookingPrice = playGroundService.returnPricePerHour(playGroundName);
//		Booking newBooking = new Booking(bDate, bTime, bookingPrice, userID, playGroundID, BookingStatusEnum.ACTIVE);
//		bookingService.saveNewBooking(newBooking);
//
//		/**@brief: following should be taken from Web Interface*/
//		//BEGIN ********************************************************
//		bDate = Date.valueOf("2021-05-08");
//		bTime = Time.valueOf("20:00:00");
//		userName = "danielszocs";
//		playGroundName = "TennisField1";
////		bookingDuration = 2;
//		//END ********************************************************
//		userID = userService.returnUserID(userName);
//		playGroundID = playGroundService.returnPlayGroundID(playGroundName);
//		/*calculate based on bookingDuration and pricePerHour*/
////		bookingTotalPrice = bookingDuration * playGroundService.returnPricePerHour(playGroundName);
////		newBooking = new Booking(bDate, bTime, bookingDuration, bookingTotalPrice, userID, playGroundID, BookingStatusEnum.ACTIVE);
//		bookingPrice = playGroundService.returnPricePerHour(playGroundName);
//		newBooking = new Booking(bDate, bTime, bookingPrice, userID, playGroundID, BookingStatusEnum.ACTIVE);
//		bookingService.saveNewBooking(newBooking);
//
//		//booking status enum = CANCEL, not ACTIVE
//		//should not be added to db
//		//because booking signature already exists
//		/**@brief: following should be taken from Web Interface*/
//		//BEGIN ********************************************************
//		bDate = Date.valueOf("2021-05-08");
//		bTime = Time.valueOf("20:00:00");
//		userName = "nelutufarcas";
//		playGroundName = "TennisField1";
////		bookingDuration = 2;
//		//END ********************************************************
//		userID = userService.returnUserID(userName);
//		playGroundID = playGroundService.returnPlayGroundID(playGroundName);
//		/*calculate based on bookingDuration and pricePerHour*/
////		bookingTotalPrice = bookingDuration * playGroundService.returnPricePerHour(playGroundName);
////		newBooking = new Booking(bDate, bTime, bookingDuration, bookingTotalPrice, userID, playGroundID, BookingStatusEnum.ACTIVE);
//		bookingPrice = playGroundService.returnPricePerHour(playGroundName);
//		newBooking = new Booking(bDate, bTime, bookingPrice, userID, playGroundID, BookingStatusEnum.CANCEL);
//		bookingService.saveNewBooking(newBooking);

//		/**@brief: following should be taken from Web Interface*/
//		//BEGIN ********************************************************
//		Date bDate = Date.valueOf("2022-06-06");
//		Time bTime = Time.valueOf("22:22:22");
//		userName = "nelutufarcas";
//		playGroundName = "FootballField2"/*should get value from Web Interface*/;
////		int bookingDuration = 1;
//		// END ******************************************************************************
//		Long userID = userService.returnUserID(userName);
//		Long playGroundID = playGroundService.returnPlayGroundID(playGroundName);
//		/*calculate based on bookingDuration and pricePerHour*/
////		int bookingTotalPrice = bookingDuration * playGroundService.returnPricePerHour(playGroundName);
////		Booking newBooking = new Booking(bDate, bTime, bookingDuration, bookingTotalPrice, userID, playGroundID, BookingStatusEnum.ACTIVE);
//		int bookingPrice = playGroundService.returnPricePerHour(playGroundName);
//		Booking newBooking = new Booking(bDate, bTime, bookingPrice, userID, playGroundID, BookingStatusEnum.ACTIVE);
//		bookingService.saveNewBooking(newBooking);

//		User testUser = new User();
//		testUser.setUserName("flo");
//		testUser.setPassword("12345f");
//		System.out.println("Testing user login: " + userService.loginUser(testUser));


	}



}

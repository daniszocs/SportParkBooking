package org.sci.finalproject.SportParkBooking;

import org.sci.finalproject.SportParkBooking.model.PlayGround;
import org.sci.finalproject.SportParkBooking.model.Sport;
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
		@Override
	public void run(String... args) throws Exception {
		//initialize database with Sport and PlayGround values;
		initIndex();
	}

	private void initIndex(){
		//************************************************************************
		Sport sport1 = new Sport("Football");
		sportService.register(sport1);

		Sport sport2 = new Sport("Tennis");
		sportService.register(sport2);

		//************************************************************************
		String sportName;
		String playGroundName;
		String playGroundAddress;
		String playGroundDescription;
		Long sportId;
		int pricePerHour;

		sportName = "Football";
		playGroundName = "FootballField1";
		playGroundAddress= "Feleacului Street nr 1, Feleac";
		playGroundDescription= "High up on Feleac we offer you a unique opportunity! Football in the middle of nature in a special location!";
		sportId = sportService.returnSportId(sportName);
		pricePerHour = 110;
		PlayGround playGround1 = new PlayGround(playGroundName,playGroundAddress,playGroundDescription,sportId,pricePerHour);
		playGroundService.register(playGround1);

		sportName = "Football";
		playGroundName = "FootballField2";
		playGroundAddress= "Somesului Street nr 2, Cluj-Napoca";
		playGroundDescription= "For a perfect combination of sports + nature outing, we recommend this playground! Located on the banks of the Someș, a stone's throw from Cluj-Napoca Airport, this playground is accessible to all those who choose a pleasant exercise with friends!";
		sportId = sportService.returnSportId(sportName);
		pricePerHour = 120;
		PlayGround playGround2 = new PlayGround(playGroundName,playGroundAddress,playGroundDescription,sportId,pricePerHour);
		playGroundService.register(playGround2);

		sportName = "Football";
		playGroundName = "FootballField3";
		playGroundAddress= "Turda Street nr 3, Turda";
		playGroundDescription= "Located on the road that connects Cluj-Napoca to Turda, this playground offers you the opportunity to do sports in a truly nice place!";
		sportId = sportService.returnSportId(sportName);
		pricePerHour = 130;
		PlayGround playGround3 = new PlayGround(playGroundName,playGroundAddress,playGroundDescription,sportId,pricePerHour);
		playGroundService.register(playGround3);

		sportName = "Football";
		playGroundName = "FootballField4";
		playGroundAddress= "Gheorgheni Street nr 4, Cluj-Napoca";
		playGroundDescription= "We invite you to discover our new playground near Lake Gheorgheni! Book in advance. It is one of the most requested locations!";
		sportId = sportService.returnSportId(sportName);
		pricePerHour = 140;
		PlayGround playGround4 = new PlayGround(playGroundName,playGroundAddress,playGroundDescription,sportId,pricePerHour);
		playGroundService.register(playGround4);

		sportName = "Football";
		playGroundName = "FootballField5";
		playGroundAddress= "Floresti Street nr 5, Floresti";
		playGroundDescription= "The newest playground for our customers is also the most spectacular! Located in the Floresti area, this playground benefits from underground heating so football can be played here regardless of the weather conditions!";
		sportId = sportService.returnSportId(sportName);
		pricePerHour = 150;
		PlayGround playGround5 = new PlayGround(playGroundName,playGroundAddress,playGroundDescription,sportId,pricePerHour);
		playGroundService.register(playGround5);


		sportName = "Tennis";
		playGroundName = "TennisField1";
		playGroundAddress= "Faget Street nr 1, Faget";
		playGroundDescription= "This playground is for relaxation, relaxation, relaxation! It couldn't be otherwise given the beautiful area that surrounds it! Located in the Făget Cluj area, this playground is waiting for you at night with recently installed ultra-modern lights!";
		sportId = sportService.returnSportId(sportName);
		pricePerHour = 210;
		PlayGround playGround6 = new PlayGround(playGroundName,playGroundAddress,playGroundDescription,sportId,pricePerHour);
		playGroundService.register(playGround6);

		sportName = "Tennis";
		playGroundName = "TennisField2";
		playGroundAddress= "Centru Street nr 1, Cluj-Napoca";
		playGroundDescription= "Located right in the heart of Cluj-Napoca, two steps from the center, in an exclusive playground, for those who want more from a than just an hour of sports!";
		sportId = sportService.returnSportId(sportName);
		pricePerHour = 220;
		PlayGround playGround7 = new PlayGround(playGroundName,playGroundAddress,playGroundDescription,sportId,pricePerHour);
		playGroundService.register(playGround7);

		sportName = "Tennis";
		playGroundName = "TennisField3";
		playGroundAddress= "Gara Street nr 3, Cluj-Napoca";
		playGroundDescription= "Situated in the old train station of Cluj, this playground invites you to take part in movement and history at the same time!";
		sportId = sportService.returnSportId(sportName);
		pricePerHour = 230;
		PlayGround playGround8 = new PlayGround(playGroundName,playGroundAddress,playGroundDescription,sportId,pricePerHour);
		playGroundService.register(playGround8);
	}

}

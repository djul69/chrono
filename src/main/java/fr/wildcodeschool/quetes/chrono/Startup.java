package fr.wildcodeschool.quetes.chrono;


public class Startup {
	
	public static void main(String... args) throws InterruptedException {
		
		TimeProvider tp = new CmoileDummy4();
		new Chrono(tp).roll();	
	}
}
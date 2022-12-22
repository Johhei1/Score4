
public class Main {

	public static void main(String[] args) {
		
		System.out.println("This is Score4");
		
		Game game = new Game(); // New Game
	
		game.ReadPlayerData(); // Reading Player info
		game.ReadGameData(); // Reading Game info
		game.TheGame(); // Start of the game
	}

}

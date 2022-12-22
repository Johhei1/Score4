import java.util.Random;
import java.util.Scanner;

public class Game {
	
	private int Rows;
	private int Columns;
	private char[][] Stats ; //the table
	private Player p1;
	private Player p2;
	
	public Game()
	{
		Rows = 15;
		Columns = 15;
		Stats  = new char [15][15]; 
		p1 = new Player();
		p2 = new Player();
	}
	
	public void ReadPlayerData() //Reading every player's name and chip
	{
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Please enter the name of the 1st player: ");
		p1.setName(scanner.nextLine());
		System.out.println("Please enter the name of the 2nd player: ");
		p2.setName(scanner.nextLine());
		
		System.out.println(p1.getName()+", please enter your chip: ");
		p1.setChip(scanner.nextLine().charAt(0));
		while(p1.getChip()!='x' && p1.getChip()!='o')
		{
			p1.setChip(scanner.nextLine().charAt(0));
		}
		if(p1.getChip()=='x')
		{
			p2.setChip('o');
			System.out.println(p2.getName()+", your chip is "+p2.getChip());
			
		}
			
		else if(p1.getChip()=='o')
		{
			p2.setChip('x');
			System.out.println(p2.getName()+", your chip is "+p2.getChip());
		}
	}
	
	public void ReadGameData() // Reading the game info
	{
		Scanner in = new Scanner(System.in);
		
		System.out.println("Please enter the number of rows: ");
		Rows = in.nextInt();
		while(Rows<4 || Rows>15)
		{
			System.out.print("Incorrect input. ");
			System.out.println("Please enter the number of rows: ");
			Rows = in.nextInt();
		}
		
		System.out.println("Please enter the number of columns: ");
		Columns = in.nextInt();
		while(Columns<4 || Columns>15)
		{
			System.out.print("Incorrect input. ");
			System.out.println("Please enter the number of columns: ");
			Columns = in.nextInt();
		}
	
		for(int i=0;i<Rows;i++) //Resetting the table
		{
			for(int j=0;j<Columns;j++)
			{
				Stats[i][j] = '-';
			}
		}
	}
	
	public void TheGame()
	{
		boolean result = false;
		String TurnPlayer = "";
		char TurnChip = ' ';
		int TurnRow;
		int column ;
		int GameRows, GameColumns;
		int RandomStarter;
		int SumSpots = 0;
		
		
		Random random = new Random();
		Scanner play = new Scanner(System.in);
		RandomStarter = random.nextInt(2) + 1;
		GameRows = Rows;
		GameColumns = Columns;
		
		PrintTable(); // Empty Table
		
		if(RandomStarter==1) // Deciding which player will start randomly
		{
			TurnPlayer = p1.getName();
			TurnChip = p1.getChip();
		}
		else if(RandomStarter==2)
		{
			TurnPlayer = p2.getName();
			TurnChip = p2.getChip();
		}
		
		while(result==false) //Start of the game
		{
			// Checking if it is a draw
			if(SumSpots==Rows*Columns && result==false) 
			{
				System.out.println("GAME OVER. WE HAVE A DRAW");
				break;
			}
			
			//TURNS
			System.out.print(TurnPlayer+", your turn. Select column: "); 
			column = play.nextInt();
			while(column<1 || column>Columns)
			{
				System.out.print("Incorrect input. ");
				System.out.println("Please select a column: ");
				column = play.nextInt();
			}
			
			TurnRow = SpotTaken(column-1,GameRows-1);
			PrintTable(column-1,TurnRow,TurnChip);
			SumSpots++;
			
			// Checking if a player won
			result = Win(TurnChip,GameRows,GameColumns);
			if(result==true) 
			{
				if(TurnChip == p1.getChip())
				{
					System.out.println("GAME OVER. THE WINNER IS "+p1.getName().toUpperCase());
				}
				else if(TurnChip == p2.getChip())
				{
					System.out.println("GAME OVER. THE WINNER IS "+p2.getName().toUpperCase());
				}
			}
			
			// Changing Player Turn
			if(TurnPlayer == p1.getName()) 
			{
				TurnPlayer = p2.getName();
				TurnChip = p2.getChip();
			}
			else if(TurnPlayer == p2.getName())
			{
				TurnPlayer = p1.getName();
				TurnChip = p1.getChip();
			}		
		}
	}


	private void PrintTable() // method for printing the table the first turn
	{
		
		for(int i=0;i<Rows;i++)
		{
			System.out.print("| ");
			for(int j=0;j<Columns;j++)
			{
				System.out.print("- ");
			}
			System.out.println("|");
		}
		
		System.out.print("--");
		for(int j=0;j<Columns*2;j++)
		{
			System.out.print("-");
		}
		System.out.println("-");
		
		System.out.print("  ");
		for(int j=0;j<Columns;j++)
		{
			System.out.print(j+1+" ");
		}
		System.out.println();
	}
	
	private void PrintTable(int choice, int row, char chip) // method for printing the table every new turn
	{
		int newRow = row; //new row in case the column changed
		int choice2 = choice; // new column in case the one the player chose is filled
		
		Scanner in = new Scanner(System.in);
		
		//Checking if the column is full
		while(Stats[0][choice2]== 'o' || Stats[0][choice2]== 'x')
		{
			System.out.println("The column is full. Select a different column: ");
			choice2 = in.nextInt() ;
			while(choice2>Columns || choice2<1)
			{
				System.out.print("Incorrect input. ");
				System.out.println("Please select a column: ");
				choice2 = in.nextInt();
			}
			choice2--;
		}
		
		newRow = SpotTaken(choice2,Rows-1);
		
		//updated table
		for(int i=0;i<Rows;i++)
		{
			System.out.print("| ");
	
			for(int j=0;j<Columns;j++)
			{
				if((choice2==j) || (newRow==i))
				{
					Stats[newRow][choice2] = chip;
				}
				System.out.print(Stats[i][j]+" ");
			}
			
			System.out.println("|");
		}
		System.out.print("--");
		for(int j=0;j<Columns*2;j++)
		{
			System.out.print("-");
		}
		System.out.println("-");
		
		System.out.print("  ");
		for(int j=0;j<Columns;j++)
		{
			System.out.print(j+1+" ");
		}
		System.out.println();
	}
	
	
	private int SpotTaken(int column, int GameRows) // New row if the spot in that row is already filled
	{
		int Spot = 0;
		int rows = GameRows;
		
		for(int j=GameRows;j>=0;j--) 
		{
			if(Stats[j][column] =='x' || Stats[j][column]== 'o') 
			{
				rows--;
			}
			if(Stats[j][column] =='-')
			{
				Spot = rows;
			}
		}
		return Spot;
	}
	
	private boolean Win(char chip, int GameRows, int GameColumns)
	{
		// Horizontal win
		for(int j=0;j<GameColumns-3;j++) 
		{
			for(int i=0;i<GameRows;i++)
			{
				if(Stats[i][j] ==chip && Stats[i][j+1] ==chip && Stats[i][j+2] ==chip && Stats[i][j+3] ==chip)
				{
					return true;
				}
			}
		}
		
		// Vertical win
		for(int i=0;i<GameRows-3;i++) 
		{
			for(int j=0;j<GameColumns;j++)
			{
				if(Stats[i][j] ==chip && Stats[i+1][j] ==chip &&Stats[i+2][j] ==chip &&Stats[i+3][j] ==chip)
				{
					return true;
				}
			}
		}
		
		// Rising Diagonal win
		for(int i=3;i<GameRows;i++) 
		{
			for(int j=0;j<GameColumns-3;j++)
			{
				if(Stats[i][j] ==chip && Stats[i-1][j+1] ==chip && Stats[i-2][j+2] ==chip && Stats[i-3][j+3] ==chip)
				{
					return true;
				}
			}
		}
		
		// Falling Diagonal win
		for(int i=3;i<GameRows;i++) 
		{
			for(int j=3;j<GameColumns;j++)
			{
				if(Stats[i][j] ==chip && Stats[i-1][j-1] ==chip &&Stats[i-2][j-2] ==chip &&Stats[i-3][j-3] ==chip)
				{
					return true;
				}
			}
		}
		return false;
	}		
}

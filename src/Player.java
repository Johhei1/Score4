
public class Player {
	
	private String name;
	private char chip;
  
	public Player()
	{
		name = "";
		chip = ' ';
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public char getChip() {
		return chip;
	}

	public void setChip(char c) {
		this.chip = c;
	}
}

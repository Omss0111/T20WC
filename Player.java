package T20_WC;

public class Player {

	int id;
	String name;
	String team_name;
	String category;
	int score;
	int catches;
	int wickets;

	public Player() {
	}

	public Player(int id, String name, String team_name, String category, int score, int catches, int wickets) {

		this.id = id;
		this.name = name;
		this.team_name = team_name;
		this.category = category;
		this.score = score;
		this.catches = catches;
		this.wickets = wickets;
	}

	@Override
	public String toString() {
		return "\nPlayer ID: " + id + "\nPlayer Name: " + name + "\nTeam Name: " + team_name + "\nCategory: " + category
				+ "\nRuns are " + score + "\nCatches are " + catches + "\nWickets are " + wickets
				+ "\n------------------------------";
	}
}
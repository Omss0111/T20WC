package T20_WC;

import java.util.Scanner;

public class T20WCMain {

	public static void main(String[] args) throws Exception {

		Scanner sc = new Scanner(System.in);
		PlayerDAO dao = new PlayerDAO();

		while (true) {

			System.out.println("\n========== T20WC PLAYER MANAGEMENT ==========");
			System.out.println("1. Display All Players");
			System.out.println("2. Display Batsmen");
			System.out.println("3. Display Bowlers");
			System.out.println("4. Display All-Rounders");
			System.out.println("5. Search Player By ID");
			System.out.println("6. Search Player By Name");
			System.out.println("7. Search Players By Team");
			System.out.println("8. Search Players By Category");
			System.out.println("9. Highest Scorer");
			System.out.println("10. Lowest Scorer");
			System.out.println("11. Most Catches");
			System.out.println("12. Fewest Catches");
			System.out.println("13. Most Wickets");
			System.out.println("14. Fewest Wickets");
			System.out.println("15. Total Runs");
			System.out.println("16. Total Catches");
			System.out.println("17. Total Wickets");
			System.out.println("18. Average Runs");
			System.out.println("19. Total Players");
			System.out.println("20. Count Players By Team");
			System.out.println("21. Update Runs");
			System.out.println("22. Update Catches");
			System.out.println("23. Update Wickets");
			System.out.println("24. Update Team Name");
			System.out.println("25. Delete Player");
			System.out.println("0. Exit");

			System.out.print("Enter your choice: ");
			int choice = sc.nextInt();
			sc.nextLine();

			switch (choice) {

			case 1:
				dao.display("ALL PLAYERS", "SELECT * FROM player ORDER BY id");
				break;

			case 2:
				dao.display("BATSMEN", "SELECT * FROM player WHERE category = ?", "Batsman");
				break;

			case 3:
				dao.display("BOWLERS", "SELECT * FROM player WHERE category = ?", "Bowler");
				break;

			case 4:
				dao.display("ALL-ROUNDERS", "SELECT * FROM player WHERE category = ?", "All-Rounder");
				break;

			case 5:
				System.out.print("Enter player ID: ");
				int id = sc.nextInt();
				sc.nextLine();

				dao.display("PLAYER DETAILS", "SELECT * FROM player WHERE id = ?", id);
				break;

			case 6:
				System.out.print("Enter player name: ");
				String name = sc.nextLine();

				dao.display("SEARCH BY NAME", "SELECT * FROM player WHERE name LIKE ?", "%" + name + "%");
				break;

			case 7:
				System.out.print("Enter team name: ");
				String team = sc.nextLine();

				dao.display("TEAM PLAYERS", "SELECT * FROM player WHERE team_name = ?", team);
				break;

			case 8:
				System.out.print("Enter category: ");
				String category = sc.nextLine();

				dao.display("CATEGORY PLAYERS", "SELECT * FROM player WHERE category = ?", category);
				break;

			case 9:
				dao.display("HIGHEST SCORER",
						"SELECT * FROM player WHERE score = " + "(SELECT MAX(score) FROM player)");
				break;

			case 10:
				dao.display("LOWEST SCORER", "SELECT * FROM player WHERE score = " + "(SELECT MIN(score) FROM player)");
				break;

			case 11:
				dao.display("MOST CATCHES",
						"SELECT * FROM player WHERE catches = " + "(SELECT MAX(catches) FROM player)");
				break;

			case 12:
				dao.display("FEWEST CATCHES",
						"SELECT * FROM player WHERE catches = " + "(SELECT MIN(catches) FROM player)");
				break;

			case 13:
				dao.display("MOST WICKETS",
						"SELECT * FROM player WHERE wickets = " + "(SELECT MAX(wickets) FROM player)");
				break;

			case 14:
				dao.display("FEWEST WICKETS",
						"SELECT * FROM player WHERE wickets = " + "(SELECT MIN(wickets) FROM player)");
				break;

			case 15:
				dao.showNumber("TOTAL RUNS:", "SELECT SUM(score) FROM player");
				break;

			case 16:
				dao.showNumber("TOTAL CATCHES:", "SELECT SUM(catches) FROM player");
				break;

			case 17:
				dao.showNumber("TOTAL WICKETS:", "SELECT SUM(wickets) FROM player");
				break;

			case 18:
				dao.showNumber("AVERAGE RUNS:", "SELECT AVG(score) FROM player");
				break;

			case 19:
				dao.showNumber("TOTAL PLAYERS:", "SELECT COUNT(*) FROM player");
				break;

			case 20:
				System.out.print("Enter team name: ");
				String countTeam = sc.nextLine();

				try (java.sql.Connection con = DBConnection.getConnection();
						java.sql.PreparedStatement ps = con
								.prepareStatement("SELECT COUNT(*) FROM player " + "WHERE team_name = ?")) {

					ps.setString(1, countTeam);

					try (java.sql.ResultSet rs = ps.executeQuery()) {
						rs.next();
						System.out.println("Players in " + countTeam + ": " + rs.getInt(1));
					}
				}
				break;

			case 21:
				System.out.print("Enter player ID: ");
				int scoreId = sc.nextInt();

				System.out.print("Enter new runs: ");
				int runs = sc.nextInt();
				sc.nextLine();

				dao.change("Runs updated", "UPDATE player SET score = ? WHERE id = ?", runs, scoreId);
				break;

			case 22:
				System.out.print("Enter player ID: ");
				int catchId = sc.nextInt();

				System.out.print("Enter new catches: ");
				int catches = sc.nextInt();
				sc.nextLine();

				dao.change("Catches updated", "UPDATE player SET catches = ? WHERE id = ?", catches, catchId);
				break;

			case 23:
				System.out.print("Enter player ID: ");
				int wicketId = sc.nextInt();

				System.out.print("Enter new wickets: ");
				int wickets = sc.nextInt();
				sc.nextLine();

				dao.change("Wickets updated", "UPDATE player SET wickets = ? WHERE id = ?", wickets, wicketId);
				break;

			case 24:
				System.out.print("Enter player ID: ");
				int teamId = sc.nextInt();
				sc.nextLine();

				System.out.print("Enter new team name: ");
				String newTeam = sc.nextLine();

				dao.change("Team name updated", "UPDATE player SET team_name = ? WHERE id = ?", newTeam, teamId);
				break;

			case 25:
				System.out.print("Enter player ID to delete: ");
				int deleteId = sc.nextInt();
				sc.nextLine();

				dao.change("Player deleted", "DELETE FROM player WHERE id = ?", deleteId);
				break;

			case 0:
				System.out.println("Program closed.");
				sc.close();
				return;

			default:
				System.out.println("Invalid choice. Try again.");
			}
		}
	}
}
package T20_WC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PlayerDAO {

	// Display players using any SELECT query
	public void display(String title, String sql, Object... values) throws Exception {

		List<Player> players = new ArrayList<>();

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			for (int i = 0; i < values.length; i++) {
				ps.setObject(i + 1, values[i]);
			}

			try (ResultSet rs = ps.executeQuery()) {

				while (rs.next()) {
					Player p = new Player(rs.getInt("id"), rs.getString("name"), rs.getString("team_name"),
							rs.getString("category"), rs.getInt("score"), rs.getInt("catches"), rs.getInt("wickets"));

					players.add(p);
				}
			}
		}

		System.out.println("\n===== " + title + " =====");

		if (players.isEmpty()) {
			System.out.println("No players found.");
		} else {
			for (Player p : players) {
				System.out.println(p);
			}
			System.out.println("Total records: " + players.size());
		}
	}

	// Display totals, counts and averages
	public void showNumber(String title, String sql) throws Exception {

		try (Connection con = DBConnection.getConnection();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql)) {

			if (rs.next()) {
				double value = rs.getDouble(1);

				System.out.println("\n" + title);

				if (value == Math.rint(value)) {
					System.out.println((long) value);
				} else {
					System.out.printf("%.2f%n", value);
				}
			}
		}
	}

	// Update or delete a record
	public void change(String message, String sql, Object... values) throws Exception {

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			for (int i = 0; i < values.length; i++) {
				ps.setObject(i + 1, values[i]);
			}

			int rows = ps.executeUpdate();

			if (rows > 0) {
				System.out.println(message + " successfully.");
			} else {
				System.out.println("No player found with that ID.");
			}
		}
	}
}
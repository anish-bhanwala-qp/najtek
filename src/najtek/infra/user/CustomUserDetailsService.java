package najtek.infra.user;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import najtek.infra.config.AppDatabaseConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private DataSource dataSource;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user = new User("anishuser", "b");
		printAllUsers();
		/*
		 * if (user == null) { throw new UsernameNotFoundException("username " +
		 * username + " not found"); }
		 */

		return user;
	}

	private void printAllUsers() {
		Connection conn;
		try {

			ApplicationContext context = new AnnotationConfigApplicationContext(
					AppDatabaseConfiguration.class);
			dataSource = (DataSource) context.getBean("mainDataSource");
			conn = dataSource.getConnection();
			/**
			 * Use Connection to query the database for a simple table listing.
			 * Statement will be closed automatically.
			 */
			try (Statement stm = conn.createStatement()) {
				String query = "select id, username, password from user;";
				ResultSet rs = stm.executeQuery(query);
				// Store and return result of the query
				while (rs.next()) {
					System.out.println("ID: " + rs.getInt("id"));
					System.out.println("Username: " + rs.getString("username"));
					System.out.println("Password: " + rs.getString("password"));
				}
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			} finally {
				// Release connection back to the pool
				if (conn != null) {
					conn.close();
				}
				conn = null; // prevent any future access
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}

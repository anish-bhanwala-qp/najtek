package najtek.infra.config;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"najtek.infra.user"})
public class AppDatabaseConfiguration {

	private static final String MAIN_DATSOURCE_NAME = "jdbc/NAJTekMainDB";
	
	@Bean
	public DataSource mainDataSource() throws NamingException {
		Context initialContext = new InitialContext();

		/**
		 * Get Context object for all environment naming (JNDI), such as
		 * Resources configured for this web application.
		 */
		Context environmentContext = (Context) initialContext
				.lookup("java:comp/env");

		DataSource dataSource = (DataSource) environmentContext
				.lookup(MAIN_DATSOURCE_NAME);
		
		System.out.println("*************************************************************\n" +
				"DATASOURCE IS NULL: " + (dataSource == null));
		
		return dataSource;
	}
}

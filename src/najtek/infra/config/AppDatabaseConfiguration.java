package najtek.infra.config;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import najtek.database.mapper.user.UserMapper;

import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({ "najtek.infra.user", "najtek.database.common" , "najtek.database.dao"})
public class AppDatabaseConfiguration {

	private static final String MAIN_DATSOURCE_NAME = "jdbc/NAJTekMainDB";
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws NamingException {
		DataSource dataSource = mainDataSource();
		TransactionFactory transactionFactory = new JdbcTransactionFactory();
		Environment environment = new Environment("development",
				transactionFactory, dataSource);
		org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration(
				environment);
		
		registerMappers(configuration);
		
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
				.build(configuration);

		return sqlSessionFactory;
	}

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

		System.out
				.println("*************************************************************\n"
						+ "DATASOURCE IS NULL: " + (dataSource == null));

		return dataSource;
	}
	
	private void registerMappers(org.apache.ibatis.session.Configuration configuration) {
		configuration.addMapper(UserMapper.class);
	}
}

package najtek.database.common;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import najtek.domain.common.DomainObject;

public class SelectFromDatabase {

	public DomainObject selectOneById(SqlSessionFactory sqlSessionFactory,
			String namespace, String method, long id) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			String fullyQualifiedName = namespace + "." + method;
			return (DomainObject) session.selectOne(fullyQualifiedName , id);
		} finally {
			session.close();
		}
	}
}

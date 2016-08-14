package najtek.database.common;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by anish on 14/8/16.
 */
@Repository
public abstract class DatabaseInsert {

    public void fire(SqlSessionFactory sqlSessionFactory) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            process(session);
            session.commit();
        } finally {
            session.close();
        }
    }

    public abstract void process(SqlSession session);
}

package najtek.database.common;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by anish on 14/8/16.
 */
@Repository
public abstract class DatabaseInsert {
    private final static Logger logger = LoggerFactory.getLogger(DatabaseInsert.class);

    public void fire(SqlSessionFactory sqlSessionFactory) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            logger.info("************EXECUTING SQL INSERT COMMAND**************");
            process(session);
            session.commit();
        } finally {
            session.close();
        }
    }

    public abstract void process(SqlSession session);
}

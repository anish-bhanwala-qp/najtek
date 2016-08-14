package najtek.database.common;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * Created by anish on 14/8/16.
 */
@Repository
public abstract class DatabaseSelect {
    private final Logger logger = LoggerFactory.getLogger(DatabaseSelect.class);

    public Object fire(SqlSessionFactory sqlSessionFactory) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            logger.info("************EXECUTING SQL COMMAND**************");
            System.out.println("************EXECUTING SQL COMMAND**************");
            return processSelect(session);
        } finally {
            session.close();
        }
    }

    public abstract Object processSelect(SqlSession session);
}

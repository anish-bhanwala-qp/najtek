package najtek.database.mapper.user;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import najtek.database.common.AppDatabase;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

@MappedTypes(AppDatabase.class)
public class AppDatabaseTypeHandler implements TypeHandler<AppDatabase> {
	@Override
	public AppDatabase getResult(ResultSet rs, String param)
			throws SQLException {
		return AppDatabase.valueOf(rs.getString(param));
	}
	
	@Override
	public AppDatabase getResult(CallableStatement cs, int col)
			throws SQLException {
		return AppDatabase.valueOf(cs.getString(col));
	}
	
	@Override
	public void setParameter(PreparedStatement ps, int paramInt,
			AppDatabase paramType, JdbcType jdbctype) throws SQLException {
		ps.setString(paramInt, paramType.name());
	}

	@Override
	public AppDatabase getResult(ResultSet rs, int col) throws SQLException {
		return AppDatabase.valueOf(rs.getString(col));
	}
}

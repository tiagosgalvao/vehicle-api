package com.galvao.vehicle.model.enums;

import org.hibernate.engine.spi.SharedSessionContractImplementor;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class PostgreSQLEnumType extends org.hibernate.type.EnumType {

	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session) throws SQLException {
		if (value == null) {
			st.setNull(index, Types.OTHER);
		} else if ("H2".equalsIgnoreCase(session.connection().getMetaData().getDatabaseProductName())) {
			st.setString(index, value.toString());
		} else {
			st.setObject(index, value.toString(), Types.OTHER);
		}
	}
}

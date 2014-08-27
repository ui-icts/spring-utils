package edu.uiowa.icts.test;

import org.dbunit.dataset.datatype.DataType;
import org.dbunit.dataset.datatype.DataTypeException;
import org.dbunit.dataset.datatype.DefaultDataTypeFactory;

/**
 * @author rrlorent
 */
public class MsSqlDataTypeFactory extends DefaultDataTypeFactory {

	public static int NVARCHAR = -9;
	public static int UNIQUEIDENTIFIER = -11;

	public DataType createDataType( int sqlType, String sqlTypeName ) throws DataTypeException {

		if ( sqlType == NVARCHAR ) {
			return DataType.VARCHAR;
		}

		if ( sqlType == UNIQUEIDENTIFIER ) {
			return DataType.VARCHAR;
		}

		return super.createDataType( sqlType, sqlTypeName );
	}
}
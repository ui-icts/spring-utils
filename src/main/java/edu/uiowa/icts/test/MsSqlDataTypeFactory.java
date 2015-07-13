package edu.uiowa.icts.test;

/*
 * #%L
 * spring-utils
 * %%
 * Copyright (C) 2010 - 2015 University of Iowa Institute for Clinical and Translational Science (ICTS)
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import java.util.Collection;

import org.dbunit.dataset.datatype.DataType;
import org.dbunit.dataset.datatype.DataTypeException;
import org.dbunit.dataset.datatype.DefaultDataTypeFactory;

/**
 * <p>MsSqlDataTypeFactory class.</p>
 *
 * @author rrlorent
 * @version $Id: $
 */
public class MsSqlDataTypeFactory extends DefaultDataTypeFactory {

	/** Constant <code>NVARCHAR=-9</code> */
	public static int NVARCHAR = -9;
	/** Constant <code>UNIQUEIDENTIFIER=-11</code> */
	public static int UNIQUEIDENTIFIER = -11;

	/** {@inheritDoc} */
	@Override
	public Collection<?> getValidDbProducts() {
		// TODO Auto-generated method stub
		return super.getValidDbProducts();
	}
	
	/** {@inheritDoc} */
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

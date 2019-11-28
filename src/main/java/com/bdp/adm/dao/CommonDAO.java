package com.bdp.adm.dao;


import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommonDAO {
	
	/*ArrayList<Object> getColumnNames(String columnsQuery);

	ArrayList<Object> getList(String dataQuery);*/
	
	ArrayList<Object> getColumnNames(@Param("columnsQuery") String columnsQuery);

	ArrayList<Object> getList(@Param("dataQuery") String dataQuery);
	
	int insertData(@Param("insertQuery") String insertQuery);
}

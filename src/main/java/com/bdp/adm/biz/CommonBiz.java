package com.bdp.adm.biz;

import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

public interface CommonBiz {

	public void exportXlsx(int tableNo, HttpServletResponse response) throws Throwable;
	
	public ArrayList<Object> importXlsx(MultipartFile[] multipartFiles) throws Throwable;
	
	public int insertData(ArrayList<Object> list, ArrayList<String> columns, String tableName);
}

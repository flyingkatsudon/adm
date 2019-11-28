package com.bdp.adm.biz;

import com.bdp.adm.vo.TaxonomyVO;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface TaxonomyBiz {
	
	// Taxonomy Setting
	public TaxonomyVO setTaxonomyVO(String[] data);
	
	// Select All
	public String selectAllTaxonomy(String action) throws JsonProcessingException;
	
	// Select One
	public TaxonomyVO selectOneTaxonomy(String DT_RowId, String columnName, String newValue);
	
	// Insert
	public int insertTaxonomy(String[] data, String currentUser);
	
	// Update
	public int updateTaxonomy(String[] data, String currentUser);
	
	// Delete
	public int deleteTaxonomy(String[] data, String currentUser);
	
	// ***TaxonomyTree***
	public String selectTaxonomyTree() throws JsonProcessingException;

}

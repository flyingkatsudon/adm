package com.bdp.adm.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.bdp.adm.vo.TaxonomyVO;

@Repository
public interface TaxonomyDAO {
	// Select All
	public List<TaxonomyVO> selectAllTaxonomy(Map<String, String> param);
	
	// Select One
	public TaxonomyVO selectOneTaxonomy(String DT_RowId);
	
	// Insert
	public int insertTaxonomy(TaxonomyVO taxonomyVO);
	
	// Update
	public int updateTaxonomy(TaxonomyVO taxonomyVO);

	// Delete
	public int deleteTaxonomy(TaxonomyVO taxonomyVO);
	
	// ***Select TaxonomyTree***
	public List<TaxonomyVO> selectTaxonomyTree();
}

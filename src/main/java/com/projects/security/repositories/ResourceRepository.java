package com.projects.security.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import com.projects.security.models.ResourceDetail;


@Transactional
public interface ResourceRepository extends PagingAndSortingRepository <ResourceDetail, Long>{
	
	@Query("select p from ResourceDetail p where UPPER(p.resourceName) like UPPER(?1) or " +
            "UPPER(p.longDescription) like UPPER(?1)")
    List search(String term);

}

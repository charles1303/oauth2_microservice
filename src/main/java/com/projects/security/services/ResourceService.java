package com.projects.security.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projects.security.models.ResourceDetail;
import com.projects.security.repositories.ResourceRepository;


@Service
public class ResourceService {
	
	@Autowired
	private final ResourceRepository repository;
	
	public ResourceService(ResourceRepository repository){
		this.repository = repository;
		
	}
	
	@Transactional(readOnly=true)
	 public Page<ResourceDetail> getAll(int page, int count, Sort.Direction direction,String sortProperty) {
		return repository.findAll(new PageRequest(page, count, new Sort(direction, sortProperty)));
		
	}

}

package com.projects.security.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.projects.security.models.BaseModel;


@NoRepositoryBean
//@Qualifier("baseRepositoryBean")
public interface BaseRepository<T extends BaseModel> extends JpaRepository<T, Long> {

	public BaseModel findById(Long id);
	
	
}

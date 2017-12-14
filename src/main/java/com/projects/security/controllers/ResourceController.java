package com.projects.security.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projects.security.services.ResourceService;


@RestController
@RequestMapping("/resources")
public class ResourceController {
	
	private final ResourceService service;
	
	public ResourceController(ResourceService service){
		this.service = service;
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Iterable findAll(@RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "count", defaultValue = "10", required = false) int count,
            @RequestParam(value = "order", defaultValue = "ASC", required = false) Sort.Direction direction,
            @RequestParam(value = "sort", defaultValue = "resourceName", required = false) String sortProperty) {
    	System.out.println("In Here....");
	Page result = service.getAll(page,count,direction,sortProperty);
	return result.getContent();
	}

}

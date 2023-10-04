package com.example.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring.entity.Tag;

public interface TagRepository extends JpaRepository<Tag, Integer>{
	
	public Tag findByTagName(String tagName);

}

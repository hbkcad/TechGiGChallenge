package com.challenge.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.challenge.model.Word;

@Repository
public interface IWordDao extends JpaRepository<Word, Integer>{

	@Transactional
	@Modifying
	@Query(value="INSERT INTO Word(word) Values(?1)",nativeQuery = true)
	public int insertData(@Param("word")String word);
	
}

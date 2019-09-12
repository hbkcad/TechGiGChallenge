package com.challenge.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.challenge.model.Word;

public interface IWordService {

	public List<Word> getAllWords();

	public void uploadText(MultipartFile file) throws Exception;
	

}

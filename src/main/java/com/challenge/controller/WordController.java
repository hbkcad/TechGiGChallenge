package com.challenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.challenge.model.Word;
import com.challenge.service.IWordService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/word/v1")
public class WordController {

	@Autowired
	IWordService iWordService;

	@GetMapping(value = "/all")
	@CrossOrigin(origins = "*")
	@ApiOperation(value = "Returns list of all words", response = Word.class, responseContainer = "List")
	public ResponseEntity<Object> allGroups() {

		return new ResponseEntity<>(iWordService.getAllWords(), HttpStatus.OK);
	}
	
	
	
	@PostMapping("/uploadFile")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
	
		try {
			iWordService.uploadText(file);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		return HttpStatus.OK.toString();
}}

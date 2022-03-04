package com.qa.testSpring.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import domain.Test;

@RestController
public class TestController {
	
	private List<Test> testList = new ArrayList<Test>();

	//CRUD
	
	//Create - POST
	@PostMapping("/createTest")
	public ResponseEntity<Test> createTest(@RequestBody Test testIn) {
		this.testList.add(testIn);
		this.testList.get(testList.size() - 1);
		return new ResponseEntity<Test>(testIn, HttpStatus.CREATED);
	}
	//Read - GET
	@GetMapping("/readTest")
	public List<Test> getTest() {
		return this.testList;
	}
	
	@GetMapping("/readID/{id}")
	public Test readID(@PathVariable int id) {
		return this.testList.get(id);
	}
	//update - PUT/PATCH
	@PutMapping("/updateTest/{id}")
	public Test update(@PathVariable int id, @RequestBody Test testIn) {
		this.testList.remove(id);
		this.testList.add(id, testIn);
		return testIn;
		
	}
	//Delete - DELETE
	@DeleteMapping("/removeTest/{id}")
	public Test removeTest(@PathVariable int id) {
		return this.testList.remove(id);
	}
	
}

package com.anandvardhan.rule_engine.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.anandvardhan.rule_engine.dto.EmployeeDto;

@RestController
public class EngineContoller {
	
   
	@PutMapping("/create_rule")
	public void createRule(String rule) {
		
	}
	
	@GetMapping("/combine_rule")
	public void combineRule(List<String> ruleStrings) {
		System.out.println("hii..");
	}
	
	@GetMapping("/evaluate_rule")
	public void evaluateRule(EmployeeDto data , String rule) {
		System.out.println("hii..");
	}
}

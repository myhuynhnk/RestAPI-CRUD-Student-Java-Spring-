package com.example.JPA01HocSinh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.JPA01HocSinh.model.Lop;
import com.example.JPA01HocSinh.services.ILopService;

@RestController
@RequestMapping("/lop")
public class LopController {
	@Autowired
	ILopService lopService;
	
	@CrossOrigin
	@GetMapping(value="/hienthi", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Lop> hienthi() {
		return lopService.getAll();
	}
	
	
}

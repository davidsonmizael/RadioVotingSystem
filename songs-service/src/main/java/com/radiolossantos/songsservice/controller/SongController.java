package com.radiolossantos.songsservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.radiolossantos.songsservice.dto.SongDTO;
import com.radiolossantos.songsservice.service.SongService;

@Controller
@RequestMapping("/song")
public class SongController {
	
	@Autowired
	private SongService songService;
	
	@GetMapping
	public @ResponseBody List<SongDTO> listSongs(){
		return songService.getAll();
	}
	
	@GetMapping("{id}")
	public @ResponseBody SongDTO getSong(@PathVariable String id) {
		return songService.getOneById(id);
	}
}
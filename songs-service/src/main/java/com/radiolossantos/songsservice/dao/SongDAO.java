package com.radiolossantos.songsservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.radiolossantos.songsservice.entity.Song;

public interface SongDAO extends JpaRepository<Song, String>{
	
}

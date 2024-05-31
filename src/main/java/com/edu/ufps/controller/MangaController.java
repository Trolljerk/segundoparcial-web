package com.edu.ufps.controller;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.edu.ufps.service.*;
import com.edu.ufps.entity.Manga;

@RestController
@RequestMapping("/mangas")
public class MangaController {
    @Autowired
    private MangaService mangaService;

    @GetMapping
    public List<Manga> getAllMangas() {
        return mangaService.findAll();
    }

    @GetMapping("/{id}")
    public Manga getMangaById(@PathVariable Long id) {
        return mangaService.findById(id);
    }

    @PostMapping
    public Manga createManga(@RequestBody Manga manga) {
        return mangaService.create(manga);
    }

    @PutMapping("/{id}")
    public Manga updateManga(@PathVariable Long id, @RequestBody Manga manga) {
        return mangaService.update(id, manga);
    }

    @DeleteMapping("/{id}")
    public void deleteManga(@PathVariable Long id) {
        mangaService.delete(id);
    }
}

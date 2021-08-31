package com.undec.corralon.controlador;

import com.undec.corralon.DTO.ArticuloDTO;
import com.undec.corralon.DTO.Response;
import com.undec.corralon.excepciones.articulo.ArticuloException;
import com.undec.corralon.modelo.Articulo;
import com.undec.corralon.repository.MovimientoArticuloRepository;
import com.undec.corralon.service.ArticuloService;
import com.undec.corralon.service.MovimientoArticuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/articulos")
public class ArticuloController {

    @Autowired
    ArticuloService articuloService;

    @Autowired
    MovimientoArticuloService movimientoArticuloService;

    @GetMapping
    public ResponseEntity<List<Articulo>> listAllArticles() {
        return ResponseEntity.status(HttpStatus.OK).body(articuloService.listAllArticles());
    }

    @GetMapping("/habilitados")
    public ResponseEntity<List<Articulo>> listAllArticlesEnabled() {
        return ResponseEntity.status(HttpStatus.OK).body(articuloService.listAllArticlesEnabled());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Articulo> findArticleById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(articuloService.findArticleById(id));
    }

//    @GetMapping("/stock/{id}")
//    public ResponseEntity<Double> findStockArticleById(@PathVariable("id") Integer id) {
//        return ResponseEntity.status(HttpStatus.OK).body(movimientoArticuloService.findStockArticle(id));
//    }

    @PostMapping
    public ResponseEntity<Articulo> saveArticle(@RequestBody ArticuloDTO articuloDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(articuloService.saveArticle(articuloDTO));
    }

    @PutMapping
    public ResponseEntity<Articulo> updatedTheArcticle(@RequestBody ArticuloDTO articuloDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(articuloService.updatedTheArticle(articuloDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Articulo> changeTheHabilitation(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(articuloService.changeTheHabilitation(id));
    }
}

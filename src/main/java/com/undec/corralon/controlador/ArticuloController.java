package com.undec.corralon.controlador;

import com.undec.corralon.DTO.ArticuloDTO;
import com.undec.corralon.DTO.ArticuloRemitoDTO;
import com.undec.corralon.DTO.ArticuloStockDTO;
import com.undec.corralon.DTO.ArticuloVentaDTO;
import com.undec.corralon.modelo.Articulo;
import com.undec.corralon.service.ArticuloService;
import com.undec.corralon.service.MovimientoArticuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.QueryParam;
import java.text.ParseException;
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
    public ResponseEntity<List<ArticuloDTO>> listAllArticles() {
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

    @GetMapping("/stock-actual/{id}")
    public ResponseEntity<Double> findStockArticleById(@PathVariable("id") Integer id) throws ParseException {
        return ResponseEntity.status(HttpStatus.OK).body(movimientoArticuloService.findStockArticle(id));
    }

    @GetMapping("/stock-movimiento/{id}")
    public ResponseEntity<Double> findStockArticleByMovimientoById(@PathVariable Integer id,
                                                                   @QueryParam("fechaTipoMovimiento") String fechaTipoMovimiento) throws ParseException {
        return ResponseEntity.status(HttpStatus.OK).body(movimientoArticuloService.findStockByMovimientoArticle(id, fechaTipoMovimiento));
    }

    @GetMapping("/proveedor/{id}")
    public ResponseEntity<List<ArticuloStockDTO>> findByProviderWithStock(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(articuloService.findByProviderWithStock(id));
    }

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

    @GetMapping("/venta")
    public ResponseEntity<List<ArticuloVentaDTO>> obtenerArticulosVentaHabilitados() throws ParseException{
        return ResponseEntity.status(HttpStatus.OK).body(articuloService.obtenerArticulosVenta());
    }
    @GetMapping("/remito")
    public ResponseEntity<List<ArticuloRemitoDTO>> obtenerArticulosRemitoHabilitados(){
        return ResponseEntity.status(HttpStatus.OK).body(articuloService.obtenerArticulosRemito());
    }
}

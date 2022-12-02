package com.undec.corralon.repository;

import com.undec.corralon.modelo.Articulo;
import com.undec.corralon.modelo.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ArticuloRepository extends JpaRepository<Articulo, Integer> {
    List<Articulo> findArticuloByHabilitadoEquals(boolean habilitado);

    Boolean existsByCodigo(String codigo);

    Articulo findArticuloByCodigo(String codigo);

    Boolean existsArticuloByCodigoAndIdArticuloNot(String codigo, Integer idArticulo);

    // TODO: Reemplazar por findArticuloByCodigo
    @Deprecated
    @Query("select a from Articulo a where a.nombre = :nombre and a.codigo = :codigo")
    Articulo findArticuloForCodigo(@Param("nombre") String nombre, @Param("codigo") String codigo);

    List<Articulo> findArticulosByProveedorByIdProveedorAndHabilitadoTrue(Proveedor proveedor);

    List<Articulo> findArticulosByHabilitadoIsTrue();

    @Query(value = "select a.codigo,a.nombre,m.nombre as marca,dv.cantidad as cantidad, " +
            "(select SUM(ma.movimiento) from movimiento_articulo ma " +
            "where (ma.id_articulo = a.id_articulo and (ma.fecha >= :fechaInicial and ma.fecha <= :fechaFinal)) " +
            "and (ma.id_detalle_ajuste is not null or ma.id_detalle_pedido is not null " +
            "or ma.id_detalle_remito is not null)) as Stock, " +
            "a.precio * dv.cantidad as recaudacion " +
            "from articulo a " +
            "inner join marca m on m.id_marca = a.id_marca " +
            "inner join detalle_venta dv on dv.id_articulo = a.id_articulo " +
            "join venta v on v.id_venta = dv.id_venta " +
            "where  dv.fecha >= :fechaInicial and dv.fecha <= :fechaFinal " +
            "group by a.id_articulo ", nativeQuery = true)
    List<Object[]> obtenerRecaudacion(@Param("fechaInicial") Date fechaInicial, @Param("fechaFinal") Date fechaFinal);
}

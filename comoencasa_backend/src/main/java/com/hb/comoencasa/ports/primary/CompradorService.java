package com.hb.comoencasa.ports.primary;


import com.hb.comoencasa.domain.*;
import com.hb.comoencasa.ports.secondary.*;

import com.hb.comoencasa.domain.Comprador;
import com.hb.comoencasa.domain.Factura;
import com.hb.comoencasa.domain.Producto;
import com.hb.comoencasa.ports.secondary.CompradorRepository;
import com.hb.comoencasa.ports.secondary.FacturaRepository;
import com.hb.comoencasa.ports.secondary.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
//import com.hb.comoencasa.ports.secondary.RoleRepository;


@Service
public class CompradorService {
    @Autowired
    private CompradorRepository compradorRepository;

    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ListaProductoRepository listaProductoRepository;

    @Autowired
    private ResenaRepository resenaRepository;
    //@Autowired
    //private RoleRepository roleRepository;

    //@Autowired
    //private cecUserDetailsService CecUserDetailsService;



    public Comprador register(Comprador comprador) throws Exception {
       Comprador c = null;
       c = comprador;

       if(c == null){
           throw new Exception("No se pudo registrar");
       }else{
           System.out.println("Comprador Registrado");
           return compradorRepository.save(c);
       }
    }

    public Comprador obtenerComprador(Long Id) throws Exception{
        return compradorRepository.findById(Id).orElseThrow(
            ()->new Exception("No se encontro comprador"));
    }

    public Factura registrarFactura(Factura factura, Long IdC, Long IdP) throws Exception{
        Factura f = null;
        f = factura;
        Comprador c = null;
        c = obtenerComprador(IdC);
        Producto p = null;
        p = productoRepository.findById(IdP).orElseThrow(() -> new Exception("No se encontro producto"));
        if (p == null || f == null || c == null) throw new Exception("No se pudo registrar");
        else {
            f.setComprador(c);
            f.setProducto(p);
            f.setSubTotal(p.getPrice());
            f.setTotal((f.getCantidad() * f.getSubTotal()) + f.getEnvio());
            System.out.println("Se registró el producto");
            return facturaRepository.save(f);
        }
    }

    public List<Factura> listarFacturasComprador(Long Id){
        return facturaRepository.facturasComprador(Id);
    }

    
    public Lista_Producto anadirProducto(Lista_Producto producto, Long IdC, Long IdP) throws Exception {
        Lista_Producto p = null;
        p = producto;
        Comprador c = null;
        c = obtenerComprador(IdC);
        if (p == null) {
            throw new Exception("No se pudo agregar");
        } else {
            Producto p1 = null;
            p1 = productoRepository.findById(IdP).get();
            p.setComprador(c);
            p.setProducto(p1);
            System.out.println("Producto agregado a la lista");
            return listaProductoRepository.save(p);
        }
    }

    public List<Lista_Producto> listarProductosComprador(Long Id){
        return listaProductoRepository.productosComprador(Id);
    }

    public Lista_Producto eliminarProductoLista(Long id) throws Exception {
        Lista_Producto producto = listaProductoRepository.findById(id).orElseThrow(() -> new Exception("No se encontro producto"));
        listaProductoRepository.delete(producto);
        return producto;
    }

    public Resena anadirResena(Resena resena, Long IdC, Long IdP) throws Exception{
        Resena r = null;
        r = resena;
        Comprador c = null;
        c = obtenerComprador(IdC);
        if (r == null){
            throw new Exception("No se pudo agregar la reseña");
        }else{
            Producto p1 = null;
            p1 = productoRepository.findById(IdP).get();
            r.setComprador(c);
            r.setProducto(p1);
            System.out.println("Reseña agregada");
            return resenaRepository.save(r);

        }

    }

    public List<Resena> listarResenas(Long Id) {
        return resenaRepository.resenasComprador(Id);
    }



}


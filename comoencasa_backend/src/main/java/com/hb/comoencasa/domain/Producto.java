package com.hb.comoencasa.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Producto")
public class Producto implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 5692494217372834410L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;
    private String name;
    private double price;
    private String description;
    private String images;
    private String tags;

    @ManyToOne()
    @JoinColumn(name = "vendedor_Id")
    private Vendedor vendedor;

    @OneToMany(mappedBy = "producto", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Lista_Producto> lista_producto;

    @OneToMany(mappedBy = "producto", cascade=CascadeType.ALL)
    @JsonIgnore
    private List<Resena> resena;

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public List<Lista_Producto> getLista_producto() {
        return lista_producto;
    }

    public void setLista_producto(List<Lista_Producto> lista_producto) {
        this.lista_producto = lista_producto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}

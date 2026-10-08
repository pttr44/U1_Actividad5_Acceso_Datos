package com.promehub;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name = "catalogo") //elemento raiz en el XML
@XmlAccessorType (XmlAccessType.FIELD) //JAXB trabaja directamente con los atributos
public class Catalogo {

    @XmlElement (name = "videojuego")
    public List<com.promehub.Videojuego> catalogo;

    public Catalogo(){

    }

    public Catalogo(List<Videojuego> catalogo) {
        this.catalogo = catalogo;
    }

    public List<Videojuego> getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(List<Videojuego> catalogo) {
        this.catalogo = catalogo;
    }
}

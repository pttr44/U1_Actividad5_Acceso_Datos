import com.promehub.Videojuego;

import java.util.List;

@XmlRootElement (name = "catalogo") //elemento raiz en el XML
@XmlAccesorType (XmlAccesType.FIELD) //JAXB trabaja directamente con los atributos
public class Catalogo {

    @XmlElement (name = "videojuego")
    private List<com.promehub.Videojuego> catalogo;

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

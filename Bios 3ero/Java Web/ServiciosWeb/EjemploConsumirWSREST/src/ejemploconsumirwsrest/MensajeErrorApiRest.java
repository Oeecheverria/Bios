/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemploconsumirwsrest;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Raul
 */
@XmlRootElement
public class MensajeErrorApiRest implements Serializable {
    
    private String mensajeAmigable;
    private String mensajeExcepcion;
    
    
    public String getMensajeAmigable() {
        return mensajeAmigable;
    }
    
    @XmlElement
    public void setMensajeAmigable(String mensajeAmigable) {
        this.mensajeAmigable = mensajeAmigable;
    }
    
    public String getMensajeExcepcion() {
        return mensajeExcepcion;
    }
    
    @XmlElement
    public void setMensajeExcepcion(String mensajeExcepcion) {
        this.mensajeExcepcion = mensajeExcepcion;
    }
    
    
    public MensajeErrorApiRest() {
        this(null, null);
    }
    
    public MensajeErrorApiRest(String mensajeAmigable) {
        this(mensajeAmigable, null);
    }
    
    public MensajeErrorApiRest(String mensajeAmigable, String mensajeExcepcion) {
        setMensajeAmigable(mensajeAmigable);
        setMensajeExcepcion(mensajeExcepcion);
    }
    
}

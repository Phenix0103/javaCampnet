/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author ASUS
 */
public class CentreCamp {
     float id;
String nom_centre;
    String Description_centre;
    String img_centre;
    String lieux;
    String tlf_centre;
    String mail_centre;
   
    public CentreCamp() {
    }
    
  

    public CentreCamp(String nom_centre, String Description_centre, String img_centre, String lieux, String tlf_centre, String mail_centre) {
        this.nom_centre = nom_centre;
        this.Description_centre = Description_centre;
        this.img_centre = img_centre;
        this.lieux = lieux;
        this.tlf_centre = tlf_centre;
        this.mail_centre = mail_centre;
        
    }

    public CentreCamp(float id, String nom_centre, String Description_centre, String img_centre, String lieux, String tlf_centre, String mail_centre) {
        this.id = id;
        this.nom_centre = nom_centre;
        this.Description_centre = Description_centre;
        this.img_centre = img_centre;
        this.lieux = lieux;
        this.tlf_centre = tlf_centre;
        this.mail_centre = mail_centre;
       
    }

    public float getId() {
        return id;
    }

    public String getNom_centre() {
        return nom_centre;
    }

    public String getDescription_centre() {
        return Description_centre;
    }

    public String getImg_centre() {
        return img_centre;
    }

    public String getLieux() {
        return lieux;
    }

    public String getTlf_centre() {
        return tlf_centre;
    }

    public String getMail_centre() {
        return mail_centre;
    }

    public void setId(float id) {
        this.id = id;
    }

    public void setNom_centre(String nom_centre) {
        this.nom_centre = nom_centre;
    }

    public void setDescription_centre(String Description_centre) {
        this.Description_centre = Description_centre;
    }

    public void setImg_centre(String img_centre) {
        this.img_centre = img_centre;
    }

    public void setLieux(String lieux) {
        this.lieux = lieux;
    }

    public void setTlf_centre(String tlf_centre) {
        this.tlf_centre = tlf_centre;
    }

    public void setMail_centre(String mail_centre) {
        this.mail_centre = mail_centre;
    }
    
    
}

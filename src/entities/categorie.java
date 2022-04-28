/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;



/**
 *
 * @author sarra
 */
public class categorie {
    int id;
    String theme;
    String description;
    
   

    public categorie() {
    }

    public categorie(int id, String theme, String description) {
        this.id = id;
        this.theme = theme;
        this.description = description;
        
    }
    

    

    public categorie(String description, String theme) {
       this.theme = theme;
        this.description = description;
       
    }

    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }

    
    
    public String gettheme() {
        return theme;
    }

    public void settheme(String theme) {
        this.theme = theme;
    }

    public String getdescription() {
        return description;
    }

    public void setdescription(String description) {
        this.description = description;
    }

   

    @Override
    public String toString() {
        return "categorie{" + "id=" + id + ", theme=" + theme + ", description=" + description +  '}';
    }
    
     @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final categorie other = (categorie) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
}

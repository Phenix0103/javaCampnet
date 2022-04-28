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
public class commentaire {
    int id;
   
    String Description;
    
   

    public commentaire() {
    }

    public commentaire(int id, String Description) {
        this.id = id;
       
        this.Description = Description;
        
    }
    

    

    public commentaire(String Description) {
       
        this.Description = Description;
       
    }

    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }

    
    
   
    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

   

    @Override
    public String toString() {
        return "commentaire{" + "id=" + id + ", Description=" + Description +  '}';
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
        final commentaire other = (commentaire) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
}

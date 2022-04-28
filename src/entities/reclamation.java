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
public class reclamation {
    int id;
    String description;
    String email;
   

    public reclamation() {
    }

    public reclamation(int id, String description, String email) {
        this.id = id;
        this.description = description;
        this.email = email;
    }
    

    

    public reclamation(String description, String email) {
      this.description = description;
        this.email = email;
    }

    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }

    public String getdescription() {
        return description;
    }

    public void setdescription(String description) {
        this.description = description;
    }

    public String getemail() {
        return email;
    }

    public void setemail(String email) {
        this.email = email;
    }

   

    @Override
    public String toString() {
        return "reclamation{" + "id=" + id + ", description=" + description + ", email=" + email + '}';
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
        final reclamation other = (reclamation) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
}

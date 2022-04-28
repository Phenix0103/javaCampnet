/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author POSTE HP
 */
public class reservation {
     int id;
    int nbr_pers;
     Date date;
    Date date_r ;
 int evenement_id;

    public reservation() {
    }

    public reservation(int id, int nbr_pers, Date date, Date date_r, int evenement_id) {
        this.id = id;
        this.nbr_pers = nbr_pers;
        this.date = date;
        this.date_r = date_r;
        this.evenement_id = evenement_id;
    }

    public reservation(int nbr_pers, Date date, Date date_r, int evenement_id) {
        this.nbr_pers = nbr_pers;
        this.date = date;
        this.date_r = date_r;
        this.evenement_id = evenement_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNbr_pers() {
        return nbr_pers;
    }

    public void setNbr_pers(int nbr_pers) {
        this.nbr_pers = nbr_pers;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate_r() {
        return date_r;
    }

    public void setDate_r(Date date_r) {
        this.date_r = date_r;
    }

    public int getEvenement_id() {
        return evenement_id;
    }

    public void setEvenement_id(int evenement_id) {
        this.evenement_id = evenement_id;
    }

    @Override
    public String toString() {
        return "reservation{" + "id=" + id + ", nbr_pers=" + nbr_pers + ", date=" + date + ", date_r=" + date_r + ", evenement_id=" + evenement_id + '}';
    }
 
 
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iservice;

import entities.produit;

import java.util.List;

/**
 *
 * @author sarra
 */

public interface iserviceproduit {
    
 public void Addproduct(produit p);
 public  List<produit> Readproduit();
 
}
    

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.cpo.s2.Cartes;

import projet.cpo.s2.Joueur;

/**
 *
 * @author Pierre
 */
public class CarteDCo extends Carte{

    public CarteDCo() {
        super(1,1,12);
    }

    @Override
    public int getValC(Joueur j, int tas) {
        return 0; //si la valeure renoyée est 1 on reset la valeur du tas dans la méthode addTas
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
}

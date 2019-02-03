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
public class Carte4 extends Carte {

    public Carte4(int coulC, int formeC) {
        super(coulC, formeC, 4);
    }

    @Override
    public int getValC(Joueur j, int tas) {
        return tas+4;
    }
    
    @Override
    public String toString() {
        return super.toString();
    }
    
    
    
}

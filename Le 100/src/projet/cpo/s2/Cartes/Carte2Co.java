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
public class Carte2Co extends Carte{
    
    public Carte2Co() {
        super(1,1,2);
    }

    public int getValC(Joueur j, int tas) {
        return tas*2;
    }

    @Override
    public String toString() {
        return super.toString();
    }   
}

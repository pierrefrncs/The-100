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
public class CarteRoi extends Carte{

    public CarteRoi(int coulC, int formeC) {
        super(coulC, formeC,13);
    }

    @Override
    public int getValC(Joueur j, int tas) {
        return tas; //To change body of generated methods, choose Tools | Templates.
    } 

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    } 
}

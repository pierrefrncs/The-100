/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.cpo.s2.Cartes;

import java.util.Random;
import java.util.Scanner;
import projet.cpo.s2.Joueur;

/**
 *
 * @author Pierre
 */
public class CarteAN extends Carte{

    public CarteAN(int formeC) {
        super(2, formeC,1);
    }

    @Override
    public int getValC(Joueur j, int tas) {
        int temp=j.demVal();        
        return temp; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }  
}

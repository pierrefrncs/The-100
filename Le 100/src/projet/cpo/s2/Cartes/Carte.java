/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.cpo.s2.Cartes;

import java.util.LinkedList;
import projet.cpo.s2.Joueur;

/**
 *
 * @author pfrancoi
 */
public class Carte {
    private int valC; //1= As , 2=2, ... , 13 = Roi
    private int formeC;//1= Coeur , 2= Carreau, 3= Trèfle, 4= Pique
    private int coulC;//1= rouge, 2= Noir

//constructeur
    public Carte(int coulC,int formeC,int valC) {
        this.formeC = formeC;
        this.valC = valC; 
        this.coulC=coulC;
    }

//donne l'info sur la valeur de la carte/ on passe le joueur en argument pour la carte AS NOIR,
//    pour appliquer une fonctionnement particulier en fonction de la classe du joueur
    public int getValC(Joueur j, int tas) {
        return tas+valC;
    }
    
    public int getValC() {
        return valC;
    }
    
// donne l'info sur la forme de la carte 
    public int getFormeC() {
        return formeC;
    }
 
//affiche les infos nécéssaires sur la carte    
    @Override
    public String toString() {
        return "valC=" + valC + ", formeC=" + formeC ;
    }  
}

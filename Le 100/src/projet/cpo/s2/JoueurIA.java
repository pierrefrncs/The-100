/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.cpo.s2;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import projet.cpo.s2.Cartes.Carte;

/**
 *
 * @author pfrancoi
 */
public class JoueurIA extends Joueur{    
    
    public JoueurIA(int i) {
        super("IA "+i);
    }

    //à améliorer, en mettant masse conditions pour la jouer stratégique
    @Override
    public Carte defausse() { 
        this.afficherMain();
        
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(JoueurIA.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Carte temp=this.mainJ.removeFirst();
        System.out.println("\nL'IA a jouer : " + temp.toString());
        
        return temp;
    }

    @Override
    public int demVal() {
        Random rn = new Random();
        int temp=0;
        temp=rn.nextInt(100)+1;
        return temp; //To change body of generated methods, choose Tools | Templates.
    }
    
    

    @Override
    public void afficherMain() {
        super.afficherMain();
    }
    
    @Override
    public void addCarte(Carte c) {
        super.addCarte(c); 
    }   
}

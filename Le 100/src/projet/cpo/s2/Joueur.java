/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.cpo.s2;

import java.util.InputMismatchException;
import projet.cpo.s2.Cartes.Carte;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author pfrancoi
 */
public class Joueur {
    protected LinkedList<Carte> mainJ = new LinkedList();
    private String nomJ;
    private int nbVictoires;

    public Joueur(String nomJ) {
        this.nomJ = nomJ;
        this.nbVictoires = 0;
    }
   
    //ajoute une carte de la pioche vers la main du joueur
    public void addCarte(Carte c){
        this.mainJ.add(c);
    }
    
    public int demVal(){
        int temp=0;
        boolean test =false;      
        Scanner sc = new Scanner(System.in);
        do{
            try{
            System.out.println("Quelle valeur voulez vous donner au tas ? ");
                temp=sc.nextInt();
                test=true;
            }catch(InputMismatchException ex)
            {
                System.out.println("msg");
                sc.next();
                test=false;
            }
        }while(test==false);       
        return temp;
    }
    
    //choisit une carte à défausser 
    public Carte defausse(){
        Scanner sc=new Scanner(System.in);
        boolean test=false;
        int ind=0;
        //le joueur vois sa main (main courante)
        System.out.println("\nVoici votre main : \n");
        this.afficherMain();

        
       
        do{
            try{
                System.out.println("\nQuelle carte voulez vous jouer ?");
                ind=(sc.nextInt()-1);
                if(ind>2 || ind<0){
                    System.out.println("Index de la carte incorrect, veuillez rentrer une nouvelle valeure :");
                    test=false;
                }
                else{
                    test=true;
                }
            }catch(InputMismatchException ex){
                System.out.println("msg");
                sc.next();
                test=false;
            }
        }while(test==false);
        
        Carte temp=mainJ.get(ind);
        this.mainJ.remove(ind);
        
        System.out.println("\nVous avez jouer : " + temp.toString());
        return temp;
    }
    
    //affiche une a une les cartes dans la main
    public void afficherMain(){
        for (Carte c:mainJ)
            System.out.println("    "+c.toString());
    }

    public String getNomJ() {
        return nomJ;
    }

    public LinkedList<Carte> getMainJ() {
        return mainJ;
    }

    public void ResetMainJ() {
        this.mainJ.clear();
    }

    public void setNbVictoires() {
        this.nbVictoires++;
    }   

    public int getNbVictoires() {
        return nbVictoires;
    }
}

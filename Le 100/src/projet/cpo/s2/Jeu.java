/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.cpo.s2;

import projet.cpo.s2.Cartes.Carte4;
import projet.cpo.s2.Cartes.CarteRoi;
import projet.cpo.s2.Cartes.Carte;
import projet.cpo.s2.Cartes.CarteDameTPC;
import projet.cpo.s2.Cartes.CarteV;
import projet.cpo.s2.Cartes.Carte5R;
import projet.cpo.s2.Cartes.CarteDCo;
import projet.cpo.s2.Cartes.CarteAN;
import projet.cpo.s2.Cartes.Carte2Co;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author pfrancoi
 */
public class Jeu {
    private LinkedList<Joueur> listJ = new LinkedList(); //tableau des joueurs 
    private LinkedList<Carte> listP = new LinkedList(); //Pioche contenant des cartes 
    private LinkedList<Carte> listT = new LinkedList(); //Tas de défausse 
    private LinkedList<Joueur> stockJ = new LinkedList();
    private int nbJ; //nombre de joueurs de départ 
    private int valT=0; //valeur courante du tas 
    private int valM=0; //indice de la manche en cour 
    private int tourJ; //indice du joueur courant 

    //constructeur (initialisation de la partie et du plateau de jeu) 
    public Jeu() throws InterruptedException{
        Scanner sc=new Scanner(System.in);
        String temp;
        boolean test=false;
        int nbJR=0; // joueurs réels
 
        //crée les joueurs/IA
        do{
            try{
                System.out.println("\nA combien voulez vous jouer ? ");
                this.nbJ=sc.nextInt(); 
                test=true;
             }catch(InputMismatchException ex){
                 System.out.println("msg");
                 sc.next();
                 test=false;
             }
        }while(test==false);
        
        do{
            try{
                System.out.println("\nCombien de joueurs réels voulez vous dans cette partie ? ");
                nbJR=sc.nextInt(); 
                test=true;
             }catch(InputMismatchException ex){
                 System.out.println("msg");
                 sc.next();
                 test=false;
             }
        }while(test==false);

        //joueurs
        for(int i=0;i<nbJR;i++){
            System.out.println("\nEntrez le nom du "+(i+1)+" joueur");
            temp=sc.next();
            listJ.add(new Joueur(temp));
        }
        
        //IA
        for(int i=0;i<(nbJ-nbJR);i++){
            listJ.add(new JoueurIA((i+1)));
        } 
        
        //on créee une "sauvegarde de notre liste de joueurs au cas ou la partie est relancée. C'est dans cette liste que l'on stock aussi les scores
        for (Joueur j:listJ)
            this.stockJ.add(j);
    }
    
    public void partieBoucle() throws InterruptedException{
        int test=0;
        boolean temp=false;
        Scanner sc = new Scanner(System.in);
        
        do {
            this.JeuManche();
            
            System.out.println("Voici les scores des joueurs : ");
            
            for(Joueur j:stockJ)
                System.out.println(j.getNomJ() + " : " + j.getNbVictoires() + " pt");

            do{
                do{
                    try{
                        System.out.println("\nVoulez  vous rejouer une partie ? 1 - Oui 2 - Non");
                        test=sc.nextInt();
                        temp=true;
                    }catch(InputMismatchException ex){
                        System.out.println("msg");
                        sc.next();
                        temp=false;
                    }
                }while(temp!=true);
            }while(test<1 && test>2);
            
            //remet dans la liste des joueur 
            if (test==1){
                this.listJ.clear();
                for(Joueur j:stockJ)
                this.listJ.add(j);
            }
        }while(test!=2);
        
        System.out.println("Fin du jeu au revoir");
    }
    
    //fait tourner tout le jeu manche par manche
    public void JeuManche() throws InterruptedException{
        this.initPioche();
        int temp=listT.size();
        do{ 
            for(this.tourJ=0;tourJ<listJ.size();this.tourJ++){
                System.out.println("\n----------------------------------------"
                        + "\nLa carte sur le dessus du tas est :" + listT.getLast().toString()+"\nValeur actuelle du tas : "+ valT);           
                System.out.println("\nTour de :" + listJ.get(tourJ).getNomJ());
                this.tourJoueur(listJ.get(tourJ));

                if(valT>=100){
                   System.out.println("\nLe joueur "+this.listJ.get(tourJ).getNomJ()+" a été éliminé\n"); 
                   this.listJ.remove(tourJ);
                   
                   this.valT=50;
                }
                
                if(listP.isEmpty()){
                    for(int i=0;i<(temp-2);i++){
                        this.listP.add(this.listT.removeFirst());
                    }
                    Collections.shuffle(listP);
                }
           }
            
           this.valM++;           
        }while(this.victoire()==false); 
    }

    //fait jouer un joueur 
    public void tourJoueur(Joueur j) throws InterruptedException{
        //ajoute la carte défaussée dans le tas directement de la main du joueur
        this.listT.add(j.defausse());

        //calcul de la nouvelle valeur du tas/changement de sens si nécessaire
        this.addTas(listT.getLast(),j); 
        TimeUnit.SECONDS.sleep(1);
        //le joueur pioche une carte
        j.addCarte(listP.removeLast());        
    } 

    //ajoute la valeur de la carte jouée sur le tas, ou en modifie la valeur en fonction des attributs de la carte    
    public void addTas(Carte c,Joueur tempJ){
        this.valT=c.getValC(tempJ,valT);
        
        if(listT.getLast().getValC()==4){
             LinkedList<Joueur> temp = new LinkedList();
              for(Joueur j:listJ)
                  temp.add(j);
              
              this.listJ.clear();
              int taille=temp.size();
              
              for(int i =0;i<taille;i++){
                  this.listJ.add(temp.removeLast());
              }
        }
    }
    
    //test de la condition de last man standing
    public boolean victoire(){
        if (listJ.size()==1){
            System.out.println("Le joueur "+listJ.getFirst().getNomJ() + " est le 'lastman standing' de la partie, il a donc gagné");
            for(Joueur j:stockJ)
                    if(j.getNomJ().equals(listJ.getFirst().getNomJ()))
                        j.setNbVictoires();
            
            return true;
        }
        return false;
    }
    
    //initialise/réinitialise la pioche/tas/main joueur
    public void initPioche(){
        this.listP.clear();
        this.listT.clear();

        //Création/ajout des cartes spéciales
        CarteRoi roiCo = new CarteRoi(1,1);listP.add(roiCo);
        CarteRoi roiCa = new CarteRoi(1,2);listP.add(roiCa);
        CarteRoi roiT = new CarteRoi(2,3);listP.add(roiT);
        CarteRoi roiP = new CarteRoi(2,4);listP.add(roiP);

        CarteDameTPC dameCa = new CarteDameTPC(1,2);listP.add(dameCa);
        CarteDameTPC dameT = new CarteDameTPC(2,3);listP.add(dameT);
        CarteDameTPC dameP = new CarteDameTPC(2,4);listP.add(dameP);

        CarteDCo dameCo = new CarteDCo();listP.add(dameCo);

        CarteV valetCo = new CarteV(1,1);listP.add(valetCo);
        CarteV valetCa = new CarteV(1,2);listP.add(valetCa);
        CarteV valetT = new CarteV(2,3);listP.add(valetT);
        CarteV valetP = new CarteV(2,4);listP.add(valetP);

        Carte5R cinqCo = new Carte5R(1);listP.add(cinqCo);
        Carte5R cinqCa = new Carte5R(2);listP.add(cinqCa);

        Carte4 quatreCo = new Carte4(1,1);listP.add(quatreCo);
        Carte4 quatreCa = new Carte4(1,2);listP.add(quatreCa);
        Carte4 quatreT = new Carte4(2,3);listP.add(quatreT);
        Carte4 quatreP = new Carte4(2,4);listP.add(quatreP);

        Carte2Co deuxCo = new Carte2Co();listP.add(deuxCo);

        CarteAN asT = new CarteAN(3);listP.add(asT);
        CarteAN asP = new CarteAN(4);listP.add(asP);

        //Création/ajout des carte normal
        Carte dixCa = new Carte(1,1,10);listP.add(dixCa);
        Carte dixCo = new Carte(1,2,10);listP.add(dixCo);
        Carte dixT = new Carte(2,3,10);listP.add(dixT);
        Carte dixP = new Carte(2,4,10);listP.add(dixP);

        Carte neufCo = new Carte(1,1,9);listP.add(neufCo);
        Carte neufCa = new Carte(1,2,9);listP.add(neufCa);
        Carte neufT = new Carte(2,3,9);listP.add(neufT);
        Carte neufP = new Carte(2,4,9);listP.add(neufP);

        Carte huitCo = new Carte(1,1,8);listP.add(huitCo);
        Carte huitCa = new Carte(1,2,8);listP.add(huitCa);
        Carte huitT = new Carte(2,3,8);listP.add(huitT);
        Carte huitP = new Carte(2,4,8);listP.add(huitP);

        Carte septCo = new Carte(1,1,7);listP.add(septCo);
        Carte septCa = new Carte(1,2,7);listP.add(septCa);
        Carte septT = new Carte(2,3,7);listP.add(septT);
        Carte septP = new Carte(2,4,7);listP.add(septP);

        Carte sixCo = new Carte(1,1,6);listP.add(sixCo);
        Carte sixCa = new Carte(1,2,6);listP.add(sixCa);
        Carte sixT = new Carte(2,3,6);listP.add(sixT);
        Carte sixP = new Carte(2,4,6);listP.add(sixP);

        Carte cinqT = new Carte(2,3,5);listP.add(cinqT);
        Carte cinqP = new Carte(2,4,5);listP.add(cinqP);

        Carte troisCo = new Carte(1,1,3);listP.add(troisCo);
        Carte troisCa = new Carte(1,2,3);listP.add(troisCa);
        Carte troisT = new Carte(2,3,3);listP.add(troisT);
        Carte troisP = new Carte(2,4,3);listP.add(troisP);

        Carte deuxCa = new Carte(1,2,2);listP.add(deuxCa);
        Carte deuxT = new Carte(2,3,2);listP.add(deuxT);
        Carte deuxP = new Carte(2,4,2);listP.add(deuxP);

        Carte asCo = new Carte(1,1,1);listP.add(asCo);
        Carte asCa = new Carte(1,2,1);listP.add(asCa);

        //Mélange du pacquet de cartes
        Collections.shuffle(listP);
        
        //vide les mains 
        for(int i=0;i<listJ.size();i++){
            this.listJ.get(i).ResetMainJ();
        }

        //Distribution des cartes (6 dans la main) 
        for(int i=0;i<listJ.size();i++){
            for(int j=0;j<3;j++){
               listJ.get(i).addCarte(listP.removeLast());
            }
        }
        listT.add(listP.removeLast());
        this.valT=listT.getFirst().getValC();
    }
}

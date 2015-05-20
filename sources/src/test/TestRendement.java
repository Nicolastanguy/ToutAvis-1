package test;

import exception.BadEntry;
import exception.ItemBookAlreadyExists;
import exception.ItemFilmAlreadyExists;
import exception.MemberAlreadyExists;
import exception.NotMember;
import avis.SocialNetwork;

public class TestRendement {
	
	public static void sequenceTestRendement() throws BadEntry, MemberAlreadyExists, NotMember, ItemBookAlreadyExists, ItemFilmAlreadyExists {
		
		System.out.println("Tests de rendement du réseau social");
		
	//Tests de capacité
		
		//Création et remplissage du réseau social de test
		SocialNetwork snRendement = new SocialNetwork();
		
		long maxDuration = 2000000000; // = 2s
		long tic = 0;
		long tac = 0;
		long duration = 0; 
		int nbTests = 0;
		int nbErreurs = 0;
		int nbMaxMember = 500;
		int nbMaxBooks = 2500;
		int nbMaxFilms = 2500;
		int nbMaxAdd = nbMaxFilms+nbMaxBooks+nbMaxMember;
		
		System.out.println("	Ajout de "+ nbMaxMember +" membres, "+nbMaxBooks+" livres et "+nbMaxFilms+" films");
		tic=System.nanoTime();
		for(int i = 1 ; i <= nbMaxMember ; i++){
			snRendement.addMember("Personne"+i,"psw"+i,"profil"+i);
		}
		for(int i = 1 ; i <= nbMaxBooks ; i++){
			snRendement.addItemBook("Personne1", "psw1", "titreBook"+i, "genre"+i, "auteur"+i, 250);
		}
		for(int i = 1 ; i <= nbMaxFilms ; i++){
			snRendement.addItemFilm("Personne1", "psw1", "titreFilm"+i, "genre"+i, "réalisateur"+i, "scénariste"+i, 120);
		}
		tac=System.nanoTime();
		duration=tac-tic;
		nbTests++;	
		
		float timeAdd;	
		timeAdd =((float)duration/(float)nbMaxAdd)/1000000; //Calcul du temps moyen en ms d'ajout d'un objet au SN(membre, livre ou film)
		if ((duration/nbMaxAdd)>maxDuration) {							
			System.out.format("	   Le temps d'ajout des membres et des items semble trop long : %.3f ms par ajout", timeAdd);
			nbErreurs++;
		}else System.out.format("		Durée de cet ajout : "+duration+" ns soit environ : %.3f ms par ajout \n", timeAdd);
		
		
		//Vérification du contenu du réseau social
		System.out.println("	Vérification du contenu de ce réseau social...");
		nbTests++;
		if (snRendement.nbMembers() != nbMaxMember){
			System.out.println("		Le réseau social contient "+ snRendement.nbMembers()+ " membres au lieu de "+nbMaxMember+" membres.");
			nbErreurs++;
		}
		nbTests++;
		if (snRendement.nbBooks() != nbMaxBooks){
			System.out.println("		Le réseau social contient "+ snRendement.nbBooks()+" livres au lieu de "+nbMaxBooks+" livres.");
			nbErreurs++;
		}
		nbTests++;
		if (snRendement.nbFilms() != nbMaxFilms){
			System.out.println("		Le réseau social contient "+ snRendement.nbFilms()+" films au lieu de "+nbMaxFilms+" films.");
			nbErreurs++;
		}
		
	//Tests de perfomance
		System.out.println("	Ajout d'un membre :");
		//Ajout d'un membre
		tic=System.nanoTime();
		snRendement.addMember("Personne0","psw0","profil0");
		tac=System.nanoTime();
		duration=tac-tic;
		nbTests++;
		if ((duration) >= maxDuration){
			System.out.println("	L'ajout d'un membre est trop long ("+duration+" ns)");
			nbErreurs++;
		}else System.out.println("		Durée de l'ajout d'un membre : "+duration+" ns");
		
		System.out.println("	Ajout d'un livre :");
		//Ajout d'un livre
		tic=System.nanoTime();
		snRendement.addItemBook("Personne1", "psw1", "titreBook0", "genre0", "auteur0", 250);
		tac=System.nanoTime();
		duration=tac-tic;
		nbTests++;
		if ((duration) >= maxDuration){
			System.out.println("	L'ajout d'un livre est trop long ("+duration+" ns)");
			nbErreurs++;
		}else System.out.println("		Durée de l'ajout d'un livre : "+duration+" ns");
		
		//Ajout d'un film
		System.out.println("	Ajout d'un film :");
		tic=System.nanoTime();
		snRendement.addItemFilm("Personne1", "psw1", "titreFilm0", "genre0", "réalisateur0", "scénariste0", 120);
		tac=System.nanoTime();
		duration=tac-tic;
		nbTests++;
		if ((duration) >= maxDuration){
			System.out.println("	L'ajout d'un film est trop long ("+duration+" ns)");
			nbErreurs++;
		}
		else System.out.println("		Durée de l'ajout d'un film : "+duration+" ns");
		
		//Consultation d'item
		System.out.println("	Consultation d'un item/Recherche d'un item :");
		tic=System.nanoTime();
		snRendement.consultItems("titreFilm1");
		tac=System.nanoTime();
		duration=tac-tic;
		nbTests++;
		if ((duration) >= maxDuration){
			System.out.println("	La consultation d'un item est trop long ("+duration+" ns)");
			nbErreurs++;
		}else System.out.println("		Durée pour la recherche d'un item (ConsultItems) : "+duration+" ns");
		
	//Test sur l'espace disque

		nbTests++;
		if ((Runtime.getRuntime().totalMemory()/1000)>100000000) {
			System.out.println("	Le programme est trop lourd.");
			nbErreurs++;
		}
		
	// bilan du test de addItemBook
		System.out.println("TestAddItemBook :   " + nbErreurs + " erreur(s) / " +  nbTests + " tests effectués\n");
		
		TestSocialNetwork.nbTests += nbTests;
		TestSocialNetwork.nbErreurs += nbErreurs;	
	}
}
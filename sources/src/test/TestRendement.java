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
		
		int nbTests = 0;
		int nbErreurs = 0;
		
		int nbMaxMember = 500;
		int nbMaxBooks = 2500;
		int nbMaxFilms = 2500;
		int nbMaxItems = nbMaxBooks+nbMaxFilms;
		
		for(int i = 1 ; i <= nbMaxMember ; i++){
			snRendement.addMember("Personne"+i,"psw"+i,"profil"+i);
		}
		for(int i = 1 ; i <= nbMaxBooks ; i++){
			snRendement.addItemBook("Personne1", "psw1", "titreBook"+i, "genre"+i, "auteur"+i, 250);
		}
		for(int i = 1 ; i <= nbMaxFilms ; i++){
			snRendement.addItemFilm("Personne1", "psw1", "titreFilm"+i, "genre"+i, "réalisateur"+i, "scénariste"+i, 120);
		}
		
		//Vérification du contenu du réseau social
		
		nbTests++;
		if (snRendement.nbMembers() != nbMaxMember){
			System.out.println("Le réseau social n'accepte pas jusqu'à "+nbMaxMember+" membres.");
			nbErreurs++;
		}
		nbTests++;
		if (snRendement.nbBooks()+snRendement.nbFilms() != nbMaxItems){
			System.out.println("Le réseau social n'accepte pas jusqu'à "+nbMaxItems+" items.");
			nbErreurs++;
		}
		
	//Tests de perfomance
		
		long maxDuration = 2000000000;
		long tic = 0;
		long tac = 0;
		long duration = 0;
		
		//Ajout d'un membre
		tic=System.nanoTime();
		snRendement.addMember("Personne0","psw0","profil0");
		tac=System.nanoTime();
		duration=tac-tic;
		nbTests++;
		if ((duration) >= maxDuration){
			System.out.println("L'ajout d'un membre est trop long ("+duration+" ns)");
			nbErreurs++;
		}
		
		//Ajout d'un livre
		tic=System.nanoTime();
		snRendement.addItemBook("Personne1", "psw1", "titreBook0", "genre0", "auteur0", 250);
		tac=System.nanoTime();
		duration=tac-tic;
		nbTests++;
		if ((duration) >= maxDuration){
			System.out.println("L'ajout d'un livre est trop long ("+duration+" ns)");
			nbErreurs++;
		}
		
		//Ajout d'un film
		tic=System.nanoTime();
		snRendement.addItemFilm("Personne1", "psw1", "titreFilm0", "genre0", "réalisateur0", "scénariste0", 120);
		tac=System.nanoTime();
		duration=tac-tic;
		nbTests++;
		if ((duration) >= maxDuration){
			System.out.println("L'ajout d'un film est trop long ("+duration+" ns)");
			nbErreurs++;
		}
		
		//Consultation d'item
		tic=System.nanoTime();
		snRendement.consultItems("titreFilm1");
		tac=System.nanoTime();
		duration=tac-tic;
		nbTests++;
		if ((duration) >= maxDuration){
			System.out.println("La consultation d'un item est trop long ("+duration+" ns)");
			nbErreurs++;
		}
		
	// bilan du test de addItemBook
		System.out.println("TestAddItemBook :   " + nbErreurs + " erreur(s) / " +  nbTests + " tests effectués\n");
		
		TestSocialNetwork.nbTests += nbTests;
		TestSocialNetwork.nbErreurs += nbErreurs;	
	}
}
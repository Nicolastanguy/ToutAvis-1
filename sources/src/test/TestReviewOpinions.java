package test;

import exception.*;
import avis.SocialNetwork;
import test.TestCreationSN;

public class TestReviewOpinions {
	
	public static int reviewOpinionsBadEntryTest(SocialNetwork sn,String pseudo1, String password,String titre,String type,String pseudo2, float note,String idTest,String messErreur){
		try {
			sn.reviewOpinions(pseudo1, password, titre, type, pseudo2, note);
			System.out.println(idTest+messErreur);
			return 1;
		}
		catch (BadEntry e){
			return 0;
		}
		catch (Exception e){
			System.out.println(idTest+"Exception non prevue");
			return 1;
		}	
	}

	
	// reviewOpinions(String pseudo1, String password,String titre,String type,String pseudo2, float note)
	
	public static void sequenceTestReviewOpinions() throws BadEntry, NotItem, NotMember{
		
		System.out.println("Tests noter les avis/notion de karma (lot2)");
		
		SocialNetwork sn = null;
		try {
			sn = TestCreationSN.createNewSocialNetwork();
		}
		catch (Exception e) {
			System.out.println("Erreur lors de la création du SN de test");
		}
		
		int nbMembers = sn.nbMembers();
		int nbBooks = sn.nbBooks();

		int nbTests = 0;
		int nbErreurs = 0;
		
		//Ajout d'avis sur des items déjà existant dans le SN :
		sn.reviewItemBook("personne1", "psw1", "Arts martiaux", 3.0F, "bien!");
		
		// tentative de notation d'un avis avec entrées "incorrectes"
		nbTests++;
		nbErreurs += reviewOpinionsBadEntryTest(sn,"Personne2","psw2","Arts martiaux","book","Personne1",3.0F,"id Test"," L'ajout d'une note à un avis a été accepté avec un pseudo non instancié"); 
		
		
		
		
	//	nbErreurs += reviewOpinions("Personne2","psw2","Arts martiaux","book","Personne1",3.0F); //Ajout d'une note à l'avis
		
		
		
		
		
		
		// bilan du test de addItemFilm
		System.out.println("TestReviewOpinions :   " + nbErreurs + " erreur(s) / " +  nbTests + " tests effectués\n");
		
		TestSocialNetwork.nbTests += nbTests;
		TestSocialNetwork.nbErreurs += nbErreurs;		
	}


}

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
	
	public static void sequenceTestReviewOpinions()throws BadEntry, MemberAlreadyExists, NotMember, ItemBookAlreadyExists, ItemFilmAlreadyExists, NotItem{
		
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
		
		
		// <=> Fiche 9 : Eprouver la méthode reviewOpinions sur les cas d’anomalies BadEntry.
		// 9.1 : reviewOpinions avec en paramètre un pseudo non instancié 
		nbTests++; 
		nbErreurs += reviewOpinionsBadEntryTest(sn,null,"psw2","Arts martiaux","book","Personne1",3.0F,"9.1a"," L'ajout d'une note à un avis a été accepté avec un pseudo1 non instancié"); 
		nbTests++;
		nbErreurs += reviewOpinionsBadEntryTest(sn,"   ","psw2","Arts martiaux","book","Personne1",3.0F,"9.1b"," L'ajout d'une note à un avis a été accepté avec un pseudo1 ne contenant que des espaces");
		nbTests++;
		nbErreurs += reviewOpinionsBadEntryTest(sn,"Personne2","psw2","Arts martiaux","book",null,3.0F,"9.1c"," L'ajout d'une note à un avis a été accepté avec un pseudo2 non instancié");
		nbTests++;
		nbErreurs += reviewOpinionsBadEntryTest(sn,"Personne2","psw2","Arts martiaux","book","   ",3.0F,"9.1d"," L'ajout d'une note à un avis a été accepté avec un pseudo2 ne contenant que des espaces");
		// 9.2: reviewOpinions avec en paramètre un password non instancié ou a moins de 4 caractères autres que des leadings or trailing blanks.
		nbTests++; 
		nbErreurs += reviewOpinionsBadEntryTest(sn,"Personne2",null,"Arts martiaux","book","Personne1",3.0F,"9.2a"," L'ajout d'une note à un avis a été accepté avec un password non instancié"); 
		nbTests++; 
		nbErreurs += reviewOpinionsBadEntryTest(sn,"Personne2","  p   ","Arts martiaux","book","Personne1",3.0F,"9.2a"," L'ajout d'une note à un avis a été accepté avec un password au format incorrect"); 
		// 9.3: reviewOpinions avec en paramètre un titre non instancié ou qui a moins de 1 caractère autre que des espaces.
		nbTests++; 
		nbErreurs += reviewOpinionsBadEntryTest(sn,"Personne2","psw2",null,"book","Personne1",3.0F,"9.3a"," L'ajout d'une note à un avis a été accepté avec un titre non instancié"); 
		nbTests++; 
		nbErreurs += reviewOpinionsBadEntryTest(sn,"Personne2","psw2","    ","book","Personne1",3.0F,"9.3b"," L'ajout d'une note à un avis a été accepté avec un titre ne contenant que des espaces"); 
		// 9.4: reviewOpinions avec une note non comprise entre 0.0 et 5.0.
		nbTests++; 
		nbErreurs += reviewOpinionsBadEntryTest(sn,"Personne2","psw2","Arts martiaux","book","Personne1",9.0F,"9.4"," L'ajout d'une note à un avis a été accepté avec une note non comprise entre 0 et 5"); 
		// 9.5: reviewOpinions avec un type non instancié ou différent de « book » ou « film ».
		nbTests++; 
		nbErreurs += reviewOpinionsBadEntryTest(sn,"Personne2","psw2","Arts martiaux",null,"Personne1",3.0F,"9.5a"," L'ajout d'une note à un avis a été accepté avec un type non instancié"); 
		nbTests++; 
		nbErreurs += reviewOpinionsBadEntryTest(sn,"Personne2","psw2","Arts martiaux","typeincorrect","Personne1",3.0F,"9.5b"," L'ajout d'une note à un avis a été accepté avec un type différent de book ou de film"); 
		
		// <=> Fiche 10 : Eprouver la méthode reviewOpinions dans le cas d’un fonctionnement standard.
		
		sn = null; //creation d'un nouveau sn
		try {
			sn = TestCreationSN.createNewSocialNetwork();
		}
		catch (Exception e) {
			System.out.println("Erreur lors de la création du SN de test");
		}
		
		
		
		
		// bilan du test de reviewOpinions
		System.out.println("TestReviewOpinions :   " + nbErreurs + " erreur(s) / " +  nbTests + " tests effectués\n");
		
		TestSocialNetwork.nbTests += nbTests;
		TestSocialNetwork.nbErreurs += nbErreurs;		
	}


}

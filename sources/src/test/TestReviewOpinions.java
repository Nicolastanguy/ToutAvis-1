package test;

import exception.*;
import avis.SocialNetwork;
import test.TestCreationSN;
import avis.Member;

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
	
	public static int reviewOpinionsOkTest(SocialNetwork sn,String pseudo1, String password,String titre,String type,String pseudo2, float note,String idTest,String messErreur,float karmaAttendu, float moyenneAttendu){
		try {
			float karmaMember;
			sn.reviewOpinions(pseudo1, password, titre, type, pseudo2, note);						
			
			System.out.println(idTest+messErreur);
			return 0;
			
		}
		catch (BadEntry e){
			return 1;
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
		
		//Ajout d'avis sur des items déjà existant dans le SN :
		sn.reviewItemBook("personne1", "psw1", "Arts martiaux", 4.0F, "bien!");
		sn.reviewItemBook("personne2", "psw2", "Arts martiaux", 2.5F, "moyen");
		sn.reviewItemBook("personne3", "psw3", "Arts martiaux", 0.5F, "nul");
		sn.reviewItemBook("personne1", "psw1", "La Chine", 4.0F, "bien!");
		sn.reviewItemBook("personne2", "psw2", "La Chine", 2.5F, "moyen");
		sn.reviewItemBook("personne3", "psw3", "La Chine", 0.5F, "nul");
		
		//Des membres notes l'avis d'un utilisateur : on vérifie la mise à jour du karma et de la note du review
		
		//personne2 et personne3 note des avis de personne1
		
//		reviewOpinionsOkTest(sn,"personne2","psw2","Arts martiaux","book","personne1",3.0F,"9.3a"," Le karma de l'utilisateur noté n'a pas la valeur attendue",3.0F,3.0F); 
//		reviewOpinionsOkTest(sn,"personne3","psw3","Arts martiaux","book","personne1",1.0F,"9.3a"," Le karma de l'utilisateur noté n'a pas la valeur attendue",2.0F,2.0F); 
//		
//		reviewOpinionsOkTest(sn,"personne2","psw2","La Chine","book","personne1",5.0F,"9.3a"," Le karma de l'utilisateur noté n'a pas la valeur attendue",3.0F,5.0F); 
//		reviewOpinionsOkTest(sn,"personne3","psw3","La Chine","book","personne1",2.3F,"9.3a"," Le karma de l'utilisateur noté n'a pas la valeur attendue",2.825F,3,65F); 
//		//Un membre tente de noter un avis qu'il à déjà noté
		
		//Un membre tente de noter ses propres avis
		
		//Vérifier que le karma des utilisateur à bien une influence sur un avis qu'il poste
		
		
		
		// bilan du test de reviewOpinions
		System.out.println("TestReviewOpinions :   " + nbErreurs + " erreur(s) / " +  nbTests + " tests effectués\n");
		
		TestSocialNetwork.nbTests += nbTests;
		TestSocialNetwork.nbErreurs += nbErreurs;		
	}


}

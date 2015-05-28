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
	
	public static int reviewOpinionsMemberAlreadyOpinion(SocialNetwork sn,String pseudo1, String password,String titre,String type,String pseudo2, float note,String idTest,String messErreur){
		try {
			sn.reviewOpinions(pseudo1, password, titre, type, pseudo2, note);
			System.out.println(idTest+messErreur);
			return 1;
		}
		catch (MemberAlreadyOpinion e){
			return 0;
		}
		catch (Exception e){
			System.out.println(idTest+"Exception non prevue");
			return 1;
		}	
	}
	
	public static int reviewOpinionsSameMember(SocialNetwork sn,String pseudo1, String password,String titre,String type,String pseudo2, float note,String idTest,String messErreur){
		try {
			sn.reviewOpinions(pseudo1, password, titre, type, pseudo2, note);
			System.out.println(idTest+messErreur);
			return 1;
		}
		catch (SameMember e){
			return 0;
		}
		catch (Exception e){
			System.out.println(idTest+"Exception non prevue");
			return 1;
		}	
	}
	public static int reviewOpinionKarmaTestFilm(SocialNetwork sn,String pseudo1, String password,String titre,float note,String idTest,String messErreur, float moyenneAttendue){
		try {
			float moyenne;			
			moyenne = sn.reviewItemBook(pseudo1, password, titre, note, "MDR");
			moyenne = (float)(Math.round(moyenne*1000));
			moyenne = moyenne/1000;
			System.out.println("Moyenne : "+moyenne);
			System.out.println("Moyenne prevue : "+moyenneAttendue);
			if(moyenne!=moyenneAttendue){
				System.out.println(idTest+messErreur);
				return 1;
			}
			return 0;
		}
		catch (Exception e){
			System.out.println(idTest+"Exception non prevue");
			return 1;
		}	
	}
	
	
	public static int reviewOpinionsOkTest(SocialNetwork sn,String pseudo1, String password,String titre,String type,String pseudo2, float note,String idTest,float karmaAttendu, float moyenneAttendue){
		try {
			float moyenneReview;
			moyenneReview = sn.reviewOpinions(pseudo1, password, titre, type, pseudo2, note);
			
			if(moyenneAttendue!=moyenneReview){
				System.out.println(idTest+" La moyenne du review ne correspond pas à la moyenne attendue");
				return 1;
			}
			if(sn.getKarma(pseudo2)!=karmaAttendu){
				System.out.println(idTest+" Le Karma du membre ne correspond pas au Karma attendu");
				return 1;
			}
			return 0;
			
		}
		catch (BadEntry e){
			System.out.println(idTest+" badEntry");
			return 1;
		}
		catch (Exception e){
			System.out.println(idTest+"Exception non prevue");
			return 1;
		}	
	}

	
	// reviewOpinions(String pseudo1, String password,String titre,String type,String pseudo2, float note)

	public static void sequenceTestReviewOpinions()throws BadEntry, MemberAlreadyExists, NotMember, ItemBookAlreadyExists, ItemFilmAlreadyExists, NotItem, MemberAlreadyOpinion{
		
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
		sn.reviewItemBook("personne2", "psw2", "Le Seigneur des anneaux", 2.5F, "moyen");
		
		
		//personne2 et personne3 notent des avis de personne1 Verif Karma + moyenne review
		nbTests++; 
		nbErreurs += reviewOpinionsOkTest(sn,"personne2","psw2","Arts martiaux","livre","personne1",3.0F,"10.1a",3.0F,3.0F); 
		nbTests++; 
		nbErreurs += reviewOpinionsOkTest(sn,"personne3","psw3","Arts martiaux","livre","personne1",1.0F,"10.1b",2.0F,2.0F); 
		nbTests++; 
		nbErreurs += reviewOpinionsOkTest(sn,"personne2","psw2","La Chine","livre","personne1",5.0F,"10.1c",3.0F,5.0F); 
		nbTests++; 
		nbErreurs += reviewOpinionsOkTest(sn,"personne3","psw3","La Chine","livre","personne1",2.3F,"10.1d",2.825F,3.65F); 
		
		//Un membre tente de noter un avis qu'il à déjà noté
		nbTests++; 
		nbErreurs += reviewOpinionsMemberAlreadyOpinion(sn,"personne3","psw3","Arts martiaux","livre","personne1",1.0F,"10.2"," Erreur : un membre à noté un avis qu'il avait déjà noté");
		
		
		//Un membre tente de noter ses propres avis
		nbTests++; 
		nbErreurs += reviewOpinionsSameMember(sn,"personne1","psw1","Arts martiaux","livre","personne1",1.0F,"10.3"," Erreur : un membre a noté son propre avis");
		
		//Vérifier que le karma des utilisateur à bien une influence sur un avis qu'il poste
		//personne 1 a un karma de 2.825 après les précédentes opérations
		//personne 1 poste un avis sur un livre que personne2 a déjà commenté
		//On vérifie que la moyenne de l'item a bien été modifiée en tenant compte du karma de personne1
		nbTests++; //3,826 de moy attendue
		nbErreurs += reviewOpinionKarmaTestFilm(sn,"personne1","psw1","Le Seigneur des anneaux",5.0F,"10.4"," Erreur : le karma de semble pas influencer la note mise par l'utilisateur", 3.826F);
		
		
		
		// bilan du test de reviewOpinions
		System.out.println("TestReviewOpinions :   " + nbErreurs + " erreur(s) / " +  nbTests + " tests effectués\n");
		
		TestSocialNetwork.nbTests += nbTests;
		TestSocialNetwork.nbErreurs += nbErreurs;		
	}


}

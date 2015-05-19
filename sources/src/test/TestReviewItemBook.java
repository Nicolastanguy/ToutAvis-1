package test;

import exception.*;
import avis.SocialNetwork;
import test.TestCreationSN;

public class TestReviewItemBook{
	
	public static int reviewItemBookBadEntryTest (SocialNetwork sn,String pseudo,
													String password,String titre,
													float note, String commentaire,String idTest){
		int nbBooks = sn.nbBooks();
		try{
			sn.reviewItemBook(pseudo, password, titre, note, commentaire);
			System.out.println(idTest+"L'ajout d'un avis avec des paramètres non valide à été accepté");
			return 1;
		}
		catch (BadEntry e){
			if(sn.nbBooks()!=nbBooks){
				System.out.println(idTest+"Exception BadEntry bien levée mais le nombre de livre à été modifié");
				return 1;
			}
			return 0;
		}
		catch (Exception e){
			System.out.println(idTest+"Exception non prevue");
			return 1;
		}			
	}
	
	public static int reviewItemBookOKTest (SocialNetwork sn,String pseudo,
											String password,String titre,
											float note,String commentaire,String idTest){
		try {
			sn.reviewItemBook(pseudo, password, titre, note, commentaire);
			return 0;
		}
		catch (Exception e){
			System.out.println(idTest+"Exception non prevue");
			return 1;
		}
		
	}
	
	//Test la note moyenne renvoyée par reviewItemBook
	public static int reviewItemBookAverageRatingTest (SocialNetwork sn,String pseudo,
			String password,String titre,
			float note,String commentaire,String idTest,float noteAttendue){
		
		try {
			float noteMoyenne = 0;
			noteMoyenne = sn.reviewItemBook(pseudo, password, titre, note, commentaire);
			if (Math.abs(noteMoyenne-noteAttendue)<0.01) return 0;
			else {
				System.out.println(idTest+" Ajout d'un avis par un membre ayant déjà posté un avis : la moyenne retournée n'est pas celle attendue");
				return 1;
			}
		}
		catch (Exception e){
			System.out.println(idTest+"Exception non prevue");
			return 1;
		}

}
	
	public static int reviewItemBookNotMemberTest (SocialNetwork sn,String pseudo,
											String password,String titre,
											float note,String commentaire,String idTest){
		int nbBooks = sn.nbBooks();
		try {
			sn.reviewItemBook(pseudo, password, titre, note, commentaire);
			System.out.println(idTest+"Ajout d'un avis avec des identifiants incorrects accepté");
			return 0;
		}
		catch (NotMember e){
			if(sn.nbBooks()!=nbBooks){
				System.out.println(idTest+"Exception NotMember bien levée mais le nombre de livre à été modifié");
				return 1;
			}
			return 0;
		}
		catch (Exception e){
			System.out.println(idTest+"Exception non prevue");
			return 1;
		}
	}
	
	public static int reviewItemBookNotItemTest (SocialNetwork sn,String pseudo,
			String password,String titre,
			float note,String commentaire,String idTest){
		int nbBooks = sn.nbBooks();
		try {
			sn.reviewItemBook(pseudo, password, titre, note, commentaire);
			System.out.println(idTest+"Ajout d'un avis sur un livre non existant accepté");
			return 0;
		}
		catch (NotItem e){
			if(sn.nbBooks()!=nbBooks){
				System.out.println(idTest+"Exception NotItem bien levée mais le nombre de livre à été modifié");
				return 1;
			}
			return 0;
		}
		catch (Exception e){
			System.out.println(idTest+"Exception non prevue");
			return 1;
		}
	}	
	
	
	public static void sequenceTestReviewItemBook() {
		
		System.out.println("Tests ajouter des avis sur des livres");
		
		SocialNetwork sn = null;
		
		try{
			sn = TestCreationSN.createNewSocialNetwork();
		}
		catch(Exception e){
			System.out.println("Erreur lors de la création du SN de test");
		}
		
		int nbMembers = sn.nbMembers();
		int nbBooks = sn.nbBooks();
		int nbFilms = sn.nbFilms();
		
		int nbTests = 0;
		int nbErreurs = 0;
		
		// <=> Fiche 7 : Eprouver la méthode reviewItemBook sur les cas d’anomalies BadEntry.			
		//7.1a   
		nbTests ++;
		nbErreurs += reviewItemBookBadEntryTest(sn,null,"psw1","superlivre1",1.3F,"Bien bien bien!","TestReviewItemBook : 7.1a"); //pseudo incorrect 
		//7.1b   
		nbTests ++;
		nbErreurs += reviewItemBookBadEntryTest(sn,"     ","psw1","superlivre1",1.3F,"Bien bien bien!","TestReviewItemBook : 7.1b"); //pseudo incorrect 
		//7.2a
		nbTests ++;
		nbErreurs += reviewItemBookBadEntryTest(sn,"Personne1",null,"superlivre1",1.3F,"Bien bien bien!","TestReviewItemBook : 7.2"); //password incorrect
		//7.2b
		nbTests ++;
		nbErreurs += reviewItemBookBadEntryTest(sn,"Personne1","  a   ","superlivre1",1.3F,"Bien bien bien!","TestReviewItemBook : 7.2"); //password incorrect
		//7.3a
		nbTests ++;
		nbErreurs += reviewItemBookBadEntryTest(sn,"Personne1","psw1",null,1.3F,"Bien bien bien!","TestReviewItemBook : 7.3"); //titre livre incorrect
		//7.3b
		nbTests ++;
		nbErreurs += reviewItemBookBadEntryTest(sn,"Personne1","psw1","    ",1.3F,"Bien bien bien!","TestReviewItemBook : 7.3"); //titre livre incorrect
		//7.4
		nbTests ++;
		nbErreurs += reviewItemBookBadEntryTest(sn,"Personne1","psw1","superlivre1",7.0F,"Bien bien bien!","TestReviewItemBook : 7.4"); //note incorrecte
		//7.5
		nbTests ++;
		nbErreurs += reviewItemBookBadEntryTest(sn,"Personne1","psw1","superlivre1",1.3F,null,"TestReviewItemBook : 7.5"); //Commentaire incorrect
		
		
		// <=> Fiche 8 : Eprouver la méthode reviewItemBook dans le cas d’un fonctionnement standard.
		
		//8.1 Ajout d'avis sur des livres : les 3 membres postent un avis sur les livres qu'ils ont ajoutés (3 livres / 3avis par membre)
		//8.1a
		nbTests ++;
		nbErreurs += reviewItemBookOKTest(sn,"Personne1", "psw1", "Arts martiaux",4.5F,"Super livre pour etre un ninja","TestReviewItemBook : 8.1a");
		//8.1b
		nbTests ++;
		nbErreurs += reviewItemBookOKTest(sn,"Personne2", "psw2", "La Chine",3.2F,"Beau livre mais incomplet","TestReviewItemBook : 8.1b");
		//8.1c
		nbTests ++;
		nbErreurs += reviewItemBookOKTest(sn,"Personne3", "psw3", "Coupe du monde football",4.2F,"Vive le foot","TestReviewItemBook : 8.1c");
		//8.1d
		nbTests ++;
		nbErreurs += reviewItemBookOKTest(sn,"Personne1", "psw1", "La Chine",2.1F,"Un peu perdu dans ce long livre","TestReviewItemBook : 8.1d");
		//8.1e
		nbTests ++;
		nbErreurs += reviewItemBookOKTest(sn,"Personne2", "psw2", "Arts martiaux",4.5F,"Une belle surprise","TestReviewItemBook : 8.1e");
		//8.1f
		nbTests ++;
		nbErreurs += reviewItemBookOKTest(sn,"Personne3", "psw3", "Arts martiaux",2.3F,"Beaucoup d'erreurs","TestReviewItemBook : 8.1f");
		//8.1g
		nbTests ++;
		nbErreurs += reviewItemBookOKTest(sn,"Personne1", "psw1","Coupe du monde football",3.7F,"Pas mal, meme sans aimer le foot","TestReviewItemBook : 8.1g");
		//8.1h
		nbTests ++;
		nbErreurs += reviewItemBookOKTest(sn,"Personne2", "psw2", "Coupe du monde football",1.5F,"Rien a foot du foot","TestReviewItemBook : 8.1h");
		//8.1i
		nbTests ++;
		nbErreurs += reviewItemBookOKTest(sn,"Personne3", "psw3", "La Chine",3.1F,"Xio Ping Ping","TestReviewItemBook : 8.1i");
		
		//8.2 test avec un pseudo inconnu
		nbTests ++;
		nbErreurs += reviewItemBookNotMemberTest (sn,"PersonneInconnue", "psw1", "Arts martiaux",4.5F,"Super livre pour etre un ninja","TestReviewItemBook : 8.2");
		//8.3 test avec un password mauvais et un pseudo existant
		nbTests ++;
		nbErreurs += reviewItemBookNotMemberTest (sn,"Personne1", "pswMauvais", "Arts martiaux",4.5F,"Super livre pour etre un ninja","TestReviewItemBook : 8.3");
		//8.4 le titre du livre est inconnu
		nbTests ++;
		nbErreurs += reviewItemBookNotItemTest (sn,"Personne1", "psw1", "LivreInconnu",4.5F,"Super livre pour etre un ninja","TestReviewItemBook : 8.4");
		
		
		//8.5 On vérifie que la note est bien mise à jour si un membre ayant déjà posté un avis sur l'item reposte un avis	
			//note de personne 1 passe de 4,5 à 0 pour le livre Arts martiaux
		nbTests ++;
		nbErreurs += reviewItemBookAverageRatingTest (sn,"Personne1","psw1","Arts martiaux",0,"okok","TestReviewItemBook : 8.5a",2.266F);
			//note de personne 2 passe de 1,5 à 5 pour le livre Coupe du monde
		nbTests ++;
		nbErreurs += reviewItemBookAverageRatingTest (sn,"Personne2","psw2","Coupe du monde football",5,"okok","TestReviewItemBook : 8.5b",4.3F);
			//note de personne 3 passe de 3,1 à 1 pour le livre La Chine
		nbTests ++;
		nbErreurs += reviewItemBookAverageRatingTest (sn,"Personne3","psw3","La Chine",1,"okok","TestReviewItemBook : 8.5c",2.1F);

		
		TestSocialNetwork.nbTests++;
		if (sn.nbBooks()!=nbBooks){
			System.out.println("Erreur  :  le nombre de livres après utilisation de reviewItemBook a été modifié");
			nbErreurs++;
		}
		TestSocialNetwork.nbTests++;
		if (sn.nbMembers()!=nbMembers){
			System.out.println("Erreur  :  le nombre de membres après utilisation de reviewItemBook a été modifié");
			nbErreurs++;
		}
		TestSocialNetwork.nbTests++;
		if (sn.nbFilms()!=nbFilms){
			System.out.println("Erreur  :  le nombre de films après utilisation de reviewItemBook a été modifié");
			nbErreurs++;
		}
		
		// bilan du test de ReviewItemBook
		System.out.println("TestsReviewItemBook :   " + nbErreurs + " erreur(s) / " +  nbTests + " tests effectués\n");
		
		TestSocialNetwork.nbTests += nbTests;
		TestSocialNetwork.nbErreurs += nbErreurs;			
	}
}

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
	
	public static int reviewItemBookNotItem (SocialNetwork sn,String pseudo,
			String password,String titre,
			float note,String commentaire,String idTest){
		int nbBooks = sn.nbBooks();
		try {
			sn.reviewItemBook(pseudo, password, titre, note, commentaire);
			System.out.println(idTest+"Ajout d'un avis sur un livre non existant accepté");
			return 0;
		}
		catch (NotMember e){
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
		
			int nbMembers = 0;
			int nbFilms = 0;
			int nbLivres = 0;	
			
			int nbTests = 0;
			int nbErreurs = 0;
			
			SocialNetwork sn = null;
			
			System.out.println("Tests  ajouter des avis sur des livres  ");
			try{
				sn = TestCreationSN.createNewSocialNetwork();
			}
			catch(Exception e){
				System.out.println("Erreur lors de la création du SN de test");
			}
			
			nbMembers = sn.nbMembers();
			nbLivres = sn.nbBooks();
			nbFilms = sn.nbFilms();
			
			// <=> Fiche 1 : Eprouver la méthode reviewItemBook sur les cas d’anomalies BadEntry.			
			//1.1a   
			nbTests ++;
			nbErreurs += reviewItemBookBadEntryTest(sn,null,"psw1","superlivre1",1.3F,"Bien bien bien!","TestReviewItemBook : 1.1a"); //pseudo incorrect 
			//1.1b   
			nbTests ++;
			nbErreurs += reviewItemBookBadEntryTest(sn,"     ","psw1","superlivre1",1.3F,"Bien bien bien!","TestReviewItemBook : 1.1b"); //pseudo incorrect 
			//1.2a
			nbTests ++;
			nbErreurs += reviewItemBookBadEntryTest(sn,"Personne1",null,"superlivre1",1.3F,"Bien bien bien!","TestReviewItemBook : 1.2"); //password incorrect
			//1.2b
			nbTests ++;
			nbErreurs += reviewItemBookBadEntryTest(sn,"Personne1","  a   ","superlivre1",1.3F,"Bien bien bien!","TestReviewItemBook : 1.2"); //password incorrect
			//1.3a
			nbTests ++;
			nbErreurs += reviewItemBookBadEntryTest(sn,"Personne1","psw1",null,1.3F,"Bien bien bien!","TestReviewItemBook : 1.3"); //titre livre incorrect
			//1.3b
			nbTests ++;
			nbErreurs += reviewItemBookBadEntryTest(sn,"Personne1","psw1","    ",1.3F,"Bien bien bien!","TestReviewItemBook : 1.3"); //titre livre incorrect
			//1.4
			nbTests ++;
			nbErreurs += reviewItemBookBadEntryTest(sn,"Personne1","psw1","superlivre1",7.0F,"Bien bien bien!","TestReviewItemBook : 1.4"); //note incorrecte
			//1.5
			nbTests ++;
			nbErreurs += reviewItemBookBadEntryTest(sn,"Personne1","psw1","superlivre1",1.3F,null,"TestReviewItemBook : 1.5"); //Commentaire incorrect
			
			
			// <=> Fiche 2 : Eprouver la méthode reviewItemBook dans le cas d’un fonctionnement standard.
			
			//2.1 Ajout d'avis sur des livres : les 3 membres postent un avis sur les livres qu'ils ont posté
			//2.1a
			nbTests ++;
			nbErreurs += reviewItemBookOKTest(sn,"Personne1", "psw1", "Arts martiaux",4.5F,"Super livre pour etre un ninja","TestReviewItemBook : 2.1a");
			//2.1b
			nbTests ++;
			nbErreurs += reviewItemBookOKTest(sn,"Personne2", "psw2", "La Chine",3.2F,"Beau livre mais incomplet","TestReviewItemBook : 2.1b");
			//2.1c
			nbTests ++;
			nbErreurs += reviewItemBookOKTest(sn,"Personne3", "psw3", "Coupe du monde football",4.2F,"Vive le foot","TestReviewItemBook : 2.1c");
			//2.1d
			nbTests ++;
			nbErreurs += reviewItemBookOKTest(sn,"Personne1", "psw1", "La Chine",2.1F,"Un peu perdu dans ce long livre","TestReviewItemBook : 2.1d");
			//2.1e
			nbTests ++;
			nbErreurs += reviewItemBookOKTest(sn,"Personne2", "psw2", "Arts martiaux",4.5F,"Une belle surprise","TestReviewItemBook : 2.1e");
			//2.1f
			nbTests ++;
			nbErreurs += reviewItemBookOKTest(sn,"Personne3", "psw3", "Arts martiaux",2.3F,"Beaucoup d'erreurs","TestReviewItemBook : 2.1f");
			//2.1g
			nbTests ++;
			nbErreurs += reviewItemBookOKTest(sn,"Personne1", "psw1","Coupe du monde football",3.7F,"Pas mal, meme sans aimer le foot","TestReviewItemBook : 2.1g");
			//2.1h
			nbTests ++;
			nbErreurs += reviewItemBookOKTest(sn,"Personne2", "psw2", "Coupe du monde football",1.5F,"Rien a foot du foot","TestReviewItemBook : 2.1h");
			//2.1i
			nbTests ++;
			nbErreurs += reviewItemBookOKTest(sn,"Personne3", "psw3", "La Chine",3.1F,"Xio Ping Ping","TestReviewItemBook : 2.1i");
			
			//2.2
			nbTests ++;
			nbErreurs += reviewItemBookNotMemberTest (sn,"PersonneInconnue", "psw1", "Arts martiaux",4.5F,"Super livre pour etre un ninja","TestReviewItemBook : 2.2");
			//2.3
			nbTests ++;
			nbErreurs += reviewItemBookNotMemberTest (sn,"Personne1", "pswMauvais", "Arts martiaux",4.5F,"Super livre pour etre un ninja","TestReviewItemBook : 2.2");
			//2.4
			nbTests ++;
			nbErreurs += reviewItemBookNotMemberTest (sn,"Personne1", "psw1", "LivreInconnu",4.5F,"Super livre pour etre un ninja","TestReviewItemBook : 2.2");
			
			TestSocialNetwork.nbTests++;
			if (sn.nbBooks()!=nbLivres){
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
			System.out.println("TestsReviewItemBook :   " + nbErreurs + " erreur(s) / " +  nbTests + " tests effectués");
			
			TestSocialNetwork.nbTests += nbTests;
			TestSocialNetwork.nbErreurs += nbErreurs;			
	}

}

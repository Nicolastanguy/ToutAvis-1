package test;

import exception.*;
import avis.SocialNetwork;
import test.TestCreationSN;

public class TestReviewItemFilm{
	
	public static int reviewItemFilmBadEntryTest (SocialNetwork sn,String pseudo,
													String password,String titre,
													float note, String commentaire,String idTest){
		int nbFilms = sn.nbFilms();
		try{
			sn.reviewItemFilm(pseudo, password, titre, note, commentaire);
			System.out.println(idTest+"L'ajout d'un avis avec des paramètres non valide à été accepté");
			return 1;
		}
		catch (BadEntry e){
			if(sn.nbFilms()!=nbFilms){
				System.out.println(idTest+"Exception BadEntry bien levée mais le nombre de film à été modifié");
				return 1;
			}
			return 0;
		}
		catch (Exception e){
			System.out.println(idTest+"Exception non prevue");
			return 1;
		}			
	}
	
	public static int reviewItemFilmOKTest (SocialNetwork sn,String pseudo,
											String password,String titre,
											float note,String commentaire,String idTest){
		try {
			sn.reviewItemFilm(pseudo, password, titre, note, commentaire);
			return 0;
		}
		catch (Exception e){
			System.out.println(idTest+"Exception non prevue");
			return 1;
		}
		
	}
	
	public static int reviewItemFilmNotMemberTest (SocialNetwork sn,String pseudo,
											String password,String titre,
											float note,String commentaire,String idTest){
		int nbFilms = sn.nbFilms();
		try {
			sn.reviewItemFilm(pseudo, password, titre, note, commentaire);
			System.out.println(idTest+"Ajout d'un avis avec des identifiants incorrects accepté");
			return 0;
		}
		catch (NotMember e){
			if(sn.nbFilms()!=nbFilms){
				System.out.println(idTest+"Exception NotMember bien levée mais le nombre de film à été modifié");
				return 1;
			}
			return 0;
		}
		catch (Exception e){
			System.out.println(idTest+"Exception non prevue");
			return 1;
		}
	}
	
	public static int reviewItemFilmNotItem (SocialNetwork sn,String pseudo,
			String password,String titre,
			float note,String commentaire,String idTest){
		int nbFilms = sn.nbFilms();
		try {
			sn.reviewItemFilm(pseudo, password, titre, note, commentaire);
			System.out.println(idTest+"Ajout d'un avis sur un film non existant accepté");
			return 0;
		}
		catch (NotMember e){
			if(sn.nbFilms()!=nbFilms){
				System.out.println(idTest+"Exception NotItem bien levée mais le nombre de film à été modifié");
				return 1;
			}
			return 0;
		}
		catch (Exception e){
			System.out.println(idTest+"Exception non prevue");
			return 1;
		}
	}	
	
	
	public static void sequenceTestReviewItemFilm() {
		
			int nbMembers = 0;
			int nbFilms = 0;
			int nbLivres = 0;	
			
			int nbTests = 0;
			int nbErreurs = 0;
			
			SocialNetwork sn = null;
			
			System.out.println("Tests  ajouter des avis sur des films  ");
			try{
				sn = TestCreationSN.createNewSocialNetwork();
			}
			catch(Exception e){
				System.out.println("Erreur lors de la création du SN de test");
			}
			
			nbMembers = sn.nbMembers();
			nbLivres = sn.nbBooks();
			nbFilms = sn.nbFilms();
			
			// <=> Fiche 9 : Eprouver la méthode reviewItemFilm sur les cas d’anomalies BadEntry.			
			//9.1a   
			nbTests ++;
			nbErreurs += reviewItemFilmBadEntryTest(sn,null,"psw1","Interstellar",1.3F,"Bien bien bien!","TestReviewItemFilm : 9.1a"); //pseudo incorrect 
			//9.1b   
			nbTests ++;
			nbErreurs += reviewItemFilmBadEntryTest(sn,"     ","psw1","Interstellar",1.3F,"Bien bien bien!","TestReviewItemFilm : 9.1b"); //pseudo incorrect 
			//9.2a
			nbTests ++;
			nbErreurs += reviewItemFilmBadEntryTest(sn,"Personne1",null,"Interstellar",1.3F,"Bien bien bien!","TestReviewItemFilm : 9.2"); //password incorrect
			//9.2b
			nbTests ++;
			nbErreurs += reviewItemFilmBadEntryTest(sn,"Personne1","  a   ","Interstellar",1.3F,"Bien bien bien!","TestReviewItemFilm : 9.2"); //password incorrect
			//9.3a
			nbTests ++;
			nbErreurs += reviewItemFilmBadEntryTest(sn,"Personne1","psw1",null,1.3F,"Bien bien bien!","TestReviewItemFilm : 9.3"); //titre livre incorrect
			//9.3b
			nbTests ++;
			nbErreurs += reviewItemFilmBadEntryTest(sn,"Personne1","psw1","    ",1.3F,"Bien bien bien!","TestReviewItemFilm : 9.3"); //titre livre incorrect
			//9.4
			nbTests ++;
			nbErreurs += reviewItemFilmBadEntryTest(sn,"Personne1","psw1","Interstellar",7.0F,"Bien bien bien!","TestReviewItemFilm : 9.4"); //note incorrecte
			//9.5
			nbTests ++;
			nbErreurs += reviewItemFilmBadEntryTest(sn,"Personne1","psw1","Interstellar",1.3F,null,"TestReviewItemFilm : 9.5"); //Commentaire incorrect
			
			
			// <=> Fiche 10 : Eprouver la méthode reviewItemFilm dans le cas d’un fonctionnement standard.
			
			//10.1 Ajout d'avis sur des films : les 3 membres postent un avis sur chacun des films
			//10.1a
			nbTests ++;
			nbErreurs += reviewItemFilmOKTest(sn,"Personne1", "psw1", "Interstellar",4.5F,"Super","TestReviewItemFilm : 10.1a");
			//10.1b
			nbTests ++;
			nbErreurs += reviewItemFilmOKTest(sn,"Personne2", "psw2", "Interstellar",4.8F,"Grandiose","TestReviewItemFilm : 10.1b");
			//10.1c
			nbTests ++;
			nbErreurs += reviewItemFilmOKTest(sn,"Personne3", "psw3", "Interstellar",2.1F,"Trop complexe...","TestReviewItemFilm : 10.1c");
			//10.1d
			nbTests ++;
			nbErreurs += reviewItemFilmOKTest(sn,"Personne1", "psw1", "Matrix",2.1F,"Bon film d'action, sans plus","TestReviewItemFilm : 10.1d");
			//10.1e
			nbTests ++;
			nbErreurs += reviewItemFilmOKTest(sn,"Personne2", "psw2", "Matrix",4.5F,"un classique","TestReviewItemFilm : 10.1e");
			//10.1f
			nbTests ++;
			nbErreurs += reviewItemFilmOKTest(sn,"Personne3", "psw3", "Matrix",2.3F,"N'importe quoi!","TestReviewItemFilm : 10.1f");
			//10.1g
			nbTests ++;
			nbErreurs += reviewItemFilmOKTest(sn,"Personne1", "psw1","Titanic",3.7F,"Coule coule bateau coule","TestReviewItemFilm : 10.1g");
			//10.1h
			nbTests ++;
			nbErreurs += reviewItemFilmOKTest(sn,"Personne2", "psw2","Titanic",1.5F,"Nian nian","TestReviewItemFilm : 10.1h");
			//10.1i
			nbTests ++;
			nbErreurs += reviewItemFilmOKTest(sn,"Personne3", "psw3","Titanic",3.9F,"Un succès","TestReviewItemFilm : 10.1i");
			
			//10.2
			nbTests ++;
			nbErreurs += reviewItemFilmNotMemberTest (sn,"PersonneInconnue", "psw1", "Matrix",4.5F,"blabla","TestReviewItemFilm : 10.2");
			//10.3
			nbTests ++;
			nbErreurs += reviewItemFilmNotMemberTest (sn,"Personne1", "pswMauvais", "Matrix",4.5F,"blablabla","TestReviewItemFilm : 10.2");
			//10.4
			nbTests ++;
			nbErreurs += reviewItemFilmNotMemberTest (sn,"Personne1", "psw1", "Matrix",4.5F,"okokok","TestReviewItemFilm : 10.2");
			
			TestSocialNetwork.nbTests++;
			if (sn.nbBooks()!=nbLivres){
				System.out.println("Erreur  :  le nombre de livres après utilisation de reviewItemFilm a été modifié");
				nbErreurs++;
			}
			TestSocialNetwork.nbTests++;
			if (sn.nbMembers()!=nbMembers){
				System.out.println("Erreur  :  le nombre de membres après utilisation de reviewItemFilm a été modifié");
				nbErreurs++;
			}
			TestSocialNetwork.nbTests++;
			if (sn.nbFilms()!=nbFilms){
				System.out.println("Erreur  :  le nombre de films après utilisation de reviewItemFilm a été modifié");
				nbErreurs++;
			}
			// bilan du test de ReviewItemFilm
			System.out.println("TestsReviewItemFilm :   " + nbErreurs + " erreur(s) / " +  nbTests + " tests effectués\n");
			
			TestSocialNetwork.nbTests += nbTests;
			TestSocialNetwork.nbErreurs += nbErreurs;			
	}

}


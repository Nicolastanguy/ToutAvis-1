package test;

import avis.SocialNetwork;
import exception.BadEntry;
import exception.ItemBookAlreadyExists;
import exception.ItemFilmAlreadyExists;
import exception.MemberAlreadyExists;
import exception.NotMember;

public class TestAddItemFilm {
	
	public static int addItemFilmBadEntryTest (SocialNetwork sn, String pseudo, String pwd, String titre, String genre, String realisateur, String scenariste, int duree, String idTest, String messErreur){
		int nbFilms = sn.nbFilms();
		try {
			sn.addItemFilm (pseudo, pwd, titre, genre, realisateur, scenariste, duree);
			System.out.println ("Test " + idTest + " : " + messErreur);
			return 1;
		}
		catch (BadEntry e) {
			if (sn.nbFilms() != nbFilms) {
				System.out.println("Test " + idTest + " : l'exception BadEntry a bien été levée mais le nombre de films a été modifié");
				return 1;
			}
			else 
				return 0;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prévue. " + e); 
			e.printStackTrace();
			return 1;
		}
	}
	
	public static int addItemFilmOKTest (SocialNetwork sn, String pseudo, String pwd, String titre, String genre, String realisateur, String scenariste, int duree, String idTest){
		int nbFilms = sn.nbFilms();
		try{
			sn.addItemFilm (pseudo, pwd, titre, genre, realisateur, scenariste, duree);
			if (sn.nbFilms() != nbFilms+1) {
				System.out.println("Test " + idTest + " :  le nombre de films n'a pas été correctement incrémenté");
				return 1;
			}
			else 
				return 0;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prévue. " + e); 
			e.printStackTrace();
			return 1;
		}
	}
	
	public static int addItemFilmNotMemberTest (SocialNetwork sn, String pseudo, String pwd, String titre, String genre, String realisateur, String scenariste, int duree, String idTest, String messErreur){
		int nbFilms = sn.nbFilms();
		try {
			sn.addItemFilm (pseudo, pwd, titre, genre, realisateur, scenariste, duree);
			System.out.println ("Test " + idTest + " : " + messErreur);
			return 1;
		}
		catch (NotMember e) {
			if (sn.nbFilms() != nbFilms) {
				System.out.println("Test " + idTest + " : l'exception NotMember a bien été levée mais le nombre de films a été modifié");
				return 1;
			}
			else 
				return 0;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prévue. " + e); 
			e.printStackTrace();
			return 1;
		}
	}
	
	public static int addItemFilmAlreadyExistsTest (SocialNetwork sn, String pseudo, String pwd, String titre, String genre, String realisateur, String scenariste, int duree, String idTest, String messErreur){
		int nbFilms = sn.nbFilms();
		try {
			sn.addItemFilm (pseudo, pwd, titre, genre, realisateur, scenariste, duree);
			System.out.println ("Test " + idTest + " : " + messErreur);
			return 1;
		}
		catch (ItemFilmAlreadyExists e) {
			if (sn.nbFilms() != nbFilms) {
				System.out.println("Test " + idTest + " : l'exception ItemBookAlreadyExists a bien été levée mais le nombre de films a été modifié");
				return 1;
			}
			else
				return 0;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prévue. " + e); 
			e.printStackTrace();
			return 1;
		}
	}
	
	public static void sequenceTestAddItemFilm() {
		
		System.out.println("Tests ajouter des films au réseau social");
		
		SocialNetwork sn = null;
		
		try {
			sn = TestCreationSN.createNewSocialNetwork();
		}
		catch (Exception e) {
			System.out.println("Erreur lors de la création du SN de test");
		}
		
		int nbMembers = sn.nbMembers();
		int nbBooks = sn.nbBooks();
		int nbFilms = sn.nbFilms();

		int nbTests = 0;
		int nbErreurs = 0;
		
		// <=> fiche numéro 5

		// tentative d'ajout de films avec entrées "incorrectes"
		
		nbTests++;
		nbErreurs += addItemFilmBadEntryTest ( sn, null, "", "", "", "", "", 120, "5.1", "L'ajout d'un film avec un membre dont le pseudo n'est pas instancié est accepté");
		nbTests++;
		nbErreurs += addItemFilmBadEntryTest ( sn, " ", "", "", "", "", "", 120, "5.2", "L'ajout d'un film avec un membre dont le pseudo ne contient pas un caracteres, autre que des espaces, est accepté");
		nbTests++;
		nbErreurs += addItemFilmBadEntryTest ( sn, "B", null, "", "", "", "", 120, "5.3", "L'ajout d'un film avec un membre dont le password n'est pas instancié est accepté");
		nbTests++;
		nbErreurs += addItemFilmBadEntryTest ( sn, "B", "   qwd ", "", "", "", "", 120, "5.4", "L'ajout d'un film avec un membre dont le password ne contient pas au moins 4 caracteres, autre que des espaces de début ou de fin, est accepté");
		nbTests++;
		nbErreurs += addItemFilmBadEntryTest ( sn, "B", "qsdfgh", null, "", "", "", 120, "5.5", "L'ajout d'un film dont le titre n'est pas instancié est accepté");
		nbTests++;
		nbErreurs += addItemFilmBadEntryTest ( sn, "B", "qsdfgh", " ", "", "", "", 120, "5.6", "L'ajout d'un film dont le titre contient que des espaces est accepté");
		nbTests++;
		nbErreurs += addItemFilmBadEntryTest ( sn, "B", "qsdfgh", "", null, "", "", 120, "5.7", "L'ajout d'un film dont le genre n'est pas instancié est accepté");
		nbTests++;
		nbErreurs += addItemFilmBadEntryTest ( sn, "B", "qsdfgh", "", "", null, "", 120, "5.8", "L'ajout d'un film dont le réalisateur n'est pas instancié est accepté");
		nbTests++;
		nbErreurs += addItemFilmBadEntryTest ( sn, "B", "qsdfgh", "", "", "", null, 120, "5.9", "L'ajout d'un film dont le scénariste n'est pas instancié est accepté");
		nbTests++;
		nbErreurs += addItemFilmBadEntryTest ( sn, "B", "qsdfgh", "", "", "", "", -120, "5.10", "L'ajout d'un film dont la durée est négative est accepté");
		
		// <=> fiche numéro 6

		// ajout de 3 films avec entrées "correctes"
		
		nbTests++;
		nbErreurs += addItemFilmOKTest ( sn, "Personne1", "psw1", "titre1", "genre1", "realisateur1", "scenariste1", 120, "6.1a");
		nbTests++;
		nbErreurs += addItemFilmOKTest ( sn, "Personne1", "psw1", "titre2", "genre2", "realisateur2", "scenariste2", 120, "6.1b");
		nbTests++;
		nbErreurs += addItemFilmOKTest ( sn, "Personne1", "psw1", "titre3", "genre3", "realisateur3", "scenariste3", 120, "6.1c");
		
		// tentative d'ajout de films avec un membre "inexistant"
		
		nbTests++;
		nbErreurs += addItemFilmNotMemberTest ( sn, "membreInexistant", "password", "titre4", "genre4", "realisateur4", "scenariste4", 120, "6.2", "L'ajout d'un film avec un membre inexistant est accepté");
		nbTests++;
		nbErreurs += addItemFilmNotMemberTest ( sn, "Personne1", "mauvaisPassword", "titre5", "genre5", "realisateur5", "scenariste5", 120, "6.3", "L'ajout d'un film avec un membre existant et un mauvais password est accepté");
		
		// tentative d'ajout de films "existant"
		
		nbTests++;
		nbErreurs += addItemFilmAlreadyExistsTest ( sn, "Personne1", "psw1", "titre1", "genre1", "realisateur1", "scenariste1", 120, "6.4", "L'ajout d'un film avec le titre du premier livre ajouté est accepté");
		nbTests++;
		nbErreurs += addItemFilmAlreadyExistsTest ( sn, "Personne1", "psw1", "titre3", "genre3", "realisateur3", "scenariste3", 120, "6.5", "L'ajout d'un film avec le titre du dernier livre ajouté est accepté");
		nbTests++;
		nbErreurs += addItemFilmAlreadyExistsTest ( sn, "Personne1", "psw1", "tiTre2", "genre2", "realisateur2", "scenariste2", 120, "6.6", "L'ajout d'un film avec un titre existant (avec casse différente) est accepté");
		nbTests++;
		nbErreurs += addItemFilmAlreadyExistsTest ( sn, "Personne1", "psw1", " titre2 ", "genre2", "realisateur2", "scenariste2", 120, "6.7", "L'ajout d'un film avec un titre existant (avec leading et trailing blanks) est accepté");
		
		TestSocialNetwork.nbTests++;
		if (sn.nbBooks()!=nbBooks){
			System.out.println("Erreur  :  le nombre de livres après utilisation de addItemBook a été modifié");
			nbErreurs++;
		}
		TestSocialNetwork.nbTests++;
		if (sn.nbMembers()!=nbMembers){
			System.out.println("Erreur  :  le nombre de membres après utilisation de addItemBook a été modifié");
			nbErreurs++;
		}
		
		// bilan du test de addItemFilm
		System.out.println("TestAddItemFilm :   " + nbErreurs + " erreur(s) / " +  nbTests + " tests effectués\n");
		
		TestSocialNetwork.nbTests += nbTests;
		TestSocialNetwork.nbErreurs += nbErreurs;
	}
}

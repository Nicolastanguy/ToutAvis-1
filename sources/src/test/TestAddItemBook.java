package test;

import avis.SocialNetwork;
import exception.BadEntry;
import exception.ItemBookAlreadyExists;
import exception.ItemFilmAlreadyExists;
import exception.MemberAlreadyExists;
import exception.NotMember;

public class TestAddItemBook {
	
	public static int addItemBookBadEntryTest (SocialNetwork sn, String pseudo, String pwd, String titre, String genre, String auteur, int nbPages, String idTest, String messErreur){
		int nbBooks = sn.nbBooks();
		try {
			sn.addItemBook (pseudo, pwd, titre, genre, auteur, nbPages);
			System.out.println ("Test " + idTest + " : " + messErreur);
			return 1;
		}
		catch (BadEntry e) {
			if (sn.nbBooks() != nbBooks) {
				System.out.println("Test " + idTest + " : l'exception BadEntry a bien été levée mais le nombre de livres a été modifié");
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
	
	public static int addItemBookOKTest (SocialNetwork sn, String pseudo, String pwd, String titre, String genre, String auteur, int nbPages, String idTest){
		int nbBooks = sn.nbBooks();
		try{
			sn.addItemBook (pseudo, pwd, titre, genre, auteur, nbPages);
			if (sn.nbBooks() != nbBooks+1) {
				System.out.println("Test " + idTest + " :  le nombre de livres n'a pas été correctement incrémenté");
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
	
	public static int addItemBookNotMemberTest (SocialNetwork sn, String pseudo, String pwd, String titre, String genre, String auteur, int nbPages, String idTest, String messErreur){
		int nbBooks = sn.nbBooks();
		try {
			sn.addItemBook (pseudo, pwd, titre, genre, auteur, nbPages);
			System.out.println ("Test " + idTest + " : " + messErreur);
			return 1;
		}
		catch (NotMember e) {
			if (sn.nbBooks() != nbBooks) {
				System.out.println("Test " + idTest + " : l'exception NotMember a bien été levée mais le nombre de livres a été modifié");
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
	
	public static int addItemBookAlreadyExistsTest (SocialNetwork sn, String pseudo, String pwd, String titre, String genre, String auteur, int nbPages, String idTest, String messErreur){
		int nbBooks = sn.nbBooks();
		try {
			sn.addItemBook (pseudo, pwd, titre, genre, auteur, nbPages);
			System.out.println ("Test " + idTest + " : " + messErreur);
			return 1;
		}
		catch (ItemBookAlreadyExists e) {
			if (sn.nbBooks() != nbBooks) {
				System.out.println("Test " + idTest + " : l'exception ItemBookAlreadyExists a bien été levée mais le nombre de livres a été modifié");
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
	
	public static void sequenceTestAddItemBook() {
		
		System.out.println("Tests ajouter des livres au réseau social");
		
		SocialNetwork sn = null;
		
		try {
			sn = TestCreationSN.createNewSocialNetwork();
		}
		catch (BadEntry e) {
			
		}
		catch (MemberAlreadyExists e) {
			
		}
		catch (NotMember e) {
			
		}
		catch (ItemBookAlreadyExists e) {
			
		}
		catch (ItemFilmAlreadyExists e) {
			
		}
		catch (Exception e) {
			System.out.println("Erreur lors de la création du SN de test");
		}
		
		int nbMembers = sn.nbMembers();
		int nbBooks = sn.nbBooks();
		int nbFilms = sn.nbFilms();

		int nbTests = 0;
		int nbErreurs = 0;
		
		// <=> fiche numéro 3

		// tentative d'ajout de livres avec entrées "incorrectes"
		
		nbTests++;
		nbErreurs += addItemBookBadEntryTest ( sn, null, "", "", "", "", 10, "3.1", "L'ajout d'un livre avec un membre dont le pseudo n'est pas instancié est accepté");
		nbTests++;
		nbErreurs += addItemBookBadEntryTest ( sn, " ", "", "", "", "", 10, "3.2", "L'ajout d'un livre avec un membre dont le pseudo ne contient pas un caracteres, autre que des espaces, est accepté");
		nbTests++;
		nbErreurs += addItemBookBadEntryTest ( sn, "B", null, "", "", "", 10, "3.3", "L'ajout d'un livre avec un membre dont le password n'est pas instancié est accepté");
		nbTests++;
		nbErreurs += addItemBookBadEntryTest ( sn, "B", "   qwd ", "", "", "", 10, "3.4", "L'ajout d'un livre avec un membre dont le password ne contient pas au moins 4 caracteres, autre que des espaces de début ou de fin, est accepté");
		nbTests++;
		nbErreurs += addItemBookBadEntryTest ( sn, "B", "qsdfgh", null, "", "", 10, "3.5", "L'ajout d'un livre dont le titre n'est pas instancié est accepté");
		nbTests++;
		nbErreurs += addItemBookBadEntryTest ( sn, "B", "qsdfgh", " ", "", "", 10, "3.6", "L'ajout d'un livre dont le titre contient que des espaces est accepté");
		nbTests++;
		nbErreurs += addItemBookBadEntryTest ( sn, "B", "qsdfgh", "", null, "", 10, "3.7", "L'ajout d'un livre dont le genre n'est pas instancié est accepté");
		nbTests++;
		nbErreurs += addItemBookBadEntryTest ( sn, "B", "qsdfgh", "", "", null, 10, "3.8", "L'ajout d'un livre dont le auteur n'est pas instancié est accepté");
		nbTests++;
		nbErreurs += addItemBookBadEntryTest ( sn, "B", "qsdfgh", "", "", "", -10, "3.9", "L'ajout d'un livre dont le nombre de page est négatif est accepté");
		
		// <=> fiche numéro 4

		// ajout de 3 livres avec entrées "correctes"
		
		nbTests++;
		nbErreurs += addItemBookOKTest ( sn, "Personne1", "psw1", "titre1", "genre1", "auteur1", 10, "4.1a");
		nbTests++;
		nbErreurs += addItemBookOKTest ( sn, "Personne1", "psw1", "titre2", "genre2", "auteur2", 10, "4.1b");
		nbTests++;
		nbErreurs += addItemBookOKTest ( sn, "Personne1", "psw1", "titre3", "genre3", "auteur3", 10, "4.1c");
		
		// tentative d'ajout de livres avec un membre "inexistant"
		
		nbTests++;
		nbErreurs += addItemBookNotMemberTest ( sn, "membreInexistant", "password", "titre4", "genre4", "auteur4", 10, "4.2", "L'ajout d'un livre avec un membre inexistant est accepté");
		nbTests++;
		nbErreurs += addItemBookNotMemberTest ( sn, "Personne1", "mauvaisPassword", "titre4", "genre4", "auteur4", 10, "4.3", "L'ajout d'un livre avec un membre existant et un mauvais password est accepté");
		
		// tentative d'ajout de livres "existant"
		
		nbTests++;
		nbErreurs += addItemBookBadEntryTest ( sn, "Personne1", "psw1", "titre5", "genre5", "auteur5", 10, "4.4", "L'ajout d'un livre avec le titre du premier livre ajouté est accepté");
		nbTests++;
		nbErreurs += addItemBookBadEntryTest ( sn, "Personne1", "psw1", "titre6", "genre6", "auteur6", 10, "4.5", "L'ajout d'un livre avec le titre du dernier livre ajouté est accepté");
		nbTests++;
		nbErreurs += addItemBookBadEntryTest ( sn, "Personne1", "psw1", "tiTre7", "genre7", "auteur7", 10, "4.6", "L'ajout d'un livre avec un titre existant (avec casse différente) est accepté");
		nbTests++;
		nbErreurs += addItemBookBadEntryTest ( sn, "Personne1", "psw1", " titre8 ", "genre8", "auteur8", 10, "4.7", "L'ajout d'un livre avec un titre existant (avec leading et trailing blanks) est accepté");
		
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
		TestSocialNetwork.nbTests++;
		if (sn.nbFilms()!=nbFilms){
			System.out.println("Erreur  :  le nombre de films après utilisation de addItemBook a été modifié");
			nbErreurs++;
		}
		
		// bilan du test de addItemBook
		System.out.println("TestAddItemBook :   " + nbErreurs + " erreur(s) / " +  nbTests + " tests effectués\n");
		
		TestSocialNetwork.nbTests += nbTests;
		TestSocialNetwork.nbErreurs += nbErreurs;	
	}
}

package test;

import avis.SocialNetwork;
import exception.BadEntry;
import exception.ItemBookAlreadyExists;
import exception.ItemFilmAlreadyExists;
import exception.MemberAlreadyExists;
import exception.NotMember;

public class TestAddItemBook {
	
	
	public static int addItemBookBadEntryTest (SocialNetwork sn, String pseudo, String pwd, String titre, String genre, String auteur, int nbPages, String idTest, String messErreur){
		// vérifie que l'ajout d'un livre (pseudo, pwd, titre, genre, auteur, nbPages) est refusée (levée de l'exception BadEntry et  pas de modification du sn)
		// si c'est bien le cas, ne fait rien
		// sinon, affiche le message d'erreur passé en paramètre
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
	
	public static void main(String[] args) {


		
		System.out.println("Tests  ajouter des livres au réseau social  ");
		
		try {
			int nbLivres = 0;
			int nbFilms = 0;

			int nbTests = 0;
			int nbErreurs = 0;
			
			SocialNetwork sn = TestCreationSN.createNewSocialNetwork();

			// tests de addMember
			nbFilms = sn.nbFilms();
			nbLivres = sn.nbBooks();

			// <=> fiche numéro 1

			// tentative d'ajout de livres avec entrées "incorrectes"

			nbTests++;
			nbErreurs += addItemBookBadEntryTest ( sn, null, "", "", "", "", 10, "1.1", "L'ajout d'un livre avec un membre dont le pseudo n'est pas instancié est accepté");
			nbTests++;
			nbErreurs += addItemBookBadEntryTest ( sn, " ", "", "", "", "", 10, "1.2", "L'ajout d'un livre avec un membre dont le pseudo ne contient pas un caracteres, autre que des espaces, est accepté");
			nbTests++;
			nbErreurs += addItemBookBadEntryTest ( sn, "B", null, "", "", "", 10, "1.3", "L'ajout d'un livre avec un membre dont le password n'est pas instancié est accepté");
			nbTests++;
			nbErreurs += addItemBookBadEntryTest ( sn, "B", "   qwd ", "", "", "", 10, "1.4", "L'ajout d'un livre avec un membre dont le password ne contient pas au moins 4 caracteres, autre que des espaces de début ou de fin, est accepté");
			nbTests++;
			nbErreurs += addItemBookBadEntryTest ( sn, "B", "qsdfgh", null, "", "", 10, "1.5", "L'ajout d'un livre dont le titre n'est pas instancié est accepté");
			nbTests++;
			nbErreurs += addItemBookBadEntryTest ( sn, "B", "qsdfgh", " ", "", "", 10, "1.6", "L'ajout d'un livre dont le titre contient que des espaces est accepté");
			nbTests++;
			nbErreurs += addItemBookBadEntryTest ( sn, "B", "qsdfgh", "", null, "", 10, "1.7", "L'ajout d'un livre dont le genre n'est pas instancié est accepté");
			nbTests++;
			nbErreurs += addItemBookBadEntryTest ( sn, "B", "qsdfgh", "", "", null, 10, "1.8", "L'ajout d'un livre dont le auteur n'est pas instancié est accepté");
			nbTests++;
			nbErreurs += addItemBookBadEntryTest ( sn, "B", "qsdfgh", "", "", "", -10, "1.9", "L'ajout d'un livre dont le nombre de page est négatif est accepté");
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
			
		}

	}
}

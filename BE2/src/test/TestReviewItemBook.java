package test;

import exception.*;
import avis.SocialNetwork;
import test.TestCreationSN;


public class TestReviewItemBook {
	
	public static int reviewItemBookBadEntryTest (SocialNetwork sn,String pseudo,
													String password,String titre,
													float note, String commentaire){
		int nbBooks = sn.nbBooks();
		try{
			sn.reviewItemBook(pseudo, password, titre, note, commentaire);
			System.out.println("L'ajout d'un avis avec des paramètres non valide à été accepté");
			return 1;
		}
		catch (BadEntry e){
			if(sn.nbBooks()!=nbBooks){
				System.out.println("Exception BadEntry bien levée mais le nombre de livre à été modifié");
				return 1;
			}
			return 0;
		}
		catch (Exception e){
			System.out.println("Exception non prevue");
			return 1;
		}			
	}
	
	public static int reviewItemBookOKTest (SocialNetwork sn,String pseudo,
											String password,String titre,
											float note,String commentaire){
		try {
			sn.reviewItemBook(pseudo, password, titre, note, commentaire);
			return 0;
		}
		catch (Exception e){
			System.out.println("Exception non prevue");
			return 1;
		}
		
	}
	
	public static int reviewItemBookNotMemberTest (SocialNetwork sn,String pseudo,
											String password,String titre,
											float note,String commentaire){
		int nbBooks = sn.nbBooks();
		try {
			sn.reviewItemBook(pseudo, password, titre, note, commentaire);
			System.out.println("Ajout d'un avis avec des identifiants incorrects accepté");
			return 0;
		}
		catch (NotMember e){
			if(sn.nbBooks()!=nbBooks){
				System.out.println("Exception NotMember bien levée mais le nombre de livre à été modifié");
				return 1;
			}
			return 0;
		}
		catch (Exception e){
			System.out.println("Exception non prevue");
			return 1;
		}
	}
	
	public static int reviewItemBookNotItem (SocialNetwork sn,String pseudo,
			String password,String titre,
			float note,String commentaire){
		int nbBooks = sn.nbBooks();
		try {
			sn.reviewItemBook(pseudo, password, titre, note, commentaire);
			System.out.println("Ajout d'un avis sur un livre non existant accepté");
			return 0;
		}
		catch (NotMember e){
			if(sn.nbBooks()!=nbBooks){
				System.out.println("Exception NotItem bien levée mais le nombre de livre à été modifié");
				return 1;
			}
			return 0;
		}
		catch (Exception e){
			System.out.println("Exception non prevue");
			return 1;
		}
	}	
	
	
	public static void main(String[] args) {
		
			int nbTests = 0;
			int nbErreurs = 0;
			
			System.out.println("Tests  ajouter des avis sur des livres  ");
			
			SocialNetwork sn = TestCreationSN.createNewSocialNetwork();
	}

}

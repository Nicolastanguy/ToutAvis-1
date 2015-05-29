package test;

import java.util.LinkedList;

import avis.SocialNetwork;
import exception.BadEntry;

public class TestConsultItems {
	
	public static int consultItemsBadEntryTest (SocialNetwork sn, String nom, String idTest, String messErreur){
		try {
			sn.consultItems (nom);
			System.out.println ("Test " + idTest + " : " + messErreur);
			return 1;
		}
		catch (BadEntry e) {
			return 0;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prévue. " + e); 
			e.printStackTrace();
			return 1;
		}
	}
	
	public static int consultItemsOKTest (SocialNetwork sn, String nom, String idTest){
		try{
			LinkedList<String> itemsFindList = new LinkedList<String>();
			itemsFindList = sn.consultItems (nom);
			
			if(nom=="ItemInexistant"){
				if(itemsFindList.size()!=0){
					System.out.println(idTest+" La liste retournée n'est pas vide quand on cherche un item inexistant");
					return 1;
				}
			}
			
			return 0;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prévue. " + e); 
			e.printStackTrace();
			return 1;
		}		
	}
	
	public static void sequenceTestConsultItems() {
		
		System.out.println("Tests consulter les items du réseau social");
		
		SocialNetwork sn = null;
		
		try {
			sn = TestCreationSN.createNewSocialNetwork();
		}
		catch (Exception e) {
			System.out.println("Erreur lors de la création du SN de test");
		}
		
		int nbMembers = sn.nbMembers();
		int nbFilms = sn.nbFilms();
		int nbBooks = sn.nbBooks();

		int nbTests = 0;
		int nbErreurs = 0;
		
		// <=> fiche numéro 11
		
		// tentative de consultation d'item avec entrées "incorrectes"
		
		nbTests++;
		nbErreurs += consultItemsBadEntryTest ( sn, null, "11.1", "La consultation d'un item dont le nom n'est pas instancié est accepté");
		nbTests++;
		nbErreurs += consultItemsBadEntryTest ( sn, " ", "11.2", "La consultation d'un item dont le nom ne contient pas un caracteres, autre que des espaces, est accepté");

		// <=> fiche numéro 12
		
		// consultation de 3 items avec entrées "correctes"
		
		nbTests++;
		nbErreurs += consultItemsOKTest ( sn, "La Chine", "12.1a");
		nbTests++;
		nbErreurs += consultItemsOKTest ( sn, "Interstellar", "12.1b");
		nbTests++;
		nbErreurs += consultItemsOKTest ( sn, "Le Seigneur des anneaux", "12.1c");
		
		// consultation d'un item inexistant
		nbTests++;
		nbErreurs += consultItemsOKTest ( sn, "ItemInexistant", "12.4c");
		
		TestSocialNetwork.nbTests++;
		if (sn.nbMembers()!=nbMembers){
			System.out.println("Erreur  :  le nombre de membres après utilisation de consulItems a été modifié");
			nbErreurs++;
		}
		TestSocialNetwork.nbTests++;
		if (sn.nbFilms()!=nbFilms){
			System.out.println("Erreur  :  le nombre de films après utilisation de consulItems a été modifié");
			nbErreurs++;
		}
		TestSocialNetwork.nbTests++;
		if (sn.nbBooks()!=nbBooks){
			System.out.println("Erreur  :  le nombre de livre après utilisation de consulItems a été modifié");
			nbErreurs++;
		}
		
		// bilan du test de addItemBook
		System.out.println("TestConsultItems :   " + nbErreurs + " erreur(s) / " +  nbTests + " tests effectués\n");
		
		TestSocialNetwork.nbTests += nbTests;
		TestSocialNetwork.nbErreurs += nbErreurs;	
	}
}

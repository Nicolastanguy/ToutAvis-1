package test;

import avis.SocialNetwork;

public class TestSocialNetwork {
	
	protected static int nbErreurs = 0;
	protected static int nbTests = 0;
	
	public static void main (String[] args){
		System.out.println("---TestInitialisation---");
		TestsInitialisation.sequenceTestsInitialisation(); //Test de d'instanciation d'un nouveau réseau social
		System.out.println("---TestAddMember---");
		TestsAddMember.sequenceTestsAddMember(); //Test de addMember
		System.out.println("---TestAddItemBook---");
		TestAddItemBook.sequenceTestAddItemBook(); //Test de addItemBook
		System.out.println("---TestAddItemFilm---");
		TestAddItemFilm.sequenceTestAddItemFilm(); //Test de addItemFilm
		System.out.println("---TestReviewItemBook---");
		TestReviewItemBook.sequenceTestReviewItemBook(); //Test de reviewItemBook
		System.out.println("---TestReviewItemFilm---");
		TestReviewItemFilm.sequenceTestReviewItemFilm(); //Test de reviewItemFilm
		System.out.println("--------------------------------------------");
		System.out.println("---Bilan Global du test du Social Network---");
		System.out.println("Il y a eu " + nbErreurs + " erreur(s) pour " +  nbTests + " tests effectués");
		System.out.println("--------------------------------------------");	
		
//		SocialNetwork sn = null;
//		
//		System.out.println("Test to String");
//		try{
//			sn = TestCreationSN.createNewSocialNetwork();
//		}
//		catch(Exception e){
//			System.out.println("Erreur lors de la création du SN de test");
//		}
//		
//		sn.reviewItemFilm("personne1", "psw1", "interstellar", 5.0f, "une bombe !");
//		sn.reviewItemFilm("personne1", "psw1", "interstellar", 0.0f, "une daube !");
//		System.out.println(sn.toString());
		
	}
}

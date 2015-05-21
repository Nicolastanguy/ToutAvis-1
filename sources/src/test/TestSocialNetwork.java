package test;

import exception.*;

public class TestSocialNetwork {
	
	protected static int nbErreurs = 0;
	protected static int nbTests = 0;
	
	public static void main (String[] args) throws BadEntry, MemberAlreadyExists, NotMember, ItemBookAlreadyExists, ItemFilmAlreadyExists,NotItem,MemberAlreadyOpinion {
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
		System.out.println("---TestConsultItems---");
		TestConsultItems.sequenceTestConsultItems(); //Test de consultItems
		System.out.println("---TestReviewOpinions---");
		TestReviewOpinions.sequenceTestReviewOpinions(); //Test de consultItems
		
		System.out.println("---TestRendement---");
		TestRendement.sequenceTestRendement(); //Test du rendement
		System.out.println("--------------------------------------------");
		System.out.println("---Bilan Global du test du Social Network---");
		System.out.println("Il y a eu " + nbErreurs + " erreur(s) pour " +  nbTests + " tests effectués");
		System.out.println("--------------------------------------------");	
		
	}
}
package test;

public class TestSocialNetwork {
	
	protected static int nbErreurs = 0;
	protected static int nbTests = 0;
	
	public static void main (String[] args){
		System.out.println("---TestInitialisation---");
		TestsInitialisation.sequenceTestsInitialisation(); //Test de d'instanciation d'un nouveau réseau social
		System.out.println("---TestAddMember---");
		TestsAddMember.sequenceTestsAddMember();
		System.out.println("---TestAddItemBook---");
		
		System.out.println("---TestAddItemFilm---");
		
		System.out.println("---TestReviewItemBook---");
		TestReviewItemBook.sequenceTestReviewItemBook(); //Test de ReviewItemBook
		System.out.println("---TestReviewItemFilm---");
		
		System.out.println("--------------------------------------------");
		System.out.println("---Bilan Global du test du Social Network---");
		System.out.println("Il y a eu " + nbErreurs + " erreur(s) pour " +  nbTests + " tests effectués");
		System.out.println("--------------------------------------------");	
		
	}
}

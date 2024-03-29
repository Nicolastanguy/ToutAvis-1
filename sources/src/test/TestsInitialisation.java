package test;

import avis.SocialNetwork;

/** 
 * @author B. Prou
 * @date mars 2011
 * @version V0.6
 */

public class TestsInitialisation {


	public static void sequenceTestsInitialisation() {
		
		System.out.println("Tests  initialisation  réseau social  ");
		
		try {
			
			// un réseau social créé ne doit avoir ni membres ni items
			SocialNetwork sn = new SocialNetwork();
			if (sn.nbMembers()!= 0) {
				System.out.println("Erreur 0.1 :  le nombre de membres après création du réseau est non nul");
				System.exit(1);
			}
			if (sn.nbBooks() != 0) {
				System.out.println("Erreur 0.2 : le nombre de livres après création du réseau est non nul");
				System.exit(1);
			}
			if (sn.nbFilms() != 0) {
				System.out.println("Erreur 0.3 : le nombre de films après création du réseau est non nul");
				System.exit(1);
			}
			
			
			// ce n'est pas du test, mais cela peut "rassurer"...
			System.out.println(sn);
			
		}
		catch (Exception e) {
			System.out.println("Exception non prévue : " + e);
			e.printStackTrace();
			System.exit(1);
		}
	}

	
}

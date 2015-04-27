package avis;

import java.util.Collection;


public class Member {
	
	private String pseudo;
	private String password;
	private String profil;
	/** 
	 * @uml.property name="review"
	 * @uml.associationEnd multiplicity="(0 -1)" inverse="member:avis.Review"
	 */
	private Collection<Review> review;

}

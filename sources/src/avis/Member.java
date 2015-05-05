package avis;

import java.util.Collection;


public class Member {
	
	private String pseudo;
	private String password;
	private String profil;
	
	public Member (String pseudo, String password, String profil){
		this.pseudo = pseudo;
		this.password = password;
		this.profil = profil;
	}
	/** 
	 * @uml.property name="review"
	 * @uml.associationEnd multiplicity="(0 -1)" inverse="member:avis.Review"
	 */
	private Collection<Review> review;

}

package avis;

import java.util.Collection;
import java.util.LinkedList;


public class Member {
	
	/**
	 * @uml.property  name="reviews"
	 * @uml.associationEnd  multiplicity="(0 -1)" ordering="true" inverse="member:avis.Review"
	 */
	private LinkedList<Review> reviews;
	private String pseudo;
	private String password;
	private String profil;
	
	public Member (String pseudo, String password, String profil){
		this.pseudo = pseudo;
		this.password = password;
		this.profil = profil;
	}
	
}

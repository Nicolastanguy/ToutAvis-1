package avis;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Membre du réseau social
 *
 */
public class Member {
	
	/**
	 * @uml.property  name="reviews"
	 * @uml.associationEnd  multiplicity="(0 -1)" ordering="true" inverse="member:avis.Review"
	 */
	private LinkedList<Review> reviews;
	private String pseudo;
	private String password;
	private String profil;
	private float karma;
	
	public Member (String pseudo, String password, String profil){
		this.pseudo = pseudo;
		this.password = password;
		this.profil = profil;
	}
	
	//Accesseur du pseudo 
	public String getPseudo(){
		return pseudo;
	}
	
	//Accesseur du password
	public String getPassword(){
		return password;
	}
	
	public String toString(){
		//Ne renvoie que le pseudo du membre
		return ("\n"+pseudo);
	}
	
}

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
	private int nbNoteKarma;
	
	public Member (String pseudo, String password, String profil){
		this.pseudo = pseudo;
		this.password = password;
		this.profil = profil;
		karma = 2.5f;
		nbNoteKarma = 0;
	}
	
	public void karmaUpdate(float note){
		float temp1 = karma*nbNoteKarma;
		float temp2 = temp1+note;
		nbNoteKarma++;
		karma=temp2/nbNoteKarma;
	}
	
	//Accesseur du pseudo 
	public String getPseudo(){
		return pseudo;
	}
	
	//Accesseur du password
	public String getPassword(){
		return password;
	}
	//Accesseur du karma
		public float getKarma(){
			return karma;
		}
	
	public String toString(){
		//Ne renvoie que le pseudo du membre
		return ("\n"+pseudo);
	}
	
}

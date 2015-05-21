package avis;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Membre du réseau social
 *
 */
public class Member {
	
	private String pseudo;
	private String password;
	private String profil;
	private float karma;
	private int nbNoteKarma;
	
	/**
	 * Constructeur de <i> Member </i>
	 * @param pseudo 
	 * @param password
	 * @param profil
	 */
	public Member (String pseudo, String password, String profil){
		this.pseudo = pseudo;
		this.password = password;
		this.profil = profil;
		karma = 2.5f;
		nbNoteKarma = 0;
	}
	/**
	 * Permet de mettre à jour le karma du membre
	 * Appelée lorsque un autre membre note un avis de ce membre.
	 * @param note
	 */
	public void karmaUpdate(float note){
		float temp1 = karma*nbNoteKarma;
		float temp2 = temp1+note;
		nbNoteKarma++;
		karma=temp2/nbNoteKarma;
	}
	
	/**
	 * Accesseur du pseudo du membre
	 * @return
	 */
	public String getPseudo(){
		return pseudo;
	}
	
	/**
	 * Accesseur du password
	 * @return
	 */
	public String getPassword(){
		return password;
	}
	/**
	 * Accesseur du karma
	 * @return
	 */
	public float getKarma(){
		return karma;
	}
	/**
	 * renvoie un String de description du membre avec son pseudo et son karma.
	 */
	public String toString(){
		//Ne renvoie que le pseudo du membre
		return ("\n"+pseudo+",karma: "+karma);
	}
	
}

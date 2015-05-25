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
	 * Réseau social auquel appartient le membre
	 * @uml.property  name="sn"
	 * @uml.associationEnd  multiplicity="(1 1)" inverse="members:avis.SocialNetwork"
	 */
	private SocialNetwork sn;
	
	/**
	 * Constructeur de <i> Member </i>
	 * @param pseudo 
	 * @param password
	 * @param profil
	 */
	public Member (String pseudo, String password, String profil,SocialNetwork sn){
		this.pseudo = pseudo;
		this.password = password;
		this.profil = profil;
		karma = 2.5f;
		nbNoteKarma = 0;
		this.sn = sn;
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
	 * Demande à un membre si il fait partie du sn
	 * @param pseudo
	 * @return member (retourne le membre)
	 */
	public Member isMember(String pseudo){
		//on compare le pseudo de ce membre avec celui passé en paramètre
		if (pseudo.trim().toLowerCase().equals(this.pseudo.trim().toLowerCase())){
			return this; //retourne le membre
		}
		else return null;
	}
	/**
	 * Vérifie si le password passé en paramètre correspond à celui du membre
	 * @param password
	 * @return boolean : true si c'est le bon mot de passe/false sinon
	 */
	public boolean isPassword(String password){
		if(password.trim().toLowerCase().equals(this.password.trim().toLowerCase())){
			return true;
		}
		else return false;
	}
	
	/**
	 * Accesseur du pseudo du membre
	 * @return
	 */
	public String getPseudo(){
		return pseudo;
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
	

	/**
	 * Getter of the property <tt>sn</tt>
	 * @return  Returns the sn.
	 * @uml.property  name="sn"
	 */
	public SocialNetwork getSn() {
		return sn;
	}
	/**
	 * Setter of the property <tt>sn</tt>
	 * @param sn  The sn to set.
	 * @uml.property  name="sn"
	 */
	public void setSn(SocialNetwork sn) {
		this.sn = sn;
	}
	
}

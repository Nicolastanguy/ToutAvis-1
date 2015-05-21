package avis;

import java.util.LinkedList;

public class Review {
	
	private float note; //note de l'avis sur l'item (donnée par le membre)
	private float noteReview; //note moyenne sur cet avis
	private float coef; //poids qu'aura la note dans le calcul de la moyenne, corespond au karma du membre lorsqu'il a posté cet avis
	private String commentaire; //commentaire du membre sur l'item
	private String pseudo; //pseudo du membre ayant posté l'avis
	private LinkedList<String> reviewOpinions; //liste du pseudo des membres ayant noté cet avis
	
	/**
	 * Constructeur de l'avis
	 * 
	 * @param note
	 * @param coef
	 * @param commentaire
	 * @param pseudo
	 */
	public Review(float note, float coef, String commentaire, String pseudo){
		this.note = note;
		this.commentaire = commentaire;
		this.pseudo = pseudo;
		this.coef = coef;
		reviewOpinions = new LinkedList<String>();
	}
	
	/**
	 * Met à jour la moyenne de l'avis avec la nouvelle note
	 * 
	 * @param pseudo1 pseudo du membre qui note l'avis
	 * @param note note que le membre donne à l'avis
	 */
	public void noteReviewUpdate(String pseudo1, float note){
		float temp1 = noteReview*reviewOpinions.size(); //calcul de la somme des notes précédentes
		float temp2 = temp1+note; //ajout de la nouvelle note à cette somme
		reviewOpinions.add(pseudo1); //ajout du pseudo du membre à la liste des membres ayants noté cet avis
		noteReview=temp2/reviewOpinions.size(); //calcul de la nouvelle moyenne
	}
	
	/**
	 * Vérifie si le membre à déjà noté un avis de l'item
	 * 
	 * @param pseudo1 pseudo du membre à vérifier
	 * @return true si oui, false si non
	 */
	public boolean memberAlreadyReviewOpinion(String pseudo1){
		for (String reviewOpinion : reviewOpinions){
			if (reviewOpinion.trim().toLowerCase().equals(pseudo1.trim().toLowerCase())) return true; //retourne true si le membre est présent dans la liste 
		}
		return false;
	}
	
	/**
	 * Accesseur de la note moyenne d'un avis
	 * 
	 * @return
	 */
	public float getNoteReview(){
		return noteReview;
	}
	
	/**
	 * Accesseur de la note donnée à l'item
	 * 
	 * @return la note donnée à l'item
	 */
	public float getNote(){
		return note;
	}
	
	/**
	 * Accesseur du coefficient
	 * 
	 * @return le coefficient
	 */
	public float getCoef(){
		return coef;
	}
	
	/**
	 * Accesseur du pseudo du membre ayant posté l'avis
	 * 
	 * @return le pseudo du membre ayant posté l'avis
	 */
	public String getPseudo() {
		return pseudo;
	}
	
	/**
	 * Renvoie une chaine de caractères représentatif de l'avis
	 * 
	 */
	public String toString(){
		return (" note : "+note+ " commentaire : <<"+commentaire+">>");
	}
	
}
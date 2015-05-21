package avis;

import java.util.LinkedList;

/**
 * Classe abstraite d'item, cette classe est implémentée par ItemBook et ItemFilm
 * 
 */
public abstract class Item {
	
	/**
	 * @uml.property  name="reviews"
	 * @uml.associationEnd  multiplicity="(0 -1)" ordering="true" inverse="item:avis.Review"
	 */
	protected LinkedList<Review> reviews;
	protected String titre;
	protected String genre;
	protected float averageRating;
		
	/**
	 * Met a jour la note de l'item (calcul de la moyenne des différents commentaires)
	 * @return
	 */	
	public void averageRating(){
		
		float rateSum = 0;
		float coefSum = 0;
		for (Review review : reviews){
			rateSum += review.getNote()*review.getCoef(); //somme des notes pondérées (chacunes multipliées par leur coefs) données à un item
			coefSum += review.getCoef(); //somme des coefs
		}
		averageRating = rateSum/coefSum; //Calcul de la moyenne
	}
	
	/**
	 * 
	 * 
	 */	
	public void addReviewToItem(float note, float coef, String commentaire, String pseudo){
		Review newReview = new Review(note, coef, commentaire, pseudo);
		reviews.add(newReview);
	}
	
	/**
	 * 
	 * 
	 */	
	public void deletePreviousMemberReview(String pseudo){
		//Vérifie si le membre a déjà posté un avis sur cet item et supprimer cet avis si c'est le cas
		Review tempReview = new Review(0, 0, "", "");
		for (Review review : reviews){
			if (review.getPseudo().trim().toLowerCase().equals(pseudo.trim().toLowerCase())) tempReview=review;
		}
		reviews.remove(tempReview);
	}
	
	/**
	 * 
	 * 
	 */	
	public void addNoteToReview(String pseudo1, String pseudo2, float note){
		for (Review review : reviews){
			if (review.getPseudo().trim().toLowerCase().equals(pseudo2.trim().toLowerCase())){
				review.noteReviewUpdate(pseudo1, note);
			}
		}
	}
	
	/**
	 * 
	 * 
	 */	
	public float getNoteReview(String pseudo1, String pseudo2){
		for (Review review : reviews){
			if (review.getPseudo().trim().toLowerCase().equals(pseudo2.trim().toLowerCase())) return review.getNoteReview();
		}
		return 0;
	}
	
	public boolean isMemberReview(String pseudo){
		//Vérifie si le membre a bien commenté l'item
		for (Review review : reviews){
			if (review.getPseudo().trim().toLowerCase().equals(pseudo.trim().toLowerCase())) return true;
		}
		return false;
	}
	
	public boolean memberAlreadyReviewOpinion(String pseudo1, String pseudo2){
		//Vérifie si le membre à déjà noté cet avis
		for (Review review : reviews){
			if (review.getPseudo().trim().toLowerCase().equals(pseudo1.trim().toLowerCase())) return review.memberAlreadyReviewOpinion(pseudo2);
		}
		return false;
	}
	
	/**
	 * Accesseur du titre de l'item
	 * @return
	 */
	public String getTitre(){
		return titre;
	}
	
	/**
	 * Accesseur de la note moyenne
	 * @return
	 */
	public float getAverageRating(){
		return averageRating;
	}
		
	/**
	 */
	public abstract String toString();
	
}
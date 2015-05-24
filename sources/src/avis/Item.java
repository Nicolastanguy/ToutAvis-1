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
	 * 
	 * @return
	 */	
	public void averageRating(){
		
		float rateSum = 0;
		float coefSum = 0;
		for (Review review : reviews){
			rateSum += review.getNote()*review.getCoef(); //somme des notes pondérées (chacunes multipliées par leur coef) données à un item
			coefSum += review.getCoef(); //somme des coefs
		}
		averageRating = rateSum/coefSum; //Calcul de la moyenne
	}
	
	/**
	 * Ajoute un nouvel avis à la liste des avis de l'item
	 * 
	 * @param note note choisie par le membre
	 * @param coef poids que la note aura dans le calcul de la moyenne (correspond au karma du membre)
	 * @param commentaire le commentaire que fait le membre de l'item
	 * @param pseudo le pseudo du membre
	 */
	public void addReviewToItem(float note, float coef, String commentaire, String pseudo){
		Review newReview = new Review(note, coef, commentaire, pseudo);
		reviews.add(newReview);
	}
	
	/**
	 * Vérifie si le membre a déjà posté un avis sur l'item et supprime cet avis si c'est le cas
	 * 
	 * @param pseudo pseudo du membre à vérifier
	 */
	public void deletePreviousMemberReview(String pseudo){
		Review tempReview = new Review(0, 0, "", "");
		for (Review review : reviews){
			if (review.getPseudo().trim().toLowerCase().equals(pseudo.trim().toLowerCase())) tempReview=review; //récupération de l'avis s'il existe
		}
		reviews.remove(tempReview); //suppression de l'avis
	}
	
/**
 * Ajoute une nouvelle note à un avis de l'item
 * 
 * @param pseudo1 pseudo du membre qui note l'avis
 * @param pseudo2 pseudo du membre dont l'avis est noté
 * @param note note à ajouter
 */
	public void addNoteToReview(String pseudo1, String pseudo2, float note){
		for (Review review : reviews){
			if (review.getPseudo().trim().toLowerCase().equals(pseudo2.trim().toLowerCase())){
				review.noteReviewUpdate(pseudo1, note); //appel de la fonction qui va ajouter la note et mettre à jour la moyenne
			}
		}
	}
	
	/**
	 * Accesseur de la note moyenne d'un avis de l'item
	 * 
	 * @param pseudo1 pseudo du membre qui note l'avis
	 * @param pseudo2 pseudo du membre dont l'avis est noté
	 * @return la note moyenne de l'avis du membre
	 */
		public float getNoteReview(String pseudo1, String pseudo2){
			for (Review review : reviews){
				if (review.getPseudo().trim().toLowerCase().equals(pseudo2.trim().toLowerCase())) return review.getNoteReview(); //appel de l'accesseur de l'avis
			}
			return 0;
		}
	
		/**
		 * Vérifie si le membre a bien commenté l'item
		 * 
		 * @param pseudo pseudo du membre
		 * @return true si oui, false si non
		 */
	public boolean isMemberReview(String pseudo){
		for (Review review : reviews){
			if (review.getPseudo().trim().toLowerCase().equals(pseudo.trim().toLowerCase())) return true; //retourne true si un avis avec le pseudo du membre est trouvé
		}
		return false;
	}
	
	/**
	 * Vérifie si le membre à déjà noté un avis de l'item
	 * 
	 * @param pseudo1 pseudo du membre à vérifier
	 * @param pseudo2 pseudo du membre dont l'avis est noté
	 * @return true si oui, false si non
	 */
	public boolean memberAlreadyReviewOpinion(String pseudo1, String pseudo2){
		for (Review review : reviews){
			if (review.getPseudo().trim().toLowerCase().equals(pseudo2.trim().toLowerCase())) return review.memberAlreadyReviewOpinion(pseudo1); //appel la fonction qui va parcourir la liste des membres ayants déjà noté l'avis
		}
		return false;
	}
	
	/**
	 * Vérifie si le titre passé en paramètre correspond à celui de l'item
	 * 
	 * @param titre titre recherché
	 * @return true sur le titre correspond, false sinon
	 */
	public boolean isItem(String titre){
		if(titre.trim().toLowerCase().equals(this.titre.trim().toLowerCase())) return true;
		else return false;
	}
	
	/**
	 * Accesseur du titre de l'item
	 * 
	 * @return le titre de l'item
	 */
	public String getTitre(){
		return titre;
	}
	
	/**
	 * Accesseur de la note moyenne
	 * 
	 * @return la note moyenne de l'item
	 */
	public float getAverageRating(){
		return averageRating;
	}
		
	/**
	 * 
	 */
	public abstract String toString();
	
}
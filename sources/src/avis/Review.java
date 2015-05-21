package avis;

import java.util.LinkedList;

public class Review {
	
	private float note;
	private float noteReview;
	private float coef; //poids qu'aura la note dans le calcul de la moyenne, corespond au karma du membre lorsqu'il a posté cet avis
	private String commentaire;
	private String pseudo;
	private LinkedList<String> reviewOpinions; //liste pseudos des membres ayant noté cet avis
	
	public Review(float note, float coef, String commentaire, String pseudo){
		this.note = note;
		this.commentaire = commentaire;
		this.pseudo = pseudo;
		this.coef = coef;
	}
	
	public void noteReviewUpdate(String pseudo1, float note){
		float temp1 = noteReview*reviewOpinions.size();
		float temp2 = temp1+note;
		reviewOpinions.add(pseudo1);
		noteReview=temp2/reviewOpinions.size();
	}
	
	public boolean memberAlreadyReviewOpinion(String pseudo2){
		for (String reviewOpinion : reviewOpinions){
			if (reviewOpinion.trim().toLowerCase().equals(pseudo2.trim().toLowerCase())) return true;
		}
		return false;
	}
	
	//accesseur de la note moyenne d'un avis
	public float getNoteReview(){
		return noteReview;
	}
	
	//accesseur de la note de l'avis sur l'item
	public float getNote(){
		return note;
	}
	
	//accesseur du coef
	public float getCoef(){
		return coef;
	}
	
	public String toString(){
		return (" note : "+note+ " commentaire : <<"+commentaire+">>");
	}
	
	//Accesseur du pseudo 
	public String getPseudo() {
		return pseudo;
	}
	
}
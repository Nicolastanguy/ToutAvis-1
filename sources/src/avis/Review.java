package avis;

import java.util.LinkedList;

public class Review {
	
	private float note;
	private float noteReview;
	private String commentaire;
	private String pseudo;
	
	public Review(float note, String commentaire, String pseudo){
		this.note = note;
		this.commentaire = commentaire;
		this.pseudo = pseudo;
	}
	//accesseur de la note d'un avis
	public float getNote(){
		return note;
	}
	
	public String toString(){
		return (" note : "+note+ " commentaire : <<"+commentaire+">>");
	}
	
	//Accesseur du pseudo 
	public String getPseudo() {
		return pseudo;
	}
	//liste des membres ayant noté cet avis
	private LinkedList<Member> reviewOpinions;
	
	//karma du membre lorsqu'il a posté cet avis
	private float karma;
	
}
package avis;

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
	
}
package avis;

public class Review {
	
	private float note;
	private String commentaire;
	
	public Review(float note, String commentaire){
		this.note = note;
		this.commentaire = commentaire;
	}
	
	public float getNote(){
		return note;
	}
	
	public String toString(){
		return (" note : "+note+ " commentaire : <<"+commentaire+">>");
	}
	
}
package test;

import exception.BadEntry;
import exception.ItemBookAlreadyExists;
import exception.ItemFilmAlreadyExists;
import exception.MemberAlreadyExists;
import exception.NotMember;
import avis.SocialNetwork;


//Cette classe permet la création d'un Social Network pour les différents tests.

// 3 membres / 3 livres / 3 films

public class TestCreationSN {
	
	public static SocialNetwork createNewSocialNetwork() throws BadEntry, MemberAlreadyExists, NotMember, ItemBookAlreadyExists, ItemFilmAlreadyExists{
		
		SocialNetwork sn = new SocialNetwork();
		//Ajout de 3 membres au réseau social
		try{
			sn.addMember("Personne1","psw1","haha");
		}
		catch(Exception e){System.out.println("La création du réseau social ne s'est pas bien déroulée.");}		
		try{
			sn.addMember("Personne2","psw2","hoho");
		}
		catch(Exception e){System.out.println("La création du réseau social ne s'est pas bien déroulée.");}
		try{
			sn.addMember("Personne3","psw3","hihi");
		}
		catch(BadEntry e){System.out.println("La création du réseau social ne s'est pas bien déroulée.");}
		
		
		//Ajout de 3 livres au réseau social
		try{
			sn.addItemBook("Personne1", "psw1", "Arts martiaux", "Documentaire", "Jackie Chan", 150);
		}
		catch(Exception e){System.out.println("La création du réseau social ne s'est pas bien déroulée.");}
		try{
			sn.addItemBook("Personne2", "psw2", "La Chine", "Histoire", "Yang Ping Pong", 328);
		}
		catch(Exception e){System.out.println("La création du réseau social ne s'est pas bien déroulée.");}
		try{
			sn.addItemBook("Personne3", "psw3", "Coupe du monde football", "Sport", "Zinedine Zidane", 12);
		}
		catch(Exception e){System.out.println("La création du réseau social ne s'est pas bien déroulée.");}
		
		
		//Ajout de 3 films au réseau social
		try{
			sn.addItemFilm("Personne1", "psw1", "Interstellar", "Science-fiction", "Christopher Nolan", "Christopher Nolan", 169);
		}
		catch(Exception e){System.out.println("La création du réseau social ne s'est pas bien déroulée.");}
		try{
			sn.addItemFilm("Personne2", "psw2", "Matrix", "Action", "Andy Wachowski", "Lana Wachowski", 130);
		}
		catch(Exception e){System.out.println("La création du réseau social ne s'est pas bien déroulée.");}
		try{
			sn.addItemFilm("Personne3", "psw3", "Titanic", "Drame", "James Cameron", "James Cameron", 194);
		}
		catch(Exception e){System.out.println("La création du réseau social ne s'est pas bien déroulée.");}
				
		return sn; //Retourne le réseau social créé
		
	}
	
}

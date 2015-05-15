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
	
	public static SocialNetwork createNewSocialNetwork() throws BadEntry, MemberAlreadyExists, NotMember, ItemBookAlreadyExists, ItemFilmAlreadyExists {
		
		SocialNetwork sn = new SocialNetwork();
		//Ajout de 3 membres au réseau social
		sn.addMember("Personne1","psw1","haha");
		sn.addMember("Personne2","psw2","hoho");
		sn.addMember("Personne3","psw3","hihi");
		
		//Ajout de 3 livres au réseau social
		sn.addItemBook("Personne1", "psw1", "Arts martiaux", "Documentaire", "Jackie Chan", 150);
		sn.addItemBook("Personne2", "psw2", "La Chine", "Histoire", "Yang Ping Pong", 328);
		sn.addItemBook("Personne3", "psw3", "Coupe du monde football", "Sport", "Zinedine Zidane", 12);
		sn.addItemBook("Personne3", "psw3", "Le Seigneur des anneaux", "Fantasy", "J. R. R. Tolkien", 1000);
		
		//Ajout de 3 films au réseau social
		sn.addItemFilm("Personne1", "psw1", "Interstellar", "Science-fiction", "Christopher Nolan", "Christopher Nolan", 169);
		sn.addItemFilm("Personne2", "psw2", "Matrix", "Action", "Andy Wachowski", "Lana Wachowski", 130);
		sn.addItemFilm("Personne3", "psw3", "Titanic", "Drame", "James Cameron", "James Cameron", 194);
		sn.addItemFilm("Personne3", "psw3", "Le Seigneur des anneaux", "Fantasy", "Peter Jackson", "Peter Jackson", 219);
		
		//Retourne le réseau social créé
		return sn;
	}
}

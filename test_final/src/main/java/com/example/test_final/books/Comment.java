package com.example.test_final.books;

public class Comment {
	private int id;
	private String comment;
	private int numsOfStarRating;
	private int idUser;
	private int idBook;
	
	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Comment(int id, String comment, int numsOfStarRating, int idUser,int idBook) {
		super();
		this.id = id;
		this.comment = comment;
		this.numsOfStarRating = numsOfStarRating;
		this.idUser = idUser;
		this.idBook = idBook;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getNumsOfStarRating() {
		return numsOfStarRating;
	}

	public void setNumsOfStarRating(int numsOfStarRating) {
		this.numsOfStarRating = numsOfStarRating;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public int getIdBook() {
		return idBook;
	}

	public void setIdBook(int idBook) {
		this.idBook = idBook;
	}
	
	
	
}

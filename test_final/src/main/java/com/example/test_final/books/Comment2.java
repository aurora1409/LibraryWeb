package com.example.test_final.books;

public class Comment2 {
	private int id;
	private String comment;
	private int numsOfStarRating;
	private String name;
	private int idBook;
	
	public Comment2() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Comment2(int id, String comment, int numsOfStarRating, String name, int idBook) {
		super();
		this.id = id;
		this.comment = comment;
		this.numsOfStarRating = numsOfStarRating;
		this.name = name;
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


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIdBook() {
		return idBook;
	}

	public void setIdBook(int idBook) {
		this.idBook = idBook;
	}
	
	
	
}

package com.example.test_final.books;

public class BookBuying {
	private int id;
	private int numsOfBuying;
	private int idUser;
	private int idBook;
	private String confirm;
	
	public BookBuying() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BookBuying(int id, int numsOfBuying, int idUser,int idBook, String confirm ) {
		super();
		this.id = id;
		this.numsOfBuying = numsOfBuying;
		this.idUser = idUser;
		this.idBook = idBook;
		this.confirm=confirm;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNumsOfBuying() {
		return numsOfBuying;
	}
	public void setNumsOfBuying(int numsOfBuying) {
		this.numsOfBuying = numsOfBuying;
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
	public String getConfirm() {
		return confirm;
	}
	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}
	
	
	
}

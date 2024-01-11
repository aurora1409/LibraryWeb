package com.example.test_final.books;

public class BookBuying2 {
	private int id;
	private int idBook;
	private String title;
	private String author;
	private String urlImage;
	private int price;
	private int numsOfBuying;
	private int totalPrice;
	private String confirm;
	
	public BookBuying2() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BookBuying2(int id,int idBook, String title, String author, String urlImage, int price, int numsOfBuying,int totalPrice, String confirm) {
		super();
		this.id = id;
		this.idBook=idBook;
		this.title = title;
		this.author = author;
		this.urlImage = urlImage;
		this.price = price;
		this.numsOfBuying=numsOfBuying;
		this.totalPrice = totalPrice;
		this.confirm=confirm;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	

	public int getIdBook() {
		return idBook;
	}

	public void setIdBook(int idBook) {
		this.idBook = idBook;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	

	public int getNumsOfBuying() {
		return numsOfBuying;
	}

	public void setNumsOfBuying(int numsOfBuying) {
		this.numsOfBuying = numsOfBuying;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}
	
	
	
}

package com.example.test_final.books;

import java.sql.Date;

public class Book {
	private int id;
	private String title;
	private String author;
	private String category;
	private Date releaseDate;
	private int numsOfPages;
	private int numsOfSold;
	private String urlImage;
	private String description;
	private int price;
	
	public Book() {}

	public Book(int id, String title, String author, String category, Date releaseDate, int numsOfPages, int numsOfSold,
			String urlImage, String description, int price) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.category = category;
		this.releaseDate = releaseDate;
		this.numsOfPages = numsOfPages;
		this.numsOfSold = numsOfSold;
		this.urlImage = urlImage;
		this.description= description;
		this.price= price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public int getNumsOfPages() {
		return numsOfPages;
	}

	public void setNumsOfPages(int numsOfPages) {
		this.numsOfPages = numsOfPages;
	}

	public int getNumsOfSold() {
		return numsOfSold;
	}

	public void setNumsOfSold(int numsOfSold) {
		this.numsOfSold = numsOfSold;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	
}

package com.ultimatesoftware.libraryapi.rest.rental;

import java.time.LocalDate;

import com.ultimatesoftware.libraryapi.domain.book.Book;
import com.ultimatesoftware.libraryapi.domain.cardholder.CardHolder;

public class RentalDto {
	private Long id;
	private CardHolder cardHolder;
	private Book book;
	private LocalDate dueDate;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public CardHolder getCardHolder() {
		return cardHolder;
	}
	public void setCardHolder(CardHolder cardHolder) {
		this.cardHolder = cardHolder;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public LocalDate getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	

}

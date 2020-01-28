package com.ultimatesoftware.libraryapi.domain.rental;

import java.time.LocalDate;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ultimatesoftware.libraryapi.domain.book.Book;
import com.ultimatesoftware.libraryapi.domain.cardholder.CardHolder;

@Service
public class RentalService {

	private final RentalRepository rentalRepository;

	@Autowired
	public RentalService(final RentalRepository rentalRepository) {
		this.rentalRepository = rentalRepository;
	}

	@Transactional(readOnly = true)
	public Rental getRentalById(Long id) {
		return rentalRepository.getOne(id);
	}

	@Transactional(readOnly = true)
	public Collection<Rental> getRentalsByBook(Book book) {
		return rentalRepository.findRentalsByBook(book);
	}

	@Transactional(readOnly = true)
	public Collection<Rental> getRentalsByCardholder(CardHolder cardHolder) {
		return rentalRepository.findRentalsByCardHolder(cardHolder);
	}

	@Transactional(readOnly = true)
	public Collection<Rental> getRentalsDueAfter(LocalDate date) {
		return rentalRepository.findRentalsByDueDateAfter(date);
	}

	@Transactional(readOnly = true)
	public Collection<Rental> getAllRentals() {
		return rentalRepository.findAll();
	}

	@Transactional
	public Rental save(Rental rental) {
		return rentalRepository.save(rental);
	}

	@Transactional
	public void delete(Rental rental) {
		rentalRepository.delete(rental);
	}
}

package com.ultimatesoftware.libraryapi.rest.rental;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ultimatesoftware.libraryapi.domain.book.Book;
import com.ultimatesoftware.libraryapi.domain.book.BookService;
import com.ultimatesoftware.libraryapi.domain.rental.Rental;
import com.ultimatesoftware.libraryapi.domain.rental.RentalService;
import com.ultimatesoftware.libraryapi.rest.book.BookDto;

@RestController
@RequestMapping("/v1/rental-info")
public class RentalController {
	
	private final RentalService rentalService;
	private final BookService bookService;
	private final ModelMapper modelMapper;

	@Autowired
	public RentalController(final RentalService rentalService, final BookService bookService,final ModelMapper modelMapper) {
		this.rentalService = rentalService;
		this.modelMapper = modelMapper;
		this.bookService = bookService;
	}

	@GetMapping
	public List<RentalDto> getAll() {
		return rentalService.getAllRentals().stream()
				.map(rental -> modelMapper.map(rental, RentalDto.class))
				.collect(Collectors.toList());
	}
	
	@GetMapping(path = "/getAllRentedBooks")
	public List<BookDto> getAllRentedBook(){
		return rentalService.getAllRentals().stream()
				.map(rental -> modelMapper.map(rental.getBook(), BookDto.class))
				.collect(Collectors.toList());
	}
	
	@GetMapping(path = "/getAllOverDueBooks")
	public List<BookDto> getAllOverDueBooks(){
		
		LocalDate cur = LocalDate.now();
		List<BookDto> overDueBookLst = new ArrayList<BookDto>();
		rentalService.getAllRentals().stream().forEach(rental -> {
					
					if(rental.getDueDate().isBefore(cur)){
						overDueBookLst.add(modelMapper.map(rental.getBook(), BookDto.class));
					}
				});
		
	
		return overDueBookLst;
	
	}

	
	@DeleteMapping(path = "/returnBook/{bookId}")
	public String returnBookById(@PathVariable long bookId) {
		Optional<Book> bookInfo = bookService.getBookById(bookId);
		Collection<Rental> rentalCol = rentalService.getRentalsByBook(bookInfo.get());
	
		rentalCol.stream().forEach(rental ->{
			rentalService.delete(rental);			
		});
		
		return "Book:  "+bookId+" returned successfully";
	}
}

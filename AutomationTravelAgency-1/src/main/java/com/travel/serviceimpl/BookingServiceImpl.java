package com.travel.serviceimpl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.entity.Booking;
import com.travel.exception.BookingNotFoundException;
import com.travel.model.BookingDTO;
import com.travel.repo.BookingRepository;
import com.travel.service.BookingService;
import com.travel.util.ConverterClass;

@Service
public class BookingServiceImpl implements BookingService {

	private static Logger logBooking = LoggerFactory.getLogger(BookingServiceImpl.class);
	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private ConverterClass converter;

	@Override
	public Booking bookVehicle(BookingDTO bookingDto) {

		return bookingRepository.save(converter.convertToBookingEntity(bookingDto));
	}

	@Override
	public BookingDTO viewBooking(int bookingId) throws BookingNotFoundException {
		Optional<Booking> Booking = bookingRepository.findById(bookingId);
		BookingDTO dto = null;
		Booking book = null;
		if (Booking.isPresent()) {
			book = Booking.get();
			dto = converter.convertToBookingDTO(book);
			

		} else {
			logBooking.error("No such Booking Found");
			throw new BookingNotFoundException("No such Booking Found");
		}
		return dto;

	}

	@Override
	public String cancelBooking(int bookingId) throws BookingNotFoundException {
		Optional<Booking> Booking = bookingRepository.findById(bookingId);
		String message = null;
		if (Booking.isPresent()) {
			bookingRepository.deleteById(bookingId);
			message = "Booking Cancel Successfully";
			logBooking.info(message);

		} else {
			message = "No Booking Found";
			logBooking.error(message);
			throw new BookingNotFoundException(message);
		}
		return message;
	}

	@Override
	public BookingDTO viewBookingStatus(int bookingId) throws BookingNotFoundException {
		Optional<Booking> Booking = bookingRepository.findById(bookingId);
		BookingDTO dto = null;
		Booking book = null;
		if (Booking.isPresent()) {
			book = Booking.get();
			dto = converter.convertToBookingDTO(book);
			

		} else {
			logBooking.error("No such Booking Found");
			throw new BookingNotFoundException("No such Booking Found");
		}
		return dto;
	}

	
}
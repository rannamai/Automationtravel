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
import com.travel.util.ConversionClass;

@Service
public class BookingServiceImpl implements BookingService {
	
	private static Logger logBooking = LoggerFactory.getLogger(BookingServiceImpl.class);
	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private ConversionClass converter;
	
	@Override
	public Booking bookVehicle(BookingDTO bookingDto) {
		return bookingRepository.save(converter.convertToBookingEntity(bookingDto));
	}
	@Override
	public BookingDTO viewBooking(int bookingId) throws BookingNotFoundException {
Optional<Booking> booking = bookingRepository.findById(bookingId);
		BookingDTO dto=null;
		Booking book = null;
		if(booking.isPresent()) {
			book = booking.get();
			 dto=converter.convertToBookingDTO(book);
		}else {
			throw new BookingNotFoundException("Booking not found");
		}
		return dto;
	}

	@Override
	public String cancelBooking(int bookingId) throws BookingNotFoundException {
Optional<Booking> booking = bookingRepository.findById(bookingId);
			String message=null;
		if(booking.isPresent()) {
			bookingRepository.deleteById(bookingId);
			message="Deleted Successfully";
			logBooking.info(message);
		}else {
			throw new BookingNotFoundException("Booking not found");
		}
		return message;
	}
	@Override
	public BookingDTO viewBookingStatus(int bookingId) throws BookingNotFoundException {
Optional<Booking> booking = bookingRepository.findById(bookingId);
		BookingDTO dto=null;
		Booking bookingStatus = null;
		if(booking.isPresent()) {
			bookingStatus = booking.get();
			dto=converter.convertToBookingDTO(bookingStatus);
		}else {
			throw new BookingNotFoundException("Booking not found");
		}
		return dto;
	}

}

package com.montran.banking.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.montran.banking.payment.business.PaymentService;
import com.montran.banking.payment.domain.dto.PaymentCreateDTO;
import com.montran.banking.payment.domain.dto.PaymentVerifyDTO;
import com.montran.banking.payment.domain.entity.Payment;

@RestController
@RequestMapping("payment")
@CrossOrigin(origins = "http://localhost:4200")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@GetMapping
	public Iterable<Payment> findAll() {
		return paymentService.findAll();
	}

	@PostMapping("create")
	public void create(@RequestBody PaymentCreateDTO paymentCreateDTO) {
		paymentService.create(paymentCreateDTO);
	}

	@PostMapping("verify/{id}")
	public Boolean verify(@PathVariable Long id, @RequestBody PaymentVerifyDTO paymentVerifyDTO) {
		return paymentService.verify(id, paymentVerifyDTO);
	}

	@PostMapping("approve/{id}")
	public Boolean approve(@PathVariable Long id) {
		return paymentService.approve(id);
	}

	@PostMapping("authorize/{id}")
	public Boolean authorize(@PathVariable Long id) {
		return paymentService.authorize(id);
	}

	@PostMapping("cancel/{id}")
	public Boolean cancel(@PathVariable Long id) {
		return paymentService.cancel(id);
	}
}

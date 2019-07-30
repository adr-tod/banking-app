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
	public Payment create(@RequestBody PaymentCreateDTO paymentCreateDTO) {
		return paymentService.create(paymentCreateDTO);
	}

	@PostMapping("verify/{id}")
	public void verify(@PathVariable Long id, @RequestBody PaymentVerifyDTO paymentVerifyDTO) {
		paymentService.verify(id, paymentVerifyDTO);
	}

	@PostMapping("approve/{id}")
	public void approve(@PathVariable Long id) {
		paymentService.approve(id);
	}

	@PostMapping("authorize/{id}")
	public void authorize(@PathVariable Long id) {
		paymentService.authorize(id);
	}

	@PostMapping("cancel/{id}")
	public void cancel(@PathVariable Long id) {
		paymentService.cancel(id);
	}
}

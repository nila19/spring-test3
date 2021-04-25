package com.springtest.spring.api;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springtest.spring.api.model.Response;
import com.springtest.spring.service.TransactionService;

@RestController
@RequestMapping(value = "/trans")
@RequiredArgsConstructor
public class TransactionController {
  private static final String JSON = APPLICATION_JSON_VALUE;
  private final TransactionService transactionService;

  @Operation(summary = "Save the transaction")
  @PostMapping(value = "/save", consumes = JSON, produces = JSON)
  public Response saveTransaction() {
    this.transactionService.createTransaction();
    return new Response(0, "All OK");
  }
}

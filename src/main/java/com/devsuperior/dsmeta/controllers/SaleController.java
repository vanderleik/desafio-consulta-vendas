package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.dto.SaleSumaryDTO;
import com.devsuperior.dsmeta.services.SaleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	private final SaleService service;

    public SaleController(SaleService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/report")
	public ResponseEntity<Page<SaleReportDTO>> getReport(
			@RequestParam(value = "minDate", required = false) String minDate,
			@RequestParam(value = "maxDate", required = false) String maxDate,
			@RequestParam(value = "name", required = false) String name,
			Pageable pageable) {

		Page<SaleReportDTO> result = service.getReport(minDate, maxDate, name, pageable);
		return ResponseEntity.ok(result);
	}

	@GetMapping(value = "/summary")
	public ResponseEntity<Page<SaleSumaryDTO>> getSummary(
			@RequestParam(value = "minDate", required = false) String minDate,
			@RequestParam(value = "maxDate", required = false) String maxDate,
			Pageable pageable) {

		Page<SaleSumaryDTO> result = service.getSummary(minDate, maxDate, pageable);

		return ResponseEntity.ok(result);
	}
}

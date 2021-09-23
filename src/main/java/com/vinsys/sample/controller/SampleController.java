package com.vinsys.sample.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinsys.sample.model.Sample;
import com.vinsys.sample.repos.SampleRepo;

@RestController
@RequestMapping("/sample")
public class SampleController {
	@Autowired
	private SampleRepo sr;

	@PostMapping("/insert")
	public String insert(@RequestBody Sample sam) {
		sr.save(sam);
		return "Record Inserted....";
	}

	@GetMapping("/showAll")
	public List<Sample> showAll() {
		List<Sample> list = sr.findAll();
		return list;
	}

	@PutMapping("/update/{sampleNo}")
	public ResponseEntity<Sample> update(@PathVariable Integer sampleNo, @RequestBody Sample sampledetails) {
		Sample sample = sr.findById(sampleNo)
				.orElseThrow(() -> new SampleNotFoundException("Sample Not Found :" + sampleNo));
		sample.setName(sampledetails.getName());
		sample.setDate(sampledetails.getDate());

		Sample updatesample = sr.save(sample);

		return ResponseEntity.ok(updatesample);
	}

	@DeleteMapping(path="/delete/{sampleNo}")
		public String remove(@PathVariable Integer sampleNo) {
		Optional<Sample> sample=sr.findById(sampleNo);
		sr.deleteById(sampleNo);
		return "Record Deleted....";
	}
}

package healthygym.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import healthygym.model.SelfReport;
import healthygym.repository.SelfReportRepository;

@RestController
public class SelfReportController {
	
    private SelfReportRepository selfReportRepository;

	public SelfReportController(SelfReportRepository selfReportRepository) {
        this.selfReportRepository = selfReportRepository;
    }
	
	@GetMapping("/selfreports/{userId}")
    public List<SelfReport> getSelfReportsByUserId(@PathVariable Long userId) {
		return selfReportRepository.findByUserId(userId);
    }
	
	@PostMapping("/selfreport")
	public SelfReport newSelfReport(@RequestBody SelfReport selfReport) {
	    return selfReportRepository.save(selfReport);
	}

}

package healthygym.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import healthygym.model.SelfReport;
import healthygym.repository.CheckInRepository;
import healthygym.repository.SelfReportRepository;

@RestController
public class SelfReportController {
	
    private SelfReportRepository selfReportRepository;
    private CheckInRepository checkInRepository;

	public SelfReportController(SelfReportRepository selfReportRepository, CheckInRepository checkInRepository) {
        this.selfReportRepository = selfReportRepository;
        this.checkInRepository = checkInRepository;
    }
	
	@GetMapping("/selfreports/{userId}")
    public List<SelfReport> getSelfReportsByUserId(@PathVariable Long userId) {
		return selfReportRepository.findByUserId(userId);
    }
	
	@PostMapping("/selfreport")
	public SelfReport newSelfReport(@RequestBody SelfReport selfReport) {
		selfReportRepository.save(selfReport);
		checkInRepository.updateRiskForCommonCheckinsByUserId(selfReport.getReport_timestamp(), selfReport.getUserId(), (selfReport.isPositive_test())? 2 : 1);
		return selfReport;
	}

}

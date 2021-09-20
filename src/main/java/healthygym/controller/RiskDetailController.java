package healthygym.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import healthygym.model.RiskDetail;
import healthygym.repository.RiskDetailRepository;

@RestController
public class RiskDetailController {
	
	private RiskDetailRepository riskDetailRepository;

	public RiskDetailController(RiskDetailRepository riskDetailRepository) {
		this.riskDetailRepository = riskDetailRepository;
	}
	
	@GetMapping("/risk/{dow}")
    public List<RiskDetail> getRiskDetail(@PathVariable int dow) {
		return riskDetailRepository.findRiskDetailByDayOfWeek(dow);
    }
	

}

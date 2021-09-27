package healthygym.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import healthygym.service.RiskModelServiceImpl;

@Configuration
@EnableScheduling
public class RiskModelScheduler {
	
    @Autowired
    private RiskModelServiceImpl riskModelService;
    
	@Scheduled(cron = "0 0 1 * * MON")
	public void scheduleRiskModelUpdate() {
		String result = riskModelService.updateRiskModel();
	    System.out.println(
	      "Scheduled Risk Model Update - " + result);
	}

}

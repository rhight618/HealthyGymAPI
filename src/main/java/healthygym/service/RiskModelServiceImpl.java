package healthygym.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



@Configuration
@Service
public class RiskModelServiceImpl {
	
	@Value("${risk.model.url}")
	private String riskModelUrl;

	public String updateRiskModel() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response
		  = restTemplate.getForEntity(riskModelUrl, String.class);
		return response.getBody();
	}

}

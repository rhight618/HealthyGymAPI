package healthygym.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import healthygym.model.CheckIn;
import healthygym.repository.CheckInRepository;

@RestController
public class CheckInController {
	
    private CheckInRepository checkInRepository;

	public CheckInController(CheckInRepository checkInRepository) {
        this.checkInRepository = checkInRepository;
    }
	
	@GetMapping("/checkins/{userId}")
    public List<CheckIn> getCheckInsByUserId(@PathVariable Long userId) {
		return checkInRepository.findByUserId(userId);
    }
	
	@PostMapping("/checkin")
	public CheckIn newCheckIn(@RequestBody CheckIn checkin) {
	    return checkInRepository.save(checkin);
	}

}

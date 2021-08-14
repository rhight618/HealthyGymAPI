package healthygym.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import healthygym.model.CheckIn;

public interface CheckInRepository extends JpaRepository<CheckIn, Long>{
	
	List<CheckIn> findByUserId(Long userId);
}

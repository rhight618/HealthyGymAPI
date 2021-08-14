package healthygym.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import healthygym.model.SelfReport;

public interface SelfReportRepository extends JpaRepository<SelfReport, Long>{
	
	List<SelfReport> findByUserId(Long userId);
}

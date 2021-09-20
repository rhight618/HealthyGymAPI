package healthygym.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import healthygym.model.RiskDetail;

public interface RiskDetailRepository extends JpaRepository<RiskDetail, Long>{
	
	@Transactional
	@Query(value = "select * from healthygymdb1.riskdetail where day_of_week = :dow", nativeQuery = true)
	List<RiskDetail> findRiskDetailByDayOfWeek(@Param("dow") int dow);

}

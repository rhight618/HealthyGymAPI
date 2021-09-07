package healthygym.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import healthygym.model.CheckIn;

public interface CheckInRepository extends JpaRepository<CheckIn, Long>{
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "CALL UpdateRiskForCommonCheckins(:userId,:reportDate,:riskValue);", nativeQuery = true)
	void updateRiskForCommonCheckinsByUserId(@Param("reportDate") Timestamp reportDate, 
	  @Param("userId") Long userId, @Param("riskValue") Integer riskValue);
	List<CheckIn> findByUserId(Long userId);
}

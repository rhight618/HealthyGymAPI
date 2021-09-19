package healthygym.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import healthygym.model.RiskDetail;

public interface RiskDetailRepository extends JpaRepository<RiskDetail, Long>{

}

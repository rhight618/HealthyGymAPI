package healthygym.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import healthygym.model.User;

public interface UserRepository extends JpaRepository<User, Long>{


}

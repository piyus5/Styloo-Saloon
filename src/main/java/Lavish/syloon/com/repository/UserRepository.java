package Lavish.syloon.com.repository;

import Lavish.syloon.com.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  UserRepository extends JpaRepository<User, Long>  {
}

package comdbbyerik.project.repositories.UserRepository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import comdbbyerik.project.entity.User.UserSession;

public interface UserRepository extends JpaRepository<UserSession, String>{
  UserDetails  findByLogin(String login);
  List <UserSession>findAll();
}

package Lavish.syloon.com.controller;

import Lavish.syloon.com.Model.User;
import Lavish.syloon.com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

   @PostMapping("/api/user")
    public User createUser(@RequestBody User user){
       return userRepository.save(user);
    }

@GetMapping("/api/user")
   public List<User> getUsers(){
     return userRepository.findAll();
   }

@GetMapping ("/api/user/{id}")
   public User getUserById(@PathVariable Long id) throws Exception {
       Optional<User> otp = userRepository.findById(id);
       if (otp.isPresent()){
           return otp.get();
       }
       throw new Exception("user not found") ;
   }

   @PutMapping ("/api/user/{id}")
   public User updateUser(@RequestBody User user,
                          @PathVariable Long id) throws Exception {

Optional<User> otp=userRepository.findById(id);
if(otp.isEmpty()){
    throw new Exception("user not found with id"+id);
}
User existingUser=otp.get();

existingUser.setFullName(user.getFullName());
existingUser.setEmail(user.getEmail());
existingUser.setRole(user.getRole());

return userRepository.save(existingUser);

   }
@DeleteMapping ("/api/user/{id}")
   public String deleteUserById(@PathVariable Long id) throws Exception {
       Optional<User> otp=userRepository.findById(id);
       if(otp.isEmpty()){
           throw new Exception("user not exist with id"+id);
       }
       userRepository.deleteById(otp.get().getId());
       return "User deleted";
   }


}

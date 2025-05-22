package Lavish.syloon.com.controller;

import Lavish.syloon.com.Model.User;
import Lavish.syloon.com.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

   @PostMapping("/api/user")
    public User createUser(@RequestBody @Valid User user){
       return userRepository.save(user);
    }

@GetMapping("/api/user")
   public List<User> getUsers(){
     return userRepository.findAll();
   }
//
//@GetMapping ("/api/user/{id}")
//   public User getUserById(@PathVariable Long id) throws Exception
//    {
//       Optional<User> otp = userRepository.findById(id);
//       if (otp.isPresent()){
//           return otp.get();
//       }
//       throw new Exception("user not found") ;
//   }


    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }


//   @PutMapping ("/api/user/{id}")
//   public User updateUser(@RequestBody User user,
//                          @PathVariable Long id) throws Exception {
//
//Optional<User> otp=userRepository.findById(id);
//if(otp.isEmpty()){
//    throw new Exception("user not found with id"+id);
//}
//
//User existingUser=otp.get();
//
//existingUser.setFullName(user.getFullName());
//existingUser.setEmail(user.getEmail());
//existingUser.setRole(user.getRole());
//
//return userRepository.save(existingUser);
//
//   }

    @PutMapping("/{id}")
    public User updateUser(@RequestBody @Valid User user, @PathVariable Long id) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id " + id));

        existingUser.setFullName(user.getFullName());
        existingUser.setEmail(user.getEmail());
        existingUser.setRole(user.getRole());

        return userRepository.save(existingUser);
    }

//
//@DeleteMapping ("/api/user/{id}")
//   public String deleteUserById(@PathVariable Long id) throws Exception {
//       Optional<User> otp=userRepository.findById(id);
//       if(otp.isEmpty()){
//           throw new Exception("user not exist with id"+id);
//       }
//       userRepository.deleteById(otp.get().getId());
//       return "User deleted";
//   }



    @DeleteMapping("/{id}")
    public String deleteUserById(@PathVariable Long id) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id " + id));

        userRepository.deleteById(existingUser.getId());
        return "User deleted";
    }
}

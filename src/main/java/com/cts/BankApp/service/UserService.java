package com.cts.BankApp.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

//import org.apache.catalina.realm.JNDIRealm.User;
//import com.cts.exception.UserNotFoundException;
//import com.example.banking.exception.InvalidUserDataException;
//import com.example.banking.exception.AccountDeletionException;
//
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cts.BankApp.repository.UserRepository;
import com.cts.BankApp.users.Users;

@Service
@Transactional
public class UserService {
    
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private static final Pattern ACCOUNT_NUMBER_PATTERN = Pattern.compile("^[0-9]{10}$");
    private static final double MINIMUM_BALANCE = 100.0;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
//    private AuditService auditService; // Assuming we have an audit service
    
    
    public List<Users> getUsers(){
		return userRepository.findAll();    	
    }
    
    public Optional<Users> getUserById(int id){
    	return userRepository.findById(id);
    }
    
    @Transactional
    public Users createUser(Users user) {
        logger.info("Attempting to create new user with account number: {}", user.getAccountNumber());
        
        // Validate user data
        validateUserData(user);
        
        // Check if account number already exists
        if (userRepository.findByAccountNumber(user.getAccountNumber()) != null) {
            logger.error("Account creation failed - Account number already exists: {}", user.getAccountNumber());
            throw new InvalidUserDataException("Account number already exists");
        }
        
        try {
            // Set initial status and creation timestamp
//            user.setStatus("ACTIVE");
//            user.setCreatedAt(LocalDateTime.now());
            
            // Ensure minimum balance requirement
            if (user.getBalance() < MINIMUM_BALANCE) {
                logger.error("Account creation failed - Insufficient initial balance: {}", user.getBalance());
                throw new InvalidUserDataException("Initial balance must be at least " + MINIMUM_BALANCE);
            }
            
            // Save the user
            Users savedUser = userRepository.save(user);
            
            // Audit the creation
//            auditService.logUserCreation(savedUser);
            
            logger.info("Successfully created new user with ID: {}", savedUser.getId());
            return savedUser;
            
        } catch (Exception e) {
            logger.error("Error occurred while creating user: {}", e.getMessage());
            throw new RuntimeException("Failed to create user: " + e.getMessage());
        }
    }
    
//    @Transactional
//    public void deleteUser(Integer id) {
//        logger.info("Attempting to delete user with ID: {}", id);
//        
//        try {
//            int user = user.getId();
//            
//            // Check if user has non-zero balance
//            if (user.getBalance() > 0) {
//                logger.error("Cannot delete user with non-zero balance. User ID: {}", id);
//                throw new AccountDeletionException("Cannot delete account with non-zero balance");
//            }
//            
//            // Check for any pending transactions
//            if (hasPendingTransactions(user)) {
//                logger.error("Cannot delete user with pending transactions. User ID: {}", id);
//                throw new AccountDeletionException("Cannot delete account with pending transactions");
//            }
//            
//            // Soft delete - Update status instead of actual deletion
////            user.setStatus("INACTIVE");
////            user.setClosedAt(LocalDateTime.now());
////            user.setClosureReason("User requested account deletion");
////            userRepository.save(user);
//            
//            // Audit the deletion
////            auditService.logUserDeletion(user);
//            
//            logger.info("Successfully deactivated user account with ID: {}", id);
//            
//        } catch (UserNotFoundException e) {
//            logger.error("Delete failed - User not found with ID: {}", id);
//            throw e;
//        } catch (Exception e) {
//            logger.error("Error occurred while deleting user: {}", e.getMessage());
//            throw new RuntimeException("Failed to delete user: " + e.getMessage());
//        }
//    }
    
    private void validateUserData(Users user) {
        List<String> validationErrors = new ArrayList<>();
        
        // Validate account number format
        if (!ACCOUNT_NUMBER_PATTERN.matcher(user.getAccountNumber()).matches()) {
            validationErrors.add("Account number must be exactly 10 digits");
        }
        
        // Validate bank name
        if (user.getBankName() == null || user.getBankName().trim().isEmpty()) {
            validationErrors.add("Bank name is required");
        }
        
        // Validate customer name
//        if (user.getCustomerName() == null || user.getCustomerName().trim().isEmpty()) {
//            validationErrors.add("Customer name is required");
//        }
        
        // Validate email format
//        if (!isValidEmail(user.getEmail())) {
//            validationErrors.add("Invalid email format");
//        }
        
        // Validate balance
//        if (user.getBalance() == null || user.getBalance() < 0) {
//            validationErrors.add("Balance must be non-negative");
//        }
        
        if (!validationErrors.isEmpty()) {
            throw new InvalidUserDataException("Invalid user data: " + String.join(", ", validationErrors));
        }
    }
    
    private boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }
    
    private boolean hasPendingTransactions(Users user) {
        // Implementation would check for any pending transactions
        // This is just a placeholder - you would need to implement the actual check
        return false;
    }
    
    // Custom exceptions
    public class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String message) {
            super(message);
        }
    }
    
    public class InvalidUserDataException extends RuntimeException {
        public InvalidUserDataException(String message) {
            super(message);
        }
    }
    
    public class AccountDeletionException extends RuntimeException {
        public AccountDeletionException(String message) {
            super(message);
        }
    }
    
}
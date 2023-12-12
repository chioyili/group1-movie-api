package sg.edu.ntu.movie_api.serviceImpls;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

// import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import sg.edu.ntu.movie_api.entities.User;
import sg.edu.ntu.movie_api.repositories.UserRepository;
// import sg.edu.ntu.simplecrm.entities.Customer;
// import sg.edu.ntu.simplecrm.exceptions.CustomerNotFoundException;
// import sg.edu.ntu.simplecrm.repositories.CustomerRepository;
// import sg.edu.ntu.simplecrm.serviceImpls.CustomerServiceImpl;

@SpringBootTest
public class UserServiceImplTest {
    
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;

    @Test
    public void createUserTest() {
        // TODO: make this work 😭
        // 1. SETUP
        // Create a new customer
        // Customer customer = new Customer(1L, "Clint", "Barton", "clint@avenver.com", "12345678", "Special Agent", 1975, new ArrayList<Interaction>());
        User user = User.builder().userid(4L).email("user4@gmail.com").password("password4").name("name4").build();


        // mock the save method of the customer repository
        when((userRepository.save(user))).thenReturn(user);

        // 2. EXECUTE
        User savedUser = userService.createUser(user);

        // 3. ASSERT
        assertEquals(user, savedUser, "The saved user should be the same as the new user.");

        // also verify that the save method of the customer repository is called once only.
        verify(userRepository, times(1)).save(user);
    }

    // @Test
    // public void getCustomerTest() {
    //     // 1. SETUP
    //     // Create a new cutomer
    //     Customer customer = Customer.builder().firstName("Clint").lastName("Barton").email("inevermiss@avenger.com").contactNo("12345678").jobTitle("Special Agent").yearOfBirth(1975).build();

    //     Long customerId = 1L;

    //     when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

    //     // 2.EXECUTE
    //     Customer retrievedCustomer = customerService.getCustomer(customerId);

    //     // 3. ASSERT
    //     assertEquals(customer, retrievedCustomer);
    // }

    // @Test
    // void testGetCustomerNotFound() {
    //     Long customerId = 1L;
    //     when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

    //     assertThrows(CustomerNotFoundException.class, () -> customerService.getCustomer(customerId));
    // }
}
package uz.camunda.task.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.camunda.task.dto.client.CustomerDto;
import uz.camunda.task.model.client.Customer;
import uz.camunda.task.model.enums.Status;
import uz.camunda.task.repository.client.CustomerRepository;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final ProcessService processService;
    private final CustomerRepository customerRepository;

    @Transactional
    public String createCustomer(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setStatus(Status.ACTIVE);
        customer.setClientId(customerDto.getClientId());
        customer.setLastName(customerDto.getLastName());
        customer.setFirstName(customerDto.getFirstName());
        customerRepository.save(customer);

        return processService.startCreateClient(customerDto.getClientId());
    }

    public ResponseEntity<?> list() {
        return new ResponseEntity<>(customerRepository.findAll(), HttpStatus.OK);
    }
}

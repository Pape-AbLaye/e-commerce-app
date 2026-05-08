package tech.laye.ecomerce.customer;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import tech.laye.ecomerce.exception.CustomerNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private  final CustomerMapper customerMapper;

    public String createCustomer( CustomerRequest request) {
        var customer = customerRepository.save(customerMapper.toCustomer(request));
        return customer.getId();
    }

    public void updateCustomer( CustomerRequest request) {
        var customer = customerRepository.findById(request.id()).orElseThrow(
                () -> new CustomerNotFoundException(
                     String.format("Cannot update customer :: No customer Found with the provided ID : %s",request.id())   )
        );

        mergerCustomer(customer , request);
        customerRepository.save(customer);

    }

    private void mergerCustomer(Customer customer,  CustomerRequest request) {
        if(StringUtils.isNotBlank(request.firstname())){
            customer.setFirstname(request.firstname());
        }
        if(StringUtils.isNotBlank(request.lastname())){
            customer.setLastname(request.lastname());
        }
        if(StringUtils.isNotBlank(request.email())){
            customer.setEmail(request.email());
        }
        if(request.address() != null){
            customer.setAddress(request.address());
        }
    }

    public List<CustomerResponse> findAll() {

        return customerRepository.findAll().stream()
                .map(customerMapper::toCustomerResponse)
                .collect(Collectors.toList());
    }

    public Boolean existsById(String customerId) {
        return customerRepository.findById(customerId).isPresent();
    }

    public CustomerResponse findById(String customerId) {
        return customerRepository.findById(customerId)
                .map(customerMapper::toCustomerResponse)
                .orElseThrow(
                        () -> new CustomerNotFoundException(String.format(" No customer Found with the provided ID : %s",customerId) )
        );
    }

    public void deleteById(String customerId) {
        customerRepository.deleteById(customerId);
    }
}

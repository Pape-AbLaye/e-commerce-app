package tech.laye.ecomerce.customer;

import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {

    public Customer toCustomer(CustomerRequest request) {
        if (request == null){
            return null;
        }
        Customer customer = Customer
                .builder()
                .id(request.id())
                .address(request.address())
                .email(request.email())
                .firstname(request.firstname())
                .lastname(request.lastname())
                .build();
        return customer;
    }

    public CustomerResponse toCustomerResponse(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getFirstname(),
                customer.getLastname(),
                customer.getEmail(),
                customer.getAddress()

        );
    }
}

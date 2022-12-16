package com.softdreams.excel.service.impl;

import com.softdreams.excel.domain.Customer;
import com.softdreams.excel.repository.CustomerRepository;
import com.softdreams.excel.service.CustomerService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Customer}.
 */
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer save(Customer customer) {
        log.debug("Request to save Customer : {}", customer);
        return customerRepository.save(customer);
    }

    @Override
    public Customer update(Customer customer) {
        log.debug("Request to update Customer : {}", customer);
        return customerRepository.save(customer);
    }

    @Override
    public Optional<Customer> partialUpdate(Customer customer) {
        log.debug("Request to partially update Customer : {}", customer);

        return customerRepository
            .findById(customer.getId())
            .map(existingCustomer -> {
                if (customer.getCustomerCode() != null) {
                    existingCustomer.setCustomerCode(customer.getCustomerCode());
                }
                if (customer.getCustomerName() != null) {
                    existingCustomer.setCustomerName(customer.getCustomerName());
                }
                if (customer.getAddress() != null) {
                    existingCustomer.setAddress(customer.getAddress());
                }
                if (customer.getCustomerGroup() != null) {
                    existingCustomer.setCustomerGroup(customer.getCustomerGroup());
                }
                if (customer.getTax() != null) {
                    existingCustomer.setTax(customer.getTax());
                }
                if (customer.getPhoneNumber() != null) {
                    existingCustomer.setPhoneNumber(customer.getPhoneNumber());
                }
                if (customer.getUnfollow() != null) {
                    existingCustomer.setUnfollow(customer.getUnfollow());
                }
                if (customer.getCreatedDate() != null) {
                    existingCustomer.setCreatedDate(customer.getCreatedDate());
                }
                if (customer.getKeyUUID() != null) {
                    existingCustomer.setKeyUUID(customer.getKeyUUID());
                }

                return existingCustomer;
            })
            .map(customerRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Customer> findAll() {
        log.debug("Request to get all Customers");
        return customerRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Customer> findOne(Long id) {
        log.debug("Request to get Customer : {}", id);
        return customerRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Customer : {}", id);
        customerRepository.deleteById(id);
    }
}

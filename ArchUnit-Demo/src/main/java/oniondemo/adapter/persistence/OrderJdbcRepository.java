package oniondemo.adapter.persistence;

import jakarta.ejb.Stateless;
import oniondemo.domain.service.OrderRepository;

@Stateless
public class OrderJdbcRepository implements OrderRepository {
}

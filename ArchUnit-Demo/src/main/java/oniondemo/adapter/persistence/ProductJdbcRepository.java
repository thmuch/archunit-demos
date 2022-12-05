package oniondemo.adapter.persistence;

import jakarta.ejb.Stateless;
import oniondemo.domain.service.ProductRepository;

@Stateless
public class ProductJdbcRepository implements ProductRepository {
}

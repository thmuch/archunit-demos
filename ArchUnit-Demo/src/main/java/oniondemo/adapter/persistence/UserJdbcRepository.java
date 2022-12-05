package oniondemo.adapter.persistence;

import jakarta.ejb.Stateless;
import oniondemo.domain.service.UserRepository;

@Stateless
public class UserJdbcRepository implements UserRepository {
}

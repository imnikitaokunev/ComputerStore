package com.nikitkasss.store.repository;

import com.nikitkasss.store.dto.UserDto;
import com.nikitkasss.store.model.AbstractUser;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<AbstractUser, Long> {
    UserDto getByUserName(String login);
}

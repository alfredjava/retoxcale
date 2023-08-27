package com.xcale.ecommerce.domain.port;

import com.xcale.ecommerce.domain.User;

public interface UserPersistencePort {

    User createUser(User user);
    User findByEmail (String email);

    User findById (Long id);
}

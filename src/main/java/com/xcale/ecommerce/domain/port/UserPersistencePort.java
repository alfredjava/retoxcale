package com.xcale.ecommerce.domain.port;

import com.xcale.ecommerce.domain.User;

public interface UserPersistencePort {

    User createUser(User user);

    User findById (Long id);
}

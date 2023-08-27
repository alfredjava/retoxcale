package com.xcale.ecommerce.infrastructure.repository;

import com.xcale.ecommerce.domain.Cart;
import com.xcale.ecommerce.domain.CartDetails;
import com.xcale.ecommerce.domain.Product;
import com.xcale.ecommerce.domain.User;
import com.xcale.ecommerce.domain.port.CartPersistencePort;
import com.xcale.ecommerce.infrastructure.MyException;
import com.xcale.ecommerce.infrastructure.database.entity.CartDetailsEntity;
import com.xcale.ecommerce.infrastructure.database.entity.CartEntity;
import com.xcale.ecommerce.infrastructure.database.entity.mapper.CartEntityMapper;
import com.xcale.ecommerce.infrastructure.database.entity.mapper.UserEntityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Component
@Slf4j
@Transactional(readOnly = true)
public class CartRepository implements CartPersistencePort {

    private final CartJpaRepository cartJpaRepository;
    private final CartDetailsJpaRepository cartDetailsJpaRepository;
    private final CartEntityMapper cartEntityMapper;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final UserEntityMapper userEntityMapper;


    @Override
    @Transactional()
    public Cart saveCart(Cart cart) {
        try {

            User user = userRepository.findById(cart.getIdUser());

            if (user == null) {
                throw new MyException("User exits by id :" + cart.getIdUser() +" dont exist ", user.toString());
            }

            CartEntity cartEntity = cartJpaRepository.findCartEntityByUserEntity(userEntityMapper.toEntity(user));

            if (cartEntity != null){
                //elimino el carrito
                cartJpaRepository.delete(cartEntity);
            }
            Double totalCart = cart.getCartDetails().stream().mapToDouble(
                    detalle ->{
                        Product product = productRepository.findById(detalle.getIdProduct());
                        detalle.setPrice(product.getPrice());
                        detalle.setTotal(product.getPrice() * detalle.getQuantity());
                        return detalle.getTotal();
                    }
            ).sum();
            cart.setTotal(totalCart);
            cartEntity = cartJpaRepository.save(cartEntityMapper.toEntity(cart));


            CartEntity finalCartEntity = cartEntity;
            List<CartDetailsEntity> listCartDet = cartDetailsJpaRepository.saveAll(cartEntityMapper.setDetails(cart.getCartDetails()).stream()
                    .map(cartDetailsEntity -> {
                        cartDetailsEntity.setCart(finalCartEntity);
                        //update stock.
                        productRepository.updateStock(cartDetailsEntity.getProduct().getId(),
                                cartDetailsEntity.getQuantity());

                        return cartDetailsEntity;
                    }).collect(Collectors.toList()));
            cartEntity.setCartDetailsEntities(listCartDet);
            return cartEntityMapper.toDomain(cartEntity);
        }catch (Exception e){
            log.error("Error creating car: {}", e.getMessage());
            throw new MyException("Error creating cart: {}", cart.toString());
        }
    }

    @Override
    public Cart findById(Long id) {
        return cartEntityMapper.toDomain(cartJpaRepository.findById(id).orElse(null));
    }

    @Override
    public List<Cart> findAll() {
        return cartJpaRepository.findAll()
                .stream().map(this.cartEntityMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    @Transactional()
    public void deleteById(Long id) {
        try {

            cartJpaRepository.delete(cartJpaRepository.findById(id).get());
        }
        catch (Exception e){
            throw new MyException("Error when delete cart by id"+ id, e.getMessage());
        }
    }
}

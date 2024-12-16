package az.ingress.dao.repository;

import az.ingress.dao.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderRepository extends JpaRepository<OrderEntity,Long> , JpaSpecificationExecutor<OrderEntity> {
}

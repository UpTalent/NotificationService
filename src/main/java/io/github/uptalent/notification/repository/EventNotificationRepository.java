package io.github.uptalent.notification.repository;

import io.github.uptalent.notification.model.hash.EventNotification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventNotificationRepository extends CrudRepository<EventNotification, String> {
    List<EventNotification> findAllByToOrderByNotifiedDesc(String to);
    Optional<EventNotification> findByUuidAndTo(String id, String to);
}

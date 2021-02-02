package com.tools.clipboard.repos;

import com.tools.clipboard.models.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {

    Message findByExternalId(String id);
}
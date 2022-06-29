package net.midgard.dummy.mta;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface MessageRepository extends PagingAndSortingRepository<Message, Long> {
    public List<Message> findByRecipientId(long recipientId);
}

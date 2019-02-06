package tool.rpg.campaign.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tool.rpg.campaign.domain.Page;

public interface PageRepository extends MongoRepository<Page, String> {
}

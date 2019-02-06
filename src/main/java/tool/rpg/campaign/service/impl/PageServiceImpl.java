package tool.rpg.campaign.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tool.rpg.campaign.domain.Page;
import tool.rpg.campaign.repository.PageRepository;
import tool.rpg.campaign.service.IndexingService;
import tool.rpg.campaign.service.PageService;

import java.util.List;

@Service
@AllArgsConstructor
public class PageServiceImpl implements PageService {

    private final PageRepository pageRepository;
    private final IndexingService indexingService;

    @Override
    public void save(Page page) {
        pageRepository.save(page);
        indexingService.indexPages();
    }

    @Override
    public List<Page> getAll() {
        return pageRepository.findAll();
    }
}

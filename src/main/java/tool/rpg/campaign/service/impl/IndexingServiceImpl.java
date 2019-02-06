package tool.rpg.campaign.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import tool.rpg.campaign.domain.BasePage;
import tool.rpg.campaign.domain.Page;
import tool.rpg.campaign.repository.PageRepository;
import tool.rpg.campaign.service.IndexingService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class IndexingServiceImpl implements IndexingService {

    private final PageRepository pageRepository;

    @Async
    @Override
    public void indexPages() {
        log.info("Starting asynchronous process!");

        List<Page> pageList = pageRepository.findAll();

        Map<Page, List<BasePage>> indexedStructure = pageList.stream()
                                                             .collect(
                                                                     Collectors.toMap(
                                                                             page -> page,
                                                                             page -> pageList.stream()
                                                                                             .filter(title -> StringUtils.containsIgnoreCase(page.getContent(), title.getTitle()))
                                                                                             .collect(Collectors.toList())
                                                                     )
                                                             );

        indexedStructure.keySet()
                        .forEach(page -> page.setReferences(indexedStructure.get(page)));

        indexedStructure.keySet().stream()
                        .peek(x -> x.deepLinkContent()).forEach(pageRepository::save);



        log.info("Asynchronous process done!");
    }
}

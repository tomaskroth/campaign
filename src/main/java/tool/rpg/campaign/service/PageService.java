package tool.rpg.campaign.service;

import tool.rpg.campaign.domain.Page;

import java.util.List;

public interface PageService {

    void save(Page page);

    List<Page> getAll();

}

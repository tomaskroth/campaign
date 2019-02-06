package tool.rpg.campaign.controller.rest;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tool.rpg.campaign.controller.dto.PageDTO;
import tool.rpg.campaign.domain.Page;
import tool.rpg.campaign.service.PageService;

import java.util.List;
import java.util.stream.Collectors;

@RestController()
@RequestMapping("/page")
@AllArgsConstructor
public class PageRestController {

    private final PageService pageService;
    private final ModelMapper modelMapper;

    @PostMapping(value = "")
    public ResponseEntity savePage(@RequestBody PageDTO pageDTO) {
        Page page = modelMapper.map(pageDTO, Page.class);

        pageService.save(page);

        return ResponseEntity.ok("");
    }

    @GetMapping(value = "")
    public ResponseEntity<List<PageDTO>> listPages() {
        List<PageDTO> collect = pageService.getAll()
                                           .stream()
                                           .map(page -> modelMapper.map(page, PageDTO.class))
                                           .collect(Collectors.toList());

        return ResponseEntity.ok(collect);
    }



}

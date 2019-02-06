package tool.rpg.campaign.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Document(collection = "pages")
public class Page extends BasePage {

    @Id
    private String id;
    private String content;

    private List<BasePage> references = new ArrayList<>();

    @Transient
    private Boolean indexed = false;


    public void deepLinkContent() {
        this.content = this.content.replace("[[","").replace("]]","");
        references.forEach(
                ref -> this.setContent(this.content
                                           .replace(ref.title, "[[" + ref.title + "]]"))
        );
    }

    @Override
    public String toString() {
        return "Page{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", content='" + content + '\'' +
                ", indexed=" + indexed +
                ", references=[" + references.stream()
                                             .map(references -> references.getTitle())
                                             .collect(Collectors.joining(",")) + "]" +
                '}';
    }


}

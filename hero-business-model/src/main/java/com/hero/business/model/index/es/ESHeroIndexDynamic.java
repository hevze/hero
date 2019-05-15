package com.hero.business.model.index.es;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

/**
 * 类说明 ：
 *
 * @ClassName ESHeroIndexSearch
 * @Author hwz.hs
 * @Date 2019/5/11 10:25
 * @Version v_1.0
 */
@Document(indexName = "hero", type = "hero_index")
public class ESHeroIndexDynamic implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;
    private String title;
    private String  brief;
    private String  keyword;
    private String  content;
    private String  url;
    private String  hits;
    private String  create_by;
    private String  create_date;
    private String  del_flag;
    private String  imgs;
    private String  tags;
    private String  weight;
    private String  fromAuth;
    private String  text_type;
    private String  timing;
    private String  publish;
    private String  section_type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHits() {
        return hits;
    }

    public void setHits(String hits) {
        this.hits = hits;
    }

    public String getCreate_by() {
        return create_by;
    }

    public void setCreate_by(String create_by) {
        this.create_by = create_by;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getDel_flag() {
        return del_flag;
    }

    public void setDel_flag(String del_flag) {
        this.del_flag = del_flag;
    }

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getFromAuth() {
        return fromAuth;
    }

    public void setFromAuth(String fromAuth) {
        this.fromAuth = fromAuth;
    }

    public String getText_type() {
        return text_type;
    }

    public void setText_type(String text_type) {
        this.text_type = text_type;
    }

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public String getSection_type() {
        return section_type;
    }

    public void setSection_type(String section_type) {
        this.section_type = section_type;
    }
}

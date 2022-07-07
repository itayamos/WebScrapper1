import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
public class ScrapHelper {
    private String site;
    private String articleClass;
    private static final String attr="href";
    private String bodyClass;

    public ScrapHelper(String site, String articleClass, String bodyClass) {
        this.site = site;
        this.articleClass = articleClass;
        this.bodyClass = bodyClass;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getArticleClass() {
        return articleClass;
    }

    public void setArticleClass(String articleClass) {
        this.articleClass = articleClass;
    }

    public static String getAttr() {
        return attr;
    }

    public String getBodyClass() {
        return bodyClass;
    }

    public void setBodyClass(String bodyClass) {
        this.bodyClass = bodyClass;
    }

    public LinkedList<String> search(String str)throws IOException{
        LinkedList<String> artList=new LinkedList<>();
        Document website = Jsoup.connect(site).get();
        List<Element> elementsList=website.getElementsByClass(articleClass);
        String title = website.title();
        for (int i=0; i<elementsList.size();i++){
            Element currentElement= elementsList.get(i);
            Element linkElement=currentElement.child(0);
            String linkToArticle= linkElement.attr(attr);
            if(!linkToArticle.contains("https")) {
                linkToArticle=site+""+linkToArticle;
            }
            Document article = Jsoup.connect(linkToArticle).get();
            List<Element> articleBody=article.getElementsByClass(bodyClass);
            for (int k=0; k< articleBody.size(); k++) {
                String body = articleBody.get(k).text();
                if (body.contains(str)) {
                    String result = "title: " + title + '\n' +
                            "article: " + article.title() + '\n' +
                            "link: " + linkToArticle;
                    artList.add(result);
                }
            }
        }
        return artList;
    }
}

package najtek.infra.navigation;

/**
 * Created by anish on 21/8/16.
 */
public class NavigationLink {
    private String title;
    private String url;

    public NavigationLink(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }
}

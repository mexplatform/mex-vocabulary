package ontology;

/**
 * Created by esteves on 01.08.15.
 */
public class Algorithm {

    private final String label;
    private final String className;

    public Algorithm(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

}




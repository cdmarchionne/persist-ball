package ar.edu.unq.tpi.persistencia.persistence;

public class LoadByIdsQuery {

    private final String query;

    private final String replaceToken;

    protected LoadByIdsQuery(final String query, final String replaceToken) {
        this.query = query;
        this.replaceToken = replaceToken;
    }

    public LoadByIdsQuery(final Class poClass) {
        this("FROM " + poClass.getName() + " WHERE id IN ($IDS$)", "$IDS$");
    }

    public String getQuery() {
        return query;
    }

    public String getReplaceToken() {
        return replaceToken;
    }

}

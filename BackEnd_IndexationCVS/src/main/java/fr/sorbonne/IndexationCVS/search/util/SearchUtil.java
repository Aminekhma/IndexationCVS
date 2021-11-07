package fr.sorbonne.IndexationCVS.search.util;

import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.builder.SearchSourceBuilder;

public final class SearchUtil {

    private SearchUtil() {
    }

    public static SearchRequest buildSearchRequest(final String indexName, final String field, final String content) {
        try {
            final SearchSourceBuilder builder = new SearchSourceBuilder().query(getQueryBuilder(field, content));

            final SearchRequest request = new SearchRequest(indexName);
            request.source(builder);

            return request;

        } catch (final Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static SearchRequest buildSearchRequest(final String indexName, final String field) {
        try {
            final SearchSourceBuilder builder = new SearchSourceBuilder().query(getQueryBuilder(field));
            final SearchRequest request = new SearchRequest(indexName);
            request.source(builder);
            return request;

        } catch (final Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static DeleteRequest buildDeleteRequest(final String indexName, final String field) {
        try {
            final SearchSourceBuilder builder = new SearchSourceBuilder().query(getQueryBuilder(field));
            final DeleteRequest request = new DeleteRequest(indexName);

            return request;

        } catch (final Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static QueryBuilder getQueryBuilder(final String field, final String content) {
        String regex = ".?" + content + ".?";

        return QueryBuilders.regexpQuery(field, regex);

    }

    private static QueryBuilder getQueryBuilder(final String field) {
        String regex = ".*";

        return QueryBuilders.regexpQuery(field, regex);

    }
}

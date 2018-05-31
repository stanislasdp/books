package controller;

import lombok.Data;

import java.util.Map;

@Data
public class BookSearchAttributes {

    private Map<String, String> searchParameters;

    public String getTitleParamIfPresent() {
        return searchParameters.get("title");
    }

    public String getIsbnParamIfPresent() {
        return searchParameters.get("isbn");
    }

    public String getAuthorParamIfPresent() {
        return searchParameters.get("author");
    }

    public String getPrintedDateParamIfPresent() {
        return searchParameters.get("printedDate");
    }

}

package controller;

import lombok.Data;

import java.util.Map;

@Data
public class BookSearchAttributes {

    private Map<String, String> searchParameters;

}

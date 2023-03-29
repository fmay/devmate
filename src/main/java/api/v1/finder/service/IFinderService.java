package api.v1.finder.service;

import jakarta.validation.ValidationException;

import java.io.IOException;

public interface IFinderService {
    String runQuery(String body) throws IOException, ValidationException;
}

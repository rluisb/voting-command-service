package com.github.rluisb.voting.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value="/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public interface BaseVersion {
}

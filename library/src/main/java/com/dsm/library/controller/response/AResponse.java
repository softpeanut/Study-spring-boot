package com.dsm.library.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class AResponse {
    private List<A> libraries;
}

package com.task.products.Utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseModel {
    private Object output;
    public ResponseModel(Object data) {
        this.output = data;
    }
}

package com.sample.financialgoaltracker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Setter
@Getter
@Component
public class SuccessResponse {


    private int status;
    private Object data;
    private long timeStamp;
    private HttpHeaders headers;


    public SuccessResponse() {
        this.status = HttpStatus.OK.value();
        this.timeStamp = timeStamp;
        this.headers= new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
    }
    public ResponseEntity sendSuccessResponse(Object data, long timeStamp)
    {
        this.timeStamp = timeStamp;
        return ResponseEntity.status(this.status).headers(this.headers).body(data);
    }
}

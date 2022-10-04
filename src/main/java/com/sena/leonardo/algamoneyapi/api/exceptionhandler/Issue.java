package com.sena.leonardo.algamoneyapi.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.OffsetDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Issue {
    private Integer status;
    private OffsetDateTime dateHour;
    private String title;
    private List<Description> descriptions;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public OffsetDateTime getDateHour() {
        return dateHour;
    }

    public void setDateHour(OffsetDateTime dateHour) {
        this.dateHour = dateHour;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Description> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(List<Description> descriptions) {
        this.descriptions = descriptions;
    }

    public static class Description {
        private String field;
        private String msg;

        public Description(String field, String msg) {
            this.field = field;
            this.msg = msg;
        }

        public String getField() {
            return field;
        }

        public String getMsg() {
            return msg;
        }
    }
}

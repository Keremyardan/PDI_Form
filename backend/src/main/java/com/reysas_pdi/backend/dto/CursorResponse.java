package com.reysas_pdi.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CursorResponse<T> {
    private int page;
    private int size;
    private Long totalElements;
    private List<T> items;
}

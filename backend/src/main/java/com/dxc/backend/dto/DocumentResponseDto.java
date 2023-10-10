package com.dxc.backend.dto;

import lombok.Data;

@Data
public class DocumentResponseDto {
  private final String content;

  public DocumentResponseDto(String content) {
    this.content = content;
  }
}

package me.dmitry404.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class MovieEvent {
  private String movieId;
  private Date date;
}

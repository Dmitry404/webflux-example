package me.dmitry404.bootstrap;

import me.dmitry404.domain.Movie;
import me.dmitry404.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class BootstrapCLR implements CommandLineRunner {
  private final MovieRepository movieRepository;

  @Autowired
  public BootstrapCLR(MovieRepository movieRepository) {
    this.movieRepository = movieRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    movieRepository.deleteAll()
        .thenMany(
            Flux.just("The Shawshank Redemption", "The Godfather", "The Godfather: Part II", "The Dark Knight ", "12 Angry Men")
                .map(Movie::new)
                .flatMap(movieRepository::save))
        .subscribe(null, null, () -> movieRepository.findAll().subscribe(System.out::println));
  }
}

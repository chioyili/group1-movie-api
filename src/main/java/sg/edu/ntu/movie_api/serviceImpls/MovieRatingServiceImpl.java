package sg.edu.ntu.movie_api.serviceImpls;

import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Service;
import sg.edu.ntu.movie_api.services.MovieRatingService;
import sg.edu.ntu.movie_api.entities.MovieRating;

import sg.edu.ntu.movie_api.exceptions.MovieRatingNotFoundException;
import sg.edu.ntu.movie_api.repositories.MovieRatingRepository;



@Service
public class MovieRatingServiceImpl implements MovieRatingService{

    private MovieRatingRepository movieRatingRepository;


    // @Autowired
    public MovieRatingServiceImpl(MovieRatingRepository movieRatingRepository) {
        this.movieRatingRepository = movieRatingRepository;
    }

 @Override
    public ArrayList<MovieRating> searchMovieRatings(Integer rating) {
        List<MovieRating> foundMovieRatings = movieRatingRepository.findByRating(rating);
        return (ArrayList<MovieRating>) foundMovieRatings;
    }



     @Override
    public MovieRating getMovieRatingForUserAndMovie(Long userid, Long movie_id) {
       return movieRatingRepository.findByUserIdAndMovieId(userid, movie_id ).orElseThrow(()-> new MovieRatingNotFoundException(userid, movie_id ));
 }
      @Override
    public MovieRating getMovieRatingForMovie(Long movie_id) {
       return movieRatingRepository.findByMovieId(movie_id ).orElseThrow(()-> new MovieRatingNotFoundException(movie_id));
 }

     @Override
    public ArrayList<MovieRating> getAllMovieRatingsForUser(Long userid) {
        List<MovieRating> allMovieRatings = movieRatingRepository.findAllByUserId(userid);
        return (ArrayList<MovieRating>) allMovieRatings;
    }

    @Override
    public ArrayList<MovieRating> getAllMovieRatings() {
        List<MovieRating> allMovieRatings = movieRatingRepository.findAll();
        return (ArrayList<MovieRating>) allMovieRatings;
    }

    @Override
    public MovieRating updateMovieRating(Long userid, Long movie_id, MovieRating movieRating) {
        // retrieve the movieRating from the database
        // [Activity 1 - Refactor code]
        MovieRating movieRatingToUpdate = movieRatingRepository.findByUserIdAndMovieId(userid, movie_id ).orElseThrow(()-> new MovieRatingNotFoundException(userid, movie_id));
        // update the movieRating retrieved from the database
        movieRatingToUpdate.setRating(movieRating.getRating());

        // save the updated customer back to the database
        return movieRatingRepository.save(movieRatingToUpdate);
    }

    @Override
    public MovieRating getMovieRating(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMovieRating'");
    }



}

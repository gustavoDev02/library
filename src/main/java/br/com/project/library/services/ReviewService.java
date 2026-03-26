package br.com.project.library.services;

import br.com.project.library.models.Review;
import br.com.project.library.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> getReviews(){
        return reviewRepository.findAll();
    }

    public Review addReview(Review review){
        return reviewRepository.save(review);
    }

    public Optional<Review> getReviewById(Long id){
        return reviewRepository.findById(id);
    }

    public void deleteReview(Long id){
        var optionalReview = getReviewById(id);
        if (optionalReview.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Avaliação não encontrada");
        }
        reviewRepository.deleteById(id);
    }

    public Review updateReview(Long id, Review newReview){
        var optionalReview = getReviewById(id);
        if (optionalReview.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Avaliação não encontrada");
        }
        var review = optionalReview.get();
        newReview.setId(id);
        return reviewRepository.save(newReview);
    }

}

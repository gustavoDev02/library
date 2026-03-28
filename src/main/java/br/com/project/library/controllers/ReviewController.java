package br.com.project.library.controllers;

import br.com.project.library.models.Book;
import br.com.project.library.models.Review;
import br.com.project.library.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("reviews")
public class ReviewController {

    @Autowired
    private ReviewService service;

    @GetMapping
    public List<Review> getAll(){
        return service.getReviews();
    }

    @PostMapping
    public ResponseEntity<Review> addReview(@RequestBody Review review){
        var reviwes = service.addReview(review);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(reviwes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long id){
        return service.getReviewById(id)
                .map((b) -> ResponseEntity.ok(b))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id){
        service.deleteReview(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable Long id, @RequestBody Review newReview){
        Review review = service.updateReview(id, newReview);
        return ResponseEntity.ok(review);
    }

}

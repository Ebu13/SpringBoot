package controller;

import dao.ReviewDAO;
import entities.Review;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@SessionScoped
public class ReviewController implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(ReviewController.class.getName());

    private List<Review> reviews;
    private Review selectedReview;
    private Review newReview;

    private final ReviewDAO reviewDAO;

    public ReviewController() {
        reviewDAO = new ReviewDAO();
    }

    @PostConstruct
    public void init() {
        refreshReviews();
        selectedReview = new Review();
        newReview = new Review();
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void deleteReview(int reviewId) {
        try {
            reviewDAO.deleteReview(reviewId);
            refreshReviews();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error occurred while deleting review with id: " + reviewId, ex);
        }
    }

    public void updateReview() {
        try {
            reviewDAO.updateReview(selectedReview);
            refreshReviews();
            selectedReview = new Review(); // Güncelleme sonrasında formu temizle
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error occurred while updating review with id: " + selectedReview.getReviewId(), ex);
        }
    }

    public void addReview() {
        try {
            reviewDAO.addReview(newReview);
            refreshReviews();
            newReview = new Review();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error occurred while adding review", ex);
        }
    }

    public void editReview(Review review) {
        selectedReview = review;
    }

    private void refreshReviews() {
        try {
            reviews = reviewDAO.getAllReviews();
            LOGGER.info("Review list size: " + reviews.size()); // Review listesinin boyutunu logla
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error occurred while fetching reviews", ex);
        }
    }

    public Review getSelectedReview() {
        return selectedReview;
    }

    public void setSelectedReview(Review selectedReview) {
        this.selectedReview = selectedReview;
    }

    public Review getNewReview() {
        return newReview;
    }

    public void setNewReview(Review newReview) {
        this.newReview = newReview;
    }
}

package com.example.backend.dto.rating;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RatingResponse {

    private Long reviewId;
    private Integer myRating;
}

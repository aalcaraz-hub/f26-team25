<#import "layout.ftlh" as layout>
<@layout.page title="Leave a Review">

    <div class="container">
        <h1>Leave a Review</h1>
        <p class="subtitle">Share your experience to help other customers</p>
        <div class="card">
            <div class="provider">
                <h2>Heavenly Hair Studio</h2>
                <p>Hair Color | Greensboro, NC</p>
            </div>

    <form action="/review" method="POST">
        <input type="hidden" name="customerId" value="${customerId}">
        <input type="hidden" name="appointmentId" value="${appointmentId}">
        
            <label for="rating">Your Rating</label>
            <select id="rating" name="rating">
                <option value="5">★★★★★ 5 - Excellent</option>
                <option value="4">★★★★☆ 4 - Great</option>
                <option value="3">★★★☆☆ 3 - Good</option>
                <option value="2">★★☆☆☆ 2 - Fair</option>
                <option value="1">★☆☆☆☆ 1 - Poor</option>
            </select>
            <label for="review">Your Review</label>
            <textarea id="review" name="comments" placeholder="Tell us about your experience..."></textarea>
            <button class="submit-btn" type="submit">Submit Review</button>
        </form>
        </div>
    </div>
</@layout.page>
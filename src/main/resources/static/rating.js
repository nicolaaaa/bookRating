//document.querySelectorAll(".rate-book").forEach(ratingBox => {
//    let stars = ratingBox.innerText.trim().split("");
//    ratingBox.innerHTML = stars.map((star, index) => `<span data-value="${index + 1}">${star}</span>`).join("");
//
//    ratingBox.addEventListener("mouseover", function (event) {
//        let value = event.target.dataset.value;
//        if (value) {
//            highlightStars(ratingBox, value);
//        }
//    });
//
//    ratingBox.addEventListener("mouseleave", function () {
//        resetStars(ratingBox);
//    });
//
//    ratingBox.addEventListener("click", function (event) {
//        let value = event.target.dataset.value;
//        if (value) {
//            let bookId = ratingBox.closest(".book-rating").dataset.bookId;
//            submitRating(bookId, value);
//        }
//    });
//});
//
//function highlightStars(ratingBox, value) {
//    ratingBox.querySelectorAll("span").forEach(star => {
//        star.classList.toggle("active", star.dataset.value <= value);
//    });
//}
//
//function resetStars(ratingBox) {
//    ratingBox.querySelectorAll("span").forEach(star => star.classList.remove("active"));
//}
//
//function submitRating(bookId, rating) {
//    fetch(`/api/rate-book`, {
//        method: "POST",
//        headers: {"Content-Type": "application/json"},
//        body: JSON.stringify({bookId, rating})
//    })
//            .then(response => response.json())
//            .then(updatedData => {
//                let avgRatingBox = document.querySelector(`.book-rating[data-book-id="${bookId}"] .average-rating`);
//                avgRatingBox.innerHTML = `★${updatedData.average.toFixed(1)} <span class="rating-count">(${updatedData.votes} votes)</span>`;
//            })
//            .catch(error => console.error("Error submitting rating:", error));
//}


// Open Book Modal and populate details
//document.querySelectorAll('.book-icon').forEach((book, index) => {
//    book.addEventListener('click', () => {
//        // Get book data based on index (or ID if dynamic)
//        const bookData = books[index]; // Assuming books array holds book data
//
//        // Populate modal content
//        document.getElementById('modal-book-title').textContent = bookData.title;
//        document.getElementById('modal-book-author').textContent = `Author: ${bookData.author}`;
//        document.getElementById('modal-book-isbn').textContent = bookData.isbn;
//        document.getElementById('modal-book-color').textContent = bookData.color;
//
//        // Set average rating and votes (example)
//        document.getElementById('modal-average-rating').textContent = `${bookData.averageRating} / 5`;
//        document.getElementById('modal-votes-count').textContent = `(${bookData.votesCount} votes)`;
//
//        // Show modal
//        document.getElementById('book-modal').style.display = 'flex';
//
//        // Create rating stars dynamically
//        const ratingStars = document.getElementById('rating-stars');
//        ratingStars.innerHTML = '';
//        for (let i = 1; i <= 5; i++) {
//            const star = document.createElement('i');
//            star.classList.add('fa', 'fa-star');
//            if (i <= bookData.userRating)
//                star.classList.add('filled'); // Highlight user rating stars
//            ratingStars.appendChild(star);
//        }
//    });
//});

//// Close modal when "Close" button is clicked
//document.getElementById('close-modal').addEventListener('click', () => {
//    document.getElementById('book-modal').style.display = 'none';
//});
//
//// Handle star click for rating
//document.getElementById('rating-stars').addEventListener('click', (e) => {
//    if (e.target.classList.contains('fa-star')) {
//        const clickedStarIndex = Array.from(e.target.parentElement.children).indexOf(e.target) + 1;
//        console.log('User rated this book: ', clickedStarIndex);
//        // Save rating (send it to backend or store it locally)
//        updateRating(clickedStarIndex);
//    }
//});

// Update rating function (you can save it somewhere or send it to the backend)
function updateRating(rating) {
    // Assuming book data has a userRating field
    // Update book data or send to backend
    books[currentBookIndex].userRating = rating; // Update the rating for the current book
    console.log('Rating updated:', rating);
}

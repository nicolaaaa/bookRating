document.addEventListener("DOMContentLoaded", function () {
    fetchBooks();
});
function fetchBooks() {
    fetch("/api/random-books?count=8")
            .then(response => response.json())
            .then(books => {
                const container = document.getElementById("book-container");
                container.innerHTML = ""; // Clear old books

                books.forEach((book, index) => {
                    const bookElement = createBookElement(book, index);
                    container.appendChild(bookElement);
                });
                document.querySelectorAll('.book-icon').forEach((book, index) => {
                    book.addEventListener('click', () => {
                        const bookData = books[index];
                        // Populate modal
                        document.getElementById('modal-book-title').textContent = bookData.title;
                        document.getElementById('modal-book-author').textContent = `Author: ${bookData.author}`;
                        document.getElementById('modal-book-isbn').textContent = bookData.isbn;
                        document.getElementById('modal-book-color').textContent = bookData.color;
                        // Fetch rating data
//                        fetchRatings(bookData.id);
                        // Show modal
                        fetchRatingsAndDisplayInModal(bookData.id);

                        document.getElementById('book-modal').style.display = 'flex';
                    });
                });
                // Close modal when clicking outside the content
                document.getElementById('book-modal').addEventListener('click', (event) => {
                    if (!document.querySelector('.book-modal-content').contains(event.target)) {
                        document.getElementById('book-modal').style.display = 'none';
                    }
                });
            })
            .catch(error => console.error("Error fetching books:", error));
}

function createBookElement(book, index) {
    const bookDiv = document.createElement("div");
    bookDiv.classList.add("book-icon");
    bookDiv.style.backgroundColor = book.color;
    const booksPerRow = 4;
    const row = Math.floor(index / booksPerRow);
    const col = index % booksPerRow;
    bookDiv.style.top = `${15 + row * 40}%`;
    bookDiv.style.left = `${10 + col * 20}%`;
    const titleBoxDiv = document.createElement("div");
    titleBoxDiv.classList.add("book-title-box");
    const titleDiv = document.createElement("div");
    titleDiv.classList.add("book-title");
    titleDiv.innerText = book.title;
    const authorDiv = document.createElement("div");
    authorDiv.classList.add("book-author");
    authorDiv.innerText = book.author;
    bookDiv.appendChild(titleBoxDiv);
    titleBoxDiv.appendChild(titleDiv);
    bookDiv.appendChild(authorDiv);
    // Rating container
    const ratingDiv = document.createElement("div");
    ratingDiv.classList.add("book-rating");
    ratingDiv.setAttribute("data-book-id", book.id);
    const avgRatingDiv = document.createElement("div");
    avgRatingDiv.classList.add("average-rating");
    ratingDiv.appendChild(avgRatingDiv);
    const rateBookDiv = document.createElement("div");
    rateBookDiv.classList.add("rate-book");
    ratingDiv.appendChild(rateBookDiv);
    bookDiv.appendChild(ratingDiv);
    // Fetch and update ratings
    fetchRatingsAndUpdateRatingDisplay(book.id, avgRatingDiv, rateBookDiv);
    return bookDiv;
}

function fetchRatingsAndUpdateRatingDisplay(bookId, avgRatingDiv) {
    fetch(`/api/book-ratings/${bookId}`)
            .then(response => response.json())
            .then(data => {
                // Update average rating display
                avgRatingDiv.innerHTML = "";
                for (let i = 1; i <= 5; i++) {
                    const star = document.createElement("i");
                    star.classList.add("fa-star", "fa-xs", i <= Math.round(data.averageRating) ? "fa-solid" : "fa-regular");
                    avgRatingDiv.appendChild(star);
                }
                avgRatingDiv.innerHTML += `<span class="rating-count">(${data.votesCount})</span>`;
            })
            .catch(error => console.error("Error fetching ratings:", error));
}


function fetchRatingsAndDisplayInModal(bookId) {
    fetch(`/api/book-ratings/${bookId}`)
            .then(response => response.json())
            .then(data => {
                // Display average rating
                document.getElementById('modal-average-rating').textContent = `${data.averageRating.toFixed(1)} / 5`;
                document.getElementById('modal-votes-count').textContent = `(${data.votesCount} votes)`;
                // Update modal rating stars
                const ratingStarsDiv = document.getElementById('rating-stars');
                ratingStarsDiv.innerHTML = ""; // Clear previous stars

                // Display solid/empty stars for the average rating
                for (let i = 1; i <= 5; i++) {
                    const star = document.createElement("i");
                    star.classList.add("fa-star", "fa-xs", i <= Math.round(data.userRating) ? "fa-solid" : "fa-regular");
                    ratingStarsDiv.appendChild(star);
                }

                // Handle rating in the modal (submit rating when clicked)
                let alreadySubmitted = false;
                // Add an event listener to the stars in the modal
                const ratingStarsInModal = document.getElementById("rating-stars");
                if (ratingStarsInModal) {
                    ratingStarsInModal.addEventListener("click", function (event) {
                        if (alreadySubmitted) {
                            console.log("Rating already submitted.");
                            return;
                        }

                        // Check if a star was clicked
                        if (event.target.classList.contains("fa-star")) {
                            const selectedRating = Array.from(event.target.parentElement.children).indexOf(event.target) + 1;
                            submitBookRating(bookId, selectedRating); // Submit the rating for the book

                            // Set the flag to prevent resubmission
                            alreadySubmitted = true;

                        }
                    });
                }
            })
            .catch(error => console.error("Error fetching ratings:", error));
}

function submitBookRating(bookId, rating) {
    const sessionId = "test"; // Replace with dynamic session ID if needed

    const ratingData = {
        bookId: bookId,
        rating: rating,
        sessionId: sessionId
    };
    // Send rating to the backend (POST request)
    fetch('/api/rate-book', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(ratingData)
    })
            .then(response => response.json())
            .then(data => {
                // Handle successful submission
                console.log("Rating submitted successfully:", data);
                // Optionally, close the modal
                closeModal();
                // Fetch and update the rating and votes count for this book
                updateBookRating(data, bookId); // Update rating next to the book
            })
            .catch(error => {
                console.error("Error submitting rating:", error);
            });
}

function closeModal() {
    document.getElementById('book-modal').style.display = 'none';
}



function updateBookRating(data, bookId) {

    // Find the specific book element by its data-book-id attribute
    const bookElement = document.querySelector(`[data-book-id='${bookId}']`);
    if (bookElement) {
        const avgRatingDiv = bookElement.querySelector(".average-rating");
        avgRatingDiv.innerHTML = ""; // Clear old stars

        // Update the stars based on the new average rating
        for (let i = 1; i <= 5; i++) {
            const star = document.createElement("i");
            star.classList.add("fa-star", "fa-xs", i <= Math.round(data.averageRating) ? "fa-solid" : "fa-regular");
            avgRatingDiv.appendChild(star);
        }

        // Update the vote count next to the stars
        avgRatingDiv.innerHTML += `<span class="rating-count">(${data.votesCount})</span>`;
    } else {
        console.error(`No book element found for book ID: ${bookId}`);
    }
}

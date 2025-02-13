document.addEventListener("scroll", function () {
    let shelves = document.querySelectorAll(".shelf");
    let lastShelf = shelves[shelves.length - 1];

    if (lastShelf) {
        let lastShelfPosition = lastShelf.getBoundingClientRect().bottom;
        let windowHeight = window.innerHeight;

        if (lastShelfPosition < windowHeight + 50) {  // If near bottom
            loadNewShelf();
        }
    }
});

function loadNewShelf() {
    fetch("/api/more-shelves")
            .then(response => response.text())
            .then(newShelfHtml => {
                // Create a new shelf div and wrap the fetched content inside it
                let newShelf = document.createElement("div");
                newShelf.classList.add("shelf");  // Add the 'shelf' class
                newShelf.innerHTML = newShelfHtml;  // Insert fetched content inside it

                // Append the new shelf to the document
                document.body.appendChild(newShelf);

                // Apply CSS dynamically to reposition all shelves
                updateShelfPositions();
            })
            .catch(error => console.error("Error loading shelf:", error));
}


// Function to update shelf positions dynamically
function updateShelfPositions() {
    document.querySelectorAll('.shelf').forEach((shelf, index) => {
        let position = (index + 1) * 25; // Adjust this based on shelf spacing
        shelf.style.top = `calc(${position}% - var(--shelf-depth))`;
    });
}

// Run on initial load
document.addEventListener("DOMContentLoaded", updateShelfPositions);

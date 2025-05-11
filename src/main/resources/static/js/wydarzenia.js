document.addEventListener('DOMContentLoaded', () => {
    const eventsContainer = document.getElementById('events-container');
    const paginationContainer = document.getElementById('pagination-container');
    let currentPage = 1;
    const pageSize = 5; // Number of events per page

    // Fetch and render events
    function fetchEvents(page) {
        fetch(`/api/wydarzeniaSportowe/paginated?page=${page - 1}&size=${pageSize}`)
            .then(response => response.json())
            .then(data => {
                renderEvents(data.content); // Render the events
                renderPagination(data); // Render pagination controls
            })
            .catch(error => console.error('Error fetching events:', error));
    }

    // Render events
    function renderEvents(events) {
        eventsContainer.innerHTML = ''; // Clear previous events
        events.forEach(event => {
            const eventCard = document.createElement('div');
            eventCard.className = 'col-md-4';
            eventCard.innerHTML = `
                <div class="card shadow-sm">
                    <div class="card-body">
                        <h5 class="card-title">${event.nazwa}</h5>
                        <p class="card-text"><strong>Data:</strong> ${new Date(event.data).toLocaleString()}</p>
                        <p class="card-text"><strong>Typ Sportu:</strong> ${event.typSportu}</p>
                    </div>
                </div>
            `;
            eventsContainer.appendChild(eventCard);
        });
    }

    // Render pagination controls
    function renderPagination(data) {
        paginationContainer.innerHTML = ''; // Clear previous pagination
        if (!data.first) {
            paginationContainer.innerHTML += `
                <li class="page-item">
                    <a class="page-link" href="#" onclick="navigateToPage(${currentPage - 1})">Previous</a>
                </li>`;
        }
        for (let i = 1; i <= data.totalPages; i++) {
            paginationContainer.innerHTML += `
                <li class="page-item ${currentPage === i ? 'active' : ''}">
                    <a class="page-link" href="#" onclick="navigateToPage(${i})">${i}</a>
                </li>`;
        }
        if (!data.last) {
            paginationContainer.innerHTML += `
                <li class="page-item">
                    <a class="page-link" href="#" onclick="navigateToPage(${currentPage + 1})">Next</a>
                </li>`;
        }
    }

    // Navigate to a specific page
    window.navigateToPage = function (page) {
        currentPage = page;
        fetchEvents(page);
    };

    // Initial fetch
    fetchEvents(currentPage);
});

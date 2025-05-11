document.addEventListener('DOMContentLoaded', () => {
    fetchGroups();
});

function fetchGroups() {
    fetch('/api/zawodnicy/groups?page=0&size=10')
        .then(response => response.json())
        .then(data => renderGroups(data.content))
        .catch(error => console.error('Error fetching groups:', error));
}

function renderGroups(groups) {
    const tableBody = document.getElementById('groups-table-body');
    tableBody.innerHTML = '';

    groups.forEach(group => {
        const row = document.createElement('tr');

        row.innerHTML = `
            <td>${group.name}</td>
            <td>
                <button class="btn btn-primary btn-sm" onclick="fetchGroupDetails(${group.id})">Szczegóły</button>
            </td>
        `;

        tableBody.appendChild(row);
    });
}

function fetchGroupDetails(groupId) {
    fetch(`/api/zawodnicy/groups/${groupId}`)
        .then(response => response.json())
        .then(data => renderGroupDetails(data))
        .catch(error => console.error('Error fetching group details:', error));
}

function renderGroupDetails(group) {
    document.getElementById('group-details').style.display = 'block';
    document.getElementById('group-name').textContent = `Szczegóły Grupy: ${group.groupName}`;

    const participantsList = document.getElementById('participants-list');
    participantsList.innerHTML = '';

    group.participants.forEach(participant => {
        const listItem = document.createElement('li');
        listItem.className = 'list-group-item';

        if (participant.highlighted) {
            listItem.style.backgroundColor = 'lightblue';
        }

        listItem.textContent = `${participant.name} (${participant.role})`;

        participantsList.appendChild(listItem);
    });
}

function goBackToGroups() {
    document.getElementById('group-details').style.display = 'none';
    fetchGroups();
}

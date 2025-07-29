document.getElementById("searchInput").addEventListener("keyup", function() {
    const query = this.value;
    fetch(`/classes/search?query=${query}`)
    .then(response => response.json())
    .then(data => {
    const tbody = document.querySelector("table tbody");
    tbody.innerHTML = "";

    if (data.length === 0) {
    tbody.innerHTML = `
                    <tr>
                        <td colspan="5" class="empty-state">
                          <div class="empty-icon">📋</div>
                          <div>Aucun résultat trouvé</div>
                        </td>
                    </tr>`;
    return;
}

    data.forEach(classe => {
    tbody.innerHTML += `
                  <tr>
                    <td><span class="id-badge">${classe.id}</span></td>
                    <td class="sector-name">${classe.className}</td>
                    <td class="sector-name">${classe.description}</td>
                    <td class="sector-name">${classe.sector?.name || 'Non défini'}</td>
                    <td>
                      <div class="action-buttons">
                        <button class="edit-btn" data-id="${classe.id}" data-nom="${classe.className}" data-description="${classe.description}" onclick="ouvrirFormulaireModification(this)">
                          Modifier
                        </button>
                        <button class="delete-btn" data-id="${classe.id}" onclick="confirmerSuppression(this)">
                          Supprimer
                        </button>
                      </div>
                    </td>
                  </tr>
                `;
});
});
});

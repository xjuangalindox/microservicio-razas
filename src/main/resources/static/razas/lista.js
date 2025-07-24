// Mostrar razas
document.addEventListener('DOMContentLoaded', function (){
    const tabla = document.getElementById('tabla-razas');

    fetch('/api/razas')
        .then(response => response.json())
        .then(data => {
            tabla.innerHTML = '';

            data.forEach(raza => {
                const tr = document.createElement('tr');
                tr.classList.add('align-middle');

                tr.innerHTML = /*html*/`
                    <td>${raza.nombre}</td>
                    <td>
                        <a href="/razas/formulario.html?id=${raza.id}" class="btn btn-info">Editar</a>
                        <button class="btn btn-danger" onclick="eliminarRaza(${raza.id})">Eliminar</button>
                    </td>
                `;
                tabla.appendChild(tr);
            });
        })
        .catch(error => {
            console.error('Error al cargar razas: ', error);
        });
});

// Eliminar raza
function eliminarRaza(id){
    if(confirm('¿Seguro que deseas eliminar esta raza?')) {
        
        fetch(`/api/razas/${id}`, {method: 'DELETE'})
        .then(() => location.reload())
        .catch(error => {
            console.error('Error en fetch: ',error);
            alert('Error al eliminar');
        });
    }
}
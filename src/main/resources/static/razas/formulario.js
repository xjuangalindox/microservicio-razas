// Modificar titulo segun la URI
document.addEventListener('DOMContentLoaded', () => {
    const params = new URLSearchParams(window.location.search);
    const id = params.get('id');
    const titulo = document.getElementById('titulo');
    const form = document.getElementById('form-raza');

    if(id){
        // Modo edición
        titulo.textContent = 'Editar Raza';

        fetch(`/api/razas/${id}`)
        .then(response => response.json())
        .then(data => {
            document.getElementById('nombre').value = data.nombre;
        })
        .catch(error => {
            console.error('Error al cargar la raza', error);
            alert('Error al cargar la raza');
        });

    }else{
        // Modo creación
        titulo.textContent = 'Crear Raza';
    }

    form.addEventListener('submit', function(e){
        e.preventDefault();

        // Crea el objeto que se enviará al backend
        const raza = {
            nombre : document.getElementById('nombre').value
        };

        const url = id ? `/api/razas/${id}` : `/api/razas`;
        const method = id ? 'PUT' : 'POST';

        fetch(url, {
            method : method, 
            headers: {'Content-Type': 'application/json'}, 
            body: JSON.stringify(raza)
        })
        .then(response => {
            if(!response.ok) throw new Error('Error en el guardado');
            // alert(id ? 'Raza actualizada' : 'Raza creada');
            window.location.href = '/razas/lista.html'; // Redirigir despues de guardar
        })
        .catch(error => {
            console.error('Error', error)
            alert('Ocurrio un error al guardar')
        });
    });
});
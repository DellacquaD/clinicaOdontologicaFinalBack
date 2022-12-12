window.addEventListener('load', function () {
    const formulario = document.querySelector('#update_turno_form');
    formulario.addEventListener('submit', (e) => {
        e.preventDefault();
        const formData = {
            id: document.querySelector('#turnoId').value,
            pacienteId: document.querySelector('#pacienteId').value,
            odontologoId: document.querySelector('#odontologoId').value,
            fecha: document.querySelector('#fecha').value,

        };
        const url = '/turnos';
        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }
        fetch(url,settings)
        .then(response => {
            if (response.status == 404){
                alert("Las modificaciones no fueron ejecutadas");
            }
            window.location.replace("/pages/turno_list.html");
        })

    })
 });

function findBy(id) {
    document.querySelector('#update_turno_form').classList.toggle("d-none")
    const url = '/turnos/buscar'+"/"+id;
    const settings = {
        method: 'GET'
    }
    fetch(url,settings)
    .then(response => response.json())
    .then(data => {
        let turno = data;
        document.querySelector('#turnoId').value = id;
        document.querySelector('#fecha').value = turno.fecha;
        document.querySelector('#pacienteId').value = turno.pacienteId;
        document.querySelector('#odontologoId').value = turno.odontologoId;
        document.querySelector('#div_turno_updating').style.display = "block";
        })
    .catch(error => {
        alert("Error: " + error);
    })
}
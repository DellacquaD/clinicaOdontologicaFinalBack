window.addEventListener('load', function () {
    const url = '/turnos';
    const settings = {
        method: 'GET'
    }
    fetch(url,settings)
    .then(response => response.json())
    .then(data => {

        for(turno of data){
            console.log(turno);
            var table = document.getElementById("turnoTable");
            var turnoRow = table.insertRow();
            let tr_id = 'tr_' + turno.id;
            turnoRow.id = tr_id;

            let deleteButton = '<button' +
                              ' id=' + '\"' + 'btn_delete_' + turno.id + '\"' +
                              ' type="button" onclick="deleteBy('+turno.id+')" class="btn btn-danger btn_delete">' +
                              '&times' +
                              '</button>';

            let updateButton = '<button' +
                               ' id=' + '\"' + 'btn_id_' + turno.id + '\"' +
                               ' type="button" onclick="findBy('+turno.id+')" class="btn btn_id">' +
                               '✏' +
                               '</button>';


            turnoRow.innerHTML =
                    '<td class=\"td_Id\">' + turno.id + '</td>' +
                    '<td class=\"td_fecha\">' + turno.fecha + '</td>' +
                    '<td class=\"td_pacienteId\">' + turno.pacienteId + '</td>' +
                    '<td class=\"td_odontologoId\">' + turno.odontologoId + '</td>' +
                    '<td>' + updateButton + '</td>' +
                    '<td>' + deleteButton + '</td>';
        };
    })

});
$(document).ready(function() {
	var table = $('#table').DataTable({
		"scrollY": 500,
		"scrollX": true,
		/* language: {
                "lengthMenu": "Mostrar _MENU_ registros",
                "zeroRecords": "No se encontraron resultados",
                "info": "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
                "infoEmpty": "Mostrando registros del 0 al 0 de un total de 0 registros",
                "infoFiltered": "(filtrado de un total de _MAX_ registros)",
                "sSearch": "Buscar:",
                "oPaginate": {
                    "sFirst": "Primero",
                    "sLast":"Último",
                    "sNext":"Siguiente",
                    "sPrevious": "Anterior"
                 },
                 "sProcessing":"Procesando...",
            },*/
		//para usar los botones
		order:[[3,'asc']],
		responsive: "true",
		dom: 'Bfrtilp',
		buttons:[
			{
				extend:    'excelHtml5',
				text:      '<i class="fa fa-table"></i> ',
				titleAttr: 'Export to Excel',
				className: 'btn btn-success'
			},
			{
				extend:    'print',
				text:      '<i class="fa fa-print"></i> ',
				titleAttr: 'Print',
				className: 'btn btn-info'
			},
			{
				extend:    'pdfHtml5',
				text:      '<i class="fa fa-list-alt"></i> ',
				titleAttr: 'Export to PDF',
				className: 'btn btn-primary'
			},
		]
	});
	/*$('.mydatatable tfoot th').each( function(){
		var title = $(this).text();
		$(this).html('<input type="text" placeholder="Search '+title+'" />');
	});
	table.columns().every(function(){
		var that = this;
		$('input',this.footer()).on('keyup change', function(){
			if ( that.search()!==this.value){
				that.search( this.value ).draw();
			}
		});
	});*/
});
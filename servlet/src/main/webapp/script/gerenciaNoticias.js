gerenciaNoticias = {
	listar : function() {
		$.when($.getJSON('WEB-INF/test_doT.json'),
				$.ajax({
					  url:'pages/templatenoticia.html',
					  type: "GET",
					  dataType: 'html',
					})).done(
				function(resultadoDados, resultadoTemplate) {

					for (index = 0; index < resultadoDados.length; ++index) {
						$(".noticias").append(
								doT.template(resultadoTemplate[2].responseText)(resultadoDados[0][index]));
					}
				});
	}
};

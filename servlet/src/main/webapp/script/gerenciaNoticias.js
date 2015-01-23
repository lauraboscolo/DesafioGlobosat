gerenciaNoticias = {
	listar : function() {
		$.when($.getJSON('WEB-INF/test_doT.json'),
				$.get('pages/templatenoticia.html')).done(
				function(resultadoDados, resultadoTemplate) {

					for (index = 0; index < resultadoDados.length; ++index) {
						$(".noticias").append(
								doT.template(resultadoTemplate[2].responseText)(resultadoDados[0][index]));
					}
				});
	}
};

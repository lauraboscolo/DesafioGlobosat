var array = [
    {lema: "EXCLUSIVIDADE", participacao: "0.12", idade: 42, carreira: "EMPRESARIO", classe: "A"},
 	{lema: "EXCLUSIVIDADE", participacao: "0.12", idade: 58, carreira: "EXECUTIVO", classe: "A"},
 	{lema: "INOVACAO", participacao: "0.17", idade: 30, carreira: "TECNOLOGIA", classe: "B"},
 	{lema: "MODA", participacao: "0.3", idade: 31, carreira: "FOTOGRAFO", classe: "B"},
 	{lema: "MODA", participacao: "0.3", idade: 34, carreira: "FOTOGRAFO", classe: "B"},
 	{lema: "SUSTENTABILIDADE", participacao: "0.41", idade: 27, carreira: "UNIVERSITARIO", classe: "B"},
 	{lema: "EXCLUSIVIDADE", participacao: "0.12", idade: 54, carreira: "EMPRESARIO", classe: "A"},
 	{lema: "MODA", participacao: "0.3", idade: 31, carreira: "FOTOGRAFO", classe: "B"},
 	{lema: "INOVACAO", participacao: "0.17", idade: 26, carreira: "TECNOLOGIA", classe: "B"},
 	{lema: "MODA", participacao: "0.3", idade: 28, carreira: "PUBLICIDADE", classe: "B"},
 	{lema: "EXCLUSIVIDADE", participacao: "0.12", idade: 38, carreira: "EXECUTIVO", classe: "A"},
 	{lema: "MODA", participacao: "0.3", idade: 26, carreira: "FOTOGRAFO", classe: "B"},
 	{lema: "EXCLUSIVIDADE", participacao: "0.12", idade: 41, carreira: "CONSULTORIA", classe: "A"},
 	{lema: "SUSTENTABILIDADE", participacao: "0.41", idade: 32, carreira: "PROFESSOR", classe: "B"},
 	{lema: "SUSTENTABILIDADE", participacao: "0.41", idade: 18, carreira: "PROFESSOR", classe: "B"},
 	{lema: "SUSTENTABILIDADE", participacao: "0.41", idade: 28, carreira: "ASSISTENT", classe: "B"},
 	{lema: "SUSTENTABILIDADE", participacao: "0.41", idade: 34, carreira: "PROFESSOR", classe: "B"},
 	{lema: "SUSTENTABILIDADE", participacao: "0.41", idade: 18, carreira: "PROFESSOR", classe: "B"},
 	{lema: "EXCLUSIVIDADE", participacao: "0.12", idade: 59, carreira: "EXECUTIVO", classe: "A"},
 	{lema: "MODA", participacao: "0.3", idade: 32, carreira: "FOTOGRAFO", classe: "B"}
];

function visitor() {
	var p = next();
	$.post("noticias", {caracteristicas: JSON.stringify(p)}, function(data) {
		$.get("pages/templatenoticia.html", function(template){
			for (index = 0; index < data.length; ++index) {
					$(".noticias").append(
							doT.template(template)(data[index]));
			}
			
			$('.linkNoticia').unbind('click').click(function(){
				$.ajax({
					  method: "PUT",
					  url: "noticias",
					  data: {idAssunto : $('.linkNoticia').data('assunto') ,
						   	 caracteristicas : JSON.stringify(p) }
					});
			});
		}, "html");
		
	},"json");
	
	
}

function next() {
	return array[Math.floor(Math.random() * array.length)];
}
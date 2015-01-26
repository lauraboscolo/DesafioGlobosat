CREATE TABLE perfis (
  id		integer PRIMARY KEY,
  nome		varchar(20) NOT NULL
);

CREATE TABLE assuntos (
  id		integer PRIMARY KEY,
  assunto	varchar(20) NOT NULL
);

CREATE TABLE perfis_assuntos (
  id		integer PRIMARY KEY,
  id_perfil	integer NOT NULL,
  id_assunto	integer NOT NULL
);

CREATE TABLE noticias (
  id		integer PRIMARY KEY,
  id_assunto	integer NOT NULL,
  noticia	varchar(255) NOT NULL,
  link		varchar(255) NOT NULL
);

INSERT INTO perfis(id, nome) values(1, 'EXPLORADORES');
INSERT INTO perfis(id, nome) values(2, 'VENCEDORES');
INSERT INTO perfis(id, nome) values(3, 'SEGUIDORES');
INSERT INTO perfis(id, nome) values(4, 'TRANSFORMADORES');

INSERT INTO assuntos(id, nome) values(1, 'TECNOLOGIA');
INSERT INTO assuntos(id, nome) values(2, 'CELEBRIDADES');
INSERT INTO assuntos(id, nome) values(3, 'POLITICA');
INSERT INTO assuntos(id, nome) values(4, 'MUSICA');
INSERT INTO assuntos(id, nome) values(5, 'GASTRONOMIA');
INSERT INTO assuntos(id, nome) values(6, 'CARROS');
INSERT INTO assuntos(id, nome) values(7, 'VIAGENS');
INSERT INTO assuntos(id, nome) values(8, 'EDUCACAO');

INSERT INTO perfis_assuntos(id, id_perfil, id_assunto) values(1, 1, 1);
INSERT INTO perfis_assuntos(id, id_perfil, id_assunto) values(2, 1, 7);
INSERT INTO perfis_assuntos(id, id_perfil, id_assunto) values(4, 2, 1);
INSERT INTO perfis_assuntos(id, id_perfil, id_assunto) values(5, 2, 3);
INSERT INTO perfis_assuntos(id, id_perfil, id_assunto) values(6, 2, 5);
INSERT INTO perfis_assuntos(id, id_perfil, id_assunto) values(7, 2, 6);
INSERT INTO perfis_assuntos(id, id_perfil, id_assunto) values(8, 2, 7);
INSERT INTO perfis_assuntos(id, id_perfil, id_assunto) values(9, 3, 2);
INSERT INTO perfis_assuntos(id, id_perfil, id_assunto) values(10, 3, 4);
INSERT INTO perfis_assuntos(id, id_perfil, id_assunto) values(11, 3, 7);
INSERT INTO perfis_assuntos(id, id_perfil, id_assunto) values(12, 4, 5);
INSERT INTO perfis_assuntos(id, id_perfil, id_assunto) values(13, 4, 7);
INSERT INTO perfis_assuntos(id, id_perfil, id_assunto) values(14, 4, 8);

INSERT INTO noticias(id, id_assunto, noticia, link) VALUES(1, 1, 'Nova patente do Google torna navegação privada automática na web', 'http://www.techtudo.com.br/noticias/noticia/2015/01/nova-patente-do-google-torna-navegacao-privada-automatica-no-chrome.html');
INSERT INTO noticias(id, id_assunto, noticia, link) VALUES(2, 1, 'Gmail lança função soneca para que e-mails chatos não acordem usuários', 'http://www.techtudo.com.br/noticias/noticia/2015/01/gmail-lanca-funcao-soneca-no-inbox.html');
INSERT INTO noticias(id, id_assunto, noticia, link) VALUES(3, 1, 'Zuck vai à Colômbia, lança Internet.org e compara wearables a tijolões', 'http://www.techtudo.com.br/noticias/noticia/2015/01/zuckerberg-vai-colombia-lanca-internetorg-e-critica-wearables-atuais.html');
INSERT INTO noticias(id, id_assunto, noticia, link) VALUES(4, 1, 'Como personalizar seu status de usuário no Hangouts para iPhone', 'http://www.techtudo.com.br/dicas-e-tutoriais/noticia/2015/01/como-personalizar-seu-status-de-usuario-no-hangouts-para-iphone.html');
INSERT INTO noticias(id, id_assunto, noticia, link) VALUES(5, 2, 'Gisele Bündchen usa joias de mais de R$ 16 mil em evento de beleza', 'http://ego.globo.com/moda/noticia/2015/01/gisele-bundchen-usa-joias-de-mais-de-r-16-mil-em-evento-de-beleza.html');
INSERT INTO noticias(id, id_assunto, noticia, link) VALUES(6, 2, 'Maria Ribeiro usa vestido estampado para lançar livro', 'http://ego.globo.com/moda/noticia/2015/01/look-do-dia-maria-ribeiro-usa-vestido-estampado-para-lancar-livro.html');
INSERT INTO noticias(id, id_assunto, noticia, link) VALUES(7, 2, 'Famosas trocam os vestidos por looks masculinos', 'http://ego.globo.com/moda/galeria/2015/01/e-tendencia-famosas-trocam-os-vestidos-por-looks-masculinos.html');
INSERT INTO noticias(id, id_assunto, noticia, link) VALUES(8, 3, 'Dilma se reúne no Palácio do Planalto presidentes de bancos públicos', 'http://g1.globo.com/politica/noticia/2015/01/dilma-reune-no-palacio-do-planalto-presidentes-de-bancos-publicos.html');
INSERT INTO noticias(id, id_assunto, noticia, link) VALUES(9, 3, 'CNJ autoriza aumento para juízes sem necessidade de lei estadual', 'http://g1.globo.com/politica/noticia/2015/01/cnj-autoriza-aumento-para-juizes-sem-necessidade-de-lei-estadual.html');
INSERT INTO noticias(id, id_assunto, noticia, link) VALUES(10, 3, 'Executiva Nacional do PMDB anuncia apoio oficial a Eduardo Cunha', 'http://g1.globo.com/politica/noticia/2015/01/executiva-nacional-do-pmdb-anuncia-apoio-oficial-eduardo-cunha.html');
INSERT INTO noticias(id, id_assunto, noticia, link) VALUES(11, 3, 'Cerveró transferiu bens após início da investigação da Lava Jato, diz MP', 'http://g1.globo.com/politica/operacao-lava-jato/noticia/2015/01/cervero-transferiu-bens-apos-inicio-da-investigacao-da-lava-jato-diz-mp.html');
INSERT INTO noticias(id, id_assunto, noticia, link) VALUES(12, 4, 'Queens of the Stone Age toca no dia do System of a Down no Rock in Rio', 'http://g1.globo.com/musica/rock-in-rio/2015/noticia/2014/12/queens-stone-age-toca-no-dia-do-system-down-no-rock-rio.html');
INSERT INTO noticias(id, id_assunto, noticia, link) VALUES(13, 4, 'Paul repete fórmula, faz homenagens e agrada público em show em Brasília', 'http://g1.globo.com/distrito-federal/musica/noticia/2014/11/paul-repete-formula-faz-homenagens-e-agrada-publico-em-show-em-brasilia.html');
INSERT INTO noticias(id, id_assunto, noticia, link) VALUES(14, 4, 'Arctic Monkeys fazem show pesado e sem firula para Anhembi lotado', 'http://g1.globo.com/sao-paulo/musica/noticia/2014/11/arctic-monkeys-fazem-show-pesado-e-sem-firula-para-anhembi-lotado.html');
INSERT INTO noticias(id, id_assunto, noticia, link) VALUES(15, 4, 'One Direction, Jennifer Lopez e Iggy brilham no American Music Awards', 'http://g1.globo.com/musica/noticia/2014/11/one-direction-jennifer-lopez-e-iggy-brilham-no-american-music-awards.html');
INSERT INTO noticias(id, id_assunto, noticia, link) VALUES(16, 4, 'Circuito Banco do Brasil em SP', 'http://g1.globo.com/sao-paulo/musica/fotos/2014/11/fotos-circuito-banco-do-brasil-em-sp.html');
INSERT INTO noticias(id, id_assunto, noticia, link) VALUES(17, 5, 'Croque monsieur de frigideira', 'http://gnt.globo.com/receitas/receitas/croque-monsieur-de-frigideira.htm');
INSERT INTO noticias(id, id_assunto, noticia, link) VALUES(18, 5, 'Banana dourada com merengue suíço', 'http://gnt.globo.com/receitas/receitas/banana-dourada-com-merengue-suico.htm');
INSERT INTO noticias(id, id_assunto, noticia, link) VALUES(19, 5, 'Minidonuts com chocolate', 'http://gnt.globo.com/receitas/receitas/minidonuts-com-chocolate.htm');
INSERT INTO noticias(id, id_assunto, noticia, link) VALUES(20, 5, 'Bacalhau desfiado com pão molhado no azeite: Pappa di Baccalà', 'http://gnt.globo.com/receitas/receitas/pappa-di-baccala-bacalhau-desfiado-com-pao-molhado-no-azeite.htm');
INSERT INTO noticias(id, id_assunto, noticia, link) VALUES(21, 6, 'Honda diz que HR-V começará abaixo dos R$ 80 mil', 'http://g1.globo.com/carros/noticia/2015/01/honda-diz-que-hr-v-comecara-abaixo-dos-r-80-mil.html');
INSERT INTO noticias(id, id_assunto, noticia, link) VALUES(22, 6, 'Com foco no consumo, Detroit mantém 'tradição' de grandes picapes', 'http://g1.globo.com/carros/noticia/2015/01/com-foco-no-consumo-detroit-mantem-tradicao-de-grandes-picapes.html');
INSERT INTO noticias(id, id_assunto, noticia, link) VALUES(23, 6, '27 fatos em 27 anos de reinado do Gol', 'http://g1.globo.com/carros/noticia/2015/01/27-fatos-em-27-anos-de-reinado-do-gol.html');
INSERT INTO noticias(id, id_assunto, noticia, link) VALUES(24, 6, 'Triumph faz recall de 1.436 motos no Brasil', 'http://g1.globo.com/carros/motos/noticia/2015/01/triumph-faz-recall-de-1436-motos-no-brasil.html');
INSERT INTO noticias(id, id_assunto, noticia, link) VALUES(25, 6, 'Falta de crédito faz vendas de motos caírem pelo 3º ano seguido', 'http://g1.globo.com/motos/blog/dicas-de-motos/1.html');
INSERT INTO noticias(id, id_assunto, noticia, link) VALUES(26, 6, 'Vale a pena completar o óleo do motor? Tire dúvidas', 'http://g1.globo.com/carros/blog/oficina-do-g1/1.html');
INSERT INTO noticias(id, id_assunto, noticia, link) VALUES(27, 7, 'Empresa lança voo internacional mais curto do mundo', 'http://g1.globo.com/turismo-e-viagem/noticia/2015/01/empresa-lanca-voo-internacional-mais-curto-do-mundo.html');
INSERT INTO noticias(id, id_assunto, noticia, link) VALUES(28, 7, 'Confira dicas para conseguir um upgrade de classe em voo', 'http://g1.globo.com/turismo-e-viagem/noticia/2013/05/confira-dicas-para-conseguir-um-upgrade-de-classe-em-voo.html');
INSERT INTO noticias(id, id_assunto, noticia, link) VALUES(29, 7, 'Veja dicas para economizar e não se meter em roubadas no 1º 'mochilão'', 'http://g1.globo.com/turismo-e-viagem/noticia/2012/12/veja-dicas-para-economizar-e-nao-se-meter-em-roubadas-no-1-mochilao.html');
INSERT INTO noticias(id, id_assunto, noticia, link) VALUES(30, 7, 'Turistas adaptam roteiro de viagem para levar bichos de estimação', 'http://g1.globo.com/turismo-e-viagem/noticia/2013/03/turistas-adaptam-roteiro-de-viagem-para-levar-bichos-de-estimacao.html');
INSERT INTO noticias(id, id_assunto, noticia, link) VALUES(31, 7, 'Veja dicas para evitar transtornos com a bagagem em viagens de avião', 'http://g1.globo.com/turismo-e-viagem/noticia/2012/11/veja-dicas-para-evitar-transtornos-com-bagagem-em-viagens-de-aviao.html');
INSERT INTO noticias(id, id_assunto, noticia, link) VALUES(32, 8, 'Estudantes nota 1.000 na redação do Enem revelam o segredo do sucesso', 'http://g1.globo.com/educacao/enem/2014/noticia/2015/01/estudantes-nota-1000-na-redacao-do-enem-revelam-o-segredo-do-sucesso.html');
INSERT INTO noticias(id, id_assunto, noticia, link) VALUES(33, 8, 'Detentos de Pernambuco e Ceará terão de refazer o Enem', 'http://g1.globo.com/educacao/enem/2014/noticia/2015/01/detentos-do-pernambuco-e-ceara-terao-de-refazer-o-enem.html');
INSERT INTO noticias(id, id_assunto, noticia, link) VALUES(34, 8, 'Mais de 200 mil redações do Enem foram canceladas por fuga ao tema', 'http://g1.globo.com/educacao/enem/2014/noticia/2015/01/mais-de-200-mil-redacoes-do-enem-foram-canceladas-por-fuga-ao-tema.html');



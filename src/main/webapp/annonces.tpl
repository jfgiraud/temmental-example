<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Cache-Control" content="no-cache">
        <meta http-equiv="Pragma" content="no-cache">
        <meta http-equiv="Expires" content="0">
        
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="css/bootstrap-multiselect.css" rel="stylesheet" type="text/css"/>
        <link href="css/prettify.css" rel="stylesheet" type="text/css"/>
        <link href="css/paginator3000.css" rel="stylesheet" type="text/css"/>

        <script src="js/jquery-2.1.1.min.js"></script>
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
        <script src="js/bootstrap-multiselect.js" type="text/javascript"></script>
        <script src="js/prettify.js" type="text/javascript"></script>
        <script src="js/paginator3000.js"></script>

        <title>{{title}}</title>

        <style type="text/css">
            span.small { font-size: 80%; }
            span.huge { font-size: 120%; }
            #commune { max-width: 50px; }
            .logo {
                padding-top: 0;
            }
            img.preview {
                width: 150px;
                height: 120px;
            }
        </style>

        <script> 
            $(function() {
                $('#commune').multiselect({
                    nonSelectedText: 'Aucun filtre'
                });
                $('#agence').multiselect({
                    nonSelectedText: 'Aucun filtre',
                    enableFiltering: true,
                    filterBehavior: 'text',
                    maxHeight: 300,
	            buttonWidth: '200px'
                });
                $('#site').multiselect({
                    nonSelectedText: 'Aucun filtre',
                    enableFiltering: false,
                    filterBehavior: 'text',
                    maxHeight: 300,
	            buttonWidth: '150px'
                });
	        $('#btn-submit').click(function () {
                    $('#page').attr('value', '1');
                });
            });
        </script>
    </head>
    <body id="body">

        <header class="navbar navbar-static-top bs-docs-nav" id="top" role="banner">
            <nav class="navbar navbar-default navbar-inverse navbar-fixed-top" role="navigation">
	        <div class="container-fluid">
	            <!-- Brand and toggle get grouped for better mobile display -->
	            <div class="navbar-header">
	                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
	                </button>
	                <a class="navbar-brand logo" href="/"><img src="{{url_for('static',filename='images/'+page_logo)}}"/></a>
	            </div>
                    
	            <!-- Collect the nav links, forms, and other content for toggling -->
	            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                        <form class="navbar-form navbar-left" action="{{uri}}" id="annonces" name="annonces" method="post">
	                    <div class="form-group">
		                <div class="input-group">
                                    <div class="input-group-addon">Sites</div>
				    <input id="page" name="page" type="hidden" value="{{current_page}}"/>
		                    <select name="site" id="site" multiple="multiple">
 	                                {% for site in sites %}
    		                        <option value="{{site.site}}"{% if site.site in sites_selectionnes %} selected="selected"{% endif %}>{{site.site|default('Tous',true)}}</option>
                                        {% endfor %}
		                    </select>
		                </div>
                            </div>
	                    <div class="form-group">
		                <div class="input-group">
                                    <div class="input-group-addon">Agences</div>
				    <input id="page" name="page" type="hidden" value="{{current_page}}"/>
		                    <select name="agence" id="agence" multiple="multiple">
 	                                {% for agence in agences %}
    		                        <option value="{{agence.agence}}"{% if agence.agence in agences_selectionnees %} selected="selected"{% endif %}>{{agence.agence|default('Autre',true)}}</option>
                                        {% endfor %}
		                    </select>
		                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <div class="input-group-addon"><span class="small">&euro;</span></div>
  		                    <input name="prixmin" type="text" size="8" value="{{ prixmin|round|int if prixmin != None else "" }}" class="form-control" placeholder="min">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <div class="input-group-addon"><span class="huge">&euro;</span></div>
                                    <input name="prixmax" type="text" size="8" value="{{ prixmax|round|int if prixmax != None else "" }}" class="form-control" placeholder="max">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <div class="input-group-addon"><span class="small">m&#178;</span></div>
		                    <input name="surfacemin" type="text" size="4" value="{{ surfacemin|round|int if surfacemin != None else "" }}" class="form-control" placeholder="min">
		                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <div class="input-group-addon"><span class="huge">m&#178;</span></div>
		                    <input name="surfacemax" type="text" size="4" value="{{ surfacemax|round|int if surfacemax != None else "" }}" class="form-control" placeholder="max">
		                </div>
                            </div>
                            <div class="form-group">
		                <div class="input-group">
                                    <div class="input-group-addon">Tri</div>
		                    <select name="tri" class="form-control">
		                        <option value="1"{% if tri == '1' %} selected="selected"{% endif %}>dateheure &#9660; prix &#9650; surface &#9660;</option>
		                        <option value="2"{% if tri == '2' %} selected="selected"{% endif %}>prix &#9650;</option>
		                        <option value="3"{% if tri == '3' %} selected="selected"{% endif %}>prix &#9660;</option>
		                        <option value="4"{% if tri == '4' %} selected="selected"{% endif %}>surface &#9650;</option>
		                        <option value="5"{% if tri == '5' %} selected="selected"{% endif %}>surface &#9660;</option>
		                        <option value="6"{% if tri == '6' %} selected="selected"{% endif %}>date insertion &#9650;</option>
		                    </select>
		                </div>
                            </div>
	                    <div class="form-group">
		                <div class="input-group">
                                    <div class="input-group-addon">Communes</div>
		                    <select name="commune" id="commune" multiple="multiple">
 	                                {% for commune in communes %}
  		                        <option value="{{commune.commune}}"{% if commune.commune in communes_selectionnees %} selected="selected"{% endif %}>{{commune.commune|fmt('commune')}}</option>
                                        {% endfor %}
		                    </select>
		                </div>
                            </div>
	                    <button id="btn-submit" type="submit" class="btn btn-success">Rechercher</button> 
	                    <a href="{{uril}}" class="btn btn-danger">Réinitialiser</a>
                        </form>
		        {% if stats_dispos %}
	                <ul class="nav navbar-nav navbar-right">
                            <li class="dropdown">
		                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Statistiques <span class="caret"></span></a>
		                <ul class="dropdown-menu" role="menu">
		                    <li><a href="statistiques/toutes" data-toggle="modal" data-target="#myModal">Toutes communes</a></li>
  		                    <li class="divider"></li>
                                    {% for commune in communes %}
                                    <li><a href="statistiques/{{commune.commune|urlencode}}" data-toggle="modal" data-target="#myModal">{{commune.commune|fmt('commune')}}</a></li>
                                    {% endfor %}
		                </ul>
                            </li>
	                </ul>
                        {% endif %}
	            </div><!-- /.navbar-collapse -->
	        </div><!-- /.container-fluid -->
            </nav>
        </header>

        <div class="table-responsive">
            <table class="table table-bordered">
                <thead>
                    <tr>
  	                <th>date</th>
                    <th>photo</th>
	                <th>description</th>
	                <th>surface</th>
	                <th>prix</th>
	                <th>commune</th> 
	            </tr>
	        </thead>
	        <tbody>
	            {% for annonce in annonces %}
	            <tr>
	                <td>{% if annonce.aujourdhui %}<b>{% endif %}
		                {{annonce.dateheure_libelle}}
		                {% if annonce.aujourdhui %}</b>{% endif %}
	                </td>
	                <td class="photo">
	                    {% if annonce.image_url %}
	                    <a href="{{annonce.url}}"><img class="preview" src="{{annonce.image_url}}"/></a>
	                    {% else %}
	                    <a href="{{annonce.url}}"><img class="preview" src="{{url_for('static',filename='images/link.jpg')}}"/></a>
	                    {% endif %}
                        </td>
	                <td><p><b>{{annonce.titre}}</b></p>
                            <p>{{annonce.description}}</p>
                            <p class="text-right">
			        {% if annonce.site != '' %}{{annonce.site|title}}&nbsp;&nbsp;{% endif %}
                                {% if annonce.agence != '' %}{{annonce.agence|title}}&nbsp;&nbsp;{% endif %}
                                {% if annonce.prix_moyen %}{{annonce.prix_moyen|fmt(precision)}} €<sub>/m<sup>2</sup></sub>&nbsp;&nbsp;
                                {% set (prixmin, prixmax, prixmoy, pct, color) = annonce.prix_moyen|price_indicator(type_transaction,type_bien,annonce.commune) %}
		                {% if stats_dispos %}
		                <font color="{{color}}"><span title="min: {{prixmin}} max: {{prixmax}} moy: {{prixmoy}} ({{pct|fmt('pct')}}%)" class="glyphicon glyphicon-flag"/></font>&nbsp;&nbsp;
                                {% endif %}
                                {% endif %}
                                </p>
	                </td>
	                <td class="text-center">{{annonce.surface|fmt('area') if (annonce.surface is defined and annonce.surface != None) else ""}}</td>
	                <td class="text-center">{{annonce.prix|fmt(precision) if (annonce.prix is defined and annonce.prix != None) else ""}}</td>
	                <td>{% if stats_dispos %}<span href="statistiques/{{annonce.commune|urlencode}}?prix_moyen={{annonce.prix_moyen}}" data-toggle="modal" data-target="#myModal">{% endif %}{{annonce.commune|fmt('commune')}}{% if stats_dispos %}</span>{% endif %}</td> 
	            </tr>
	            {% endfor %}
	        </tbody>
            </table>


                                    ~$communes:'s2m#for~
                                       ~$label~
                                    ~#for~


<!--
            $(document).bind('ready', function () {
                $('#paginator1').paginator({
                    pagesTotal: {{nb_pages}},
                    pagesSpan: 11,
                    pageCurrent: {{current_page}},
                    baseUrl: '{{request_url}}',
                    clickHandler: function(page) {
		        if (page) {
                            $('#page').attr('value', ''+page);
			    $('#annonces').submit();
                        }
                        return false;
                    }
                });
            });
            // -->
           
           


        </div>

{% if nb_pages > 1 %}
	    <div class="paginator" id="paginatorId"></div>
	    <script type="text/javascript">
	    paginatorId = new Paginator(
	        "paginatorId", 
		{{nb_pages}}, 
		10, 
		{{current_page}},
                function(page) {
                    return page;
                }, 
                function() {
                    $('#paginatorId a').click(function () { 
                        var p = $(this).attr('href');
                        $('#page').attr('value', ''+p);
                        $('#annonces').submit();
                        return false;
                    });
                }
	      );
	    </script>
{% endif %}

	    <script type="text/javascript">
	    $( "#body" ).keypress(function( event ) {
	        if ( event.keyCode == 37 ) {
	            $('#page').attr('value', '{{previous_page}}');
                    $('#annonces').submit();
  	            event.preventDefault();
	        } else if ( event.keyCode == 39 ) {
	            $('#page').attr('value', '{{next_page}}');
                    $('#annonces').submit();
  	            event.preventDefault();
	        }
            });
	    </script>



        
        <script>  
            $(function() { 
                $('#myModal').on('hidden.bs.modal', function () {
                    $('#myModal').removeData('bs.modal')
                })}
            )
        </script> 

        <div id="myModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->

    </body>
</html>

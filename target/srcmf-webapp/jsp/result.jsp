<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns:h="http://java.sun.com/jsf/html">
<head>
<link rel="stylesheet" href="../css/bootstrap.min.css">
<script src="../js/bootstrap.min.js"></script>
<script src="../js/handsontable-master/dist/handsontable.full.js"></script>
<link rel="stylesheet" media="screen"
	href="../js/handsontable-master/dist/handsontable.full.css">
<script src="../js/adamwdraper-Numeral-js-7487acb/min/numeral.min.js"></script>
<script src="../js/moment.js"></script>
<script
	src="../js/handsontable-master/dist/zeroclipboard/ZeroClipboard.js">
	
</script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
        
</head>

<body>

	<div class="container-fluid">

		<nav class="navbar navbar-inverse">
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" href="#">SyntacticReferenceCorpusOfMedievalFrench-NLP</a>
				</div>
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">Home</a></li>
					<li><a href="#">Apply texts models</a></li>
					<li><a href="#">Apply full model</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#"><span class="glyphicon glyphicon-user"></span>
							Sign Up</a></li>
					<li><a href="#"><span class="glyphicon glyphicon-log-in"></span>
							Login</a></li>
				</ul>
			</div>
		</nav>

		<div class="jumbotron">
			<h1>Apply texts models</h1>
			<p>Here you can choose a model based on one text of the SRCMF, to apply on another one.
			You can also import a file to test models on another corpus.</p>
		</div>

		<div class="row">
			<div class="col-sm-4">
				
			</div>
			<div class="col-sm-4">

				<!-- <form action="ooo" method="post"> -->
					<input id="button" class="btn btn-primary" type="submit" name="button1" value="Launch API" />
				<!-- </form> -->

			</div>
			<div class="col-sm-4"></div>
		</div>


	</div>
</body>
<script type="text/javascript">
document.getElementById("button").addEventListener("click", displayLoad);
function displayLoad() {
	var opts = {
			  lines: 11 // The number of lines to draw
			, length: 56 // The length of each line
			, width: 31 // The line thickness
			, radius: 64 // The radius of the inner circle
			, scale: 1 // Scales overall size of the spinner
			, corners: 1 // Corner roundness (0..1)
			, color: '#000' // #rgb or #rrggbb or array of colors
			, opacity: 0.2 // Opacity of the lines
			, rotate: 0 // The rotation offset
			, direction: 1 // 1: clockwise, -1: counterclockwise
			, speed: 1.1 // Rounds per second
			, trail: 58 // Afterglow percentage
			, fps: 20 // Frames per second when using setTimeout() as a fallback for CSS
			, zIndex: 2e9 // The z-index (defaults to 2000000000)
			, className: 'spinner' // The CSS class to assign to the spinner
			, top: '49%' // Top position relative to parent
			, left: '50%' // Left position relative to parent
			, shadow: true // Whether to render a shadow
			, hwaccel: false // Whether to use hardware acceleration
			, position: 'absolute' // Element positioning
			}
			var target = document.getElementById('jumbotron')
			var spinner = new Spinner(opts).spin(target);
}
/* $(document).on("click", "#button", function() { // When HTML DOM "click" event is invoked on element with ID "somebutton", execute the following function...
   $.get("/loading", function(responseText) {   // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
   	window.location.replace("/jsp/loading.jsp");          // Locate HTML DOM element with ID "somediv" and set its text content with the response text.
   });
}); */
$(document).on("click", "#button", function() { // When HTML DOM "click" event is invoked on element with ID "somebutton", execute the following function...
    window.location.replace("/jsp/loading.jsp");
	 $.get("/loading?action=1on1");
});
</script>
</html>
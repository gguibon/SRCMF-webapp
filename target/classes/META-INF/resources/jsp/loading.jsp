<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns:h="http://java.sun.com/jsf/html">
<head>
<link rel="stylesheet" href="../css/bootstrap.min.css">
<script src="../js/bootstrap.min.js"></script>
<script src="../js/handsontable-master/dist/handsontable.full.js"></script>
<script src="../js/spin.min.js"></script>
<link rel="stylesheet" media="screen"
	href="../js/handsontable-master/dist/handsontable.full.css">
<script src="../js/adamwdraper-Numeral-js-7487acb/min/numeral.min.js"></script>
<script src="../js/moment.js"></script>
<script
	src="../js/handsontable-master/dist/zeroclipboard/ZeroClipboard.js">
	
</script>

</head>

<body>
	<div id="jumbotron">
		<%-- <c:redirect url="loading?mode=start" /> --%>
	</div>

</body>
<script type="text/javascript">
	var opts = {
		lines : 11 // The number of lines to draw
		,
		length : 56 // The length of each line
		,
		width : 31 // The line thickness
		,
		radius : 64 // The radius of the inner circle
		,
		scale : 1 // Scales overall size of the spinner
		,
		corners : 1 // Corner roundness (0..1)
		,
		color : '#000' // #rgb or #rrggbb or array of colors
		,
		opacity : 0.2 // Opacity of the lines
		,
		rotate : 0 // The rotation offset
		,
		direction : 1 // 1: clockwise, -1: counterclockwise
		,
		speed : 1.1 // Rounds per second
		,
		trail : 58 // Afterglow percentage
		,
		fps : 20 // Frames per second when using setTimeout() as a fallback for CSS
		,
		zIndex : 2e9 // The z-index (defaults to 2000000000)
		,
		className : 'spinner' // The CSS class to assign to the spinner
		,
		top : '49%' // Top position relative to parent
		,
		left : '50%' // Left position relative to parent
		,
		shadow : true // Whether to render a shadow
		,
		hwaccel : false // Whether to use hardware acceleration
		,
		position : 'absolute' // Element positioning
	}
	var target = document.getElementById('jumbotron')
	var spinner = new Spinner(opts).spin(target);
	
	
	//var checkStatusTimeout;

	/* function checkStatus(){
	    $.ajax({
	        type: "GET",
	        url: "./getstatus",
	        dataType: "text",
	        contentType: "application/json; charset=utf-8",
	        success: function(status){
	            if (status == 'FINISHED'){
	                clearTimeout(checkStatusTimeout);
	                // execute whatever you want here
	                $.get("/ooo");
	            }
	        }
	    });

	    checkStatusTimeout = window.setTimeout("checkStatus()", 5000);
	} */
</script>
</html>
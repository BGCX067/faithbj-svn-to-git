/***
 *	CAIJINGLING Article JavaScript
 *
 *	Copyright (c) 2008 CAIJINGLING. All rights reserved.
 **/
function initialize() { 
   var myLatlng = new google.maps.LatLng(40.0205,116.4450);
  var myOptions = {
    zoom: 12,
    center: myLatlng,
    mapTypeId: google.maps.MapTypeId.ROADMAP
  };
  var map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);
    
  var marker = new google.maps.Marker({
      position: myLatlng, 
      map: map, 
      title:"菜精灵农园就在这里！"
  });   
		
} 
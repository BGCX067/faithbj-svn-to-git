<html>
<head>
	<title>My Cart</title>
</head>
<body>
<h2> 
	My Cart
</h2>
<br/>

<form name="input" modelAttriubte = "order" action="pay_request" method="post">

订单描述: <input type="text" name="paymentConfigName"/>

<br/>
订单金额: <input type="text" name="paymentFee"/>
<br/>
附&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;言: <input type="text" name="memo"/>
<br/>
<input type="submit" value="结算" />
</form> 

<br/>
</body>
</html>
function getQueryHtmlNameById(sid)
{
	var id = parseInt(sid);
	var obj = new Object();
	obj.html = "queryResult.html";
	obj.tableLayout = "QueryResult.json";
	if(id >=2 && id<=24)
	{
		
		switch(id)
		{
			case 2:
				
				obj.title = "城区支行";
				break;
			case 3:
				
				obj.title = "远郊支行";
				break;
			case 5:
				
				obj.title = "董事会办公室";
				break;
		}
	}
	if(id >=25 && id<=31)
	{
		switch(id)
		{
			case 25:
				obj.title = "支行行长";
				break;
			case 26:
				obj.title = "部门经理";
				break;
			case 28:
				obj.title = "客户经理";
				break;
			case 29:
				obj.title = "柜员";
				break;
			case 30:
				obj.title = "部门员工";
				break;
			case 31:
				obj.title = "劳务派遣";
				break;
		}
	}
	return obj;
}
function getKaoHeHtmlNameById(sid)
{
	var id = parseInt(sid);
	var obj = new Object();
	if(id >=2 && id<=24)
	{
		obj.html = "departmentKaohe.html";
		switch(id)
		{
			case 2:
				obj.tableLayout = "ChengQuZhiHang.json";
				obj.title = "城区支行";
				break;
			case 3:
				obj.tableLayout = "YuanjiaoZhiHang.json";
				obj.title = "远郊支行";
				break;
			case 5:
				obj.tableLayout = "DongShiHuiBanGongShi.json";
				obj.title = "董事会办公室";
				break;
		}
	}
	if(id == 25)
	{
		obj.html = "importData.html";	
		obj.title = "员工考核数据";		
	}
	return obj;
}
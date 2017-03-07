 <!DOCTYPE>  
   <html>  
    <head>  
   <meta http-equiv=Content-Type content="text/html; charset=utf-8">  
   <title>user.ftl</title>  
    </head>  
   <body>  
     ${user.name}  
     ${user.pwd}  
     <hr>
     <#list [1,2,3,4] as index>
     <span>${index}</span>
	</#list>
	<hr>
	<#list 1..4 as index>
   	<span>${index}</span>
	</#list>
    <#list nums as index>
     <span>${index}</span>
	</#list>
	<#list ["hello","welcome","hi"] as word>
   	 <span>${word_index+1},${word}</span></br>
	</#list>
	<hr>
	<#list users as user>
     <span>${user.name}</span>
	</#list>
	<hr>
	<select>
	<#list map?keys as key>
　　  <option value="${key}">${map[key]}</option>
    </#list>
    </select>
   </body>  
 </html>
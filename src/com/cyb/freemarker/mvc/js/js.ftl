/**
 *  @author ${author}
 *  @date ${date}
 */
var app = angular.module('app', []);
app.controller('controller', main);
var page; //当前页面对象
var path;//basePath值 http://localhost:port/projectname/
function main($scope, $q, $http) {
	page = $scope;
	path = $('#path').val();
	page.openEditView = openEditView;
	page.submit = submit;
	page.del = del${model};
	resetList();
}
/**
 *  页面列表数据初始化
 *  @author ${author}
 *  @date ${date}
 */
function resetList() {
	initPageSpilt(page.list,page);
	var url = path + "model/list.do";
	page.apply(function(){
		page.list = ajaxGet(url);
		initPageSpilt(page.list,page);
	});
	console.log(ajaxGet(url));
}
/**
 *  提交更新或者修改信息(post json 数据)
 *  @author ${author}
 *  @date ${date}
 */
function submit(model) {
	delete model['$$hashKey'];
	url = path + "${model}/add.do";
	if (page.cmd == 'mod') {
		url = path + "${model}/update.do";
	}
	var data = ajaxPostJson(url,page.${model});
	if(isLawFullRet(data)){
		page.list.push(data.t);
		$('#eidtView').modal("close");
		page.model = {};
	}
}

/**
 *  提交更新或者修改信息(post json 数据)
 *  @author ${author}
 *  @date ${date}
 */
function submit(model) {
	delete model['$$hashKey'];
	url = path + "${model}/add.do";
	if (page.cmd == 'mod') {
		url = path + "${model}/update.do";
	}
	var data = ajaxPostJson(url,page.${model});
	if(isLawFullRet(data)){
		page.list.push(data.t);
		$('#eidtView').modal("close");
		page.model = {};
	}
}

var pubObj;
function del${model}(record) {
	pubObj = record;
	$('#tip').modal({
		relatedTarget : this,
		onConfirm : function(options) {
			var url = path + "${model}/del.do?id=" + pubObj.id;
			var data = ajaxGet(url);
			if(isLawFullRet(data)){
				page.list.splice(page.list.indexOf(pubObj), 1);
				page.$apply();
				initPageSpilt(page.list,page);
				toPage(page);
			}
		},
		onCancel : function() {
		}
	});
}
function openEditView(cmd, record) {
	page.cmd = cmd;
	if (cmd == 'mod') {
		page.${model} = record;//页面变量赋值
	}else{
	  //清空页面信息
	}
	$('#eidtView').modal({
		onConfirm : function() {
			if (window.confirm('你确定要取消编辑吗？')) {
				resetList();
				$('#eidtView').modal("close");
				return true;
			} else {
				return false;
			}
		},
		closeOnConfirm : true,
		closeViaDimmer : false,
		width : $(window).width() * 0.8,
		height : Math.min($(window).height() * 0.8, 800),
	});
}

function orderEvent() {
	var up = page.direct;
	page.list.forEach(function(item) {
		return;
	});
	if (up == "0") {
		page.list.sort(function(a, b) {
			return a[page.order] < b[page.order] ? 1 : -1;
		});
	} else {
		page.list.sort(function(a, b) {
			return a[page.order] > b[page.order] ? 1 : -1;
		});
	}
}

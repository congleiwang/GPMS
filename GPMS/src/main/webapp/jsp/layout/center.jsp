<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<script type="text/javascript">
	function addTabs(opts) {
		var t = $('#layout_center_centerTabs');
		if(t.tabs('exists',opts.title)){
			t.tabs('select',opts.title);
		}else{
			t.tabs('add', opts);
		}
	}
	function serializeObject(form) {
		var o = {};
		$.each(form.serializeArray(), function(index) {
			if (o[this['name']]) {
				o[this['name']] = o[this['name']] + "," + this['value'];
			} else {
				o[this['name']] = this['value'];
			}
		});
		return o;
	};
	function timeToString(v){
		if(v){
			return new Date(parseInt(v)).toLocaleString()
		}
		return '';
	}
	function downHref(value){
		if(value){
			return "<a href=javascript:downFile('"+value+"');>"+value+"</a>";
		}
		return '';
	}
	function downFile(fileName){
		window.open("file/download?fileName="+fileName);
	}
</script>
<div id="layout_center_centerTabs" class="easyui-tabs" data-options="border:false,fit:true">
	<div title="首页"></div>
</div>
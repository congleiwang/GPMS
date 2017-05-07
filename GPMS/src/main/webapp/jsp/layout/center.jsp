<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div id="layout_center_centerTabs" class="easyui-tabs" 
	 data-options="fit:true,
	 			tools:[{
	                iconCls:'icon-cancel',
	                handler:function(){
	                    closeAllTab();
	                }
	            }],
	            onContextMenu:function(e){
	                e.preventDefault();
	                $('#tabMenu').menu('show', {
	                    left: e.pageX,
	                    top: e.pageY
	                });
	            }">
	<div title="首页">
	    <div class="easyui-layout" fit="true">
	    	<jsp:include page="msg.jsp"></jsp:include>
		    <div data-options="region:'center'">
		    	<div style="font-size:24px;left: 350px;top: 220px;position: absolute">欢迎使用论文管理系统</div>
		    </div>   
		</div>
	</div>
</div>
<!-- 菜单栏目 -->
<div id="tabMenu" class="easyui-menu"  style="width:200px;">
    <div data-options="name:'print',iconCls:'icon-close'" onclick="removeTab()">关闭当前</div>
    <div data-options="name:'save'" onclick="closeAllTab()">全部关闭</div>
    <div onclick="closeOtherTab();">关闭其他</div>
    <div onclick="closeRightTab()">关闭右侧</div>
    <div onclick="closeLeftTab();">关闭左侧</div>
</div>
<script type="text/javascript">
	function addTabs(opts) {
		var t = $('#layout_center_centerTabs');
		if(t.tabs('exists',opts.title)){
			t.tabs('select',opts.title);
		}else{
			var opentabs = 11; //允许打开的TAB数量
			var tabCount = t.tabs('tabs').length;
			if (tabCount > opentabs) {
	            var msg = '<b>您当前打开了太多的页面，如果继续打开，会造成程序运行缓慢，无法流畅操作！</b>';
	            $.messager.confirm("系统提示", msg, function (r) {
	                if(r){
						t.tabs('add', opts);
	                }
	            });
	        }else{
				t.tabs('add', opts);
	        }
		}
	}
	//关闭选择的tab
	function removeTab(){
		var tabPanel = $('#layout_center_centerTabs');
		var tab = tabPanel.tabs('getSelected');
		if (tab){
			var index = tabPanel.tabs('getTabIndex', tab);
			tabPanel.tabs('close', index);
		}
	}
	//关闭所有的tab
    function closeAllTab(){
        var tabs = $('#layout_center_centerTabs').tabs('tabs');    
        var len =  tabs.length;
        if(len>1){
	        $.messager.confirm('消息提醒', '确认关闭所有窗口?', function(r){
	                if (r){
                        for(var i=1;i<len;i++){                
                            $('#layout_center_centerTabs').tabs('close', 1);
                        }
	                }
	        });
        }
         
    }
  	//关闭当前标签页右侧标签页
  	function closeRightTab(){
        var tablist = $('#layout_center_centerTabs').tabs('tabs');
        var tab = $('#layout_center_centerTabs').tabs('getSelected');
        var index = $('#layout_center_centerTabs').tabs('getTabIndex',tab);
        for(var i=tablist.length-1;i>index;i--){
            $('#layout_center_centerTabs').tabs('close',i);
        }
    }
  	function closeLeftTab(){
        var tab = $('#layout_center_centerTabs').tabs('getSelected');
        var index = $('#layout_center_centerTabs').tabs('getTabIndex',tab);
        for(var i=1;i<index;i++){
            $('#layout_center_centerTabs').tabs('close',1);
        }
        $('#layout_center_centerTabs').tabs('select',1);
    }
  	//关闭非当前标签页
  	function closeOtherTab(){
  		closeLeftTab();
  		closeRightTab();
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
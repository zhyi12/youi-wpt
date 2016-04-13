<%@ include file="/WEB-INF/pages/include.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<giui:page>
	
	<youi:ajaxUrl name="getMaterialCount" src="/esb/weixinAdapter/wxHttpServices/getMaterialCount.json?subscriptionId=${subscriptionId}"/>
	<youi:ajaxUrl name="batchgetMaterial" src="/esb/weixinAdapter/wxHttpServices/batchgetMaterial.json?subscriptionId=${subscriptionId}"/>
	
	<youi:ajaxUrl name="getSyncedMedias" src="/wxArticleManager/getSyncedMedias.json"/>
	<youi:ajaxUrl name="syncMediaWxArticle" src="/wxArticleManager/syncMediaWxArticle.json?subscriptionId=${subscriptionId}"/>
	
	<giui:panel id="panel_wxArticle" column="12">
		<div class="grid-wxArticle-nav clearfix">
			<ul class="pagination grid-navigator pull-left" style="margin:2px;">
				<li><a title="上一页" class="pButton pPrev disabled" data-command="gridCommand" data-name="prev" aria-label="Previous"><span aria-hidden="true">‹</span></a></li>
				<li><span class="pageInfo">&nbsp;</span></li>
				<li><a title="下一页" class="pButton pNext disabled" data-command="gridCommand" data-name="next" aria-label="Previous"><span aria-hidden="true">›</span></a></li>
			</ul>
			<div class="pull-left" style="padding:5px;">
				转到 <input class="pageIndex-input" style="width:30px;"/>
			</div>
			
		</div>
		<giui:grid  id="grid_wxArticle" idKeys="media_id,media_index">
			<giui:gridCol caption="作者" property="author"/>
			<giui:gridCol caption="标题" property="title"/>
			<giui:gridCol caption="同步状态" property="syncStatus" convert="boolean"/>
			
			<giui:gridCol width="60" property="button" type="button" caption="操作">
				<giui:button name="view" action="{url}" method="_target" icon="eye-open" caption="查看"/>
				<giui:button name="sync" icon="refresh" caption="同步"/>
			</giui:gridCol>
		</giui:grid>
	</giui:panel>
	
	<youi:func name="init" urls="getMaterialCount">
		var pageSize = 10;
		
		$elem('grid_wxArticle',pageId).grid('option','usePager',false)
			.grid('option','dataProperties',['url','thumb_media_id','digest']);
		
		//查询总页数
		$.youi.ajaxUtils.ajax({
			url:funcUrls.getMaterialCount,
			success:function(results){
				if(results&&results.record&&results.record.news_count){
					var pageCount = Math.ceil(results.record.news_count/pageSize);
					//查询数据
					$.youi.pageUtils.doPageFunc('batchgetMaterial',pageId,null,1,pageSize,pageCount);
				}
			}
		});

		//监听分页动作
		$elem('panel_wxArticle',pageId).find('.grid-wxArticle-nav .pButton').bind('click',function(event){
			var data = $(this).data();
			$.youi.pageUtils.doPageFunc(data.name,pageId,null,pageSize);
		});
		
		$elem('panel_wxArticle',pageId).find('.grid-wxArticle-nav .pageIndex-input').bind('keydown',function(event){
			if(event.keyCode===13){
				var pageInfo = $elem('panel_wxArticle',pageId).find('.grid-wxArticle-nav .pageInfo').data();
				var pageIndex = parseInt($(this).val());
				
				$.youi.pageUtils.doPageFunc('batchgetMaterial',pageId,null,pageIndex,pageSize,pageInfo.pageCount);
			}
		});
		//
	</youi:func>
	<!-- 文章同步操作 -->
	<youi:func name="grid_wxArticle_sync" params="dom,options,record">
		var after = function(record){
			$('td:eq(2)',$(dom).parents('tr:first')).text('是');
		}
		if(record.syncStatus==1){
			$.youi.messageUtils.confirm('文章已经同步，确认覆盖?',function(){
				$.youi.pageUtils.doPageFunc('doSyncWxArticle',pageId,null,record,after);
			});
		}else{
			$.youi.pageUtils.doPageFunc('doSyncWxArticle',pageId,null,record,after);
		}
	</youi:func>
	<!-- 提交文章，执行同步 -->
	<youi:func name="doSyncWxArticle" urls="syncMediaWxArticle" params="record,after">
		var articleRecord = {
			wxArticleTitle:record.title,
			wxArticleAuthor:record.author,
			mediaId:record.media_id,
			mediaIndex:record.media_index,
			thumbMediaId:record.thumb_media_id,
			wxArticleContent:record.content,
			wxArticleSummary:record.digest,
			wxArticleUrl:record.url
		};
		$.youi.ajaxUtils.ajax({
			url:funcUrls.syncMediaWxArticle,
			data:$.youi.parameterUtils.toParams($.youi.recordUtils.recordToParameters(articleRecord)).join('&'),
			success:function(results){
				if(results&&$.isFunction(after)){
					after(results.record);
				}
			}
		});
	</youi:func>
	
	<!-- 分页查询微信官方文章素材数据 -->
	<youi:func name="batchgetMaterial" params="pageIndex,pageSize,pageCount" urls="batchgetMaterial,getSyncedMedias">
		$.youi.ajaxUtils.ajax({
			url:funcUrls.batchgetMaterial,
			data:'offset='+(pageIndex-1)*pageSize+'&count='+pageSize,
			success:function(results){
				if(results&&results.records){
					var articleRecords = [];
					var mediaIds = [];
					$(results.records).each(function(){
						if(this.content&&this.content.news_item){
							var media_id = this.media_id;
							mediaIds.push(media_id);
							$(this.content.news_item).each(function(index){
								articleRecords.push($.extend({media_id:media_id,media_index:index},this));
							});
						}
					});

					//查询已经同步的文章
					$.youi.ajaxUtils.ajax({
						url:funcUrls.getSyncedMedias,
						data:$.youi.parameterUtils.propertyParameter('mediaId',mediaIds),
						success:function(syncResults){
							var syncedItems = [];
							if(syncResults&&syncResults.record&&syncResults.record.items){
								syncedItems = syncResults.record.items;
							}
							
							$(articleRecords).each(function(){
								this.syncStatus = $.inArray(this.media_id+'_'+this.media_index,syncedItems)!=-1?'1':'0';
							});
							
							$elem('grid_wxArticle',pageId).grid('parseRecords',articleRecords,results.records.length);
						}
					});
					
					$.youi.pageUtils.doPageFunc('showPagination',pageId,null,pageIndex,pageCount);
				}
			}
		});
	</youi:func>
	<!-- 导航条显示 -->
	<youi:func name="showPagination" params="pageIndex,pageCount">
		$elem('panel_wxArticle',pageId).find('.grid-wxArticle-nav .pageInfo').data('pageCount',pageCount).data('pageIndex',pageIndex).html(pageIndex+'/'+pageCount);
		
	</youi:func>
	<!-- 上一页 -->
	<youi:func name="prev" params="pageSize">
		var pageInfo = $elem('panel_wxArticle',pageId).find('.grid-wxArticle-nav .pageInfo').data();
		if(pageInfo.pageIndex>1){
			var pageIndex = pageInfo.pageIndex -1;
			$.youi.pageUtils.doPageFunc('batchgetMaterial',pageId,null,pageIndex,pageSize,pageInfo.pageCount);
		}
	</youi:func>
	<!-- 下一页 -->
	<youi:func name="next" params="pageSize">
		var pageInfo = $elem('panel_wxArticle',pageId).find('.grid-wxArticle-nav .pageInfo').data();
		if(pageInfo.pageIndex<pageInfo.pageCount){
			var pageIndex = pageInfo.pageIndex + 1;
			$.youi.pageUtils.doPageFunc('batchgetMaterial',pageId,null,pageIndex,pageSize,pageInfo.pageCount);
		}
	</youi:func>
	
</giui:page>
